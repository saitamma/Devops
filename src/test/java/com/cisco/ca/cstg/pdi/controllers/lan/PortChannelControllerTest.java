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

import com.cisco.ca.cstg.pdi.pojos.EquipmentUplink;
import com.cisco.ca.cstg.pdi.pojos.LanPortchannel;
import com.cisco.ca.cstg.pdi.services.LANService;
import com.cisco.ca.cstg.pdi.utils.TestConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
@WebAppConfiguration
public class PortChannelControllerTest implements TestConstants {
	
	private MockMvc mockMvc;

	@Mock
	private LANService lanServiceMock;

	@InjectMocks
	private PortChannelController portChannelController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(portChannelController)
				.build();
	}

	@Test
	public void showPortChannel() throws Exception {
		mockMvc.perform(get("/lanPortChannelConfig.html"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("lan/lanPortChannelConfig"));
	}

	@Test
	public void getPortChannelConfigDetails_methodNameCheck() throws Exception {
		this.mockMvc.perform(
				get("/getPortChannelConfigDetails.html").sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("getPortChannelConfigDetails"));
	}

	@Test
	public void getPortChannelConfigDetails_returnTypeCheck() throws Exception {
		LanPortchannel lanPortchannel = new LanPortchannel();
		lanPortchannel.setId(1);
		lanPortchannel.setFiId("B");
		lanPortchannel.setFiName("TEST");
		lanPortchannel.setEquipmentUplink(new EquipmentUplink(1));
		lanPortchannel.getEquipmentUplink().setPortId(2);
		lanPortchannel.getEquipmentUplink().setSlotId(3);
		List<LanPortchannel> lanPortchannelList = new ArrayList<>();
		lanPortchannelList.add(lanPortchannel);
		String expected = "[{\"A\":[],\"B\":[\"{\\\"equipmentUplink\\\":1,\\\"id\\\":1,\\\"fiName\\\":\\\"TEST\\\",\\\"description\\\":null,\\\"fiId\\\":\\\"B\\\",\\\"slotId\\\":3,\\\"portId\\\":2}\"]}]";
		when(lanServiceMock.fetchLanPortChannelConfiguration(PROJECT_ID))
				.thenReturn(lanPortchannelList);
		List<Object> jsonList = portChannelController
				.getPortChannelConfigDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(1)).fetchLanPortChannelConfiguration(
				PROJECT_ID);
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test
	public void getPortChannelConfigDetails_nullCheck() throws Exception {
		String expected = "[{\"A\":[],\"B\":[]}]";
		List<Object> jsonList = portChannelController
				.getPortChannelConfigDetails(null);
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(0)).fetchLanPortChannelConfiguration(
				anyInt());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test
	public void getPortChannelConfigDetails_returnNullCheck() throws Exception {
		String expected = "[{\"A\":[],\"B\":[]}]";
		when(lanServiceMock.fetchLanPortChannelConfiguration(PROJECT_ID))
				.thenReturn(null);
		List<Object> jsonList = portChannelController
				.getPortChannelConfigDetails(PROJECT_ID);
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(1)).fetchLanPortChannelConfiguration(
				PROJECT_ID);
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test(expected = Exception.class)
	public void getPortChannelConfigDetails_exception() throws Exception {
		doThrow(Exception.class).when(
				lanServiceMock.fetchLanPortChannelConfiguration(PROJECT_ID));
		portChannelController.getPortChannelConfigDetails(PROJECT_ID);
		verify(lanServiceMock, times(1)).fetchLanPortChannelConfiguration(
				PROJECT_ID);
		verifyNoMoreInteractions(lanServiceMock);
	}

	@Test
	public void managePortChannelConfig_methodNameCheck() throws Exception {
		mockMvc.perform(
				post("/managePortChannelConfig.html").accept(
						MediaType.APPLICATION_JSON).sessionAttr(
						"activeProjectId", PROJECT_ID)).andExpect(
				handler().methodName("managePortChannelConfig"));
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void managePortChannelConfig_returnTypeCheck_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"fiName\":\"TEST\",\"fiId\":\"B\",\"slotId\":3,\"portId\":2}], \"deleted\":[]}";
		LanPortchannel lanPortchannel = new LanPortchannel();
		lanPortchannel.setId(1);
		lanPortchannel.setFiId("B");
		lanPortchannel.setFiName("TEST");
		lanPortchannel.setEquipmentUplink(new EquipmentUplink(1));
		lanPortchannel.getEquipmentUplink().setPortId(2);
		lanPortchannel.getEquipmentUplink().setSlotId(3);
		List<LanPortchannel> lanPortchannelList = new ArrayList<>();
		lanPortchannelList.add(lanPortchannel);
		when(
				lanServiceMock.saveOrUpdateLanPortChannelConfiguration(
						anyList(), anyInt())).thenReturn(lanPortchannelList);
		List<Object> jsonList = portChannelController.managePortChannelConfig(
				json, PROJECT_ID);
		String expected = "[{\"equipmentUplink\":1,\"id\":1,\"fiName\":\"TEST\",\"description\":null,\"fiId\":\"B\",\"slotId\":3,\"portId\":2}]";
		assertEquals(expected, jsonList.toString());
		verify(lanServiceMock, times(1))
				.saveOrUpdateLanPortChannelConfiguration(anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanPortChannel(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void managePortChannelConfig_returnTypeCheck_deleteData()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[{\"id\":1,\"fiName\":\"TEST\",\"fiId\":\"B\",\"slotId\":3,\"portId\":2}]}";
		doNothing().when(lanServiceMock).deleteLanPortChannel(anyList());
		List<Object> lanPortchannelList = portChannelController
				.managePortChannelConfig(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, lanPortchannelList.toString());
		verify(lanServiceMock, times(0))
				.saveOrUpdateLanPortChannelConfiguration(anyList(), anyInt());
		verify(lanServiceMock, times(1)).deleteLanPortChannel(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void managePortChannelConfig_returnTypeCheck_nullCheck_firstParameter()
			throws Exception {
		List<Object> lanPortchannelList = portChannelController
				.managePortChannelConfig(null, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, lanPortchannelList.toString());
		verify(lanServiceMock, times(0))
				.saveOrUpdateLanPortChannelConfiguration(anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanPortChannel(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void managePortChannelConfig_returnTypeCheck_nullCheck_secondParameter()
			throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[{\"id\":1,\"fiName\":\"TEST\",\"fiId\":\"B\",\"slotId\":3,\"portId\":2}]}";
		List<Object> lanPortchannelList = portChannelController
				.managePortChannelConfig(json, null);
		String expected = "[]";
		assertEquals(expected, lanPortchannelList.toString());
		verify(lanServiceMock, times(0))
				.saveOrUpdateLanPortChannelConfiguration(anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanPortChannel(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void managePortChannelConfig_returnTypeCheck_nullCheck_bothParameters()
			throws Exception {
		List<Object> lanPortchannelList = portChannelController
				.managePortChannelConfig(null, null);
		String expected = "[]";
		assertEquals(expected, lanPortchannelList.toString());
		verify(lanServiceMock, times(0))
				.saveOrUpdateLanPortChannelConfiguration(anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanPortChannel(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void managePortChannelConfig_returnNullCheck() throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1}], \"deleted\":[]}";
		when(
				lanServiceMock.saveOrUpdateLanPortChannelConfiguration(
						anyList(), anyInt())).thenReturn(null);
		List<Object> lanPortchannelList = portChannelController
				.managePortChannelConfig(json, PROJECT_ID);
		String expected = "[]";
		assertEquals(expected, lanPortchannelList.toString());
		verify(lanServiceMock, times(1))
				.saveOrUpdateLanPortChannelConfiguration(anyList(), anyInt());
		verify(lanServiceMock, times(0)).deleteLanPortChannel(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void managePortChannelConfig_exception_addOrUpdateData()
			throws Exception {
		String json = "{\"addOrUpdate\":[{\"id\":1,\"fiName\":\"TEST\",\"fiId\":\"B\",\"slotId\":3,\"portId\":2}], \"deleted\":[]}";
		when(
				lanServiceMock.saveOrUpdateLanPortChannelConfiguration(
						anyList(), PROJECT_ID)).thenThrow(
				new NullPointerException());
		portChannelController.managePortChannelConfig(json, PROJECT_ID);
		verify(lanServiceMock, times(1))
				.saveOrUpdateLanPortChannelConfiguration(anyList(), PROJECT_ID);
		verify(lanServiceMock, times(0)).deleteLanPortChannel(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void managePortChannelConfig_exception_deleteData() throws Exception {
		String json = "{\"addOrUpdate\":[], \"deleted\":[{\"id\":1,\"fiName\":\"TEST\",\"fiId\":\"B\",\"slotId\":3,\"portId\":2}]}";
		doThrow(new NullPointerException()).when(lanServiceMock)
				.deleteLanPortChannel(anyList());
		portChannelController.managePortChannelConfig(json, PROJECT_ID);
		verify(lanServiceMock, times(0))
				.saveOrUpdateLanPortChannelConfiguration(anyList(), anyInt());
		verify(lanServiceMock, times(1)).deleteLanPortChannel(anyList());
		verifyNoMoreInteractions(lanServiceMock);
	}

}
