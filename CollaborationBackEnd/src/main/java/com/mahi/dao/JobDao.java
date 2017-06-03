package com.mahi.dao;

import java.util.List;

import com.mahi.model.Job;
import com.mahi.model.JobApplication;

public interface JobDao
{

	public void add(Job job);
	public Job get(long id);
	public void delete(int id);
	public List<Job> list();
	

}
