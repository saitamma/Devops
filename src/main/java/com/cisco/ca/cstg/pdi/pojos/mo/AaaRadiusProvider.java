package com.cisco.ca.cstg.pdi.pojos.mo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cisco.ca.cstg.pdi.utils.AssessmentKey;

@XmlRootElement(name="aaaRadiusProvider") 
@XmlAccessorType(XmlAccessType.FIELD)
public class AaaRadiusProvider implements java.io.Serializable {

	private static final long serialVersionUID = 4555946176233642558L;
	private static final Logger LOGGER = LoggerFactory.getLogger(AaaRadiusProvider.class);
	private AdminRadiusProvider adminRadiusProvider; 

	@XmlAttribute
	private String name;
	@XmlAttribute
	private String encKey;
	
	public AaaRadiusProvider() {		
	}
	
	public AaaRadiusProvider(AdminRadiusProvider adminRadiusProvider, String name, String encKey) {
		this.adminRadiusProvider = adminRadiusProvider;
		this.name = name;
		this.encKey = encKey;
	}
	
	public AdminRadiusProvider getAdminRadiusProvider() {
		return adminRadiusProvider;
	}

	public void setAdminRadiusProvider(AdminRadiusProvider adminRadiusProvider) {
		this.adminRadiusProvider = adminRadiusProvider;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
