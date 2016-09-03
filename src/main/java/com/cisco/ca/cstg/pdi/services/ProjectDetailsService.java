package com.cisco.ca.cstg.pdi.services;

import java.io.IOException;
import java.util.List;

import com.cisco.ca.cstg.pdi.pojos.ADAUsers;
import com.cisco.ca.cstg.pdi.pojos.ProjectDetails;

public interface ProjectDetailsService {

	List<ProjectDetails> getProjects(String userCec);

	Integer addNewProject(ProjectDetails projectDetails);

	void updateProjectDetails(ProjectDetails projectDetails);

	void deleteProject(Integer projectId);
	
	ProjectDetails fetchProjectDetails(Integer projectId);
	
	Boolean isProjectExits(ProjectDetails pd, String userCec);
	
	ProjectDetails createCloneProject(ProjectDetails projectDetails)  throws IOException;
	List<ADAUsers> getUserDetailByUserCec(String activeUserId);
}
