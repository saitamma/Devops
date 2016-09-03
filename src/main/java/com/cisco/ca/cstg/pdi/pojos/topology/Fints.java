package com.cisco.ca.cstg.pdi.pojos.topology;

public class Fints {
	private final Pod pod;
	private final Fint fint1;
	private final Fint fint2;

	public Fints(Pod pod, String dn1, String model1, String dn2, String model2,
			long fintid1, long fintid2) {
		this.pod = pod;
		this.fint1 = new Fint(pod, 1, model1, dn1, fintid1);
		this.fint2 = new Fint(pod, 2, model2, dn2, fintid2);
	}

	public Pod getPod() {
		return pod;
	}

	public Fint getFint1() {
		return fint1;
	}

	public Fint getFint2() {
		return fint2;
	}

}
