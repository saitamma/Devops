/**
 * 
 */
package com.cisco.ca.cstg.pdi.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisco.ca.cstg.pdi.dao.CommonDaoServicesImpl;
import com.cisco.ca.cstg.pdi.pojos.MainMenuState;
import com.cisco.ca.cstg.pdi.pojos.ProjectDetails;
import com.cisco.ca.cstg.pdi.pojos.SubMenuState;
import com.cisco.ca.cstg.pdi.pojos.WizardStatus;
import com.cisco.ca.cstg.pdi.utils.Constants;

/**
 * @author pavkuma2
 *
 */

@Service("wizardService")
public class WizardConfigurationServiceImpl extends CommonDaoServicesImpl implements WizardConfigurationService {
	
	private static final String MAINMENUSTATE_ID = "mainMenuState.id";
	private static final String IS_COMPLETED = "isCompleted";
	private static final String SUB_MENU_ID  = "subMenuId";
	private CommonUtilServices commonUtilServices;
	

	@Autowired
	public WizardConfigurationServiceImpl(SessionFactory hibernateSessionFactory, CommonUtilServices commonUtilServices) {
		this.commonUtilServices = commonUtilServices;
		setSessionFactory(hibernateSessionFactory);
	}
	
	
	@Override
	public void createWizardStatus(Integer projectId) {
		
		ProjectDetails projectDetails = commonUtilServices.getProjectDetailsObject(projectId);
		
		if(fetchWizardStatus(projectId).size() == 0) {
			List<Object> wizardStatusList = new ArrayList<Object>();
			List<Object> mainMenuList = listAll(MainMenuState.class);
			
			for(int i = 0 ;  i < mainMenuList.size() ; i++) {
				MainMenuState mainMenuState = (MainMenuState) mainMenuList.get(i);
				
				List<SubMenuState> set = mainMenuState.getSubMenuStates();
					
				if(set.size() > 1) {
					Iterator<SubMenuState> it = set.iterator();
					
					while(it.hasNext()) {
						SubMenuState subMenuState = (SubMenuState) it.next();
						WizardStatus wizardStatus = new WizardStatus();
						
						if(mainMenuState.getId() == 0 && subMenuState.getId() == 0){
							wizardStatus.setIsActive(true);
						}
						else{
							wizardStatus.setIsActive(false);
						}
						wizardStatus.setIsCompleted(false);
						wizardStatus.setMainMenuState(mainMenuState);
						wizardStatus.setProjectDetails(projectDetails);
						wizardStatus.setSubMenuId(subMenuState.getSubMenuId());
						wizardStatusList.add(wizardStatus);
					}
				} else {
					WizardStatus wizardStatus = new WizardStatus();
					
					if(mainMenuState.getId() == 0){
						wizardStatus.setIsActive(true);
					}else {
						wizardStatus.setIsActive(false);
					}
					wizardStatus.setIsCompleted(false);
					wizardStatus.setMainMenuState(mainMenuState);
					wizardStatus.setProjectDetails(projectDetails);
					wizardStatus.setSubMenuId(-1);
					wizardStatusList.add(wizardStatus);
				}
			}
			
			addList(wizardStatusList);
		}
	}


	@Override
	public List<Object> fetchWizardStatus(Integer projectId) {
		Criterion whereClause = Restrictions.eq(Constants.PROJECTDETAILS_ID, projectId);
		return listAll(WizardStatus.class, whereClause);
	}


	@Override
	public void updateWizardStatus(String wizardStatusJSon) throws IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		
		int projectId = mapper.readTree(wizardStatusJSon).get("projectId").getIntValue();
		int activeStateMenuIndex =  mapper.readTree(wizardStatusJSon).get("activeStateMenuIndex").getIntValue();
		int activeStateSubMenuIndex= mapper.readTree(wizardStatusJSon).get("activeStateSubMenuIndex").getIntValue();
		
		
		WizardStatus wizardStatus = getActiveWizardStatus(projectId);
		wizardStatus.setIsActive(false);
		wizardStatus.setIsCompleted(true);		
		saveOrUpdate(wizardStatus);
		
		Criterion whereClause = Restrictions.and(Restrictions.eq(Constants.PROJECTDETAILS_ID, projectId), Restrictions.and(Restrictions.eq(MAINMENUSTATE_ID, activeStateMenuIndex), Restrictions.eq(SUB_MENU_ID, activeStateSubMenuIndex)));
		List<Object> wizardStatusList = listAll(WizardStatus.class, whereClause); 
		
		if(wizardStatusList == null || wizardStatusList.size() == 0 || wizardStatusList.size() > 1) {
			throw new IllegalStateException("Wizard Status not found for the project");
		}
		
		wizardStatus = (WizardStatus) wizardStatusList.get(0);
		wizardStatus.setIsActive(true);
		saveOrUpdate(wizardStatus);	
	}


	@Override
	public WizardStatus getActiveWizardStatus(Integer projectId) {
	
		Criterion projectIdCriterion = Restrictions.eq(Constants.PROJECTDETAILS_ID, projectId);
		Criterion isActiveCriterion = Restrictions.eq("isActive", true);
		
		Criterion whereClause = Restrictions.and(projectIdCriterion, isActiveCriterion);
		
		List<Object> list = listAll(WizardStatus.class, whereClause);
		
		if(list == null || list.size() == 0 || list.size() > 1) {
			throw new IllegalStateException("No Active Wizard Status found for the project");
		}
		
		return ((WizardStatus) (list.get(0)));		
	}
	
	@Override
	public List<Object> getCompletedMainMenuWizardStatus(Integer projectId) {
		
		DetachedCriteria wizardCriteria = DetachedCriteria.forClass(WizardStatus.class);
		
		
		wizardCriteria.add(Restrictions.eq(Constants.PROJECTDETAILS_ID, projectId)).add(Restrictions.ne(IS_COMPLETED, true));
		wizardCriteria.setProjection(Projections.groupProperty(MAINMENUSTATE_ID));
		
		List<Object> mainMenuIdlist = listAll(wizardCriteria);
		wizardCriteria = DetachedCriteria.forClass(WizardStatus.class);
		wizardCriteria.add(Restrictions.eq(Constants.PROJECTDETAILS_ID, projectId));
		
		if(mainMenuIdlist.size() >= 1) {
			wizardCriteria.add(Restrictions.not(Restrictions.in(MAINMENUSTATE_ID, mainMenuIdlist)));
		}
		wizardCriteria.addOrder(Order.asc(MAINMENUSTATE_ID));
		wizardCriteria.setProjection(Projections.distinct(Projections.groupProperty(MAINMENUSTATE_ID)));
		
		return listAll(wizardCriteria);		
	}
	
	@Override
	public List<Object> getCompletedSubMenuWizardStatus(Integer projectId, List<Object> mainMenuIds) {
		
		DetachedCriteria wizardCriteria = DetachedCriteria.forClass(WizardStatus.class);
		List<Object> subMenuIdlist = new ArrayList<>();
		
		if(mainMenuIds.size() >= 1) {		
			wizardCriteria.add(Restrictions.eq(Constants.PROJECTDETAILS_ID, projectId)).add(Restrictions.eq(IS_COMPLETED, true));
			wizardCriteria.add(Restrictions.not(Restrictions.in(MAINMENUSTATE_ID, mainMenuIds)));
			wizardCriteria.setProjection(Projections.groupProperty(SUB_MENU_ID));
			subMenuIdlist = listAll(wizardCriteria);
		}
		return subMenuIdlist;		
	}


	@Override
	public void updateMainMenuStatusCompleted(WizardStatus wizardStatus) {
		Criterion whereClause = Restrictions.and(Restrictions.eq(Constants.PROJECTDETAILS_ID, wizardStatus.getProjectDetails().getId()), Restrictions.eq(MAINMENUSTATE_ID, wizardStatus.getMainMenuState().getId()));
		List<Object> wizardStatusList = listAll(WizardStatus.class, whereClause); 
		
		for(int i = 0; i < wizardStatusList.size(); i++) {
			WizardStatus ws = (WizardStatus) wizardStatusList.get(i);
			ws.setIsCompleted(true);
			update(ws);
		}
	}
}