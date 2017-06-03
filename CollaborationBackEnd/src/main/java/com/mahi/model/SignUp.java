package com.mahi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
@Table(name="SignUp",schema="mahitha")
@Entity
public class SignUp
{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int userid;
	@Size(min=5,message="minimum characters should be 5")
	private String username;
	@Size(min=6,message="minimum characters should be 6")
	private String password;
	@Column(unique=true)
	private String emailid;
	@Column(unique=true)
	@Size(min=10,message="minimum characters should be 10")
	private String mobileno;
	private String role;
	
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}
