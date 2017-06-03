package com.mahi.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.mahi.model.SignUp;

@EnableTransactionManagement
@Transactional
@Repository("signupDao")

public class SignUpDaoImpl implements SignUpDao
{

	@Autowired
	SessionFactory sessionFactory;

	
	
	public void addUser(SignUp user) 
	{
		System.out.println("i am in add signup dao");
		Session session=sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(user);
		tx.commit();

		
	}
	
	public void updateUser(SignUp user) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
		
		
	}

	public void deleteUser(SignUp user) {
		Session session=sessionFactory.getCurrentSession();
		session.delete(user);
		
	}

	
	@SuppressWarnings("unchecked")
	public List<SignUp> listUsers() {
		Session session=sessionFactory.openSession();
		List<SignUp> users=session.createQuery("from SignUp").getResultList();
		
		return users;
	}
	
public SignUp getUserByUserId(long userid) {
		
		Session session=sessionFactory.getCurrentSession();
		SignUp user=(SignUp)session.createQuery("from SignUp where userid="+userid).getSingleResult();
		
		return user;
	}

	public SignUp getUserByUsername(String username) {
		Session session=sessionFactory.getCurrentSession();
		SignUp user=(SignUp)session.createQuery("from SignUp where username='"+username+"'").getSingleResult();
		
		return user;
	}

	public boolean isExistingUser(SignUp user) {
		SignUp u=null;
		try {
		u=getUserByUsername(user.getUsername());
		}catch(NoResultException nre){
			
		}
		if(u!=null)
		{
			return true;
		}
		else
			return false;
}
	
	public boolean authenticate(String username, String password) {
		Session session=sessionFactory.getCurrentSession();
		SignUp user;
		try{
		user=(SignUp)session.createQuery("from SignUp where username='"+username+"' and password='"+password+"'").getSingleResult();
		return true;
		}catch(NoResultException nre)
		{
			
		}
		
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<SignUp> listUsers(long userid) {
		Session session=sessionFactory.getCurrentSession();
		List<SignUp> users=session.createQuery("from SignUp where userid<>"+userid).getResultList();
		
		return users;
	}

	public SignUp getEmailId(String emailid, String password) 
	{
		Session session=sessionFactory.getCurrentSession();
		SignUp usermail=(SignUp)session.createQuery("from SignUp where emailid='"+emailid+"' and password='"+password+"'").getSingleResult();
		
		return usermail;
	}

	
}
