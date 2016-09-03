package com.cisco.ca.cstg.pdi.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Enumeration;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.HttpConnectionHelper;
import com.cisco.ca.cstg.pdi.utils.Util;

/**
 * 
 * @author avinasin
 * 
 */
@Component
public class VisioRequestProcessor implements Constants {

	@Autowired
	HttpConnectionHelper connectionhelper;	
	@Autowired
	@Value("${pdi.connectionTimeout}")
	private String TIMEOUT;
	@Autowired
	@Value("${pdi.httpMethod}")
	private String METHOD;
	@Autowired
	@Value("${pdi.visioContentType}")
	private String CONTENTTYPE;
	@Autowired
	@Value("${pdi.docOutputLocation}")
	private String outputLocation;
	@Autowired
	@Value("${pdi.topologyErrorFilename}")
	private String errorFileName;
	@Autowired
	@Value("${pdi.topologyOutputFilename}")
	private String fileName;	

	private static final String separator = "&";
	private static final String username = "username=";
	private static final String visio_dict = "visio_dict=";
	private static final String current_pod = "current_pod=";
	private static final String TOPOLOGY_RESPONSE_ERROR_FILE_NAME = "error_file.txt";
	private long USERNAME;
	private String VISIODICT;
	private String PAYLOAD;
	private String CURENTPOD;
	private String SERVER_FLAG;
	private HttpURLConnection connection;
	private String hangResolveUrl;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(VisioRequestProcessor.class);

	public void visioCall(long siteid, String jsonstring, String pods, Map<String, String> visioServerUrlsMap) {
		String folderPath = outputLocation + siteid;
		if (new File(folderPath).exists()) {
			Util.deleteFolder(folderPath);
			LOGGER.info("Deleting previously created folder {} ", folderPath);
		}
		Util.createFolder(folderPath);
		LOGGER.info("Created new folder {} ", folderPath);
		USERNAME = siteid;
		VISIODICT = jsonstring.trim();
		CURENTPOD = pods;
		createPayload(USERNAME, VISIODICT, CURENTPOD);
		connection = getConnection(visioServerUrlsMap);
		if (connection != null) {
			sendRequest(siteid, connection, hangResolveUrl, visioServerUrlsMap);
		}
		includeErrorFile(siteid);
	}

	private void createPayload(long siteid, String jsonstring,
			String pod_details) {
		PAYLOAD = username + siteid + separator + visio_dict + jsonstring
				+ separator + current_pod + pod_details;
	}

	/**
	 * This method creates connection with visio servre.If primary server fails
	 * it will try with secondary server
	 */

	private HttpURLConnection getConnection(Map<String, String> visioServerUrlsMap) {
		connection = connectionhelper.getConnection(visioServerUrlsMap.get(VISIOPRIMARYURL),
				Integer.parseInt(TIMEOUT), METHOD, CONTENTTYPE);
		if (connection != null) {
			SERVER_FLAG = "PRIMARY";
			this.hangResolveUrl = visioServerUrlsMap
					.get(VISIOHANGRESOLVERPRIMARYURL);
		} else {
			LOGGER.error(Constants.PRIMARY_CONN_ERR_MSG);
			connection = connectionhelper.getConnection(visioServerUrlsMap.get(VISIOSECONDARYURL),
					Integer.parseInt(TIMEOUT), METHOD, CONTENTTYPE);
			SERVER_FLAG = "SECONDARY";
			this.hangResolveUrl = visioServerUrlsMap
					.get(VISIOHANGRESOLVERSECONDARYURL);
		}
		if (connection == null) {
			LOGGER.error(Constants.NO_CONN_ERR_MSG);
		}
		return connection;

	}

	private void sendRequest(long siteid, HttpURLConnection connection,
			String hangResolveUrl, Map<String, String> visioServerUrlsMap) {

		LOGGER.info(Constants.CONN_SUCCESS_MSG + SERVER_FLAG + " : "
				+ connection.getURL());
		int response = connectionhelper.request(connection, PAYLOAD, siteid,
				hangResolveUrl);

		if (response != HttpURLConnection.HTTP_OK
				&& "PRIMARY".equals(SERVER_FLAG)) {
			LOGGER.error(Constants.PRIMARY_SERVER_PROCESS_ERR_MSG);
			SERVER_FLAG = "SECONDARY";
			sendRequest(
					siteid,
					connectionhelper.getConnection(visioServerUrlsMap.get(VISIOSECONDARYURL),
							Integer.parseInt(TIMEOUT), METHOD, CONTENTTYPE),
							visioServerUrlsMap.get(VISIOHANGRESOLVERSECONDARYURL), visioServerUrlsMap);

		} else if (response != HttpURLConnection.HTTP_OK
				&& "SECONDARY".equals(SERVER_FLAG)) {
			LOGGER.error(Constants.VISIO_GENERATION_ERR_MSG);
		}

	}

	private void includeErrorFile(long siteid) {
		if (!new File(outputLocation + siteid + File.separator + fileName)
				.exists()) {
			try {
				String file = outputLocation + siteid + File.separator
						+ errorFileName;
				FileOutputStream writer = new FileOutputStream(file);
				IOUtils.write(
						"An error occurred during Topology diagram generation.Please re-run the report  or email dcv-support@cisco.com",
						writer);
			} catch (IOException e) {
				LOGGER.error(
						"Error occured while includingErrorMessage in file for Topology Generation : ",
						e);
			}
		}
	}

	public String validateTopologyResponseData(Integer projectId) {
		String folderPath = outputLocation + projectId;
		String message = "success";
		if (new File(folderPath + File.separator + errorFileName).exists()) {
			message = Constants.TOPOLOGY_ERROR_MSG;
		} else if (!validateTopologyZipFile(folderPath + File.separator
				+ fileName)) {
			message = Constants.TOPOLOGY_ERROR_MSG;
		}
		return message;
	}

	private boolean validateTopologyZipFile(String filePath) {
		ZipFile zipfile = null;
		ZipEntry zipentry;
		boolean isValid = false;
		try {
			File file = new File(filePath);
			zipfile = new ZipFile(file);
			@SuppressWarnings("unused")
			int fileNumber = 0;
			for (Enumeration<? extends ZipEntry> enumeration = zipfile
					.entries(); enumeration.hasMoreElements(); fileNumber++) {
				zipentry = enumeration.nextElement();
				if (!(!zipentry.isDirectory() && zipentry.getName().equals(
						TOPOLOGY_RESPONSE_ERROR_FILE_NAME))) {
					isValid = true;
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error occured while validating topology zip file : ",
					e);
		} finally {
			try {
				if (zipfile != null) {
					zipfile.close();
				}
			} catch (Exception e) {
				LOGGER.error("Exception ,", e);
			}
		}

		return isValid;
	}

	public void startDownloadTopologyZipFile(HttpServletResponse response,
			Integer projectId) {
		String folderPath = outputLocation + projectId;
		File file = new File(folderPath);
		if (file.isDirectory()) {
			File archiveFile = new File(outputLocation + projectId
					+ File.separator + fileName);
			Util.downloadArchiveFile(response, archiveFile);
		}
	}
}
