package com.cisco.ca.cstg.pdi.pojos.mo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cisco.ca.cstg.pdi.utils.AssessmentKey;

@XmlRootElement(name="aaaLdapProvider") 
@XmlAccessorType(XmlAccessType.FIELD)
public class AaaLdapProvider implements java.io.Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(AaaLdapProvider.class);
	private static final long serialVersionUID = -4842199016540386171L;
	private AdminLdapProvider adminLdapProvider; 

	@XmlAttribute
	private String name;
	@XmlAttribute
	private String filter;
	@XmlAttribute
	private String encKey;
	
	
	public AaaLdapProvider() {		
	}
	
	public AaaLdapProvider(AdminLdapProvider adminLdapProvider, String name, String filter, String encKey) {
		this.adminLdapProvider = adminLdapProvider;
		this.name = name;
		this.filter = filter;
		this.encKey = encKey;
	}
	
	public AdminLdapProvider getAdminLdapProvider() {
		return adminLdapProvider;
	}

	public void setAdminLdapProvider(AdminLdapProvider adminLdapProvider) {
		this.adminLdapProvider = adminLdapProvider;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getEncKey() {
		return encKey;
	}

	public void setEncKey(String encKey) {
		if (null != encKey && !encKey.isEmpty()) {
			AssessmentKey assessmentKey = new AssessmentKey();
			try {
				assessmentKey.setMetaData(encKey);
				this.encKey = assessmentKey.encrypt();
			} catch (Exception ex) {
				LOGGER.error("Error occured while setting password.", ex);
			}
		}
	}
	
	public String getDecryptedKey() {
		String decryptedKey = "";
		if (null != encKey && !encKey.isEmpty()) {
			AssessmentKey assessmentKey = new AssessmentKey();
			assessmentKey.setAssessmentKey(encKey);
			try {
				decryptedKey = assessmentKey.decrypt();
			} catch (Exception ex) {
				LOGGER.error("Error occured while getting password.", ex);
			}
		}
		return decryptedKey;
	}
}
