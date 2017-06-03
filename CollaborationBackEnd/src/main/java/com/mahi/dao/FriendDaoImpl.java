package com.mahi.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.mahi.model.Friend;
import com.mahi.model.SignUp;

@EnableTransactionManagement
@Repository("friendDAO")
@Transactional

public class FriendDaoImpl implements FriendDao
{

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	SignUpDao signupDao;
	
	public void addFriend(Friend friend) {
		Session session=sessionFactory.getCurrentSession();
		Friend friend2=new Friend();
		friend2.setUser(friend.getFriend());
		friend2.setFriend(friend.getUser());
		friend2.setOnline(false);
		friend2.setStatus("New");
		session.saveOrUpdate(friend);
		session.saveOrUpdate(friend2);
		
	}

	public void updateFriend(Friend friend) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(friend);
		
	}

	public void deleteFriend(long id) {
		Session session=sessionFactory.getCurrentSession();
		Friend friend=(Friend)session.createQuery("from Friend where id="+id).getSingleResult();
		session.delete(friend);
		
	}

	public Friend getFriend(long userid, long friendId) {
		Session session=sessionFactory.getCurrentSession();
		
		SignUp user=signupDao.getUserByUserId(userid);
		SignUp friendUser=signupDao.getUserByUserId(friendId);
		Friend friend=(Friend)session.createQuery("from Friend where userid="+friendUser.getUserid()+" and friendId="+user.getUserid()).getSingleResult();
		return friend;
	}

	public List<Friend> listMyFriends(long userid) {
		Session session=sessionFactory.getCurrentSession();
		SignUp user=signupDao.getUserByUserId(userid);
		List<Friend> friends=session.createQuery("from Friend where userid="+userid+" and status='Accepted'").getResultList();
		return friends;
	}

	public List<Friend> listNewFriendRequests(long userid) {
		Session session=sessionFactory.getCurrentSession();
		SignUp user=signupDao.getUserByUserId(userid);
		List<Friend> friends=session.createQuery("from Friend where friendId="+userid+" and status='New'").getResultList();
		return friends;
	}

	public void setOnline(long userid) {
		Session session=sessionFactory.getCurrentSession();
		session.createQuery("update Friend set isOnline=true where friendId="+userid).executeUpdate();
		
	}

	public void setOffline(long userid) {
		Session session=sessionFactory.getCurrentSession();
		session.createQuery("update Friend set isOnline=false where friendId="+userid).executeUpdate();
		
	}
	
}
