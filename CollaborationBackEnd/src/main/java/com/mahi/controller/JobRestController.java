package com.mahi.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mahi.dao.JobDao;
import com.mahi.dao.SignUpDao;
import com.mahi.model.Job;
import com.mahi.model.JobApplication;

@RestController
public class JobRestController 
{

	@Autowired
	JobDao jobDAO;
	
	@Autowired
	SignUpDao signupDAO;

	@GetMapping(value="/job/")
	public ResponseEntity<List<Job>> getAllJobs(){
		
		List<Job> jobs=jobDAO.list();
		if(jobs.isEmpty())
		{
			return new ResponseEntity<List<Job>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
		
	}
	
	
	@GetMapping(value="/job/{jobId}")
	public ResponseEntity<Job> getJobById(@PathVariable("jobId") long jobId){
		
		Job job=jobDAO.get(jobId);
		if(job==null)
		{
			return new ResponseEntity<Job>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Job>(job,HttpStatus.OK);
		
	}
	
	
	//-------------------Create a Job Posting--------------------------------------------------------
    
	@PostMapping(value = "/job/")
    public ResponseEntity<Void> createJobPosting(@RequestBody Job job) {
  
        jobDAO.add(job);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
	
	
	//-------------------Apply for Job--------------------------------------------------------
	@PostMapping(value="/applyForJob/{jobId}")
	public ResponseEntity<Job> applyForJob(@PathVariable("jobId") long jobId,HttpSession session)
	{
		
		long loggedInUserId = (Long)session.getAttribute("loggedInUserId");
		JobApplication jobApplication=new JobApplication();
		jobApplication.setJob(jobDAO.get(jobId));
		jobApplication.setStatus("New");
		jobApplication.setDateapplied(new java.util.Date());
		jobApplication.setSignup(signupDAO.getUserByUserId(loggedInUserId));
		
		return new ResponseEntity<Job>(HttpStatus.CREATED);
	}
	
	
	@PostMapping("/jobposting")
	public ResponseEntity<Job> jobPosting(@RequestBody Job job)
	{
	jobDAO.add(job);
	return new ResponseEntity<Job>(job, HttpStatus.OK);
	}
}

