package com.mahi.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="JobAppl",schema="mahitha")
@Entity
public class JobApplication
{

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int jobappid;
	
	@ManyToOne
	@JoinColumn(name="userid")
	private SignUp signup;
	
	@ManyToOne
	@JoinColumn(name="id")
	private Job job;
	
	private Date dateapplied;
	private String remarks;
	private String status;
	public int getJobappid() {
		return jobappid;
	}
	public void setJobappid(int jobappid) {
		this.jobappid = jobappid;
	}
	public SignUp getSignup() {
		return signup;
	}
	public void setSignup(SignUp signup) {
		this.signup = signup;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public Date getDateapplied() {
		return dateapplied;
	}
	public void setDateapplied(Date dateapplied) {
		this.dateapplied = dateapplied;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
