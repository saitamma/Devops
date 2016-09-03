package com.cisco.ca.cstg.pdi.ucsmapper.unmarshal;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisco.ca.cstg.pdi.dao.CommonDaoServicesImpl;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Callhomedest;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Callhomeep;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Callhomeperiodicsysteminventory;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Callhomepolicy;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Callhomeprofile;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Callhomesmtp;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Callhomesource;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Callhometestalert;
import com.cisco.ca.cstg.pdi.pojos.objectFactory.Toproot;

@Service("unmarshallCallhome")
public class UnmarshallCallhome extends CommonDaoServicesImpl {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UnmarshallCallhome.class);

	@Autowired
	public UnmarshallCallhome(SessionFactory hibernateSessionFactory) {
		setSessionFactory(hibernateSessionFactory);
	}

	public void unmarshallCallhomeTag(Toproot topRoot) {
		try {
			Callhomeep callhomeep = topRoot.getCallhomeEp();
			if(callhomeep != null){
				callhomeep.setToproot(topRoot);
				addNew(callhomeep);
				unmarshallCallhomeperiodicsysteminventories(callhomeep);
				unmarshallCallhomepolicies(callhomeep);
				unmarshallCallhomeprofiles(callhomeep);
				unmarshallCallhomesmtps(callhomeep);
				unmarshallCallhomesources(callhomeep);
				unmarshallCallhometestalerts(callhomeep);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
	}

	private void unmarshallCallhometestalerts(Callhomeep callhomeep) {
		List<Callhometestalert> callhometestalerts = callhomeep
				.getCallhometestalerts();
		for (Callhometestalert callhometestalert : callhometestalerts) {
			callhometestalert.setCallhomeEp(callhomeep);
			addNew(callhometestalert);
		}
	}

	private void unmarshallCallhomesources(Callhomeep callhomeep) {
		List<Callhomesource> callhomesources = callhomeep.getCallhomesources();
		for (Callhomesource callhomesource : callhomesources) {
			callhomesource.setCallhomeEp(callhomeep);
			addNew(callhomesource);
		}
	}

	private void unmarshallCallhomesmtps(Callhomeep callhomeep) {
		List<Callhomesmtp> callhomesmtps = callhomeep.getCallhomesmtps();
		for (Callhomesmtp callhomesmtp : callhomesmtps) {
			callhomesmtp.setCallhomeEp(callhomeep);
			addNew(callhomesmtp);
		}
	}

	private void unmarshallCallhomeprofiles(Callhomeep callhomeep) {
		List<Callhomeprofile> callhomeprofiles = callhomeep
				.getCallhomeprofiles();
		for (Callhomeprofile callhomeprofile : callhomeprofiles) {
			callhomeprofile.setCallhomeEp(callhomeep);
			addNew(callhomeprofile);
			unmarshallCallhomeDest(callhomeprofile);
		}
	}
	
	private void unmarshallCallhomeDest(Callhomeprofile callhomeprofile) {
		List<Callhomedest> callhomeDests = callhomeprofile.getCallhomeDest();
		for (Callhomedest callhomedest : callhomeDests) {
			callhomedest.setCallhomeprofile(callhomeprofile);
			addNew(callhomedest);
		}
	}

	private void unmarshallCallhomepolicies(Callhomeep callhomeep) {
		List<Callhomepolicy> callhomepolicies = callhomeep
				.getCallhomepolicies();
		for (Callhomepolicy callhomepolicy : callhomepolicies) {
			callhomepolicy.setCallhomeEp(callhomeep);
			addNew(callhomepolicy);
		}
	}

	private void unmarshallCallhomeperiodicsysteminventories(
			Callhomeep callhomeep) {
		List<Callhomeperiodicsysteminventory> callhomeperiodicsysteminventories = callhomeep
				.getCallhomeperiodicsysteminventories();
		for (Callhomeperiodicsysteminventory callhomeperiodicsysteminventory : callhomeperiodicsysteminventories) {
			callhomeperiodicsysteminventory.setCallhomeEp(callhomeep);
			addNew(callhomeperiodicsysteminventory);
		}
	}
}
