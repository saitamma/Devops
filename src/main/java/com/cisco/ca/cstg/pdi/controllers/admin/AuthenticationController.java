package com.cisco.ca.cstg.pdi.controllers.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cisco.ca.cstg.pdi.pojos.AdminAuthenticationDomain;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapGeneral;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapGroupProviderMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapProvider;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapProviderGroup;
import com.cisco.ca.cstg.pdi.pojos.AdminRadiusGeneral;
import com.cisco.ca.cstg.pdi.pojos.AdminRadiusGroupProviderMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminRadiusProvider;
import com.cisco.ca.cstg.pdi.pojos.AdminRadiusProviderGroup;
import com.cisco.ca.cstg.pdi.pojos.AdminTacacsGeneral;
import com.cisco.ca.cstg.pdi.pojos.AdminTacacsGroupProviderMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminTacacsProvider;
import com.cisco.ca.cstg.pdi.pojos.AdminTacacsProviderGroup;
import com.cisco.ca.cstg.pdi.services.AdminService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
@SessionAttributes("activeProjectId")
public class AuthenticationController implements Constants {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AuthenticationController.class);

	private final AdminService adminService;

	@Autowired
	public AuthenticationController(AdminService adminService) {
		this.adminService = adminService;
	}

	@RequestMapping(value = "/adminAuthentication.html")
	public String showAdminAuthentication() {
		return "admin/adminAuthentication";
	}

	@RequestMapping(value = "/getAdminAuthenticationDomainDetails.html", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> getAdminAuthenticationDomainDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<AdminAuthenticationDomain> adminAuthDomain = null;
		try {
			if (projectId != null) {
				adminAuthDomain = adminService.fetchAdminAuthDomain(projectId);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
		return buildAuthenticationDomainJson(adminAuthDomain);
	}

	@RequestMapping(value = "/manageAdminAuthenticationDomain.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public List<Object> manageAdminAuthenticationDomain(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<AdminAuthenticationDomain> newlyAddedRecords = null;

		try {
			if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
				JsonNode addOrUpdatedNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdatedNodes)) {
					List<AdminAuthenticationDomain> adminAuthDomainList = (List<AdminAuthenticationDomain>) (List<?>) Util
							.convertJsonToListOfObject(
									addOrUpdatedNodes.toString(),
									AdminAuthenticationDomain.class);
					if (Util.listNotNull(adminAuthDomainList)) {
						newlyAddedRecords = adminService
								.saveOrUpdateAuthDomain(adminAuthDomainList,
										projectId);
					}
				}
				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<AdminAuthenticationDomain> deletedAdminAuthDomainList = (List<AdminAuthenticationDomain>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									AdminAuthenticationDomain.class);
					this.adminService
							.deleteAdminAuthDomain(deletedAdminAuthDomainList);
				}

			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		return buildAuthenticationDomainJson(newlyAddedRecords);
	}

	@RequestMapping(value = "/getLdapGeneralConfig.html", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getLdapGeneralConfig(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		AdminLdapGeneral ldapGeneral = null;
		try {
			if (projectId != null) {
				ldapGeneral = adminService.fetchLdapGeneral(projectId);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		if (ldapGeneral != null) {
			Map<String, Object> myMap = new HashMap<String, Object>();

			myMap.put(ID, ldapGeneral.getId());
			myMap.put(BASEDN, ldapGeneral.getBaseDn());
			myMap.put(FILTER, ldapGeneral.getFilter());
			myMap.put(ATTRIBUTE, ldapGeneral.getAttribute());
			myMap.put(TIMEOUT, ldapGeneral.getTimeout());

			return Util.convertMapToJson(myMap);
		}
		return null;
	}

	@RequestMapping(value = "/manageLdapGeneralConfig.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Integer manageLdapGeneralConfig(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Integer ldapGeneralSettingId = 0;

		try {
			if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
				AdminLdapGeneral alg = mapper.readValue(jsonObj,
						AdminLdapGeneral.class);
				if (alg != null) {
					ldapGeneralSettingId = adminService
							.saveOrUpdateLdapGeneral(alg, projectId);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		return ldapGeneralSettingId;
	}

	@RequestMapping(value = "/getLdapProviderConfig.html", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> getLdapProviderConfig(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<Object> jsonList = new ArrayList<Object>();
		List<AdminLdapProvider> ldapProviderList = null;
		try {
			if (projectId != null) {
				ldapProviderList = adminService.fetchLdapProvider(projectId);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		buildLdapProviderJson(jsonList, ldapProviderList);
		return jsonList;
	}

	@RequestMapping(value = "/manageLdapProviderConfig.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public List<Object> manageLdapProviderConfig(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<Object> jsonList = new ArrayList<Object>();
		List<AdminLdapProvider> newlyAddedRecords = null;

		try {
			if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
				JsonNode addOrUpdatedNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdatedNodes)) {
					List<AdminLdapProvider> ldapProviderList = (List<AdminLdapProvider>) (List<?>) Util
							.jsonToListWithIgnoringField(
									addOrUpdatedNodes.toString(),
									AdminLdapProvider.class);
					if (Util.listNotNull(ldapProviderList)) {
						newlyAddedRecords = adminService
								.saveOrUpdateLdapProvider(ldapProviderList,
										projectId);
					}
				}
				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<AdminLdapProvider> deletedAdminAuthDomainList = (List<AdminLdapProvider>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									AdminLdapProvider.class);
					this.adminService
							.deleteLdapProvider(deletedAdminAuthDomainList);
					jsonList.add(SUCCESS);
				}

			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		buildLdapProviderJson(jsonList, newlyAddedRecords);
		return jsonList;
	}

	@RequestMapping(value = "/getAdminLdapProviderGroupDetails.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> getAdminLdapProviderGroupDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.info("Start: getAdminLdapProviderGroupDetails");
		List<Object> jsonList = new ArrayList<Object>();
		List<AdminLdapProviderGroup> ldapProviderGroupList = null;

		try {
			if (projectId != null) {
				ldapProviderGroupList = adminService
						.fetchAdminLdapProviderGroup(projectId);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		buildLdapProviderGroupDataJson(jsonList, ldapProviderGroupList);
		LOGGER.info("End: getAdminLdapProviderGroupDetails");
		return jsonList;
	}

	@RequestMapping(value = "/manageAdminLdapProviderGroupDetails.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public List<Object> manageAdminLdapProviderGroupDetails(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.info("Start: manageAdminLdapProviderGroupDetails");
		LOGGER.debug(ACTIVEPROJECTID + projectId);

		List<Object> jsonList = new ArrayList<Object>();
		List<AdminLdapProviderGroup> newlyAddedRecords = null;
		List<AdminLdapProviderGroup> ldapProivderGroupList = new ArrayList<AdminLdapProviderGroup>();
		try {
			if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
				JsonNode addOrUpdatedNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdatedNodes)) {
					for (int i = 0; i < addOrUpdatedNodes.size(); i++) {
						JsonNode eachNode = addOrUpdatedNodes.get(i);
						AdminLdapProviderGroup alpg = (AdminLdapProviderGroup) Util
								.jsonToObjectWithIgnoringField(
										eachNode.toString(),
										AdminLdapProviderGroup.class);

						if (alpg != null) {
							List<AdminLdapGroupProviderMapping> mappingList = new ArrayList<AdminLdapGroupProviderMapping>();
							if (eachNode.has(PROVIDER)
									&& !eachNode.get(PROVIDER).isNull()) {
								String[] split = eachNode.get(PROVIDER)
										.toString().replaceAll(PATTERN, "")
										.split(",");
								for (int j = 0; j < split.length; j++) {
									AdminLdapGroupProviderMapping algpm = new AdminLdapGroupProviderMapping();
									algpm.setId(0);
									algpm.setAdminLdapProviderGroup(new AdminLdapProviderGroup(
											alpg.getId()));
									AdminLdapProvider alp = (split[j]
											.equalsIgnoreCase(NULL_STR)) ? null
											: new AdminLdapProvider(
													Integer.parseInt(split[j]));

									algpm.setAdminLdapProvider(alp);
									mappingList.add(algpm);
								}
							}
							alpg.setAdminLdapGroupProviderMappings(mappingList);
							ldapProivderGroupList.add(alpg);
						}
					}
					newlyAddedRecords = this.adminService
							.saveOrUpdateAdminLdapProviderGroup(
									ldapProivderGroupList, projectId);
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<AdminLdapProviderGroup> deletedalpgList = (List<AdminLdapProviderGroup>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									AdminLdapProviderGroup.class);
					this.adminService.deleteAdminLdapProviderGroup(
							deletedalpgList, projectId);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		buildLdapProviderGroupDataJson(jsonList, newlyAddedRecords);
		LOGGER.info("End: manageAdminLdapProviderGroupDetails");
		return jsonList;
	}

	@RequestMapping(value = "/getRadiusGeneralConfig.html", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getRadiusGeneralConfig(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		AdminRadiusGeneral radiusGeneral = null;
		try {
			if (projectId != null) {
				radiusGeneral = adminService.fetchRadiusGeneral(projectId);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		if (radiusGeneral != null) {
			Map<String, Object> myMap = new HashMap<String, Object>();

			myMap.put(ID, radiusGeneral.getId());
			myMap.put(TIMEOUT, radiusGeneral.getTimeout());
			myMap.put(RETRIES, radiusGeneral.getRetries());
			return Util.convertMapToJson(myMap);
		}
		return null;
	}

	@RequestMapping(value = "/manageRadiusGeneralConfig.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Integer manageRadiusGeneralConfig(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Integer radiusGeneralSettingId = 0;

		try {
			if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
				AdminRadiusGeneral radiusGeneral = mapper.readValue(jsonObj,
						AdminRadiusGeneral.class);
				if (radiusGeneral != null) {
					radiusGeneralSettingId = adminService
							.saveOrUpdateRadiusGeneral(radiusGeneral, projectId);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		return radiusGeneralSettingId;
	}

	@RequestMapping(value = "/getRadiusProviderConfig.html", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> getRadiusProviderConfig(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<Object> jsonList = new ArrayList<Object>();
		List<AdminRadiusProvider> radiusProviderList = null;
		try {
			if (projectId != null) {
				radiusProviderList = adminService
						.fetchRadiusProvider(projectId);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		buildRadiusProviderJson(jsonList, radiusProviderList);
		return jsonList;
	}

	@RequestMapping(value = "/manageRadiusProviderConfig.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public List<Object> manageRadiusProviderConfig(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<Object> jsonList = new ArrayList<Object>();
		List<AdminRadiusProvider> newlyAddedRecords = null;

		try {
			if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
				JsonNode addOrUpdatedNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdatedNodes)) {
					List<AdminRadiusProvider> radiusProviderList = (List<AdminRadiusProvider>) (List<?>) Util
							.jsonToListWithIgnoringField(
									addOrUpdatedNodes.toString(),
									AdminRadiusProvider.class);

					if (Util.listNotNull(radiusProviderList)) {
						newlyAddedRecords = adminService
								.saveOrUpdateRadiusProvider(radiusProviderList,
										projectId);
					}
				}
				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<AdminRadiusProvider> deletedAdminAuthDomainList = (List<AdminRadiusProvider>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									AdminRadiusProvider.class);
					this.adminService
							.deleteRadiusProvider(deletedAdminAuthDomainList);
					jsonList.add(SUCCESS);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		buildRadiusProviderJson(jsonList, newlyAddedRecords);
		return jsonList;
	}

	@RequestMapping(value = "/getAdminRadiusProviderGroupDetails.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> getAdminRadiusProviderGroupDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.info("Start: getAdminRadiusProviderGroupDetails");
		List<Object> jsonList = new ArrayList<Object>();
		List<AdminRadiusProviderGroup> radiusProviderGroupList = null;

		try {
			radiusProviderGroupList = adminService
					.fetchRadiusProviderGroup(projectId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		buildRadiusProviderGroupDataJson(jsonList, radiusProviderGroupList);
		LOGGER.info("End: getAdminRadiusProviderGroupDetails");
		return jsonList;
	}

	@RequestMapping(value = "/manageAdminRadiusProviderGroupDetails.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public List<Object> manageAdminRadiusProviderGroupDetails(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.info("Start: manageAdminRadiusProviderGroupDetails");
		LOGGER.debug(ACTIVEPROJECTID + projectId);

		List<Object> jsonList = new ArrayList<Object>();
		List<AdminRadiusProviderGroup> newlyAddedRecords = null;
		List<AdminRadiusProviderGroup> radiusProivderGroupList = new ArrayList<AdminRadiusProviderGroup>();
		try {
			if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
				JsonNode addOrUpdatedNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdatedNodes)) {
					for (int i = 0; i < addOrUpdatedNodes.size(); i++) {
						JsonNode eachNode = addOrUpdatedNodes.get(i);
						AdminRadiusProviderGroup arpg = (AdminRadiusProviderGroup) Util
								.jsonToObjectWithIgnoringField(
										eachNode.toString(),
										AdminRadiusProviderGroup.class);

						if (arpg != null) {
							List<AdminRadiusGroupProviderMapping> mappingList = new ArrayList<AdminRadiusGroupProviderMapping>();
							if (eachNode.has(PROVIDER)
									&& !eachNode.get(PROVIDER).isNull()) {
								String[] split = eachNode.get(PROVIDER)
										.toString().replaceAll(PATTERN, "")
										.split(",");
								for (int j = 0; j < split.length; j++) {
									AdminRadiusGroupProviderMapping argpm = new AdminRadiusGroupProviderMapping();
									argpm.setId(0);
									argpm.setAdminRadiusProviderGroup(new AdminRadiusProviderGroup(
											arpg.getId()));
									AdminRadiusProvider arp = (split[j]
											.equalsIgnoreCase(NULL_STR)) ? null
											: new AdminRadiusProvider(
													Integer.parseInt(split[j]));

									argpm.setAdminRadiusProvider(arp);
									mappingList.add(argpm);
								}
							}
							arpg.setAdminRadiusGroupProviderMappings(mappingList);
							radiusProivderGroupList.add(arpg);
						}
					}
					newlyAddedRecords = this.adminService
							.saveOrUpdateRadiusProviderGroup(
									radiusProivderGroupList, projectId);
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<AdminRadiusProviderGroup> deletedarpgList = (List<AdminRadiusProviderGroup>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									AdminRadiusProviderGroup.class);
					this.adminService.deleteRadiusProviderGroup(
							deletedarpgList, projectId);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		buildRadiusProviderGroupDataJson(jsonList, newlyAddedRecords);
		LOGGER.info("End: manageAdminRadiusProviderGroupDetails");
		return jsonList;
	}

	@RequestMapping(value = "/getTacacsGeneralConfig.html", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getTacacsGeneralConfig(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		AdminTacacsGeneral tacacsGeneral = null;
		try {
			if (projectId != null) {
				tacacsGeneral = adminService.fetchTacacsGeneral(projectId);

				if (tacacsGeneral != null) {
					Map<String, Object> myMap = new HashMap<String, Object>();

					myMap.put(ID, tacacsGeneral.getId());
					myMap.put(TIMEOUT, tacacsGeneral.getTimeout());

					return Util.convertMapToJson(myMap);
				}
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
		return null;
	}

	@RequestMapping(value = "/manageTacacsGeneralConfig.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Integer manageTacacsGeneralConfig(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Integer tacacsGeneralSettingId = 0;

		try {
			if (Util.isStringNotEmpty(jsonObj)) {
				AdminTacacsGeneral adminTacacsGeneral = mapper.readValue(
						jsonObj, AdminTacacsGeneral.class);

				if (adminTacacsGeneral != null) {
					tacacsGeneralSettingId = adminService
							.saveOrUpdateTacacsGeneral(adminTacacsGeneral,
									projectId);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		return tacacsGeneralSettingId;
	}

	@RequestMapping(value = "/getTacacsProviderConfig.html", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Object> getTacacsProviderConfig(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<Object> jsonList = new ArrayList<Object>();
		List<AdminTacacsProvider> tacacsProviderList = null;
		try {
			if (projectId != null) {
				tacacsProviderList = adminService
						.fetchTacacsProvider(projectId);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		buildTacacsProviderDataJson(jsonList, tacacsProviderList);
		return jsonList;
	}

	@RequestMapping(value = "/manageTacacsProviderConfig.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public List<Object> manageTacacsProviderConfig(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		List<Object> jsonList = new ArrayList<Object>();
		List<AdminTacacsProvider> newlyAddedRecords = null;

		try {
			if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
				JsonNode addOrUpdatedNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdatedNodes)) {
					List<AdminTacacsProvider> tacacsProviderList = (List<AdminTacacsProvider>) (List<?>) Util
							.jsonToListWithIgnoringField(
									addOrUpdatedNodes.toString(),
									AdminTacacsProvider.class);
					if (Util.listNotNull(tacacsProviderList)) {
						newlyAddedRecords = adminService
								.saveOrUpdateTacacsProvider(tacacsProviderList,
										projectId);
					}
				}
				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<AdminTacacsProvider> deletedAdminAuthDomainList = (List<AdminTacacsProvider>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									AdminTacacsProvider.class);
					this.adminService
							.deleteTacacsProvider(deletedAdminAuthDomainList);
					jsonList.add(SUCCESS);
				}

			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		buildTacacsProviderDataJson(jsonList, newlyAddedRecords);
		return jsonList;
	}

	@RequestMapping(value = "/getAdminTacacsProviderGroupDetails.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> getAdminTacacsProviderGroupDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.info("Start: getAdminTacacsProviderGroupDetails");
		List<Object> jsonList = new ArrayList<Object>();
		List<AdminTacacsProviderGroup> tacacsProviderGroupList = null;

		try {
			if (projectId != null) {
				tacacsProviderGroupList = adminService
						.fetchAdminTacacsProviderGroup(projectId);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		buildTacacsProviderGroupDataJson(jsonList, tacacsProviderGroupList);
		LOGGER.info("End: getAdminTacacsProviderGroupDetails");
		return jsonList;
	}

	@RequestMapping(value = "/manageAdminTacacsProviderGroupDetails.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public List<Object> manageAdminTacacsProviderGroupDetails(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.info("Start: manageAdminTacacsProviderGroupDetails");
		LOGGER.debug(ACTIVEPROJECTID + projectId);

		List<Object> jsonList = new ArrayList<Object>();
		List<AdminTacacsProviderGroup> newlyAddedRecords = null;
		List<AdminTacacsProviderGroup> tacacsProivderGroupList = new ArrayList<AdminTacacsProviderGroup>();
		try {
			if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
				JsonNode addOrUpdatedNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdatedNodes)) {
					for (int i = 0; i < addOrUpdatedNodes.size(); i++) {
						JsonNode eachNode = addOrUpdatedNodes.get(i);
						AdminTacacsProviderGroup adminTacacsProviderGroup = (AdminTacacsProviderGroup) Util
								.jsonToObjectWithIgnoringField(
										eachNode.toString(),
										AdminTacacsProviderGroup.class);

						if (adminTacacsProviderGroup != null) {
							List<AdminTacacsGroupProviderMapping> mappingList = new ArrayList<AdminTacacsGroupProviderMapping>();

							if (eachNode.has(PROVIDER)
									&& !eachNode.get(PROVIDER).isNull()) {
								String[] split = eachNode.get(PROVIDER)
										.toString().replaceAll(PATTERN, "")
										.split(",");
								int length = split.length;

								for (int j = 0; j < length; j++) {
									AdminTacacsGroupProviderMapping algpm = new AdminTacacsGroupProviderMapping();
									algpm.setId(0);
									algpm.setAdminTacacsProviderGroup(new AdminTacacsProviderGroup(
											adminTacacsProviderGroup.getId()));
									AdminTacacsProvider alp = (split[j]
											.equalsIgnoreCase(NULL_STR)) ? null
											: new AdminTacacsProvider(
													Integer.parseInt(split[j]));

									algpm.setAdminTacacsProvider(alp);
									mappingList.add(algpm);
								}
							}
							adminTacacsProviderGroup
									.setAdminTacacsGroupProviderMappings(mappingList);
							tacacsProivderGroupList
									.add(adminTacacsProviderGroup);
						}
					}
					newlyAddedRecords = this.adminService
							.saveOrUpdateAdminTacacsProviderGroup(
									tacacsProivderGroupList, projectId);
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<AdminTacacsProviderGroup> deletedalpgList = (List<AdminTacacsProviderGroup>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									AdminTacacsProviderGroup.class);
					this.adminService.deleteAdminTacacsProviderGroup(
							deletedalpgList, projectId);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		buildTacacsProviderGroupDataJson(jsonList, newlyAddedRecords);
		LOGGER.info("End: manageAdminTacacsProviderGroupDetails");
		return jsonList;
	}

	private List<Object> buildAuthenticationDomainJson(
			List<AdminAuthenticationDomain> adminAuthDomain) {
		List<Object> jsonList = new ArrayList<Object>();

		if (Util.listNotNull(adminAuthDomain)) {
			for (AdminAuthenticationDomain aad : adminAuthDomain) {
				Map<String, Object> myMap = new HashMap<String, Object>();

				myMap.put(ID, aad.getId());
				myMap.put(NAME, aad.getName());
				myMap.put(REALM, aad.getRealm());
				myMap.put(REFRESHPERIOD, aad.getRefreshPeriod());
				myMap.put(SESSIONTIMEOUT, aad.getSessionTimeout());

				if ("Ldap".equals(aad.getRealm())
						&& aad.getAdminLdapProviderGroup() != null) {
					myMap.put(PROVIDERGROUP, aad.getAdminLdapProviderGroup()
							.getId());
				} else if ("Radius".equals(aad.getRealm())
						&& aad.getAdminRadiusProviderGroup() != null) {
					myMap.put(PROVIDERGROUP, aad.getAdminRadiusProviderGroup()
							.getId());
				} else if ("Tacacs".equals(aad.getRealm())
						&& aad.getAdminTacacsProviderGroup() != null) {
					myMap.put(PROVIDERGROUP, aad.getAdminTacacsProviderGroup()
							.getId());
				} else {
					myMap.put(PROVIDERGROUP, null);
				}

				myMap.put(TWOFACTOR, aad.getTwoFactor());

				Util.convertMapToJson(myMap, jsonList);
			}
		}
		return jsonList;
	}

	private void buildLdapProviderJson(List<Object> jsonList,
			List<AdminLdapProvider> newlyAddedRecords) {

		if (Util.listNotNull(newlyAddedRecords)) {
			for (AdminLdapProvider lp : newlyAddedRecords) {
				Map<String, Object> myMap = new HashMap<String, Object>();

				myMap.put(ID, lp.getId());
				myMap.put(HOSTNAME, lp.getHostname());
				myMap.put(PROVIDERORDER, lp.getProviderOrder());
				myMap.put(BINDN, lp.getBindDn());
				myMap.put(BASEDN, lp.getBaseDn());
				myMap.put(PORT, lp.getPort());
				myMap.put(ENABLESSL, lp.getEnableSsl());
				myMap.put(FILTER, lp.getFilter());
				myMap.put(ATTRIBUTE, lp.getAttribute());
				myMap.put(PROVIDERPASSWORD, lp.getProviderPassword());
				myMap.put(TIMEOUT, lp.getTimeout());
				myMap.put(VENDOR, lp.getVendor());
				myMap.put(GROUPAUTHORIZAION, lp.getGroupAuthorization());
				myMap.put(GROUPRECURSION, lp.getGroupRecursion());
				myMap.put(TARGETATTRIBUTE, lp.getTargetAttribute());
				myMap.put(USEPRIMARYGROUP, lp.getUsePrimaryGroup());

				Util.convertMapToJson(myMap, jsonList);
			}
		}
	}

	private void buildRadiusProviderJson(List<Object> jsonList,
			List<AdminRadiusProvider> radiusProviderList) {
		if (Util.listNotNull(radiusProviderList)) {
			for (AdminRadiusProvider adminRadiusProvider : radiusProviderList) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, adminRadiusProvider.getId());
				myMap.put(HOSTNAME, adminRadiusProvider.getHostname());
				myMap.put(RADIUSORDER, adminRadiusProvider.getRadiusOrder());
				myMap.put(AUTHORIZATIONPORT,
						adminRadiusProvider.getAuthorizationPort());
				myMap.put(TIMEOUT,
						String.valueOf(adminRadiusProvider.getTimeout()));
				myMap.put(RETRIES,
						String.valueOf(adminRadiusProvider.getRetries()));
				myMap.put(SSLKEY, adminRadiusProvider.getSslKey());
				Util.convertMapToJson(myMap, jsonList);
			}
		}
	}

	private void buildRadiusProviderGroupDataJson(List<Object> jsonList,
			List<AdminRadiusProviderGroup> radiusProviderGroupList) {
		if (Util.listNotNull(radiusProviderGroupList)) {
			for (AdminRadiusProviderGroup arpg : radiusProviderGroupList) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, arpg.getId());
				myMap.put(NAME, arpg.getName());

				Integer[] providerArray = null;
				List<AdminRadiusGroupProviderMapping> arpgList = arpg
						.getAdminRadiusGroupProviderMappings();
				if (Util.listNotNull(arpgList)) {
					for (int i = 0; i < arpgList.size(); i++) {
						AdminRadiusGroupProviderMapping mapping = arpgList
								.get(i);
						if (i == 0) {
							providerArray = new Integer[arpgList.size()];
						}
						providerArray[i] = mapping.getAdminRadiusProvider()
								.getId();
					}
				}
				myMap.put(PROVIDER, providerArray);

				Util.convertMapToJson(myMap, jsonList);
			}
		}
	}

	private void buildTacacsProviderDataJson(List<Object> jsonList,
			List<AdminTacacsProvider> tacacsProviderList) {
		if (Util.listNotNull(tacacsProviderList)) {
			for (AdminTacacsProvider adminTacacsProvider : tacacsProviderList) {
				Map<String, Object> myMap = new HashMap<String, Object>();

				myMap.put(ID, adminTacacsProvider.getId());
				myMap.put(HOSTNAME, adminTacacsProvider.getHostname());
				myMap.put(PROVIDERORDER, adminTacacsProvider.getProviderOrder());
				myMap.put(PORT, adminTacacsProvider.getPort());
				myMap.put(PROVIDERKEY, adminTacacsProvider.getProviderKey());
				myMap.put(TIMEOUT, adminTacacsProvider.getTimeout());

				Util.convertMapToJson(myMap, jsonList);
			}
		}
	}

	private void buildTacacsProviderGroupDataJson(List<Object> jsonList,
			List<AdminTacacsProviderGroup> tacacsProviderGroupList) {
		if (Util.listNotNull(tacacsProviderGroupList)) {
			for (AdminTacacsProviderGroup adminTacacsProviderGroup : tacacsProviderGroupList) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, adminTacacsProviderGroup.getId());
				myMap.put(NAME, adminTacacsProviderGroup.getName());

				List<AdminTacacsGroupProviderMapping> adminTacacsProviderGroupList = adminTacacsProviderGroup
						.getAdminTacacsGroupProviderMappings();

				if (Util.listNotNull(adminTacacsProviderGroupList)) {
					Integer[] providerArray = new Integer[adminTacacsProviderGroupList
							.size()];
					int i = 0;
					for (AdminTacacsGroupProviderMapping mapping : adminTacacsProviderGroupList) {
						providerArray[i] = mapping.getAdminTacacsProvider()
								.getId();
						i++;
					}
					myMap.put(PROVIDER, providerArray);
				}

				Util.convertMapToJson(myMap, jsonList);
			}
		}
	}

	private void buildLdapProviderGroupDataJson(List<Object> jsonList,
			List<AdminLdapProviderGroup> ldapProviderGroupList) {

		if (Util.listNotNull(ldapProviderGroupList)) {
			for (AdminLdapProviderGroup alpg : ldapProviderGroupList) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, alpg.getId());
				myMap.put(NAME, alpg.getName());

				Integer[] providerArray = null;
				List<AdminLdapGroupProviderMapping> alpgList = alpg
						.getAdminLdapGroupProviderMappings();
				if (Util.listNotNull(alpgList)) {
					for (int i = 0; i < alpgList.size(); i++) {
						AdminLdapGroupProviderMapping mapping = alpgList.get(i);
						if (i == 0) {
							providerArray = new Integer[alpgList.size()];
						}
						providerArray[i] = mapping.getAdminLdapProvider()
								.getId();
					}
				}
				myMap.put(PROVIDER, providerArray);

				Util.convertMapToJson(myMap, jsonList);
			}
		}
	}

}
