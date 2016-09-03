package com.cisco.ca.cstg.pdi.pojos.objectFactory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "lsbootLocalStorage")
public class Lsbootlocalstorage implements java.io.Serializable {

	private static final long serialVersionUID = 2881517727823483626L;
	private int primaryKey;
	private Lsbootstorage lsbootstorage;
	
	@XmlElement
	private List<LsbootDefaultLocalImage> lsbootDefaultLocalImage = new ArrayList<LsbootDefaultLocalImage>();
	
	@XmlElement
	private List<Lsbootusbflashstorageimage> lsbootUsbFlashStorageImage = new ArrayList<Lsbootusbflashstorageimage>();
	
	@XmlElement
	private List<Lsbootusbinternalimage> lsbootUsbInternalImage = new ArrayList<Lsbootusbinternalimage>();
	
	@XmlElement
	private List<Lsbootusbexternalimage> lsbootUsbExternalImage = new ArrayList<Lsbootusbexternalimage>();
	
	@XmlElement
	private List<Lsbootlocalhddimage> lsbootLocalHddImage = new ArrayList<Lsbootlocalhddimage>();

	public Lsbootlocalstorage() {
	}

	public Lsbootlocalstorage(int primaryKey, Lsbootstorage lsbootstorage) {
		this.primaryKey = primaryKey;
		this.lsbootstorage = lsbootstorage;
	}

	public Lsbootlocalstorage(int primaryKey, Lsbootstorage lsbootstorage,
			List<LsbootDefaultLocalImage> lsbootDefaultLocalImage,
			List<Lsbootusbflashstorageimage> lsbootUsbFlashStorageImage,
			List<Lsbootusbinternalimage> lsbootUsbInternalImage,
			List<Lsbootusbexternalimage> lsbootUsbExternalImage,
			List<Lsbootlocalhddimage> lsbootLocalHddImage) {
		super();
		this.primaryKey = primaryKey;
		this.lsbootstorage = lsbootstorage;
		this.lsbootDefaultLocalImage = lsbootDefaultLocalImage;
		this.lsbootUsbFlashStorageImage = lsbootUsbFlashStorageImage;
		this.lsbootUsbInternalImage = lsbootUsbInternalImage;
		this.lsbootUsbExternalImage = lsbootUsbExternalImage;
		this.lsbootLocalHddImage = lsbootLocalHddImage;
	}

	public List<Lsbootusbflashstorageimage> getLsbootUsbFlashStorageImage() {
		return lsbootUsbFlashStorageImage;
	}

	public void setLsbootUsbFlashStorageImage(
			List<Lsbootusbflashstorageimage> lsbootUsbFlashStorageImage) {
		this.lsbootUsbFlashStorageImage = lsbootUsbFlashStorageImage;
	}

	public List<Lsbootusbinternalimage> getLsbootUsbInternalImage() {
		return lsbootUsbInternalImage;
	}

	public void setLsbootUsbInternalImage(
			List<Lsbootusbinternalimage> lsbootUsbInternalImage) {
		this.lsbootUsbInternalImage = lsbootUsbInternalImage;
	}

	public List<Lsbootusbexternalimage> getLsbootUsbExternalImage() {
		return lsbootUsbExternalImage;
	}

	public void setLsbootUsbExternalImage(
			List<Lsbootusbexternalimage> lsbootUsbExternalImage) {
		this.lsbootUsbExternalImage = lsbootUsbExternalImage;
	}

	public List<Lsbootlocalhddimage> getLsbootLocalHddImage() {
		return lsbootLocalHddImage;
	}

	public void setLsbootLocalHddImage(List<Lsbootlocalhddimage> lsbootLocalHddImage) {
		this.lsbootLocalHddImage = lsbootLocalHddImage;
	}

	public int getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Lsbootstorage getLsbootstorage() {
		return this.lsbootstorage;
	}

	public void setLsbootstorage(Lsbootstorage lsbootstorage) {
		this.lsbootstorage = lsbootstorage;
	}

	public List<LsbootDefaultLocalImage> getLsbootDefaultLocalImage() {
		return lsbootDefaultLocalImage;
	}

	public void setLsbootDefaultLocalImage(
			List<LsbootDefaultLocalImage> lsbootDefaultLocalImage) {
		this.lsbootDefaultLocalImage = lsbootDefaultLocalImage;
	}

}
