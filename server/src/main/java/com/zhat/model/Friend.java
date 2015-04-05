package com.zhat.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.zhat.hibernate.MySQLSessionFactory;

@Entity
@Table(name = "friend")
public class Friend {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "friend_user_id")
	private int friendUserId;

	public Friend() {}
	
	public static void addFriend(int userId, int friendId) {
		Session session = MySQLSessionFactory.openSession();
    	session.beginTransaction();
    	
    	Friend friend = new Friend();
    	friend.setUserId(userId);
    	friend.setFriendId(friendId);
    	
    	session.save(friend);
    	session.getTransaction().commit();
    	session.flush();
    	session.close();
	}
	
	public static List<Friend> getAllFriends(int userId) {
		Session session = MySQLSessionFactory.openSession();
    	session.beginTransaction();
    	
    	Criteria criteria = session.createCriteria(Friend.class);
    	@SuppressWarnings("unchecked")
		List<Friend> result = criteria.add(Restrictions.eq("userId", userId)).list();
    	
    	session.getTransaction().commit();
    	session.flush();
    	session.close();
    	
    	return result;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getFriendId() {
		return friendUserId;
	}
	
	public void setFriendId(int friendId) {
		this.friendUserId = friendId;
	}
}
