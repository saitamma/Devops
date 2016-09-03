package com.cisco.ca.cstg.pdi.pojos.topology;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cisco.ca.cstg.pdi.exceptions.DataNotFoundException;
import com.cisco.ca.cstg.pdi.exceptions.PojoCreationException;
import com.cisco.ca.cstg.pdi.exceptions.TopologyException;
import com.cisco.ca.cstg.pdi.pojos.Infrastructure;
import com.cisco.ca.cstg.pdi.pojos.ProjectSettings;
import com.cisco.ca.cstg.pdi.services.CommonUtilServices;
import com.cisco.ca.cstg.pdi.services.InfrastructureService;
import com.cisco.ca.cstg.pdi.services.TopologyService;

public class Pod {
	private static final String CHASSIS_COUNT = "CHASSIS_COUNT";
	final String key;
	private final int podNum;
	private final Map<String, UCS> ucss;
	private Fints fints;
	private final NetworkSwitches aggs = new NetworkSwitches(this);
	private final Map<String, List<Link>> links = new HashMap<String, List<Link>>();
	private TopologyService topologyService;
	private InfrastructureService infrastructureService;
	private CommonUtilServices commonUtilService;
	private static final Logger LOGGER = LoggerFactory.getLogger(Pod.class);

	public Pod(int podNum, TopologyService topologyService,
			InfrastructureService infrastructureService,
			CommonUtilServices commonUtilService) {
		super();
		this.podNum = podNum;
		this.key = "pod_" + podNum;
		ucss = new TreeMap<String, UCS>(new ChassisComparator());
		this.topologyService = topologyService;
		this.infrastructureService = infrastructureService;
		this.commonUtilService = commonUtilService;
	}

	public UCS createUcs(String model, String name)
			throws PojoCreationException {
		int size = ucss.size();
		size++;
		if (size > 20) {
			throw new PojoCreationException(
					"Can not create more than 20 ucs in a pod.");
		}
		UCS ucs = new UCS(this, size, model, name);
		ucss.put(name, ucs);
		return ucs;
	}

	public Fints createFint(String dn1, String model1, String dn2,
			String model2, long fintid1, long fintid2)
			throws PojoCreationException {
		if (fints != null) {
			throw new PojoCreationException(
					"FIs are already created for this pod");
		}
		this.fints = new Fints(this, dn1, model1, dn2, model2, fintid1, fintid2);
		return fints;
	}

	public boolean addAgg(String model, String ip) throws PojoCreationException {
		return aggs.addNetworkSwitch(model, ip);
	}

	public Link createUcsFintLink(int serverPort, UCS ucs, String uplinkPort,
			Fint fint) throws TopologyException {
		if (serverPort != 9999) {
			if (serverPort > 8 || serverPort < 1) {
				throw new PojoCreationException(
						"Server port can't be assigned.");
			}
			if (uplinkPort.indexOf('/') != -1) {
				Integer fPort = Integer.parseInt(uplinkPort
						.substring(uplinkPort.indexOf('/') + 1));
				if (fPort > 96 || fPort < 1) {
					throw new PojoCreationException(
							"Uplink port can't be assigned.");
				}
			}
		}
		Link link = new Link(serverPort, ucs, uplinkPort, fint);

		ucs.addLink(link);
		fint.addLink(link);
		this.addLink(link);
		return link;

	}

	private void addLink(Link link) {
		 String stringKey = this.getKey("_", this.key,
				this.getKey("-", link.getDown().key, link.getUp().key));
		List<Link> list = links.get(stringKey);
		if (list == null) {
			list = new ArrayList<Link>();
			list.add(link);
			links.put(stringKey, list);
		} else {
			list.add(link);
		}
	}

	public Map<String, List<Link>> getLinks() {
		return links;
	}

	public Fints getFints() {
		return fints;
	}

	public String getKey() {
		return key;
	}

	public int getPodNum() {
		return podNum;
	}

	public UCS getUcsByName(String name) {
		return ucss.get(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;}
		if (obj == null) {
			return false;}
		if (!(obj instanceof Device)) {
			return false; }
		Device other = (Device) obj;
		if (key == null) {
			if (other.key != null) {
				return false; }
		} else if (!key.equals(other.key)) {
			return false; }
		return true;
	}

	@Override
	public int hashCode() {
		return this.key.hashCode();
	}

	private String getKey(String delemiter, String... keys) {
		StringBuilder stringBuilderKey = new StringBuilder();
		boolean add = true;
		for (String str : keys) {
			if (add) {
				stringBuilderKey.append(str);
				add = false;
			} else {
				stringBuilderKey.append(delemiter);
				stringBuilderKey.append(str);
			}
		}

		return stringBuilderKey.toString();
	}

	public Map<String, UCS> getUcss() {
		return ucss;
	}

	private class ChassisComparator implements Comparator<String> {

		@Override
		public int compare(String o1, String o2) {
			final char HYPHEN = '-';
			return Integer.valueOf(o1.substring(o1.lastIndexOf(HYPHEN) + 1,
					o1.length()))
					- Integer.valueOf(o2.substring(o2.lastIndexOf(HYPHEN) + 1,
							o2.length()));

		}

	}

	public void init() throws DataNotFoundException   {
		this.addFints();
		this.addUcs();
		this.chassisfintlink();
	}

	private void addFints() throws DataNotFoundException {

		try {
			List<Infrastructure> resultList = infrastructureService
					.fetchInfrastructureDetails(podNum);

			if (resultList != null && resultList.size() > 0) {
				String serverModelA = resultList.get(0).getServerModel()
						.getDescription();
				String serverModelB = serverModelA;
				int infraId = resultList.get(0).getId();
				this.createFint("Switch-A", serverModelA, "Switch-B",
						serverModelB, infraId, infraId);
			} else {
				throw new DataNotFoundException(
						"No fabric interconnects found exception");
			}
		} catch (Exception e) {
			LOGGER.info("error in addFints", e);
		}

	}

	private void addUcs() throws DataNotFoundException {
		try {
			int chassisCount = 0;
			ProjectSettings fetchProjectSettings = commonUtilService
					.fetchProjectSettings(podNum, CHASSIS_COUNT);
			if (fetchProjectSettings != null && fetchProjectSettings.getProjectValue() != null) {
				String chassis = fetchProjectSettings.getProjectValue();				
				chassisCount = Integer.parseInt(chassis);
				/* LOOP FOR CREATING UCS */
				for (int i = 1; i <= chassisCount; i++) {
					this.createUcs("N20-C6508", "Chassis-" + i);
				}				
			} else {
				throw new DataNotFoundException("No chassis found execption.");
			}

		} catch (Exception e) {			
			LOGGER.info("error in addUcs", e);
			throw new DataNotFoundException(e.getMessage());
		}

	}

	private void chassisfintlink() throws DataNotFoundException {
		try {
			String[] fint = { "A", "B" };
			Map<String, Integer> switchfintmap = new HashMap<String, Integer>();

			int chassisCount = 0;
			ProjectSettings fetchProjectSettings = commonUtilService
					.fetchProjectSettings(podNum, CHASSIS_COUNT);
			if (fetchProjectSettings != null) {
				String chassis = fetchProjectSettings.getProjectValue();
				if (chassis != null) {
					chassisCount = Integer.parseInt(chassis);
					switchfintmap.put(fint[0], chassisCount);
					switchfintmap.put(fint[1], chassisCount);
				}
			} else {
				throw new DataNotFoundException("No chassis found exception.");
			}

			Map<Integer, List<EtherPorts>> etherportlist = new TreeMap<Integer, List<EtherPorts>>();

			/* LOOP FOR FETCHING FABRIC INTERCONNECTS */
			for (int fint_cnt = 0; fint_cnt < switchfintmap.size(); fint_cnt++) {
				int switchcard = switchfintmap.get(fint[fint_cnt]);
				/*
				 * LOOP FOR FETCHING ETHERPORT FOR CORRESPONDING FABRIC
				 * INTERCONNECT
				 */
				for (int switch_count = 1; switch_count <= switchcard; switch_count++) {
					List<EtherPorts> resultlist = topologyService
							.getEtherPortList(podNum, switch_count,
									fint[fint_cnt]);
					if (resultlist.size() > 0) {
						/*
						 * LOOP FOR COMBINIG THE ETHERPORTS FETCHED FOR
						 * DIFFERENT SWITCHCARDS
						 */
						for (int i = 0; i < resultlist.size(); i++) {
							Integer key = Integer.valueOf(resultlist.get(i)
									.getChassisid().toString());
							List<EtherPorts> templist = null;
							if (etherportlist.containsKey(key)) {
								templist = etherportlist.get(key);
								templist.add(templist.size(), resultlist.get(i));
							} else {
								templist = new ArrayList<EtherPorts>();
								templist.add(resultlist.get(i));
							}
							etherportlist.put(key, templist);
						}
					}
				}

				if (etherportlist.size() > 0) {
					Map<String, UCS> chassis = this.getUcss();
					Object[] chasis_name = chassis.keySet().toArray();
					/* LOOP FOR FETCHING THE CHASIS */
					for (int chasis_cnt = 0; chasis_cnt < etherportlist.size(); chasis_cnt++) {
						/* LOOP FOR CREATING LINKS B/W FINT AND UCS */
						List<EtherPorts> portlist = etherportlist
								.get(chasis_cnt + 1);
						for (int conn_cnt = 0; conn_cnt < portlist.size(); conn_cnt++) {
							EtherPorts etherPorts = portlist.get(conn_cnt);
							if (fint_cnt == 0) {
								this.createUcsFintLink(
										etherPorts.getChassisport() != null ? Integer
												.valueOf(etherPorts
														.getChassisport()
														.toString()) : Integer
												.valueOf("9999"),
										chassis.get(chasis_name[chasis_cnt]),
										etherPorts.getFabricport() != null ? etherPorts
												.getFabricport().toString()
												: "9999", this.getFints()
												.getFint1());

							}
							if (fint_cnt == 1) {
								this.createUcsFintLink(
										etherPorts.getChassisport() != null ? Integer
												.valueOf(etherPorts
														.getChassisport()
														.toString()) : Integer
												.valueOf("9999"),
										chassis.get(chasis_name[chasis_cnt]),
										etherPorts.getFabricport() != null ? etherPorts
												.getFabricport().toString()
												: "9999", this.getFints()
												.getFint2());

							}
						}
					}

				}
				etherportlist.clear();
			}
		} catch (Exception e) {
			LOGGER.info("error in chassisfintlink", e);
			throw new DataNotFoundException(e.getMessage());
		}

	}

}
