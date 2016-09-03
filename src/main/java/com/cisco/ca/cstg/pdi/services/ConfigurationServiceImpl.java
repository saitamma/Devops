package com.cisco.ca.cstg.pdi.services;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.FileExistsException;
import org.apache.commons.io.FileUtils;
import org.dom4j.DocumentException;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cisco.ca.cstg.pdi.dao.CommonDaoServicesImpl;
import com.cisco.ca.cstg.pdi.pojos.Configuration;
import com.cisco.ca.cstg.pdi.pojos.ProjectDetails;
import com.cisco.ca.cstg.pdi.pojos.ServerDetails;
import com.cisco.ca.cstg.pdi.pojos.UcsServerLogs;
import com.cisco.ca.cstg.pdi.pojos.XmlGenProjectDetails;
import com.cisco.ca.cstg.pdi.pojos.mo.Metadata;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Toproot;
import com.cisco.ca.cstg.pdi.ucsmapper.marshal.MarshalUcsXml;
import com.cisco.ca.cstg.pdi.ucsmapper.unmarshal.UnmarshalUcsXml;
import com.cisco.ca.cstg.pdi.utils.AESEncrypter;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.PdiConfig;
import com.cisco.ca.cstg.pdi.utils.Util;
import com.cisco.ucs.mgr.UcsHandle;
import com.cisco.ucs.mgr.util.ResponseException;

@Service("configurationService")
public class ConfigurationServiceImpl extends CommonDaoServicesImpl implements
		ConfigurationService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ConfigurationServiceImpl.class);

	private static final String CONF_CREATION = "Conf_Creation";
	private static final String ZIP = ".zip";
	private static final String PROC_NAME_FOR_UCS_ADA = "translate_data_UCS_to_ADA";
	private static final String PROC_NAME_FOR_CHASSIS_COUNT = "insert_chassis_count";

	@Autowired
	private MarshalUcsXml marshalUcsXml;
	@Autowired
	private UnmarshalUcsXml unmarshalUcsXml;
	@Autowired
	private PushConfigToUcsService pushConfigToUcsService;
	@Autowired
	private ProjectDetailsService projectDetailsService;

	private static final Boolean STATUS_TRUE = true;
	private static final Boolean STATUS_FALSE = false;

	public ConfigurationServiceImpl() {
	}

	@Override
	public boolean verifyConnection(ServerDetails serverDetails)
			throws ResponseException {
		if (serverDetails.getUrl() == null
				|| serverDetails.getUserName() == null
				|| serverDetails.getPassword() == null) {
			serverDetails.setPingStatus(false);
		}
		UcsHandle handle = null;
		int portNumber = 443;
		boolean noSsl = false;
		boolean refreshable = false;

		try {
			handle = new UcsHandle();
			if (handle.login(serverDetails.getUrl(),
					serverDetails.getUserName(), serverDetails.getPassword(),
					noSsl, portNumber, refreshable, true)) {
				serverDetails.setPingStatus(true);
				LOGGER.info("Connection verification to the device was successful.");
			} else {
				LOGGER.error("Connection verification to the device was NOT successful.");
			}
		} catch (ResponseException e) {
			LOGGER.error("Error occured while trying to login with device.", e);
		} finally {
			if (handle != null) {
				handle.logout();
			}
		}
		return serverDetails.isPingStatus();
	}

	@Override
	public boolean verifyConnection(UcsHandle handle, String ucsHost,
			String userName, String password, boolean noSsl, int portNumber,
			boolean refreshable) {
		boolean status = false;
		try {
			status = handle.login(ucsHost, userName, password, noSsl,
					portNumber, refreshable, true);
		} catch (ResponseException e) {
			LOGGER.error(
					"Error occured while verifying the device connection.", e);
		}
		return status;
	}

	@Override
	public Map<String, Object> processUcsPdiConfiguration(
			XmlGenProjectDetails project) {
		Map<String, Object> processUcsConfStatus = new HashMap<String, Object>();
		boolean createConfStatus = false;
		deletePreviousUcsData(project.getId());
		String pdiConfDataFolderPath = prepareFolderStructure(project.getId());
		createConfStatus = marshalUcsXml.exportUcsPdiConfiguration(
				pdiConfDataFolderPath, project);
		processUcsConfStatus.put(CONF_CREATION, createConfStatus);
		return processUcsConfStatus;
	}

	@Override
	public Map<String, Object> processAndPushUcsPdiConfiguration(
			ServerDetails serverDetails, Integer projectId) {
		LOGGER.info("Start - ConfigurationServiceImpl : processAndPushUcsPdiConfiguration : projectId - "
				+ projectId);
		XmlGenProjectDetails pd = (XmlGenProjectDetails) findById(
				XmlGenProjectDetails.class, projectId);
		Map<String, Object> processUcsConfStatus = null;

		if (pd != null) {
			processUcsConfStatus = processUcsPdiConfiguration(pd);

			if (processUcsConfStatus.get(CONF_CREATION).equals(true)) {
				try {
					String metadataFilePath = Util
							.getPdiConfDataFolderPath(
									projectDetailsService
											.fetchProjectDetails(projectId))
							.concat(File.separator)
							.concat(Constants.PDI_META_DATA_FILE_NAME)
							.toString();
					JAXBContext jaxbContext = JAXBContext
							.newInstance(Metadata.class);
					Unmarshaller jaxbUnmarshaller = jaxbContext
							.createUnmarshaller();
					Metadata metadata = (Metadata) jaxbUnmarshaller
							.unmarshal(new File(metadataFilePath));
					boolean pushStatus = pushConfigToUcsService.pushToUcs(
							projectId,
							serverDetails,
							metadata,
							Util.getPdiConfDataFolderPath(
									projectDetailsService
											.fetchProjectDetails(projectId))
									.concat(File.separator)
									.concat(Constants.PDI_CONFIG_FILE_NAME)
									.toString());
					processUcsConfStatus.put("Push_Conf", pushStatus);
				} catch (JAXBException e) {
					LOGGER.error(
							"Error occured while unmarshalling the config file for push.",
							e);
				}
			}
		}
		LOGGER.info("End - ConfigurationServiceImpl : processAndPushUcsPdiConfiguration");
		return processUcsConfStatus;
	}

	private String prepareFolderStructure(Integer projectId) {
		String returnPath = Util.getPdiConfDataFolderPath(projectDetailsService
				.fetchProjectDetails(projectId));
		Util.createFolder(returnPath);
		LOGGER.info("Created new folder path {}", returnPath);
		return returnPath;
	}

	private void deletePreviousUcsData(Integer projectId) {
		ProjectDetails pd = projectDetailsService
				.fetchProjectDetails(projectId);
		String ucsDataPath = Util.getPdiConfFolderPath(pd);
		LOGGER.info("Deleting previously created folder {}", ucsDataPath);
		try {
			FileUtils.deleteDirectory(new File(ucsDataPath));
		} catch (IOException ex) {
			LOGGER.error(
					"Error occured while trying to delete the directory for ucs  data folder.",
					ex);
		}
	}

	@Override
	public boolean processToZip(String returnPath) {

		boolean returnValue = STATUS_TRUE;
		try {

			zipFolder(returnPath, returnPath.concat(ZIP));
			Util.deleteFolder(returnPath);
			encryptZipFolder(returnPath);

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			returnValue = STATUS_FALSE;
		}
		return returnValue;
	}

	private void encryptZipFolder(String returnPath) throws IOException {
		boolean enableEncryption = Boolean.parseBoolean(PdiConfig
				.getProperty(Constants.DOWNLOAD_ZIP_ENCRYPTION));

		if (enableEncryption) {
			String tFileName = returnPath.concat("_temp.zip");
			LOGGER.info("Deleted un-zipped folder {}", tFileName);

			File tFile = new File(tFileName);
			String srcFile = returnPath.concat(ZIP);
			FileUtils.moveFile(new File(srcFile), tFile);
			AESEncrypter encrypter = new AESEncrypter();
			encrypter.encrypt(tFileName, srcFile);
			if (!tFile.delete()) {
				LOGGER.error("ERROR in delelting zip folder path" + tFileName);
			}
			LOGGER.info("Encrypted folder {}", srcFile);
		}
	}

	private void zipFolder(String srcFolder, String destZipFile)
			throws IOException {
		ZipOutputStream zip = null;
		FileOutputStream fileWriter = null;
		try {
			fileWriter = new FileOutputStream(destZipFile);
			zip = new ZipOutputStream(fileWriter);
			addFileToZip(srcFolder, zip);
		} finally {
			try {
				if (zip != null) {
					zip.flush();
					zip.close();
				}
			} catch (IOException e) {
				LOGGER.error(
						"Error occured while closing the resources after zipping the folder.",
						e);
			}
		}
		LOGGER.info("Created zip folder {}", destZipFile);
	}

	private void addFileToZip(String srcFile, ZipOutputStream zip)
			throws IOException {

		File f1 = new File(srcFile);
		String licenseFile = PdiConfig.getProperty(Constants.PDI_HOME)
				.concat(File.separator).concat(Constants.LICENSE_FILENAME);
		for (String filePath : f1.list()) {
			String fullPath = srcFile + File.separator + filePath;
			addToZipOutputStream(fullPath, zip);
			LOGGER.debug("File {} added to zip folder", fullPath);
		}
		addToZipOutputStream(licenseFile, zip);
		LOGGER.debug("File {} added to zip folder", licenseFile);
	}

	private void addToZipOutputStream(String filePath, ZipOutputStream zip)
			throws IOException {
		byte[] buf = new byte[1024];
		int len;
		File input = new File(filePath);
		try (FileInputStream in = new FileInputStream(input)) {
			zip.putNextEntry(new ZipEntry(input.getName()));
			while ((len = in.read(buf)) > 0) {
				zip.write(buf, 0, len);
			}
			zip.flush();
		}
	}

	@Override
	public void saveOrUpdateConfiguration(Configuration configs) {
		Criterion whereClause = Restrictions.eq(Constants.PROJECTDETAILS_ID,
				configs.getProjectDetails().getId());
		List<Object> listOfConfiguration = listAll(Configuration.class,
				whereClause);

		if (!listOfConfiguration.isEmpty()) {
			Configuration storedConfigData = (Configuration) listOfConfiguration
					.get(0);
			configs.setId(storedConfigData.getId());
			configs.setPushStatus(configs.getPushStatus() == null ? storedConfigData
					.getPushStatus() : configs.getPushStatus());
			configs.setPingVerified(configs.getPingVerified() == null ? storedConfigData
					.getPingVerified() : configs.getPingVerified());
			configs.setDownloadConfigStatus(configs.getDownloadConfigStatus() == null ? storedConfigData
					.getDownloadConfigStatus() : configs
					.getDownloadConfigStatus());
		}
		saveOrUpdate(configs);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UcsServerLogs> getUCSConfigLogs(ServerDetails serverDetails,
			Integer projectId) {
		Criterion whereClause = Restrictions.and(
				Restrictions.eq(Constants.PROJECTDETAILS_ID, projectId),
				Restrictions.eq("ipAddress", serverDetails.getUrl()));
		return (List<UcsServerLogs>) (List<?>) listAll(UcsServerLogs.class,
				whereClause);
	}

	@Override
	public void cloneProject(Integer sourceProjectId, Integer newProjectId)
			throws IOException, IllegalStateException, JAXBException,
			DocumentException {
		LOGGER.info("Cloning project id {} to new project id {}",
				sourceProjectId, newProjectId);
		XmlGenProjectDetails pd = (XmlGenProjectDetails) findById(
				XmlGenProjectDetails.class, sourceProjectId);

		if (pd != null) {
			Map<String, Object> status = processUcsPdiConfiguration(pd);
			String sourceDataPath = Util
					.getPdiConfDataFolderPath(projectDetailsService
							.fetchProjectDetails(sourceProjectId));
			String destDataPath = Util
					.getPdiConfDataFolderPath(projectDetailsService
							.fetchProjectDetails(newProjectId));

			if (status.get(CONF_CREATION).equals(true)) {
				try {
					FileUtils.copyDirectory(new File(sourceDataPath), new File(
							destDataPath));
					LOGGER.debug("Copied directory from {} to {}",
							sourceDataPath, destDataPath);

					synchronized (this) {
						unmarshalUcsXml.unmarshalMetatData(destDataPath
								+ File.separator
								+ Constants.PDI_META_DATA_FILE_NAME,
								newProjectId);
						deleteRecords(Toproot.class);
						unmarshalUcsXml.unmarshalTopRootElement(destDataPath
								+ File.separator
								+ Constants.PDI_CONFIG_FILE_NAME);
						LOGGER.info("Unmarshalled data to UCS tables successfully.");
						String errorMessage = executeStoredProcedure(
								PROC_NAME_FOR_UCS_ADA, newProjectId);
						if (errorMessage != null && !errorMessage.isEmpty()) {
							throw new HibernateException(errorMessage);
						}
						LOGGER.info("Stored Procedure for translating data to ADA tables executed successfully.");
						unmarshalUcsXml.unmarshalMetatDataMO(destDataPath
								+ File.separator
								+ Constants.PDI_META_DATA_FILE_NAME,
								newProjectId);

						// step: call the store procedure to save Chassis count
						errorMessage = executeStoredProcedure(
								PROC_NAME_FOR_CHASSIS_COUNT, newProjectId);
						if (errorMessage != null && !errorMessage.isEmpty()) {
							throw new HibernateException(errorMessage);
						}
					}
				} catch (IOException | HibernateException | JAXBException e) {
					LOGGER.error(
							"Error occured while trying to clone the project.",
							e);
					throw e;
				}
			} else {
				LOGGER.error("Error while generating XML for the source project");
				throw new IllegalStateException(
						"Error while generating XML from source project");
			}
		}
	}

	@Override
	public void importXMlToADAProject(Integer newProjectId) throws IOException,
			IllegalStateException, JAXBException, DocumentException {
		LOGGER.info("Import XML to new ADA Project:" + newProjectId);
		boolean metadataExits = false;
		String destinationPath = Util
				.getPdiConfDataFolderPath(projectDetailsService
						.fetchProjectDetails(newProjectId));
		try {
			synchronized (this) {
				// step1: check if the folder has meta data folder
				metadataExits = new File(destinationPath,
						Constants.PDI_META_DATA_FILE_NAME).exists();

				if (metadataExits) {
					LOGGER.info("Meta data file found for projectID:"
							+ newProjectId);
					unmarshalUcsXml.unmarshalMetatData(destinationPath
							+ File.separator
							+ Constants.PDI_META_DATA_FILE_NAME, newProjectId);
				}

				// step2: unmarshal top root element
				deleteRecords(Toproot.class);
				unmarshalUcsXml.unmarshalTopRootElement(destinationPath
						+ File.separator + Constants.PDI_CONFIG_FILE_NAME);
				LOGGER.info("Unmarshalled data to UCS tables successfully.");

				// step3: call the store procedure to save the data to db
				String errorMessage = executeStoredProcedure(
						PROC_NAME_FOR_UCS_ADA, newProjectId);
				if (errorMessage != null && !errorMessage.isEmpty()) {
					throw new HibernateException(errorMessage);
				}

				// step4: unmarsahall the MO data
				if (metadataExits) {
					unmarshalUcsXml.unmarshalMetatDataMO(destinationPath
							+ File.separator
							+ Constants.PDI_META_DATA_FILE_NAME, newProjectId);
				}
				// step5: call the store procedure to save Chassis count
				errorMessage = executeStoredProcedure(
						PROC_NAME_FOR_CHASSIS_COUNT, newProjectId);
				if (errorMessage != null && !errorMessage.isEmpty()) {
					throw new HibernateException(errorMessage);
				}
				LOGGER.info("Stored Procedure for translating data to ADA tables executed successfully.");
			}
		} catch (IOException | HibernateException | JAXBException e) {
			LOGGER.error(
					"Error occured when trying to import the project to ADA tool.",
					e);
			throw e;
		}
		LOGGER.info("Stored Procedure for translating data to ADA tables executed successfully.");
	}

	@Override
	public void validateAndCopyFile(MultipartFile file, Integer projectId)
			throws IOException {
		String copyFolderPath = null;
		String projectPath = null;
		boolean status = false;
		File tempFile = null;

		if (file.getOriginalFilename().toLowerCase()
				.matches(".*\\.zip$|.*\\.xml$")) {

			copyFolderPath = Util.getPdiConfFolderPath(projectDetailsService
					.fetchProjectDetails(projectId));
			projectPath = Util.getPdiConfDataFolderPath(projectDetailsService
					.fetchProjectDetails(projectId));
			// if exists delete previously created folder
			deletePreviousUcsData(projectId);
			// create project folder
			Util.createFolder(projectPath);

			tempFile = new File(copyFolderPath + File.separator
					+ file.getOriginalFilename());
			file.transferTo(tempFile);

			if (tempFile.getName().toLowerCase().matches(".*\\.zip$")
					&& validateZipFile(tempFile)) {
				copyZipFile(tempFile, projectPath);
				status = true;
			} else {
				copyXmlFile(tempFile, projectPath);
				status = true;
			}

			if (!status) {
				throw new FileExistsException();
			}
		}
	}

	private void copyXmlFile(File file, String path) throws IOException {
		InputStream oInStream = null;
		BufferedInputStream oBuffInputStream = null;

		try {
			oInStream = new FileInputStream(file);
			try (FileOutputStream oOutStream = new FileOutputStream(path
					+ File.separator + Constants.PDI_CONFIG_FILE_NAME)) {
				byte[] oBytes = new byte[1024];
				int nLength;
				oBuffInputStream = new BufferedInputStream(oInStream);

				while ((nLength = oBuffInputStream.read(oBytes)) > 0) {
					oOutStream.write(oBytes, 0, nLength);
				}
			}
		} catch (IOException e) {
			LOGGER.error("Error occured when copying the XML file", e);
			throw e;
		} finally {
			if (oBuffInputStream != null) {
				oBuffInputStream.close();
			}
			if (oInStream != null) {
				oInStream.close();
			}
			file.delete();
		}
	}

	private void copyZipFile(File file, String path)
			throws FileNotFoundException, IOException {
		ZipInputStream zipIs = null;
		ZipEntry zEntry = null;
		FileInputStream fis = null;

		try {
			fis = new FileInputStream(file);
			zipIs = new ZipInputStream(new BufferedInputStream(fis));

			while ((zEntry = zipIs.getNextEntry()) != null) {
				byte[] tmp = new byte[4 * 1024];
				File newFile = new File(zEntry.getName());
				String directory = newFile.getParent();
				if (directory == null) {
					try (FileOutputStream fos = new FileOutputStream(path
							+ File.separator + zEntry.getName())) {
						int size = 0;
						while ((size = zipIs.read(tmp)) != -1) {
							fos.write(tmp, 0, size);
							fos.flush();
						}
					}
				}
			}
		} catch (IOException e) {
			LOGGER.error("Error occured while trying to copy zip file.", e);
			throw e;
		} finally {
			if (zipIs != null) {
				zipIs.close();
			}
			if (fis != null) {
				fis.close();
			}
			file.delete();
		}
	}

	private boolean validateZipFile(File file) throws IOException {
		boolean isMetaDataPresent = STATUS_FALSE;
		boolean isConfigFilePresent = STATUS_FALSE;
		ZipFile zipFile = null;

		try {
			zipFile = new ZipFile(file);
			Enumeration<? extends ZipEntry> entries = zipFile.entries();
			while (entries.hasMoreElements()) {
				ZipEntry zipEntry = entries.nextElement();

				if (zipEntry.getName().equals(Constants.PDI_CONFIG_FILE_NAME)) {
					isConfigFilePresent = STATUS_TRUE;
				} else if (zipEntry.getName().equals(
						Constants.PDI_META_DATA_FILE_NAME)) {
					isMetaDataPresent = STATUS_TRUE;
				}

			}
		} catch (IOException e) {
			LOGGER.error("Error occured while trying to validate zip files.", e);
			throw e;
		} finally {
			if (zipFile != null) {
				zipFile.close();
			}
		}

		if (isMetaDataPresent && isConfigFilePresent) {
			return STATUS_TRUE;
		}

		return STATUS_FALSE;
	}

	@Override
	public void checkMetadataVersion(Integer projectId) throws JAXBException {
		LOGGER.info("Start - ConfigurationServiceImpl : checkMetadataVersion : projectId - "
				+ projectId);
		boolean metadataExits = false;
		String destinationPath = Util
				.getPdiConfDataFolderPath(projectDetailsService
						.fetchProjectDetails(projectId));
		try {
			metadataExits = new File(destinationPath,
					Constants.PDI_META_DATA_FILE_NAME).exists();

			if (metadataExits) {
				JAXBContext jaxbContext = JAXBContext
						.newInstance(Metadata.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext
						.createUnmarshaller();
				Metadata metadata = (Metadata) jaxbUnmarshaller
						.unmarshal(new File(destinationPath,
								Constants.PDI_META_DATA_FILE_NAME));

				if (metadata.getVersion() != null
						&& metadata.getVersion() >= 2.1f) {
					return;
				}

				throw new IllegalStateException();
			}
		} catch (JAXBException e) {
			LOGGER.error("Error occured when checking the metadata version.", e);
			throw e;
		}
		LOGGER.info("End - ConfigurationServiceImpl : checkMetadataVersion");
	}
}