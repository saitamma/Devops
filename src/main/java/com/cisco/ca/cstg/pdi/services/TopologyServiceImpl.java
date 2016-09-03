package com.cisco.ca.cstg.pdi.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisco.ca.cstg.pdi.dao.CommonDaoServicesImpl;
import com.cisco.ca.cstg.pdi.pojos.topology.EtherPorts;

@Service("topologyService")
public class TopologyServiceImpl extends CommonDaoServicesImpl implements
		TopologyService {

	private static final String SERVER_PORT_QUERY = "select port_id, chassis, user_label, slot_id from equipment_server where project_id = :projectId and chassis = :chassis and fi_id = :fiId";

	@Autowired
	public TopologyServiceImpl(SessionFactory hibernateSessionFactory) {
		setSessionFactory(hibernateSessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EtherPorts> getEtherPortList(Integer projectId,
			Integer chasisNo, String FISide) {
		Session openSession = getHibernateSessionFactory().openSession();
		List<EtherPorts> etherPortsList = new ArrayList<EtherPorts>();
		Query query = openSession.createSQLQuery(SERVER_PORT_QUERY);
		query.setParameter("projectId", projectId);
		query.setParameter("chassis", chasisNo);
		query.setParameter("fiId", FISide);
		List<Object[]> equipServerPOrtList = query.list();
		openSession.close();
		if (equipServerPOrtList != null && equipServerPOrtList.size() > 0) {
			for (int i = 0; i < equipServerPOrtList.size(); i++) {
				Object[] resultRow = equipServerPOrtList.get(i);
				EtherPorts etherPorts = new EtherPorts();
				etherPorts.setChassisid(resultRow[1].toString());
				String chassisPortLabel = resultRow[2].toString();
				String chassisPort = chassisPortLabel.substring(
						(chassisPortLabel.length() - 1),
						chassisPortLabel.length());
				etherPorts.setChassisport(chassisPort);
				etherPorts.setFabricport(resultRow[3].toString() + "/"
						+ resultRow[0].toString());
				etherPortsList.add(etherPorts);
			}
		}

		return etherPortsList;
	}

}
