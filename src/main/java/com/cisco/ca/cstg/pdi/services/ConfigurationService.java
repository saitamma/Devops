package com.cisco.ca.cstg.pdi.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.dom4j.DocumentException;
import org.springframework.web.multipart.MultipartFile;

import com.cisco.ca.cstg.pdi.pojos.Configuration;
import com.cisco.ca.cstg.pdi.pojos.ServerDetails;
import com.cisco.ca.cstg.pdi.pojos.UcsServerLogs;
import com.cisco.ca.cstg.pdi.pojos.XmlGenProjectDetails;
import com.cisco.ucs.mgr.UcsHandle;
import com.cisco.ucs.mgr.util.ResponseException;

/**
 * @author pavkuma2
 *
 */
public interface ConfigurationService {

	/**
	 * This method verifies the connection to a given UCS manager.
	 * 
	 * @param serverDetails
	 *            is the details of UCS manager to be verified
	 * @throws ResponseException
	 * @return boolean : true if the verification was successful, false
	 *         otherwise
	 * 
	 */
	boolean verifyConnection(ServerDetails serverDetails)
			throws ResponseException;

	/**
	 * This method prepares the file to be pushed to the UCS manager
	 * 
	 * @param project
	 *            Current project under which the configuration is created.
	 * @return returns a Map for providing the status at each stage of
	 *         processing. Keys - 'Download_Template' and 'Conf_Creation' have
	 *         boolean values set to true if it was successful, false otherwise.
	 * 
	 */
	Map<String, Object> processUcsPdiConfiguration(XmlGenProjectDetails project);

	/**
	 * This method prepares and pushes the configuration file to the UCS manager
	 * 
	 * @param serverDetails
	 *            contains the details of UCS manager to which the configuration
	 *            needs to be pushed.
	 * @return returns a Map for providing the status at each stage of
	 *         processing. Keys - 'Download_Template', 'Conf_Creation' and
	 *         'Push_Conf' have boolean values set to true if it was successful,
	 *         false otherwise.
	 * 
	 */
	Map<String, Object> processAndPushUcsPdiConfiguration(
			ServerDetails serverDetails, Integer projectId);

	/**
	 * This method creates a zip folder.
	 * 
	 * @param returnPath
	 *            is the path of the folder to be zipped.
	 * @return boolean : true if the zipping was successful, false otherwise
	 * 
	 */
	boolean processToZip(String returnPath);

	/**
	 * This method saves or updates status for ping, push and download for a
	 * given UCS manager
	 * 
	 * @param config
	 *            object carrying the status and credential details of the UCS
	 *            manager
	 */
	void saveOrUpdateConfiguration(Configuration config);

	/**
	 * This method gets status logs after the configuration is pushed to UCS
	 * manager
	 * 
	 * @param serverDetails
	 *            contains the details of the UCS manager
	 * @param projectId
	 *            is the current active project the user is working on.
	 * @return List<Object> : List of objects with gives the status name and the
	 *         description received from the UCS manager.
	 */
	List<UcsServerLogs> getUCSConfigLogs(ServerDetails serverDetails,
			Integer projectId);

	/**
	 * This method clones the source project selected to a new project
	 * 
	 * @param sourceProjectId
	 *            is the project id of the source project
	 * @param newProjectId
	 *            is the project id of the target/destination project
	 */
	void cloneProject(Integer sourceProjectId, Integer newProjectId)
			throws IOException, IllegalStateException, JAXBException,
			DocumentException;

	/**
	 * This method imports the UCSM config xml to a new project
	 * 
	 * @param newProjectId
	 *            is the project id of the target/destination project
	 */
	void importXMlToADAProject(Integer newProjectId) throws IOException,
			IllegalStateException, JAXBException, DocumentException;;

	/**
	 * This method copy the uploaded file in the project folder
	 * 
	 * @param file
	 *            is either zip or xml file
	 * @parma projectId is the new project id
	 */
	void validateAndCopyFile(MultipartFile file, Integer projectId)
			throws IOException;

	void checkMetadataVersion(Integer projectId) throws JAXBException;

	boolean verifyConnection(UcsHandle handle, String ucsHost, String userName,
			String password, boolean noSsl, int portNumber, boolean refreshable);

}
