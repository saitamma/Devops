package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="biosVProfile")
public class Biosvfinterleaveconfiguration  implements java.io.Serializable {

	private static final long serialVersionUID = -8170927910182506691L;
	private Integer primaryKey;
	private Biosvprofile biosvprofile;
	@XmlAttribute
	private String childAction;
	@XmlAttribute
	private String rn;
	@XmlAttribute
	private String vpChannelInterleaving;
	@XmlAttribute
	private String vpMemoryInterleaving;
	@XmlAttribute
	private String vpRankInterleaving;

    public Biosvfinterleaveconfiguration() {
    }

	
    public Biosvfinterleaveconfiguration(Biosvprofile biosvprofile) {
        this.biosvprofile = biosvprofile;
    }
    public Biosvfinterleaveconfiguration(Biosvprofile biosvprofile, String childAction, String rn, String vpChannelInterleaving, String vpMemoryInterleaving, String vpRankInterleaving) {
       this.biosvprofile = biosvprofile;
       this.childAction = childAction;
       this.rn = rn;
       this.vpChannelInterleaving = vpChannelInterleaving;
       this.vpMemoryInterleaving = vpMemoryInterleaving;
       this.vpRankInterleaving = vpRankInterleaving;
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
    public String getVpChannelInterleaving() {
        return this.vpChannelInterleaving;
    }
    
    public void setVpChannelInterleaving(String vpChannelInterleaving) {
        this.vpChannelInterleaving = vpChannelInterleaving;
    }
    public String getVpMemoryInterleaving() {
        return this.vpMemoryInterleaving;
    }
    
    public void setVpMemoryInterleaving(String vpMemoryInterleaving) {
        this.vpMemoryInterleaving = vpMemoryInterleaving;
    }
    public String getVpRankInterleaving() {
        return this.vpRankInterleaving;
    }
    
    public void setVpRankInterleaving(String vpRankInterleaving) {
        this.vpRankInterleaving = vpRankInterleaving;
    }




}


