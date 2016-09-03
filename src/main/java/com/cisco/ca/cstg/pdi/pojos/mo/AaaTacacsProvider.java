package com.cisco.ca.cstg.pdi.pojos.mo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cisco.ca.cstg.pdi.utils.AssessmentKey;

@XmlRootElement(name="aaaTacacsProvider") 
@XmlAccessorType(XmlAccessType.FIELD)
public class AaaTacacsProvider implements java.io.Serializable {

	private static final long serialVersionUID = -4842199016540386171L;
	private static final Logger LOGGER = LoggerFactory.getLogger(AaaTacacsProvider.class);
	private AdminTacacsProvider adminTacacsProvider; 

	@XmlAttribute
	private String name;
	@XmlAttribute
	private String encKey;
	
	public AaaTacacsProvider() {		
	}
	
	public AaaTacacsProvider(AdminTacacsProvider adminTacacsProvider, String name, String encKey) {
		this.adminTacacsProvider = adminTacacsProvider;
		this.name = name;
		this.encKey = encKey;
	}
	
	public AdminTacacsProvider getAdminTacacsProvider() {
		return adminTacacsProvider;
	}

	public void setAdminTacacsProvider(AdminTacacsProvider adminTacacsProvider) {
		this.adminTacacsProvider = adminTacacsProvider;
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
