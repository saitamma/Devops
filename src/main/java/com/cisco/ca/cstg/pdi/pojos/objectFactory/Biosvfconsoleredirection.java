package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVfConsoleRedirection")
public class Biosvfconsoleredirection implements java.io.Serializable {

	private static final long serialVersionUID = 220028207032854725L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String childAction;
	@XmlAttribute
	private String rn;
	@XmlAttribute
	private String vpBaudRate;
	@XmlAttribute
	private String vpConsoleRedirection;
	@XmlAttribute
	private String vpFlowControl;
	@XmlAttribute(name="vpLegacyOSRedirection")
	private String vpLegacyOsredirection;
	@XmlAttribute
	private String vpPuttyKeyPad;
	@XmlAttribute
	private String vpTerminalType;

	public Biosvfconsoleredirection() {
	}

	public Biosvfconsoleredirection(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public Biosvfconsoleredirection(Biosvprofile biosvprofile,
			String childAction, String rn, String vpBaudRate,
			String vpConsoleRedirection, String vpFlowControl,
			String vpLegacyOsredirection, String vpPuttyKeyPad,
			String vpTerminalType) {
		this.biosvprofile = biosvprofile;
		this.childAction = childAction;
		this.rn = rn;
		this.vpBaudRate = vpBaudRate;
		this.vpConsoleRedirection = vpConsoleRedirection;
		this.vpFlowControl = vpFlowControl;
		this.vpLegacyOsredirection = vpLegacyOsredirection;
		this.vpPuttyKeyPad = vpPuttyKeyPad;
		this.vpTerminalType = vpTerminalType;
	}

	public Integer getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(Integer primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Biosvprofile getBiosvprofile() {
		return this.biosvprofile;
	}

	public void setBiosvprofile(Biosvprofile biosvprofile) {
		this.biosvprofile = biosvprofile;
	}

	public String getChildAction() {
		return this.childAction;
	}

	public void setChildAction(String childAction) {
		this.childAction = childAction;
	}

	public String getRn() {
		return this.rn;
	}

	public void setRn(String rn) {
		this.rn = rn;
	}

	public String getVpBaudRate() {
		return this.vpBaudRate;
	}

	public void setVpBaudRate(String vpBaudRate) {
		this.vpBaudRate = vpBaudRate;
	}

	public String getVpConsoleRedirection() {
		return this.vpConsoleRedirection;
	}

	public void setVpConsoleRedirection(String vpConsoleRedirection) {
		this.vpConsoleRedirection = vpConsoleRedirection;
	}

	public String getVpFlowControl() {
		return this.vpFlowControl;
	}

	public void setVpFlowControl(String vpFlowControl) {
		this.vpFlowControl = vpFlowControl;
	}

	public String getVpLegacyOsredirection() {
		return this.vpLegacyOsredirection;
	}

	public void setVpLegacyOsredirection(String vpLegacyOsredirection) {
		this.vpLegacyOsredirection = vpLegacyOsredirection;
	}

	public String getVpPuttyKeyPad() {
		return this.vpPuttyKeyPad;
	}

	public void setVpPuttyKeyPad(String vpPuttyKeyPad) {
		this.vpPuttyKeyPad = vpPuttyKeyPad;
	}

	public String getVpTerminalType() {
		return this.vpTerminalType;
	}

	public void setVpTerminalType(String vpTerminalType) {
		this.vpTerminalType = vpTerminalType;
	}

}
