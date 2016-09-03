package com.cisco.ca.cstg.pdi.controllers.lan;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cisco.ca.cstg.pdi.pojos.LanEthernetAdapterPolicies;
import com.cisco.ca.cstg.pdi.pojos.Organizations;
import com.cisco.ca.cstg.pdi.services.LANService;
import com.cisco.ca.cstg.pdi.utils.TestConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
@WebAppConfiguration
public class EthernetAdapterPolicyControllerTest implements TestConstants {
	
	private MockMvc mockMvc;

	@Mock
	private LANService lanServiceMock;

	@InjectMocks
	private EthernetAdapterPolicyController ethernetAdapterPolicyController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(
				ethernetAdapterPolicyController).build();
	}

	@Test
	public void showLanEthernetAdapterPolicies() throws Exception {
		mockMvc.perform(get("/lanEthernetAdapterPolicy.html"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("lan/lanEthernetAdapterPolicy"));
	}

	@Test
	public void getLanEthernetAdapterPoliciesDetails_methodNameCheck()
			throws Exception {
		this.mockMvc.perform(
				get("/getLanEthernetAdapterPoliciesDetails.html").sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("getLanEthernetAdapterPoliciesDetails"));
	}

	@Test
	public void getLanEthernetAdapterPoliciesDetails_returnTypeCheck()
			throws Exception {
		LanEthernetAdapterPolicies lanEap = new LanEthernetAdapterPolicies(1);
		lanEap.setName("Test");
		lanEap.setDescription("desc");
		lanEap.setTransmitQueues(1);
		lanEap.setTransmitQueuesRingSize(256);
		lanEap.setReceiveQueues(1);
		lanEap.setReceiveQueuesRingSize(512);
		lanEap.setCompletionQueues(2);
		lanEap.setCompletionQueuesInterrupts(4);
		lanEap.setTransmitChecksumOffload("enabled");
		lanEap.setReceiveChecksumOffload("enabled");
		lanEap.setTcpSegmentationOffload("enabled");
		lanEap.setTcpLargeReceiveOffload("enabled");
		lanEap.setReceiveSideScaling("disabled");
		lanEap.setAcceleratedReceiveFlowSteering("disabled");
		lanEap.setFailbackTimeout(5);
		lanEap.setInterruptMode("msi_x");
		lanEap.setInterruptCoalescingType("min");
		lanEap.setInterruptTimer(125);
		lanEap.setOrganizations(new Organizations(1));
		List<LanEthernetAdapterPolicies> lanEapList = new ArrayList<>();
		lanEapList.add(lanEap);
		String expected = "[{\"interruptMode\":\"msi_x\",\"transmitQueues\":1,\"transmitQueuesRingSize\":256,\"interruptCoalescingType\":\"min\",\"leapDefault\":null,\"organizations\":1,\"tcpLargeReceiveOffload\":\"enabled\",\"interruptTimer\":125,\"tcpSegmentationOffload\":\"enabled\",\"receiveSideScaling\":\"disabled\",\"id\":1,\"receiveQueues\":1,\"completionQueuesInterrupts\":4,\"acceleratedReceiveFlowSteering\":\"disabled\",\"transmitChecksumOffload\":\"enabled\",\"failbackTimeout\":5,\"description\":\"desc\",\"receiveChecksumOffload\":\"enabled\",\"name\":\"Test\",\"completionQueues\":2,\"receiveQueuesRingSize\":512}]";
		when(lanServiceMock.fetchLanEthernetAdapterPolicies(PROJECT_ID))
				.thenReturn(lanEapList);
		List<Object> jsonList = ethernetAdapterPolicyController
				.getLanEthernetAdapterPoliciesDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(1)).fetchLanEthernetAdapterPolicies(
				PROJECT_ID);
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test
	public void getLanEthernetAdapterPoliciesDetails_nullCheck()
			throws Exception {
		String expected = "[]";
		List<Object> jsonList = ethernetAdapterPolicyController
				.getLanEthernetAdapterPoliciesDetails(null);
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(0)).fetchLanEthernetAdapterPolicies(
				anyInt());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test
	public void getLanEthernetAdapterPoliciesDetails_returnNullCheck()
			throws Exception {
		String expected = "[]";
		when(lanServiceMock.fetchLanEthernetAdapterPolicies(PROJECT_ID))
				.thenReturn(null);
		List<Object> jsonList = ethernetAdapterPolicyController
				.getLanEthernetAdapterPoliciesDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(1)).fetchLanEthernetAdapterPolicies(
				PROJECT_ID);
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test(expected = Exception.class)
	public void getLanEthernetAdapterPoliciesDetails_exception()
			throws Exception {
		doThrow(Exception.class).when(
				lanServiceMock.fetchLanEthernetAdapterPolicies(PROJECT_ID));
		ethernetAdapterPolicyController
				.getLanEthernetAdapterPoliciesDetails(PROJECT_ID);
		verify(lanServiceMock, times(1)).fetchLanEthernetAdapterPolicies(
				PROJECT_ID);
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test
	public void manageLanEthernetAdapterPolicies_methodNameCheck()
			throws Exception {
		mockMvc.perform(
				post("/manageLanEthernetAdapterPolicies.html").accept(
						MediaType.APPLICATION_JSON).sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("manageLanEthernetAdapterPolicies"));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanEthernetAdapterPolicies_returnTypeCheck_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		LanEthernetAdapterPolicies lanEap = new LanEthernetAdapterPolicies(1);
		lanEap.setName("Test");
		lanEap.setDescription("desc");
		lanEap.setTransmitQueues(1);
		lanEap.setTransmitQueuesRingSize(256);
		lanEap.setReceiveQueues(1);
		lanEap.setReceiveQueuesRingSize(512);
		lanEap.setCompletionQueues(2);
		lanEap.setCompletionQueuesInterrupts(4);
		lanEap.setTransmitChecksumOffload("enabled");
		lanEap.setReceiveChecksumOffload("enabled");
		lanEap.setTcpSegmentationOffload("enabled");
		lanEap.setTcpLargeReceiveOffload("enabled");
		lanEap.setReceiveSideScaling("disabled");
		lanEap.setAcceleratedReceiveFlowSteering("disabled");
		lanEap.setFailbackTimeout(5);
		lanEap.setInterruptMode("msi_x");
		lanEap.setInterruptCoalescingType("min");
		lanEap.setInterruptTimer(125);
		lanEap.setOrganizations(new Organizations(1));
		List<LanEthernetAdapterPolicies> lanEapList = new ArrayList<>();
		lanEapList.add(lanEap);
		when(
				lanServiceMock.saveOrUpdateLanEthernetAdapterPolicies(
						anyList(), anyInt())).thenReturn(lanEapList);
		List<Object> jsonList = ethernetAdapterPolicyController
				.manageLanEthernetAdapterPolicies(json, PROJECT_ID);
		String expected = "[{\"interruptMode\":\"msi_x\",\"transmitQueues\":1,\"transmitQueuesRingSize\":256,\"interruptCoalescingType\":\"min\",\"leapDefault\":null,\"organizations\":1,\"tcpLargeReceiveOffload\":\"enabled\",\"interruptTimer\":125,\"tcpSegmentationOffload\":\"enabled\",\"receiveSideScaling\":\"disabled\",\"id\":1,\"receiveQueues\":1,\"completionQueuesInterrupts\":4,\"acceleratedReceiveFlowSteering\":\"disabled\",\"transmitChecksumOffload\":\"enabled\",\"failbackTimeout\":5,\"description\":\"desc\",\"receiveChecksumOffload\":\"enabled\",\"name\":\"Test\",\"completionQueues\":2,\"receiveQueuesRingSize\":512}]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(1))
				.saveOrUpdateLanEthernetAdapterPolicies(anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanEthernetAdapterPolicies(
				anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanEthernetAdapterPolicies_nullCheck_firstParameter()
			throws Exception {
		List<Object> jsonList = ethernetAdapterPolicyController
				.manageLanEthernetAdapterPolicies(null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(0))
				.saveOrUpdateLanEthernetAdapterPolicies(anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanEthernetAdapterPolicies(
				anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanEthernetAdapterPolicies_nullCheck_secondParameter()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		List<Object> jsonList = ethernetAdapterPolicyController
				.manageLanEthernetAdapterPolicies(json, null);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(0))
				.saveOrUpdateLanEthernetAdapterPolicies(anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanEthernetAdapterPolicies(
				anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanEthernetAdapterPolicies_nullCheck_bothParameters()
			throws Exception {
		List<Object> jsonList = ethernetAdapterPolicyController
				.manageLanEthernetAdapterPolicies(null, null);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(0))
				.saveOrUpdateLanEthernetAdapterPolicies(anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanEthernetAdapterPolicies(
				anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanEthernetAdapterPolicies_returnTypeCheck_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[{\"id\":1}]}";
		doNothing().when(lanServiceMock).deleteLanEthernetAdapterPolicies(
				anyList());
		List<Object> jsonList = ethernetAdapterPolicyController
				.manageLanEthernetAdapterPolicies(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(0))
				.saveOrUpdateLanEthernetAdapterPolicies(anyList(), anyInt());
		verify(lanServiceMock, times(1)).deleteLanEthernetAdapterPolicies(
				anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void manageLanEthernetAdapterPolicies_returnNullCheck()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		when(
				lanServiceMock.saveOrUpdateLanEthernetAdapterPolicies(
						anyList(), anyInt())).thenReturn(null);
		List<Object> lanEapList = ethernetAdapterPolicyController
				.manageLanEthernetAdapterPolicies(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, lanEapList.toString());
		verify(lanServiceMock, times(1))
				.saveOrUpdateLanEthernetAdapterPolicies(anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanEthernetAdapterPolicies(
				anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageLanEthernetAdapterPolicies_exception_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[\"id\":1}], \"deleted\":[]}";
		when(
				lanServiceMock.saveOrUpdateLanEthernetAdapterPolicies(
						anyList(), PROJECT_ID)).thenThrow(
				new NullPointerException());
		ethernetAdapterPolicyController.manageLanEthernetAdapterPolicies(json,
				PROJECT_ID);
		verify(lanServiceMock, times(1))
				.saveOrUpdateLanEthernetAdapterPolicies(anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanEthernetAdapterPolicies(
				anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void manageLanEthernetAdapterPolicies_exception_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[1, 2]}";
		doThrow(new NullPointerException()).when(lanServiceMock)
				.deleteLanEthernetAdapterPolicies(anyList());
		ethernetAdapterPolicyController.manageLanEthernetAdapterPolicies(json,
				PROJECT_ID);
		verify(lanServiceMock, times(0))
				.saveOrUpdateLanEthernetAdapterPolicies(anyList(), anyInt());
		verify(lanServiceMock, times(1)).deleteLanEthernetAdapterPolicies(
				anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}
}
