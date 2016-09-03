package com.cisco.ca.cstg.pdi.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisco.ca.cstg.pdi.dao.CommonDaoServicesImpl;
import com.cisco.ca.cstg.pdi.pojos.ADAUsers;
import com.cisco.ca.cstg.pdi.pojos.ProjectDetails;
import com.cisco.ca.cstg.pdi.utils.Constants;
import com.cisco.ca.cstg.pdi.utils.Util;

@Service("projectDetailsService")
public class ProjectDetailsServiceImpl extends CommonDaoServicesImpl implements
		ProjectDetailsService , Constants {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectDetailsServiceImpl.class);
	@Autowired
	public ProjectDetailsServiceImpl(SessionFactory hibernateSessionFactory) {
		setSessionFactory(hibernateSessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectDetails> getProjects(String userCec) {
		List<ProjectDetails> projectDetails = null;
		Criterion projCriteria = null;
		Criterion criterion = Restrictions.eq(USER_ID, userCec);
		List<ADAUsers> adaUsers = (List<ADAUsers>)(List<?>) listAll(ADAUsers.class, criterion);
		
		for(ADAUsers adaUser: adaUsers) {
			String userRole = adaUser.getUserRoles().getUserRole();
			if (SUPER_ADMIN.equals(userRole)) {
				Criterion criterion2 = Restrictions.eq(CREATEDBY,
						adaUser.getId());
				List<ADAUsers> allUser = (List<ADAUsers>) (List<?>) listAll(
						ADAUsers.class, criterion2);
				List<String> userIds = new ArrayList<String>();
				for (ADAUsers user : allUser) {
					userIds.add(user.getUserId());
					Criterion criterion3 = Restrictions.eq(CREATEDBY,
							user.getId());
					List<ADAUsers> allUser2 = (List<ADAUsers>) (List<?>) listAll(
							ADAUsers.class, criterion3);
					for (ADAUsers user2 : allUser2) {
						userIds.add(user2.getUserId());
					}
				}
				userIds.add(adaUser.getUserId());
				projCriteria = Restrictions.in(CREATEDBY, userIds);
			} else if(ADMIN.equals(userRole)) {
				Criterion criterion2 = Restrictions.eq(CREATEDBY, adaUser.getId());
				List<ADAUsers> allUser = (List<ADAUsers>)(List<?>) listAll(ADAUsers.class, criterion2);
				List<String> userIds = new ArrayList<String>();
				for(ADAUsers user : allUser){
					userIds.add(user.getUserId());
				}
				userIds.add(adaUser.getUserId());
				projCriteria = Restrictions.in(CREATEDBY, userIds);
			} else{
				projCriteria = Restrictions.eq(CREATEDBY, userCec);
			}
		}
		
		if(projCriteria != null) {
			projectDetails = (List<ProjectDetails>) (List<?>) listAll(ProjectDetails.class, projCriteria); 
		}
		
		return projectDetails;	
	}

	@Override
	public Integer addNewProject(ProjectDetails projectDetails) {
		return addNew(projectDetails);
	}

	@Override
	public void updateProjectDetails(ProjectDetails projectDetails) {
		update(projectDetails);
	}

	@Override
	public void deleteProject(Integer projectId) {
		ProjectDetails pd = fetchProjectDetails(projectId);
		String projectFolderPath = Util.getPdiConfFolderPath(pd);
		Util.deleteFolder(projectFolderPath);
		delete(pd);
	}

	@Override
	public ProjectDetails fetchProjectDetails(Integer projectId) {
		return (ProjectDetails)findById(ProjectDetails.class, projectId);
	}

	@Override
	public Boolean isProjectExits(ProjectDetails pd, String userCec) {
		List<Object> findList = null;
		String query = "from ProjectDetails where projectName like '"+ pd.getProjectName() + "' and createdBy = '"+userCec+"' ";
		try {
			if(pd.getId() != 0){
				query = query.concat(" and id != ").concat(String.valueOf(pd.getId()));
			}
			findList = find(query);

			if (findList.size() > 0) {
				return true;
			}
		} catch (Exception e) {
			LOGGER.error("Exception in method isProjectExits. ",e);
		}
		return false;
	}

	@Override
	public ProjectDetails createCloneProject(ProjectDetails projectDetails) throws IOException {
		ProjectDetails cloningPD = new ProjectDetails(0);
		String name = projectDetails.getProjectName();
		if(projectDetails.getProjectName().length() >= 20){
			name = name.substring(0, projectDetails.getProjectName().length()-11)+"_"+Util.getCurrentDateTime();
		} else{
			name = "Clone_"+projectDetails.getProjectName()+"_"+Util.getCurrentDateTime();
		}
		cloningPD.setProjectName(name);
		cloningPD.setCreatedDate(projectDetails.getCreatedDate());
		cloningPD.setCreatedBy(projectDetails.getCreatedBy());
		cloningPD.setTheatre(projectDetails.getTheatre());
		cloningPD.setIsUploaded(true);
		addNewProject(cloningPD);
		return cloningPD;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ADAUsers> getUserDetailByUserCec(String activeUserId) {
		Criterion criteria = Restrictions.and(Restrictions.eq(USER_ID, activeUserId), Restrictions.eq(IS_ACTIVE, 1));
		return (List<ADAUsers>)(List<?>) listAll(ADAUsers.class, criteria);
	}
}
