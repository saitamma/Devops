package com.cisco.ca.cstg.pdi.controllers.topology;

import static com.cisco.ca.cstg.pdi.utils.TestConstants.PROJECT_ID;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.cisco.ca.cstg.pdi.pojos.Infrastructure;
import com.cisco.ca.cstg.pdi.pojos.ServerModel;
import com.cisco.ca.cstg.pdi.pojos.topology.TopologyJSONGenerator;
import com.cisco.ca.cstg.pdi.services.CommonUtilServices;
import com.cisco.ca.cstg.pdi.services.InfrastructureService;
import com.cisco.ca.cstg.pdi.services.TopologyService;
import com.cisco.ca.cstg.pdi.services.VisioRequestProcessor;


/**
 * The Class TopologyControllerTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
public class TopologyControllerTest {

	/** The mock mvc. */
	private MockMvc mockMvc;
	
	/** The req. */
	@Mock
	private HttpServletRequest req;
	
	/** The modelmap. */
	@Mock
	private ModelMap modelmap;
	
	/** The topology service. */
	@Mock
	private TopologyService topologyService;
	
	/** The infrastructure service. */
	@Mock
	private InfrastructureService infrastructureService;
	
	/** The topology controller. */
	@InjectMocks
	private TopologyController topologyController;
	
	/** The topology. */
	@Mock
	private TopologyController topology;
	
	/** The response. */
	@Mock
	private HttpServletResponse response;
	
	/** The common util services. */
	@Mock
	private CommonUtilServices commonUtilServices;
	
	/** The topology json generator. */
	@Mock
	private TopologyJSONGenerator topologyJSONGenerator;
	
	/** The visio request processor. */
	@Mock
	private VisioRequestProcessor visioRequestProcessor;
	
	
/**
 * Sets the up.
 */
@Before	
public void setUp() {
	MockitoAnnotations.initMocks(this);
	mockMvc= MockMvcBuilders.standaloneSetup(topologyController).build();
}

/**
 * Process topology generation_method name check.
 *
 * @throws Exception the exception
 */
@Test
public void processTopologyGeneration_methodNameCheck() throws Exception {
	mockMvc.perform(post("/processTopologyGeneration.html").sessionAttr("projectId", PROJECT_ID)).andExpect(handler().methodName("processTopologyGeneration"));
	verifyNoMoreInteractions(topologyService);
	
}

/**
 * Process topology generation_return type check_infrastructure server model.
 *
 * @throws Exception the exception
 */
@Test
public void processTopologyGeneration_returnTypeCheck_infrastructureServerModel() throws Exception{
	List<Infrastructure> infrastructures =new ArrayList<Infrastructure>();
	Infrastructure infrastructure=new Infrastructure();
	ServerModel serverModel=new ServerModel();
	serverModel.setDescription("Cisco UCS 6324");
	infrastructure.setServerModel(serverModel);
	infrastructures.add(infrastructure);
	when(infrastructureService.fetchInfrastructureDetails(Mockito.anyInt())).thenReturn(infrastructures);
    String actual = topologyController.processTopologyGeneration(req, modelmap, PROJECT_ID);
    String expected ="{\"Topology_Status\":\"We are not supporting topology generation for \"UCS-Mini\" configuration.\"}";
	Assert.assertNotSame(expected, actual);
	
    
}

/**
 * Process topology generation_return type check_without infrastructure server model.
 *
 * @throws Exception the exception
 *//*
@SuppressWarnings("unchecked")
@Test
public void processTopologyGeneration_returnTypeCheck_withoutInfrastructureServerModel() throws Exception {
	    Mockito.doReturn(topologyJSONGenerator).when(topGenerator);
		List<Infrastructure> infrastructures =new ArrayList<Infrastructure>();
		Infrastructure infrastructure=new Infrastructure();
		ServerModel serverModel=new ServerModel();
		infrastructure.setServerModel(serverModel);
		infrastructures.add(infrastructure);
		when(infrastructureService.fetchInfrastructureDetails(Mockito.anyInt())).thenReturn(infrastructures);
		doNothing().when(topologyJSONGenerator).generateData();
		when(topologyJSONGenerator.toJson()).thenReturn("pod_id is a very good pod_count ");
		doNothing().when(visioRequestProcessor).visioCall(Mockito.anyLong(), Mockito.anyString(), Mockito.anyString(), anyMap());
		when(visioRequestProcessor.validateTopologyResponseData(Mockito.anyInt())).thenReturn("success");
	    String actual = topologyController.processTopologyGeneration(req, modelmap, PROJECT_ID);
	    String expected ="{\"Topology_Status\":\"success\"}";
	    assertEquals(expected, actual);
}*/

/**
 * Process topology generation_return type_with exception.
 *
 * @throws Exception the exception
 */
@Test
public void processTopologyGeneration_returnType_withException() throws Exception {
	List<Infrastructure> infrastructures =new ArrayList<Infrastructure>();
	Infrastructure infrastructure=new Infrastructure();
	ServerModel serverModel=new ServerModel();
	serverModel.setDescription("Cisco UCS 6325");
	infrastructure.setServerModel(serverModel);
	infrastructures.add(infrastructure);
	when(infrastructureService.fetchInfrastructureDetails(Mockito.anyInt())).thenReturn(infrastructures);
    String actual = topologyController.processTopologyGeneration(req, modelmap, PROJECT_ID);
    String expected ="{\"Topology_Status\":\"Please first configure server ports before generate topology\"}";
	assertEquals(expected, actual);	
}


/**
 * Download topology data_methodname check.
 *
 * @throws Exception the exception
 */
@Test
public void downloadTopologyData_methodnameCheck() throws Exception{
	mockMvc.perform(get("/downloadTopologyData.html").sessionAttr("activeProjectId", PROJECT_ID)).andExpect(handler().methodName("downloadTopologyData"));
	
}

/**
 * Download topology data_with null check.
 *
 * @throws Exception the exception
 */
@Test
public void downloadTopologyData_withNullCheck() throws Exception{
	topologyController.downloadTopologyData(null, req, response);
}

/**
 * Download topology data_with out null check.
 *
 * @throws Exception the exception
 */
@Test
public void downloadTopologyData_withOutNullCheck() throws Exception{
	topologyController.downloadTopologyData(PROJECT_ID, req, response);
	
	
}
	
}
