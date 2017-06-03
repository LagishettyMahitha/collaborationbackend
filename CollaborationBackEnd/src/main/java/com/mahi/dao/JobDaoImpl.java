package com.mahi.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.mahi.model.Job;
import com.mahi.model.JobApplication;

@EnableTransactionManagement
@Transactional
@Repository("jobDAO")
public class JobDaoImpl implements JobDao
{

	@Autowired(required = true)
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		   this.sessionFactory = sessionFactory;
	}
	
	public List<Job> list() {
		String hql = "from Job";

		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings({ "unchecked", "deprecation" })
		List<Job> list = (List<Job>) query.list();

		return list;

		}

		@SuppressWarnings("deprecation")
		public Job get(long id) {
		Session session=sessionFactory.getCurrentSession();
		Job job=(Job)session.createQuery("from Job where id="+id).getSingleResult();
		return job;

		}

		public void add(Job job) {

		System.out.println("i am in add job dao");
		Session session=sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(job);
		tx.commit();

		}

		public void delete(int id) {
		Job job = new Job();
		job.setId(id);
		sessionFactory.getCurrentSession().delete(job);
		}

	
}
