package com.mahi.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.mahi.model.Job;
import com.mahi.model.JobApplication;

@EnableTransactionManagement
@Transactional
@Repository("jobapplicationdao")
public class JobApplicationDaoImpl implements JobApplicationDao
{

	@Autowired
	SessionFactory sessionFactory;
	
	public boolean addJobapp(JobApplication jobapp) {
		System.out.println("im in addjobapp");
		try{
		Session s=	sessionFactory.openSession();
			Transaction tx= s.beginTransaction();
			s.save(jobapp);
			tx.commit();
			System.out.println("added job successfully");
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}

	public JobApplication get(int id) {
		return sessionFactory.getCurrentSession().get(JobApplication.class, id);
	}

public void deleteJobApp(int id) {
// TODO Auto-generated method stub
System.out.println("im in deletjobapp");
try{
 Query q= sessionFactory.getCurrentSession().createQuery("DELETE JobApp WHERE Id = :ID");
 q.setParameter("ID", id);
 q.executeUpdate();
 System.out.println("job applicatino deleted");

}catch (Exception e) {
	// TODO: handle exception
	System.out.println(e);
	
}
	}

	
	public void updateJobApp(JobApplication jobapp) {
		// TODO Auto-generated method stub
		try{
			sessionFactory.getCurrentSession().update(jobapp);
			
			System.out.println("job application updated");
		}catch(Exception e){
			System.out.println(e);
		
		}
		
	}

	public List<JobApplication> allJObApps() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("From JobApp").getResultList();
	
	}

	
}
