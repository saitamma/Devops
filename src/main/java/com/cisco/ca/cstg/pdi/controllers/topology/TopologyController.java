package com.cisco.ca.cstg.pdi.controllers.topology;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cisco.ca.cstg.pdi.exceptions.DataNotFoundException;
import com.cisco.ca.cstg.pdi.pojos.Infrastructure;
import com.cisco.ca.cstg.pdi.pojos.topology.Pod;
import com.cisco.ca.cstg.pdi.pojos.topology.TopologyJSONGenerator;
import com.cisco.ca.cstg.pdi.services.CommonUtilServices;
import com.cisco.ca.cstg.pdi.services.InfrastructureService;
import com.cisco.ca.cstg.pdi.services.TopologyService;
import com.cisco.ca.cstg.pdi.services.VisioRequestProcessor;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;

@Controller
@SessionAttributes("activeProjectId")
public class TopologyController implements Constants {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(TopologyController.class);

	@Autowired
	TopologyService topologyService;

	@Autowired
	InfrastructureService infrastructureService;

	@Autowired
	CommonUtilServices commonUtilService;

	@Autowired
	VisioRequestProcessor visioRequestProcessor;

	@RequestMapping(value = "/processTopologyGeneration.html", method = RequestMethod.POST)
	@ResponseBody
	public String processTopologyGeneration(HttpServletRequest httpServletRequest, ModelMap model,
			@RequestParam(value = "projectId") Integer topologyProjectId)
			throws IOException {
		model.addAttribute("activeProjectId", topologyProjectId);
		Map<String, Object> processTopologyStatus = new HashMap<String, Object>();
		TopologyJSONGenerator topologyJSONGenerator = new TopologyJSONGenerator();
		try {
			if(isMiniConfig(topologyProjectId)){
				processTopologyStatus.put(TOPOLOGY_ERROR_MSG_KEY,TOPOLOGY_MINI_ERROR_MSG);
				return Util.convertMapToJson(processTopologyStatus);
			}
			addPods(topologyJSONGenerator, topologyProjectId);
			LOGGER.info("JSON - " + topologyJSONGenerator.toJson());
			String jsonString = topologyJSONGenerator.toJson();
			String pods = jsonString.substring(
					jsonString.indexOf("pod_id") + 9,
					jsonString.indexOf("pod_count") - 3).trim();
			long siteid = topologyProjectId;
			Map<String, String> visioServeUrlsMap = Util
					.getVisioServerUrl(httpServletRequest);
			visioRequestProcessor.visioCall(siteid, jsonString, pods, visioServeUrlsMap);
			String status = visioRequestProcessor
					.validateTopologyResponseData(topologyProjectId);
			processTopologyStatus.put(TOPOLOGY_ERROR_MSG_KEY, status);

			return Util.convertMapToJson(processTopologyStatus);
		} 
		
		catch (DataNotFoundException e) {
				LOGGER.error(TOPOLOGY_VAL_ERROR_MSG, e);
				processTopologyStatus.put(TOPOLOGY_ERROR_MSG_KEY,
						TOPOLOGY_VAL_ERROR_MSG);
			}
		
		catch(Exception e) {
				LOGGER.error(
						"Error Occured while processing topology generation,",
						e);
				processTopologyStatus.put(TOPOLOGY_ERROR_MSG_KEY,
						TOPOLOGY_ERROR_MSG);
			}
		
		return Util.convertMapToJson(processTopologyStatus);
	}

	private boolean isMiniConfig(Integer projectId) {
		boolean isMiniConfig = false;
		try {
			List<Infrastructure> infrastructureData = infrastructureService.fetchInfrastructureDetails(projectId);
			if (Util.listNotNull(infrastructureData)) {
				for (Infrastructure inf : infrastructureData) {
					if(SERVERMODEL6324.equals(inf.getServerModel().getDescription())){
						isMiniConfig = true;
					}

				}
			}
		} catch (Exception e) {
			LOGGER.error("Error Occured while fetching FI detail,",e);
		}
		return isMiniConfig;
	}

	@RequestMapping(value = "/downloadTopologyData.html", method = RequestMethod.GET)
	@ResponseBody
	public void downloadTopologyData(
			@ModelAttribute(ACTIVE_PROJECT_ID) Integer projectId,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		LOGGER.info("Start - downloading topology zip file for project id: {}",
				projectId);
		if (projectId != null) {
			visioRequestProcessor.startDownloadTopologyZipFile(response,
					projectId);
		}
	}

	private void addPods(TopologyJSONGenerator topologyJSONGenerator,
			int projectId) throws DataNotFoundException{
		Pod pod = new Pod(projectId, topologyService, infrastructureService,
				commonUtilService);
		try {
			pod.init();
			topologyJSONGenerator.setPod(pod);
			topologyJSONGenerator.generateData();
		} catch (Exception e) {
			throw e;
		}
	}
}
