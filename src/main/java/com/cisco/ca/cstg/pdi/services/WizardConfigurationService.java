/**
 * 
 */
package com.cisco.ca.cstg.pdi.services;

import java.io.IOException;
import java.util.List;

import com.cisco.ca.cstg.pdi.pojos.WizardStatus;

/**
 * @author pavkuma2
 * 
 */
public interface WizardConfigurationService {

	/**
	 * 
	 * This method create new wizard status for new project
	 * @param projectId active project id for which records need to be saved
	 */
	void createWizardStatus(Integer projectId);
	/**
	 * This method retrieves existing MainMenu Status from the database
	 * @param projectId active project id for which records need to be fetched
	 */
	List<Object> fetchWizardStatus(Integer projectId);


	/**
	 * This method updates existing WizardStatus to the database
	 * 
	 * @param jsonWizardStatusString is the json format of active wizard status which updated in database
	 *            
	 */
	void updateWizardStatus(String jsonWizardStatusString) throws  IOException;

	/**
	 * This method returns Active wizard status of project
	 * @param projectId active project id for which active wizard status is fetched
	 * @return active wizard status of project
	 */
	WizardStatus getActiveWizardStatus(Integer projectId);
	
	/**
	 * This method returns the list of completed wizard status of project
	 * @param projectId active project id for which completed wizard status fetched
	 * @return list of completed wizard status of project
	 */
	List<Object> getCompletedMainMenuWizardStatus(Integer projectId);
	
	/**
	 * This method returns the last of completed sub menu state of wizard status 
	 * @param projectId active project id for which completed wizard status fetched
	 * @param mainMenuId list of completed main ids 
	 * @return List of completed sub menu state wizard status of project
	 */
	List<Object> getCompletedSubMenuWizardStatus(Integer projectId, List<Object> mainMenuId);
	
	/**
	 * This method update the main menu state to completed status 
	 * @param wizardStatus active wizard status
	 */
	void updateMainMenuStatusCompleted(WizardStatus wizardStatus);
	
}
