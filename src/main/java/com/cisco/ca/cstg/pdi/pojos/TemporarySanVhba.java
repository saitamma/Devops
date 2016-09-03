package com.cisco.ca.cstg.pdi.pojos;

public class TemporarySanVhba implements java.io.Serializable {

	private static final long serialVersionUID = -7134291360218812943L;
	private Integer id;
	private String vhbaTemplateName;
	private String adapterPolicyName;
	private String name;

	public TemporarySanVhba() {
	}	

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVhbaTemplateName() {
		return this.vhbaTemplateName;
	}

	public void setVhbaTemplateName(String vhbaTemplateName) {
		this.vhbaTemplateName = vhbaTemplateName;
	}

	public String getAdapterPolicyName() {
		return this.adapterPolicyName;
	}

	public void setAdapterPolicyName(String adapterPolicyName) {
		this.adapterPolicyName = adapterPolicyName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
