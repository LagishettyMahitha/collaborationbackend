package com.mahi.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.mahi.model.Blog;

@EnableTransactionManagement
@Repository("blogDAO")
@Transactional

public class BlogDaoImpl implements BlogDao
{

	@Autowired
	SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		   this.sessionFactory = sessionFactory;
		}
	
	public void addBlog(Blog blog) {
		blog.setStatus("New");
		Session session=sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(blog);
		tx.commit();
		
	}

	public void updateBlog(Blog blog) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(blog);
		
	}

	public void deleteBlog(Blog blog) {
		Session session=sessionFactory.getCurrentSession();
		session.delete(blog);
		
	}



	public List<Blog> listBlogs() {
		Session session=sessionFactory.getCurrentSession();
		List<Blog> blogs=session.createQuery("from Blog").getResultList();
		
		return blogs;
	}
	
	public List<Blog> listNewBlogs() {
		Session session=sessionFactory.getCurrentSession();
		List<Blog> blogs=session.createQuery("from Blog where status='New'").getResultList();
		
		return blogs;
	}


	public Blog getBlogId(long blogId) {
		Session session=sessionFactory.getCurrentSession();
		Blog blog=(Blog)session.createQuery("from Blog where blogId="+blogId).getSingleResult();
		return blog;
	}
	
	
}
