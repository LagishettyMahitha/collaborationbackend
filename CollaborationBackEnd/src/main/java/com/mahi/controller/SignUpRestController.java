package com.mahi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mahi.dao.SignUpDao;
import com.mahi.model.SignUp;

@RestController
public class SignUpRestController 
{

	@Autowired
	SignUpDao userDao;

	@GetMapping(value="/user/")
	public ResponseEntity<List<SignUp>>  listAllUsers()
	{
		
        List<SignUp> users = userDao.listUsers();
  
        if(users.isEmpty()){
            return new ResponseEntity<List<SignUp>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<SignUp>>(users, HttpStatus.OK);

	}
	
	@PostMapping(value = "/users/")
    public ResponseEntity<Void> createUser(@RequestBody SignUp user) {
        System.out.println("Creating User " + user.getUsername());
  
        if (userDao.isExistingUser(user)) {
            System.out.println("A User with name " + user.getUsername() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
  
        userDao.addUser(user);
  
       
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
	
	@PostMapping("/login")
	public ResponseEntity<SignUp> loginemail(@RequestBody SignUp user)
	{
		System.out.println("get the email id :"+user.getEmailid());
		
		SignUp usere = userDao.getEmailId(user.getEmailid(),user.getPassword());
		if(usere!=null)
		{
		return new ResponseEntity<SignUp>(usere,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<SignUp>(user,HttpStatus.UNAUTHORIZED);
		}
	}
	
}	