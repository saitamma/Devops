package com.cisco.ca.cstg.pdi.services;

import java.util.List;

import com.cisco.ca.cstg.pdi.pojos.topology.EtherPorts;

public interface TopologyService {

	public List<EtherPorts> getEtherPortList(Integer projectId,
			Integer chasisNo, String FISide);
}
