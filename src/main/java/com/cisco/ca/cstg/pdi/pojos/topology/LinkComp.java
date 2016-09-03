package com.cisco.ca.cstg.pdi.pojos.topology;

import java.io.Serializable;
import java.util.Comparator;

public class LinkComp implements Comparator<Link>, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -905008974569873292L;

	@Override
	public int compare(Link l1, Link l2) {
		if (l1.getFromPort() > l2.getFromPort()) {
			return 1;
		} else {
			return -1;
		}
	}

}
