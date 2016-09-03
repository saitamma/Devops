package com.cisco.ca.cstg.pdi.controllers.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
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

import com.cisco.ca.cstg.pdi.pojos.AdminLdapGroupMap;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapGroupMapLocalesMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapGroupMapRolesMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapLocale;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapLocalesOrgMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapRole;
import com.cisco.ca.cstg.pdi.pojos.AdminLdapRolesPrivilegesMapping;
import com.cisco.ca.cstg.pdi.pojos.AdminPrivilege;
import com.cisco.ca.cstg.pdi.pojos.Organizations;
import com.cisco.ca.cstg.pdi.services.AdminService;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
@SessionAttributes("activeProjectId")
public class GroupMapsController implements Constants {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(GroupMapsController.class);

	private final AdminService adminService;

	@Autowired
	public GroupMapsController(AdminService adminService) {
		this.adminService = adminService;
	}

	@RequestMapping(value = "/adminLdapGroupMaps.html")
	public String showAdminLdapGroupMaps() {
		return "admin/adminLdapGroupMaps";
	}

	@RequestMapping(value = "/getAdminLdapGroupMapDetails.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> getAdminLdapGroupMapDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.info("Start: getAdminLdapGroupMapDetails");
		List<Object> jsonList = new ArrayList<Object>();
		List<AdminLdapGroupMap> ldapGroupMapList = null;

		try {
			if (projectId != null) {
				ldapGroupMapList = adminService
						.fetchLdapGroupMapsInfo(projectId);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
		buildLdapGroupMapDataJson(jsonList, ldapGroupMapList);

		LOGGER.info("End: getAdminLdapGroupMapDetails");
		return jsonList;
	}

	@RequestMapping(value = "/manageAdminLdapGroupMapDetails.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public List<Object> manageAdminLdapGroupMapDetails(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.info("Start: manageAdminLdapGroupMapDetails");
		LOGGER.debug(ACTIVEPROJECTID + projectId);

		List<Object> jsonList = new ArrayList<Object>();
		List<AdminLdapGroupMap> newlyAddedRecords = null;
		List<AdminLdapGroupMap> ldapGroupMapList = new ArrayList<AdminLdapGroupMap>();

		try {
			if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
				JsonNode addOrUpdatedNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdatedNodes)) {
					for (int i = 0; i < addOrUpdatedNodes.size(); i++) {
						JsonNode eachNode = addOrUpdatedNodes.get(i);
						AdminLdapGroupMap algm = (AdminLdapGroupMap) Util
								.jsonToObjectWithIgnoringField(
										eachNode.toString(),
										AdminLdapGroupMap.class);

						if (algm != null) {
							List<AdminLdapGroupMapRolesMapping> rolesMappingList = new ArrayList<AdminLdapGroupMapRolesMapping>();
							if (eachNode.has(ROLE)
									&& !eachNode.get(ROLE).isNull()) {
								String[] split = eachNode.get(ROLE).toString()
										.replaceAll(PATTERN, "").split(",");
								for (int j = 0; j < split.length; j++) {
									AdminLdapGroupMapRolesMapping algmrm = new AdminLdapGroupMapRolesMapping();
									algmrm.setId(0);
									algmrm.setAdminLdapGroupMap(new AdminLdapGroupMap(
											algm.getId()));
									AdminLdapRole alr = (split[j]
											.equalsIgnoreCase(NULL_STR)) ? null
											: new AdminLdapRole(
													Integer.parseInt(split[j]));
									algmrm.setAdminLdapRole(alr);
									rolesMappingList.add(algmrm);
								}
							}
							algm.setAdminLdapGroupMapRolesMappings(rolesMappingList);

							List<AdminLdapGroupMapLocalesMapping> localesMappingList = new ArrayList<AdminLdapGroupMapLocalesMapping>();
							if (eachNode.has(LOCALE)
									&& !eachNode.get(LOCALE).isNull()) {
								String[] split = eachNode.get(LOCALE)
										.toString().replaceAll(PATTERN, "")
										.split(",");
								for (int j = 0; j < split.length; j++) {
									AdminLdapGroupMapLocalesMapping algmlm = new AdminLdapGroupMapLocalesMapping();
									algmlm.setId(0);
									algmlm.setAdminLdapGroupMap(new AdminLdapGroupMap(
											algm.getId()));
									AdminLdapLocale all = (split[j]
											.equalsIgnoreCase(NULL_STR)) ? null
											: new AdminLdapLocale(
													Integer.parseInt(split[j]));
									algmlm.setAdminLdapLocale(all);
									localesMappingList.add(algmlm);
								}
							}
							algm.setAdminLdapGroupMapLocalesMappings(localesMappingList);

							ldapGroupMapList.add(algm);
						}
					}
					newlyAddedRecords = this.adminService
							.saveOrUpdateAdminLdapGroupMaps(ldapGroupMapList,
									projectId);
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<AdminLdapGroupMap> deleteGroupMapList = (List<AdminLdapGroupMap>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									AdminLdapGroupMap.class);
					this.adminService.deleteLdapGroupMaps(deleteGroupMapList);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		buildLdapGroupMapDataJson(jsonList, newlyAddedRecords);
		LOGGER.info("End: manageAdminLdapGroupMapDetails");
		return jsonList;
	}

	private void buildLdapGroupMapDataJson(List<Object> jsonList,
			List<AdminLdapGroupMap> ldapGroupMapList) {

		if (Util.listNotNull(ldapGroupMapList)) {
			for (AdminLdapGroupMap algm : ldapGroupMapList) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, algm.getId());
				myMap.put(NAME, algm.getName());

				Integer[] rolesArray = null;
				List<AdminLdapGroupMapRolesMapping> gmrmList = algm
						.getAdminLdapGroupMapRolesMappings();
				if (gmrmList != null) {
					for (int i = 0; i < gmrmList.size(); i++) {
						AdminLdapGroupMapRolesMapping mapping = gmrmList.get(i);
						if (i == 0) {
							rolesArray = new Integer[gmrmList.size()];
						}
						rolesArray[i] = mapping.getAdminLdapRole().getId();
					}
				}
				myMap.put(ROLE, rolesArray);

				Integer[] localesArray = null;
				List<AdminLdapGroupMapLocalesMapping> gmlmList = algm
						.getAdminLdapGroupMapLocalesMappings();
				if (gmlmList != null) {
					for (int j = 0; j < gmlmList.size(); j++) {
						AdminLdapGroupMapLocalesMapping localeMapping = gmlmList
								.get(j);
						if (j == 0) {
							localesArray = new Integer[gmlmList.size()];
						}
						localesArray[j] = localeMapping.getAdminLdapLocale()
								.getId();
					}
				}
				myMap.put(LOCALE, localesArray);
				Util.convertMapToJson(myMap, jsonList);
			}
		}
	}

	@RequestMapping(value = "/getAdminRoleDetails.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> getAdminRoleDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.info("Start: getAdminRoleDetails");
		List<Object> jsonList = new ArrayList<Object>();
		List<AdminLdapRole> adminRolesList;

		try {
			adminRolesList = adminService.fetchAdminRoles(projectId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
		buildAdminRoleDetailsJson(jsonList, adminRolesList);
		LOGGER.info("End: getAdminRoleDetails");
		return jsonList;
	}

	@RequestMapping(value = "/manageAdminRoleDetails.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public List<Object> manageAdminRoleDetails(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.info("Start: manageAdminRoleDetails");
		LOGGER.debug(ACTIVEPROJECTID + projectId);

		List<Object> jsonList = new ArrayList<Object>();
		List<AdminLdapRole> newlyAddedRecords = null;
		List<AdminLdapRole> adminRolesList = new ArrayList<AdminLdapRole>();
		try {
			if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
				JsonNode addOrUpdatedNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdatedNodes)) {
					for (int i = 0; i < addOrUpdatedNodes.size(); i++) {
						JsonNode eachNode = addOrUpdatedNodes.get(i);
						AdminLdapRole adminRole = (AdminLdapRole) Util
								.jsonToObjectWithIgnoringField(
										eachNode.toString(),
										AdminLdapRole.class);

						if (adminRole != null) {
							List<AdminLdapRolesPrivilegesMapping> privilegesMappingList = new ArrayList<AdminLdapRolesPrivilegesMapping>();
							if (eachNode.has(PRIVILEGE)
									&& !eachNode.get(PRIVILEGE).isNull()) {
								String[] split = eachNode.get(PRIVILEGE)
										.toString().replaceAll(PATTERN, "")
										.split(","); // "3" ["4","323"]
								for (int j = 0; j < split.length; j++) {
									AdminLdapRolesPrivilegesMapping privilageMapping = new AdminLdapRolesPrivilegesMapping();
									privilageMapping.setId(0);
									privilageMapping
											.setAdminLdapRole(new AdminLdapRole(
													adminRole.getId()));
									AdminPrivilege adminPrivilege = (split[j]
											.equalsIgnoreCase(NULL_STR)) ? null
											: new AdminPrivilege(
													Integer.parseInt(split[j]));
									privilageMapping
											.setAdminPrivilege(adminPrivilege);
									privilegesMappingList.add(privilageMapping);
								}
							}
							adminRole
									.setAdminLdapRolesPrivilegesMappings(privilegesMappingList);

							adminRolesList.add(adminRole);
						}
					}
					newlyAddedRecords = this.adminService
							.saveOrUpdateAdminRoles(adminRolesList, projectId);
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<AdminLdapRole> deleteAdminRolesList = (List<AdminLdapRole>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									AdminLdapRole.class);
					this.adminService.deleteAdminRoles(deleteAdminRolesList);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		buildAdminRoleDetailsJson(jsonList, newlyAddedRecords);
		LOGGER.info("End: manageAdminLdapGroupMapDetails");
		return jsonList;
	}

	@RequestMapping(value = "/getAdminLocaleDetails.html", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> getAdminLocaleDetails(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.info("Start: getAdminLocaleDetails");
		List<Object> jsonList = new ArrayList<Object>();
		List<AdminLdapLocale> adminLocalesList = null;

		try {
			if (projectId != null) {
				adminLocalesList = adminService.fetchAdminLocales(projectId);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
		buildAdminLocalesDataJson(jsonList, adminLocalesList);

		LOGGER.info("End: getAdminLocaleDetails");
		return jsonList;
	}

	@RequestMapping(value = "/manageAdminLocaleDetails.html", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SuppressWarnings("unchecked")
	public List<Object> manageAdminLocaleDetails(
			@RequestParam(JSONOBJ) String jsonObj,
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId)
			throws IOException {
		LOGGER.info("Start: manageAdminLocaleDetails");
		LOGGER.debug(ACTIVEPROJECTID + projectId);

		List<Object> jsonList = new ArrayList<Object>();
		List<AdminLdapLocale> newlyAddedRecords = null;
		List<AdminLdapLocale> adminLocalesList = new ArrayList<AdminLdapLocale>();

		try {
			if (Util.isStringNotEmpty(jsonObj) && projectId != null) {
				JsonNode addOrUpdatedNodes = Util.getJsonNodeByName(jsonObj,
						ADDORUPDATE);
				JsonNode deletedNodes = Util
						.getJsonNodeByName(jsonObj, DELETED);

				if (Util.jsonNodeNotNull(addOrUpdatedNodes)) {
					for (int i = 0; i < addOrUpdatedNodes.size(); i++) {
						JsonNode eachNode = addOrUpdatedNodes.get(i);
						AdminLdapLocale adminLocale = (AdminLdapLocale) Util
								.jsonToObjectWithIgnoringField(
										eachNode.toString(),
										AdminLdapLocale.class);

						if (adminLocale != null) {
							List<AdminLdapLocalesOrgMapping> orgsMappingList = new ArrayList<AdminLdapLocalesOrgMapping>();
							if (eachNode.has(ORGANIZATIONS)
									&& !eachNode.get(ORGANIZATIONS).isNull()) {
								String[] split = eachNode.get(ORGANIZATIONS)
										.toString().replaceAll(PATTERN, "")
										.split(",");
								for (int j = 0; j < split.length; j++) {
									AdminLdapLocalesOrgMapping orgMapping = new AdminLdapLocalesOrgMapping();
									orgMapping.setId(0);
									orgMapping
											.setAdminLdapLocale(new AdminLdapLocale(
													adminLocale.getId()));
									Organizations org = (split[j]
											.equalsIgnoreCase(NULL_STR)) ? null
											: new Organizations(
													Integer.parseInt(split[j]));
									orgMapping.setOrganizations(org);
									orgsMappingList.add(orgMapping);
								}
							}
							adminLocale
									.setAdminLdapLocalesOrgMappings(orgsMappingList);

							adminLocalesList.add(adminLocale);
						}
					}
					newlyAddedRecords = this.adminService
							.saveOrUpdateAdminLocales(adminLocalesList,
									projectId);
				}

				if (Util.jsonNodeNotNull(deletedNodes)) {
					List<AdminLdapLocale> deleteAdminLocaleList = (List<AdminLdapLocale>) (List<?>) Util
							.convertJsonToListOfObject(deletedNodes.toString(),
									AdminLdapLocale.class);
					this.adminService.deleteAdminLocales(deleteAdminLocaleList);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}

		buildAdminLocalesDataJson(jsonList, newlyAddedRecords);
		LOGGER.info("End: manageAdminLocaleDetails");
		return jsonList;
	}

	private void buildAdminRoleDetailsJson(List<Object> jsonList,
			List<AdminLdapRole> adminRolesList) {
		if (Util.listNotNull(adminRolesList)) {
			for (AdminLdapRole adminRole : adminRolesList) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, adminRole.getId());
				myMap.put(NAME, adminRole.getName());

				Integer[] privilegesArray = null;
				List<AdminLdapRolesPrivilegesMapping> rpmList = adminRole
						.getAdminLdapRolesPrivilegesMappings();
				if (rpmList != null) {
					for (int i = 0; i < rpmList.size(); i++) {
						AdminLdapRolesPrivilegesMapping mapping = rpmList
								.get(i);
						if (i == 0) {
							privilegesArray = new Integer[rpmList.size()];
						}
						if (mapping.getAdminPrivilege() != null) {
							privilegesArray[i] = mapping.getAdminPrivilege()
									.getId();
						}
					}
					myMap.put(PRIVILEGE, privilegesArray);
				}
				Util.convertMapToJson(myMap, jsonList);
			}
		}
	}

	private void buildAdminLocalesDataJson(List<Object> jsonList,
			List<AdminLdapLocale> adminLocalesList) {

		if (Util.listNotNull(adminLocalesList)) {
			for (AdminLdapLocale adminLocale : adminLocalesList) {
				Map<String, Object> myMap = new HashMap<String, Object>();
				myMap.put(ID, adminLocale.getId());
				myMap.put(NAME, adminLocale.getName());

				Integer[] organizationsArray = null;
				List<AdminLdapLocalesOrgMapping> orgList = adminLocale
						.getAdminLdapLocalesOrgMappings();
				if (orgList != null) {
					for (int i = 0; i < orgList.size(); i++) {
						AdminLdapLocalesOrgMapping mapping = orgList.get(i);
						if (i == 0) {
							organizationsArray = new Integer[orgList.size()];
						}
						if (mapping.getOrganizations() != null) {
							organizationsArray[i] = mapping.getOrganizations()
									.getId();
						}
					}
					myMap.put("organizations", organizationsArray);
				}
				Util.convertMapToJson(myMap, jsonList);
			}
		}
	}

}
