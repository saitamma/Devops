package com.cisco.ca.cstg.pdi.controllers.equipment;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cisco.ca.cstg.pdi.services.EquipmentService;
import com.cisco.ca.cstg.pdi.utils.TestConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-config.xml")
@WebAppConfiguration
public class EquipmentControllerTest implements TestConstants {
	private MockMvc mockMvc;	

	@Mock
	private EquipmentService equipmentServiceMock;

	@InjectMocks
	private EquipmentController equipmentController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(equipmentController).build();
	}

	@Test
	public void showEquipment() throws Exception {
		mockMvc.perform(
				get("/equipment.html").sessionAttr("activeProjectId",
						PROJECT_ID)).andExpect(status().isOk())
				.andExpect(forwardedUrl("equipment/equipmentMain"));
	}
}
