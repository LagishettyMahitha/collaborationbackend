package com.mahi.dao;


import java.util.List;

import com.mahi.model.JobApplication;

public interface JobApplicationDao 
{

	public boolean addJobapp(JobApplication jobapp);
	public JobApplication get(int id);
	
	public void updateJobApp(JobApplication jobapp);
	public List<JobApplication> allJObApps();
	
}
