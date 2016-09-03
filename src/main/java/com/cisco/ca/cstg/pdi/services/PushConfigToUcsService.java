package com.cisco.ca.cstg.pdi.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisco.ca.cstg.pdi.pojos.ServerDetails;
import com.cisco.ca.cstg.pdi.pojos.UcsServerLogs;
import com.cisco.ca.cstg.pdi.pojos.mo.Metadata;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.FetchErrorLogFromUcs;
import com.cisco.ca.cstg.pdi.utils.PdiConfig;
import com.cisco.ucs.mgr.MethodFactory;
import com.cisco.ucs.mgr.UcsHandle;
import com.cisco.ucs.mgr.enums.Status;
import com.cisco.ucs.mgr.enums.YesOrNo;
import com.cisco.ucs.mgr.method.ConfigConfMo;
import com.cisco.ucs.mgr.method.helper.ConfigConfig;
import com.cisco.ucs.mgr.mo.MgmtImporter;
import com.cisco.ucs.mgr.mo.TopSystem;
import com.cisco.ucs.mgr.util.FileUtils;
import com.cisco.ucs.mgr.util.ResponseException;
import com.cisco.ucs.mgr.util.StringUtils;
import com.cisco.ucs.mgr.util.UcsUtils;

/**
 * This program illustrates importing backup to UCS from a configuration xml
 * file stored in local system.
 * 
 * This program uploads the configuration xml file on UCS, creates the
 * MgmtImporter object, sets its attributes, manipulates the values and then
 * creates it in the UCS domain using the static methods available in
 * MethodFactory class.
 * 
 * This SDK is developed over the XML APIs exposed by the UCSM. So it is highly
 * recommended to understand the XML APIs to use the SDK effectively.
 * 
 * Cisco UCS XML Programming Guide is available at
 * http://www.cisco.com/en/US/docs
 * /unified_computing/ucs/sw/api/ucs_api_book.html
 * 
 */
@Service("pushConfigToUcsService")
public class PushConfigToUcsService {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(PushConfigToUcsService.class);
	private static int portNumber = 443;
	private final CommonUtilServices commonUtilService;
	private final PushMoToUcsService pushMoToUcsService;
	private static final String PENDING = "pending";
	private static final String SUCCESS = "success";
	private static final String NOFSMSTATUS = "nofsmstatus";
	private static final String IMPORTREPORTRESULTS = "importReportResults";

	@Autowired
	private ConfigurationService configurationService;

	@Autowired
	public PushConfigToUcsService(CommonUtilServices commonUtilService,
			PushMoToUcsService pushMoToUcsService) {
		this.commonUtilService = commonUtilService;
		this.pushMoToUcsService = pushMoToUcsService;
	}

	public boolean pushToUcs(Integer projectId, ServerDetails serverDetails,
			Metadata metadata, String filePath) {
		LOGGER.info("Start - pushToUcs");
		String ucsHost = serverDetails.getUrl();
		String userName = serverDetails.getUserName();
		String password = serverDetails.getPassword();

		UcsHandle handle = null;
		boolean successMessage = false;
		try {
			handle = new UcsHandle();

			boolean noSsl = false;
			boolean refreshable = false;
			if (verifyUCSMConnection(ucsHost, userName, password, handle,
					noSsl, refreshable)) {
				LOGGER.info("Logged in successfully in :{}", ucsHost);
				boolean merge = false;

				// Push MO first for chassis discovery policy
				pushMoToUcsService.invokeChassisDiscoverypolicy(handle,
						metadata.getEquipmentChassis());
				Thread.sleep(Long.valueOf(Integer.parseInt(PdiConfig
						.getProperty(Constants.DELAY_IN_CHASSIS_DISCOVERY)) * 1000));

				// 1.Push MO for Chassis configuration by server ports one by
				// one
				// 2.Configuring Scalability ports for UCS Mini
				pushMoToUcsService.invokeChassisConfiguraiotnByServerPorts(
						handle, metadata.getEquipmentServers(),
						metadata.getEquipmentMiniScalabilityPorts());

				if (handle.isTransactionInProgress()) {
					LOGGER.info(
							" {} : UCS transaction in progress. Cannot import backup to Ucs: Complete or Undo UCS transaction to import backup.",
							handle.getUcs());
				} else {

					String dn = null;
					ConfigConfig inConfig = new ConfigConfig();

					// If backup file doesn't exist then exit.
					if (filePath == null || !(new File(filePath)).exists()) {
						LOGGER.error("Image file {} not found", filePath);
						handle.logout();
						handle = null;
						throw new FileNotFoundException("Image file not found");
					}

					String localFile = ((filePath.contains("\\")) ? filePath
							.substring(filePath.lastIndexOf('\\') + 1)
							: filePath.substring(filePath.lastIndexOf('/') + 1));

					String machineName = "";
					machineName = InetAddress.getLocalHost().getHostName()
							.toLowerCase();

					// Make dn of the import backup operation in Top System with
					// local machine name and current date-time.
					String dateTime = (new SimpleDateFormat("yyyyMMddHHmm"))
							.format(new Date(System.currentTimeMillis()));
					dn = UcsUtils.makeDn(TopSystem.makeRn(),
							MgmtImporter.makeRn(machineName + dateTime));

					// Create the importer managed object and set its required
					// properties.
					MgmtImporter mgmtImporter = new MgmtImporter();
					mgmtImporter.setDn(dn);
					mgmtImporter.setStatus(Status.CREATED);
					mgmtImporter.setHostname(machineName + dateTime);
					mgmtImporter.setRemoteFile(filePath);
					mgmtImporter.setProto(MgmtImporter.PROTO_HTTP);
					mgmtImporter
							.setAdminState(MgmtImporter.ADMIN_STATE_ENABLED);
					mgmtImporter.setAction((merge) ? MgmtImporter.ACTION_MERGE
							: MgmtImporter.ACTION_REPLACE);
					inConfig.addChild(mgmtImporter);

					// construct uri of location of backup file on UCS where
					// it will be stored.
					String uri = StringUtils.fillParamString(
							"{0}/operations/file-{1}/importconfig.txt",
							handle.getUri(), localFile);
					// Upload the backup file from local system to UCS on
					// specified uri.
					String result = FileUtils.uploadFile(handle, uri,
							localFile, filePath);

					if (result == null) {
						LOGGER.error(" {} : Error while uploading file to {}",
								handle.getUcs(), handle.getUcs());
						handle.logout();
						handle = null;
						throw new FileUploadException("Error while uploading");
					} else {
						LOGGER.info("{}: File uploaded successfully.",
								handle.getUcs());
					}

					LOGGER.info("pushToUcs - before import {} ");

					ConfigConfMo ccm = MethodFactory.getConfigConfMo(handle,
							dn, inConfig, YesOrNo.FALSE);
					if (ccm.getErrorCode() == 0) {
						LOGGER.info("Success in retrieving pushed xml config details.");
						successMessage = true;
						LOGGER.info("FC Ports isAvailable :"
								+ metadata.getFcPorts().isAvailable());
						if (isRestartRequired(metadata)) {
							// Restart of UCSM happens

							TimeUnit.SECONDS.sleep(Integer.parseInt(PdiConfig
									.getProperty(Constants.DEVICE_TIME_DELAY))); // Providing
																					// Time
																					// delay
																					// for
																					// UCSM
																					// restart
							handle = null;
							UcsHandle newhandle = null;
							String errorMessage = null;
							// Verify the device connection. Loop until the
							// device is up with provided time delay and the
							// frequency
							if (verifyDeviceConnection(serverDetails)) {
								LOGGER.info("UCSM device Connection verified after restart.");
								newhandle = new UcsHandle();
								TimeUnit.SECONDS.sleep(30);
								try {
									if (verifyUCSMConnection(ucsHost, userName,
											password, newhandle, noSsl,
											refreshable)) {
										LOGGER.info(
												"Re-logging in again after restart successfully in to :{}",
												ucsHost);
										retrieveFsmStatusAndPushMOs(projectId,
												serverDetails, ucsHost,
												PENDING, newhandle, ccm,
												metadata);
									} else {
										LOGGER.info(
												"Failed to relogging in again after restart in to :{}",
												ucsHost);
										errorMessage = "Failed to relogging in again after restart in to the device.";
									}
								} catch (Exception e) {
									LOGGER.info(
											"Exception happened in relogging in after restart in to :{} "
													+ ucsHost, e);
									errorMessage = "Exception happened in relogging in after restart in to the device."
											+ e.getMessage();
								} finally {
									LOGGER.info("In finally block after restart of device.");
									if (newhandle != null) {
										try {
											newhandle.logout();
											LOGGER.info("pushToUcs - Logged out successfully for newhandle object");
										} catch (ResponseException e) {
											LOGGER.error(
													"Exception occured while trying to logout device.",
													e);
											errorMessage = "Falied to logout of newly created ucs handle.";
										}
									}
									if (errorMessage != null) {
										successMessage = false;
										commonUtilService.saveUCSServerLogs(
												projectId,
												ucsHost,
												logException(projectId,
														ucsHost, errorMessage));
									}
								}
								// newhandle = null;
								LOGGER.info("pushToUcs - Logged out successfully after pushing configuration after restart of the device.");
								return successMessage;

							} else {
								// Checking UCSM connection failed. Taking more
								// time than time interval provided.
								LOGGER.info("Checking UCSM connection failed. Taking more time than time interval provided.");
								successMessage = false;
							}
						} else {
							// No fc ports, hence no restart. So fetch fsm
							// status and push mo's
							LOGGER.info("No fc ports, hence no restart. So fetch fsm status and push mo's");
							retrieveFsmStatusAndPushMOs(projectId,
									serverDetails, ucsHost, PENDING, handle,
									ccm, metadata);
						}

					} else {
						LOGGER.info("pushToUcs - Push configuration failed."
								+ handle.getUcs());
						successMessage = false;
					}

				}
				// handle.logout();
				// handle = null;
				LOGGER.info("pushToUcs - Logged out successfully");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			commonUtilService.saveUCSServerLogs(projectId, ucsHost,
					logException(projectId, ucsHost, e.getMessage()));
		} finally {
			LOGGER.info("In finally block.");
			if (handle != null) {
				try {
					handle.logout();
					LOGGER.info("pushToUcs - Logged out successfully for handle object");
				} catch (ResponseException e) {
					LOGGER.error("Exception while trying to logout device.", e);
				}
			}
		}
		return successMessage;
	}

	private boolean verifyUCSMConnection(String ucsHost, String userName,
			String password, UcsHandle handle, boolean noSsl,
			boolean refreshable) {
		return configurationService.verifyConnection(handle, ucsHost, userName,
				password, noSsl, portNumber, refreshable);
	}

	private void retrieveFsmStatusAndPushMOs(Integer projectId,
			ServerDetails serverDetails, String ucsHost, String status,
			UcsHandle handle, ConfigConfMo ccm, Metadata metadata) {
		LOGGER.info("Start: pushToUcs - retrieveFsmStatusAndPushMOs");
		String fetchFsmStatus = fsmStatusRetrieval(projectId, ucsHost, status,
				handle);

		pushAllMOsAfterSuccessfulXMLPush(metadata, fetchFsmStatus, handle);
		LOGGER.info("End: pushToUcs - retrieveFsmStatusAndPushMOs");
	}

	private void pushAllMOsAfterSuccessfulXMLPush(Metadata metadata,
			String status, UcsHandle handle) {
		LOGGER.info("Start: pushToUcs - pushAllMOsAfterSuccessfulXMLPush");
		if (SUCCESS.equals(status) || NOFSMSTATUS.equals(status)) {
			LOGGER.info("pushToUcs - pushing MO ");
			pushMoToUcsService.pushMgmtBackupInfo(handle,
					metadata.getMgmtBackupPolicy());
			pushMoToUcsService.updateLdapPwdField(handle,
					metadata.getAdminLdapProvider());
			pushMoToUcsService.updateRADIUSKey(handle,
					metadata.getAdminRadiusProvider());
			pushMoToUcsService.updateTACACSKey(handle,
					metadata.getAdminTacacsProvider());
			pushMoToUcsService.updateAdminLdapRole(handle,
					metadata.getAdminLdapRole());
			pushMoToUcsService.updateAdminLocales(handle,
					metadata.getAdminLdapLocale());
			pushMoToUcsService.pushMoForLdapGroup(handle,
					metadata.getAdminLdapGroupMap());
		}
		LOGGER.info("End: pushToUcs - pushAllMOsAfterSuccessfulXMLPush");
	}

	private String fsmStatusRetrieval(Integer projectId, String ucsHost,
			String status, UcsHandle handle) {
		LOGGER.info("Start: pushToUcs - fsmStatusRetrieval");
		List<UcsServerLogs> ucsServerLogsList = null;
		String returnStatus = status;
		while ("pending".equalsIgnoreCase(returnStatus)) {
			try {
				TimeUnit.SECONDS.sleep(30);
				LOGGER.info("pushToUcs - before fetch fms status {} - Status is :"
						+ returnStatus);
				ucsServerLogsList = FetchErrorLogFromUcs
						.fetchFsmStepSequenceStatus(projectId, handle, ucsHost);
				LOGGER.info("pushToUcs - after fetch fms status {} ");

				if (ucsServerLogsList != null && ucsServerLogsList.size() > 0) {
					for (UcsServerLogs ucsServerLogs : ucsServerLogsList) {
						if (IMPORTREPORTRESULTS.equals(ucsServerLogs.getName())
								&& !PENDING.equals(ucsServerLogs.getStatus())) {
							returnStatus = ucsServerLogs.getStatus();
							LOGGER.info("status from fetchFsmStepSequenceStatus - inside loop:"
									+ returnStatus);
						}
					}
				} else {
					returnStatus = NOFSMSTATUS;
					ucsServerLogsList = logException(projectId, ucsHost,
							"No Fsm Status retrived from device.");
				}
			} catch (Exception e) {
				returnStatus = NOFSMSTATUS;
				LOGGER.info("Exception in fetching fms status:", e);
			}
		}
		commonUtilService.saveUCSServerLogs(projectId, ucsHost,
				ucsServerLogsList);
		LOGGER.info("End: pushToUcs - fsmStatusRetrieval");
		return returnStatus;
	}

	private List<UcsServerLogs> logException(Integer projectId,
			String ipAddress, String desc) {
		List<UcsServerLogs> ucsServerLogsList = new ArrayList<UcsServerLogs>();
		UcsServerLogs ucsServerLogs = new UcsServerLogs();
		ucsServerLogs.setName("Exception");
		ucsServerLogs.setDescription(desc);
		ucsServerLogs.setStatus("Failed");
		ucsServerLogs.setProjectDetails(commonUtilService
				.getProjectDetailsObject(projectId));
		ucsServerLogs.setIpAddress(ipAddress);
		ucsServerLogsList.add(ucsServerLogs);
		return ucsServerLogsList;
	}

	/**
	 * This method checks whether the device is up and running post the FC ports
	 * data is pushed.
	 * 
	 * @param projectId
	 * @param serverDetails
	 * @return
	 */
	private boolean verifyDeviceConnection(ServerDetails serverDetails) {
		LOGGER.info("Start: pushToUcs - verifyDeviceConnection ");
		boolean status = false;
		int delay = Integer.parseInt(PdiConfig
				.getProperty(Constants.DEVICE_CON_RETRY_INTERVAL));
		int retryTimes = Integer.parseInt(PdiConfig
				.getProperty(Constants.DEVICE_CON_RETRY_FREQ));
		try {
			for (int count = 0; count < retryTimes && !status; count++) {
				status = configurationService.verifyConnection(serverDetails);
				if (!status) {
					Thread.sleep(delay * 1000);
				}
			}
		} catch (ResponseException e) {
			LOGGER.error(
					"Response exception while verifying device connection.", e);
		} catch (InterruptedException e) {
			LOGGER.error(
					"Interrupted exception while verifying device connection.",
					e);
		}
		LOGGER.info("End: pushToUcs - verifyDeviceConnection {} status : "
				+ status);
		return status;
	}

	private boolean isRestartRequired(Metadata metadata) {
		LOGGER.info("Start :isRestartRequired - fc port available : "
				+ metadata.getFcPorts().isAvailable() + " , FC mode : "
				+ metadata.getInfrastructure().getFcMode()
				+ ", Ethernet Mode : "
				+ metadata.getInfrastructure().getEthernetMode());
		if (metadata.getFcPorts().isAvailable()
				|| "switch".equals(metadata.getInfrastructure().getFcMode())
				|| "switch".equals(metadata.getInfrastructure()
						.getEthernetMode())) {
			return true;
		}
		LOGGER.info("End : isRestartRequired");
		return false;
	}
}
