package com.mahi.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.mahi.config.ApplicationContextConfig;
import com.mahi.dao.JobApplicationDao;
import com.mahi.dao.JobDao;
import com.mahi.dao.SignUpDao;
import com.mahi.model.Job;
import com.mahi.model.JobApplication;
import com.mahi.model.SignUp;

public class Test 

{

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
	
		@SuppressWarnings("resource")
		AbstractApplicationContext context=new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
       
/*
        SignUpDao signupDao=(SignUpDao)context.getBean("signupdao");
        SignUp signupobj=new SignUp();
        signupobj.setUsername("mahitha1");
        signupobj.setPassword("password");
        signupobj.setEmailid("mahitha@gmail.com");
        signupobj.setRole("Role_Admin");
        signupobj.setMobileno("7893644447");
	
        signupDao.addUsers(signupobj);
	*/
	/*
		JobDao jobDao=(JobDao)context.getBean("jobdao");
		Job jobobj=new Job();
		jobobj.setTitle("Developer");
		jobobj.setDescription("gud");
		
		jobobj.setQualification("Any Graduate");
		jobobj.setStatus("Applicable");
		
		jobDao.addJob(jobobj);
	*/
	/*	JobDao jobDao=(JobDao)context.getBean("jobdao");
		SignUpDao signupDao=(SignUpDao)context.getBean("signupdao");
		
		JobApplicationDao jobapplicationDao=(JobApplicationDao)context.getBean("jobapplicationdao");
		JobApplication jobappobj=new JobApplication();
		jobappobj.setRemarks("Good");
		jobappobj.setStatus("Applied");
		jobappobj.setSignup(signupDao.getUser(1));
		jobappobj.setJob(jobDao.get(1));
	
		jobapplicationDao.addJobapp(jobappobj);
	*/
	}
}
