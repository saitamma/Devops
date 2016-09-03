package com.cisco.ca.cstg.pdi.ucsmapper.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cisco.ca.cstg.pdi.dao.CommonDaoServicesImpl;
import com.cisco.ca.cstg.pdi.pojos.EquipmentFcport;
import com.cisco.ca.cstg.pdi.pojos.EquipmentUplink;
import com.cisco.ca.cstg.pdi.pojos.InfrastructureEthernetFCMode;
import com.cisco.ca.cstg.pdi.pojos.LanPortchannel;
import com.cisco.ca.cstg.pdi.pojos.LanVlan;
import com.cisco.ca.cstg.pdi.pojos.SanVsan;
import com.cisco.ca.cstg.pdi.pojos.XmlGenProjectDetails;
import com.cisco.ca.cstg.pdi.utils.Util;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.FabricEp;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.FabricEthLan;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.FabricEthLanEp;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.FabricEthLanPc;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.FabricEthLanPcEp;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.FabricFcSan;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.FabricFcSanEp;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.FabricFcVsanPortEp;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.FabricLanCloud;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.FabricSanCloud;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.FabricVlan;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.FabricVsan;
import com.cisco.ca.cstg.ucsxmlobjfactory.pojos.ObjectFactory;

@Service("generateXMLForFabricEp")
public class GenerateXMLForFabricEp extends CommonDaoServicesImpl {

	private static final String RAWTYPES = "rawtypes";
	private static final String DEFAULT = "default";
	private static final String ENABLED = "enabled";
	private static final String A = "A";
	private static final String B = "B";
	private ObjectFactory factory;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(GenerateXMLForFabricEp.class);
	
	public GenerateXMLForFabricEp() {
		this.factory = new ObjectFactory();
	}

	public void addFabricLanCloud(FabricEp fabricEp,
			XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForFabricEp : addFabricEthEstcCloudVlan: "
				+ project);
		FabricLanCloud fabricLanCloud = null;
		FabricVlan fabricVlanDefault = null;
		List<LanVlan> vlanList = project.getLanVlans();

		for (Serializable s : fabricEp.getContent()) {
			if (!(s instanceof String)) {
				@SuppressWarnings(RAWTYPES)
				Object obj = ((JAXBElement) s).getValue();
				if (obj instanceof FabricLanCloud) {
					fabricLanCloud = (FabricLanCloud) obj;
					// Set LanCloud Mode (Ethernet Mode)
					fabricLanCloud = (FabricLanCloud) setEthernetOrFcMode(project, null, fabricLanCloud);
					break;
				}
			}
		}
		
		if(fabricLanCloud != null && fabricLanCloud.getContent() != null){
			for (Serializable s : fabricLanCloud.getContent()) {
				if (!(s instanceof String)) {
					@SuppressWarnings(RAWTYPES)
					Object obj = ((JAXBElement) s).getValue();
					if (obj instanceof FabricVlan) {
						fabricVlanDefault = (FabricVlan) obj;
						break;
					}
				}
			}
		}
		
		addVlanList(fabricLanCloud, fabricVlanDefault, vlanList);
		LOGGER.info("End : GenerateXMLForFabricEp : addFabricEthEstcCloudVlan: "
				+ project);
	}

	private void addVlanList(FabricLanCloud fabricLanCloud,
			FabricVlan fabricVlanDefault, List<LanVlan> vlanList) {
		LOGGER.info("Start : GenerateXMLForFabricEp : addVlanList");

		if (!Util.listNotNull(vlanList)) {
			return;
		}
		for (LanVlan vlan : vlanList) {
			if (fabricLanCloud != null) {
				String name = vlan.getVlanName();
				if (!name.equalsIgnoreCase(DEFAULT)) {
					FabricVlan fabricVlan = factory.createFabricVlan();
					fabricVlan.setId(vlan.getVlanId());
					fabricVlan.setName(vlan.getVlanName());

					JAXBElement<FabricVlan> fabricVlanjaxb = factory
							.createFabricVlan(fabricVlan);
					fabricLanCloud.getContent().add(fabricVlanjaxb);
					LOGGER.debug("Added LanVlan - FabricVlan for {}", name);
				} else {
					fabricVlanDefault.setId(vlan.getVlanId());
					LOGGER.debug("Updated Default LanVlan - FabricVlan for {}",
							name);
				}
			}
		}
		LOGGER.info("End : GenerateXMLForFabricEp : addVlanList");
	}

	public void addFabricSanCloud(FabricEp fabricEp,
			XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForFabricEp : addFabricSanCloud: "
				+ project);
		FabricSanCloud fabricSanCloud = null;
		FabricFcSan fabricFcSanA = null;
		FabricFcSan fabricFcSanB = null;

		for (Serializable s : fabricEp.getContent()) {
			if (!(s instanceof String)) {
				@SuppressWarnings(RAWTYPES)
				Object obj = ((JAXBElement) s).getValue();
				if (obj instanceof FabricSanCloud) {
					fabricSanCloud = (FabricSanCloud) obj;
					// Set SanCloud Mode (FC Mode)
					fabricSanCloud = (FabricSanCloud) setEthernetOrFcMode(project, fabricSanCloud, null);
				}
			}
		}
		if (fabricSanCloud != null) {
			for (Serializable s : fabricSanCloud.getContent()) {
				if (!(s instanceof String)) {
					@SuppressWarnings(RAWTYPES)
					Object obj = ((JAXBElement) s).getValue();
					if (obj instanceof FabricFcSan) {
						if(A.equalsIgnoreCase(((FabricFcSan) obj).getId())) {
							fabricFcSanA = (FabricFcSan) obj;
						} else if(B.equalsIgnoreCase(((FabricFcSan) obj).getId())){
							fabricFcSanB = (FabricFcSan) obj;
						}
					}
				}
			}
		}
		addVsanList(fabricSanCloud, fabricFcSanA, fabricFcSanB, project);
		LOGGER.info("End : GenerateXMLForFabricEp : addFabricSanCloud: "
				+ project);
	}

	private Object setEthernetOrFcMode(XmlGenProjectDetails project, FabricSanCloud fabricSanCloud, FabricLanCloud fabricLanCloud) {
		List<InfrastructureEthernetFCMode> infrastructureEthernetFCMode = project.getInfrastructureEthernetFCMode();
		if(infrastructureEthernetFCMode != null && infrastructureEthernetFCMode.size() > 0){
			if(fabricSanCloud != null){
				fabricSanCloud.setMode(infrastructureEthernetFCMode.get(0).getFcMode());
				return fabricSanCloud;
			} else if (fabricLanCloud != null){
				fabricLanCloud.setMode(infrastructureEthernetFCMode.get(0).getEthernetMode());
				return fabricLanCloud;
			}
		}
		return null;
	}

	private FabricVsan captureDefaultVsan(FabricSanCloud fabricSanCloud) {
		LOGGER.info("Start : GenerateXMLForFabricEp : captureDefaultVsan");
		for (Serializable s : fabricSanCloud.getContent()) {
			if (!(s instanceof String)) {
				@SuppressWarnings(RAWTYPES)
				Object obj = ((JAXBElement) s).getValue();
				if (obj instanceof FabricVsan) {
					return (FabricVsan) obj;
				}
			}
		}
		LOGGER.info("End : GenerateXMLForFabricEp : captureDefaultVsan");
		return null;

	}

	private void addVsanList(FabricSanCloud fabricSanCloud,
			FabricFcSan fabricFcSanA, FabricFcSan fabricFcSanB,
			XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForFabricEp : addVsanList: " + project);
		List<SanVsan> vsanList = project.getSanVsans();

		if (!Util.listNotNull(vsanList)) {
			return;
		}

		FabricVsan fabricVsanDefault = captureDefaultVsan(fabricSanCloud);

		if (fabricFcSanA != null && fabricFcSanB != null) {

			for (SanVsan vsan : vsanList) {
				String name = vsan.getVsanName();
				if (!name.equalsIgnoreCase(DEFAULT)) {
					FabricVsan fabricVsan = factory.createFabricVsan();
					fabricVsan.setId(Integer.toString(vsan.getFcoeVsanId()));
					fabricVsan.setFcoeVlan(vsan.getFcoeVlanName());
					fabricVsan.setName(vsan.getVsanName());

					JAXBElement<FabricVsan> fabricVsanjaxb = factory
							.createFabricVsan(fabricVsan);
					if (vsan.getFiId() != null) {
						if (A.equalsIgnoreCase(vsan.getFiId())) {
							fabricFcSanA.getContent().add(fabricVsanjaxb);
						} else {
							fabricFcSanB.getContent().add(fabricVsanjaxb);
						}
					} else {
						fabricSanCloud.getContent().add(fabricVsanjaxb);
					}
					LOGGER.debug("Added SanVsan - FabricVsan for {}", name);
				} else {
					fabricVsanDefault.setId(Integer.toString(vsan
							.getFcoeVsanId()));
					fabricVsanDefault.setFcoeVlan(vsan.getFcoeVlanName());
					LOGGER.debug("Updated Default SanVsan - FabricVsan for {}",
							name);
				}
			}
		}
		LOGGER.info("End : GenerateXMLForFabricEp : addVsanList: " + project);
	}

	public void addFabricEthLanUplinkPorts(FabricEp fabricEp,
			XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForFabricEp : addFabricEthLanUplinkPorts: "
				+ project);
		FabricLanCloud fabricLanCloud = null;
		FabricEthLan fabricEthLanA = null;
		FabricEthLan fabricEthLanB = null;
		List<EquipmentUplink> uplinkportlist = project.getEquipmentUplinks();		
		List<LanPortchannel> portchannellist = project.getLanPortchannels();

		if (!Util.listNotNull(uplinkportlist)) {
			return;
		}

		for (Serializable s : fabricEp.getContent()) {
			if (!(s instanceof String)) {
				@SuppressWarnings(RAWTYPES)
				Object obj = ((JAXBElement) s).getValue();
				if (obj instanceof FabricLanCloud) {
					fabricLanCloud = (FabricLanCloud) obj;
					break;
				}
			}
		}
		if (fabricLanCloud != null) {
			for (Serializable s : fabricLanCloud.getContent()) {
				if (!(s instanceof String)) {
					@SuppressWarnings(RAWTYPES)
					Object obj = ((JAXBElement) s).getValue();
					if (obj instanceof FabricEthLan) {
						if (A.equalsIgnoreCase(((FabricEthLan) obj).getId())) {
							fabricEthLanA = (FabricEthLan) obj;
						} else {
							fabricEthLanB = (FabricEthLan) obj;
						}
					}
				}
			}
		}
		addUplinkPort(fabricEthLanA, fabricEthLanB, uplinkportlist,
				portchannellist);
		LOGGER.info("End : GenerateXMLForFabricEp : addFabricEthLanUplinkPorts: "
				+ project);
	}

	private void addUplinkPort(FabricEthLan fabricEthLanA,
			FabricEthLan fabricEthLanB, List<EquipmentUplink> uplinkportlist,
			List<LanPortchannel> portchannellist) {
		LOGGER.info("Start : GenerateXMLForFabricEp : addUplinkPort: ");
		if (fabricEthLanA != null && fabricEthLanB != null) {
			for (EquipmentUplink server : uplinkportlist) {
				boolean presentUplinkPort = false;

				for (LanPortchannel portchannell : portchannellist) {

					if (server.getPortId().equals(
							portchannell.getEquipmentUplink().getPortId())
							&& server.getSlotId().equals(
									portchannell.getEquipmentUplink()
											.getSlotId())
							&& server.getFiId().equals(portchannell.getFiId())) {
						presentUplinkPort = true;
						break;
					}
				}
				if (presentUplinkPort) {
					continue;
				} else {
					FabricEthLanEp fabricEthLanEp = factory
							.createFabricEthLanEp();
					LOGGER.debug(
							"Added EquipmentUplink - FabricEthLanEp for {}",
							server.getUserLabel());

					fabricEthLanEp.setPortId((server.getPortId().toString()));
					fabricEthLanEp.setSlotId((server.getSlotId()).toString());
					fabricEthLanEp.setUsrLbl((Util.isStringNotEmpty(server
							.getUserLabel())) ? server.getUserLabel() : "");

					JAXBElement<FabricEthLanEp> fabricEthLanEpjaxb = factory
							.createFabricEthLanEp(fabricEthLanEp);
					if (A.equalsIgnoreCase(server.getFiId())) {
						fabricEthLanA.getContent().add(fabricEthLanEpjaxb);
					} else {
						fabricEthLanB.getContent().add(fabricEthLanEpjaxb);
					}
				}
			}
		}
		LOGGER.info("End : GenerateXMLForFabricEp : addUplinkPort: ");
	}

	public void addFabricEthLanPortChannels(FabricEp fabricEp,
			XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForFabricEp : addFabricEthLanPortChannels: "
				+ project);
		FabricEthLan fabricEthLanA = null;
		FabricEthLan fabricEthLanB = null;
		FabricLanCloud fabricLanCloud = null;

		for (Serializable s : fabricEp.getContent()) {
			if (!(s instanceof String)) {
				@SuppressWarnings(RAWTYPES)
				Object obj = ((JAXBElement) s).getValue();
				if (obj instanceof FabricLanCloud) {
					fabricLanCloud = (FabricLanCloud) obj;
					break;
				}
			}
		}
		if (fabricLanCloud != null) {
			for (Serializable s : fabricLanCloud.getContent()) {
				if (!(s instanceof String)) {
					@SuppressWarnings(RAWTYPES)
					Object obj = ((JAXBElement) s).getValue();
					if (obj instanceof FabricEthLan) {
						if (B.equalsIgnoreCase(((FabricEthLan) obj).getId())) {
							fabricEthLanB = (FabricEthLan) obj;
						} else if (A.equalsIgnoreCase(((FabricEthLan) obj).getId())) {
							fabricEthLanA = (FabricEthLan) obj;
						}
					}
				}
			}
		}
		List<LanPortchannel> portchannellistA = new ArrayList<>();
		List<LanPortchannel> portchannellistB = new ArrayList<>();
		List<LanPortchannel> lanPortchannels = project.getLanPortchannels();

		for (LanPortchannel lanPortchannel : lanPortchannels) {
			if (A.equals(lanPortchannel.getFiId())) {
				portchannellistA.add(lanPortchannel);
			} else {
				portchannellistB.add(lanPortchannel);
			}
		}
		Map<String, List<String>> pcNamesA = new HashMap<String, List<String>>();
		Map<String, List<String>> pcNamesB = new HashMap<String, List<String>>();

		if ((portchannellistA == null || portchannellistA.isEmpty())
				&& (portchannellistB == null || portchannellistB.isEmpty())) {
			return;
		}

		addPortChannel(portchannellistA, pcNamesA);
		addPortChannel(portchannellistB, pcNamesB);
		LOGGER.debug("Added Port channels.");

		addPortChannelList(pcNamesA, fabricEthLanA, portchannellistA);
		addPortChannelList(pcNamesB, fabricEthLanB, portchannellistB);

		LOGGER.info("End : GenerateXMLForFabricEp : addFabricEthLanPortChannels: "
				+ project);
	}

	private void addPortChannel(List<LanPortchannel> portchannellistA,
			Map<String, List<String>> pcNames) {
		LOGGER.info("Start : GenerateXMLForFabricEp : addPortChannel: ");
		for (LanPortchannel portchannellB : portchannellistA) {
			List<String> portList = new ArrayList<String>();

			if (pcNames.size() == 0) {
				portList.add(portchannellB.getEquipmentUplink().getPortId()
						.toString());
				pcNames.put(portchannellB.getFiName(), portList);
			} else {
				if (pcNames.get(portchannellB.getFiName()) != null) {
					portList = pcNames.get(portchannellB.getFiName());
					portList.add(portchannellB.getEquipmentUplink().getPortId()
							.toString());
					pcNames.put(portchannellB.getFiName(), portList);

				} else {
					portList.add(portchannellB.getEquipmentUplink().getPortId()
							.toString());
					pcNames.put(portchannellB.getFiName(), portList);
				}
			}
		}
		LOGGER.info("End : GenerateXMLForFabricEp : addPortChannel: ");
	}

	private void addPortChannelList(Map<String, List<String>> pcNames,
			FabricEthLan fabricEthLan, List<LanPortchannel> portchannellistA) {
		LOGGER.info("Start : GenerateXMLForFabricEp : addPortChannelList: ");
		@SuppressWarnings(RAWTYPES)
		Iterator itr = pcNames.entrySet().iterator();
		int portId = 1;
		while (itr.hasNext()) {
			@SuppressWarnings(RAWTYPES)
			Map.Entry mapEntry = (Map.Entry) itr.next();
			String key = mapEntry.getKey().toString();
			FabricEthLanPc fabricEthLanPc = factory.createFabricEthLanPc();

			fabricEthLanPc.setAdminSpeed("10gbps");
			fabricEthLanPc.setAdminState(ENABLED);
			fabricEthLanPc.setDescr(""); 
			fabricEthLanPc.setFlowCtrlPolicy(DEFAULT);
			fabricEthLanPc.setName(key);
			fabricEthLanPc.setOperSpeed("indeterminate");
			fabricEthLanPc.setPortId(Integer.toString(portId));

			for (LanPortchannel portchannel : portchannellistA) {

				if (portchannel.getFiName().equalsIgnoreCase(key)) {

					FabricEthLanPcEp fabricEthLanPcEp = factory
							.createFabricEthLanPcEp();
					fabricEthLanPcEp.setPortId((portchannel
							.getEquipmentUplink().getPortId()).toString());
					fabricEthLanPcEp.setSlotId((portchannel
							.getEquipmentUplink().getSlotId()).toString());
					fabricEthLanPcEp.setAdminState(ENABLED);
					fabricEthLanPcEp.setEthLinkProfileName(DEFAULT);
					fabricEthLanPcEp.setName("");

					JAXBElement<FabricEthLanPcEp> fabricEthLanPcEpjaxb = factory
							.createFabricEthLanPcEp(fabricEthLanPcEp);
					fabricEthLanPc.getContent().add(fabricEthLanPcEpjaxb);
					LOGGER.debug(
							"Added LanPortchannel - FabricEthLanPcEp for slot/portId {}{}",
							portchannel.getEquipmentUplink().getSlotId(),
							portchannel.getEquipmentUplink().getPortId());
				}
			}

			itr.remove();
			JAXBElement<FabricEthLanPc> fabricEthLanPcjaxb = factory
					.createFabricEthLanPc(fabricEthLanPc);
			fabricEthLan.getContent().add(fabricEthLanPcjaxb);
			portId = portId + 1;
			LOGGER.debug("Added FabricEthLan for portId {}", portId);
		}
		LOGGER.info("End : GenerateXMLForFabricEp : addPortChannelList: ");
	}

	public void addFabricFcSanEpFabricPorts(FabricEp fabricEp,
			XmlGenProjectDetails project) {
		LOGGER.info("Start : GenerateXMLForFabricEp : addFabricFcSanEpFabricPorts: "
				+ project);
		FabricSanCloud fabricSanCloud = null;
		FabricVsan fabvsan = null;
		FabricFcSan fabricFcSanA = null;
		FabricFcSan fabricFcSanB = null;
		List<EquipmentFcport> fcList = project.getEquipmentFcports();

		if (!Util.listNotNull(fcList)) {
			return;
		}

		List<EquipmentFcport> fcListA = new ArrayList<>();
		List<EquipmentFcport> fcListB = new ArrayList<>();

		for (EquipmentFcport equipmentFcport : fcList) {
			if (A.equals(equipmentFcport.getFiId())) {
				fcListA.add(equipmentFcport);
			} else {
				fcListB.add(equipmentFcport);
			}
		}
		for (Serializable s : fabricEp.getContent()) {
			if (!(s instanceof String)) {
				@SuppressWarnings(RAWTYPES)
				Object obj = ((JAXBElement) s).getValue();
				if (obj instanceof FabricSanCloud) {
					fabricSanCloud = (FabricSanCloud) obj;
					break;
				}
			}
		}

		if (fabricSanCloud != null) {
			for (Serializable s : fabricSanCloud.getContent()) {
				if (!(s instanceof String)) {
					@SuppressWarnings(RAWTYPES)
					Object obj = ((JAXBElement) s).getValue();
					if (obj instanceof FabricVsan
							&& ((FabricVsan) obj).getName().equalsIgnoreCase(
									DEFAULT)) {
						fabvsan = (FabricVsan) obj;
						break;
					}
				}
			}
			for (Serializable s : fabricSanCloud.getContent()) {
				if (!(s instanceof String)) {
					@SuppressWarnings(RAWTYPES)
					Object obj = ((JAXBElement) s).getValue();
					if (obj instanceof FabricFcSan) {
						if (A.equals(((FabricFcSan) obj).getId())) {
							fabricFcSanA = (FabricFcSan) obj;
						} else if (B.equals(((FabricFcSan) obj).getId())) {
							fabricFcSanB = (FabricFcSan) obj;
						}
					}
				}
			}
		}
		addFcPorts(fabvsan, fabricFcSanA, fabricFcSanB, fcList, fcListA,
				fcListB);
		LOGGER.info("End : GenerateXMLForFabricEp : addFabricFcSanEpFabricPorts: "
				+ project);
	}

	private void addFcPorts(FabricVsan fabvsan, FabricFcSan fabricFcSanA,
			FabricFcSan fabricFcSanB, List<EquipmentFcport> fcList,
			List<EquipmentFcport> fcListA, List<EquipmentFcport> fcListB) {
		LOGGER.info("Start : GenerateXMLForFabricEp : addFcPorts: ");

		if (fcList.size() != 0 && fabvsan != null) {
			for (EquipmentFcport fc : fcList) {
				FabricFcVsanPortEp fabricFcVsanPortEp = factory
						.createFabricFcVsanPortEp();
				fabricFcVsanPortEp.setPortId((fc.getPortId()).longValue());
				fabricFcVsanPortEp.setSlotId((fc.getSlotId()).longValue());
				fabricFcVsanPortEp.setSwitchId(fc.getFiId());
				fabricFcVsanPortEp.setAdminState(ENABLED);

				JAXBElement<FabricFcVsanPortEp> fabricFcVsanPortEpjaxb = factory
						.createFabricFcVsanPortEp(fabricFcVsanPortEp);
				fabvsan.getContent().add(fabricFcVsanPortEpjaxb);
				LOGGER.debug(
						"Added EquipmentFcport - FabricFcVsanPortEp for port Id{}",
						fc.getPortId());
			}
		}
		addFcPortAB(fabvsan, fabricFcSanA, fcListA);
		addFcPortAB(fabvsan, fabricFcSanB, fcListB);
		LOGGER.info("End : GenerateXMLForFabricEp : addFcPorts: ");
	}

	private void addFcPortAB(FabricVsan fabvsan, FabricFcSan fabricFcSan,
			List<EquipmentFcport> fcListA) {
		LOGGER.info("End : GenerateXMLForFabricEp : addFcPortAB: ");
		if (fcListA.size() != 0 && fabvsan != null) {
			for (EquipmentFcport fc : fcListA) {

				FabricFcSanEp fabricFcSanEp = factory.createFabricFcSanEp();
				fabricFcSanEp.setPortId((fc.getPortId()).longValue());
				fabricFcSanEp.setSlotId((fc.getSlotId()).longValue());
				fabricFcSanEp.setUsrLbl(fc.getUserLabel());
				fabricFcSanEp.setFillPattern("arbff");
				fabricFcSanEp.setAdminState(ENABLED);

				JAXBElement<FabricFcSanEp> fabricFcSanEpjaxb = factory
						.createFabricFcSanEp(fabricFcSanEp);
				fabricFcSan.getContent().add(fabricFcSanEpjaxb);
				LOGGER.debug(
						"Added EquipmentFcport - FabricFcSanEp for port Id{}",
						fc.getPortId());
			}
		}
		LOGGER.info("End : GenerateXMLForFabricEp : addFcPortAB: ");
	}
}
