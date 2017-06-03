package com.mahi.dao;

import java.util.List;

import com.mahi.model.SignUp;

public interface SignUpDao
{

	public void addUser(SignUp user);
	public void updateUser(SignUp user);
	public void deleteUser(SignUp user);
	public List<SignUp> listUsers();
	public List<SignUp> listUsers(long userid);
	public SignUp getUserByUserId(long userid);
	public SignUp getUserByUsername(String username);
	public boolean isExistingUser(SignUp user);
	public SignUp getEmailId(String emailid,String password);
	public boolean authenticate(String username,String password);
	
}
