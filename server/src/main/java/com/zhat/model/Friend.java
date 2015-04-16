package com.zhat.model;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import net.spy.memcached.MemcachedClient;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.zhat.constants.MemcachedConstants;
import com.zhat.constants.MemcachedConstantsHelper;
import com.zhat.hibernate.MySQLSessionFactory;
import com.zhat.memcached.MemcachedManager;

@Entity
@Table(name = "friend")
public class Friend implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8113924992531997675L;

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
    	friend.setFriendUserId(friendId);
    	
    	session.save(friend);
    	session.getTransaction().commit();
    	session.flush();
    	session.close();
	}
	
	public static List<Friend> getAllFriendsInDB(int userId) {
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
	
	@SuppressWarnings("unchecked")
	public static List<Friend> getAllFriendsInCache(int userId) {
		MemcachedClient client = MemcachedManager.getInstance().getMemcachedClient();
		Object result = null;
		Future<Object> future = client.asyncGet(MemcachedConstantsHelper.getKeyAllFriends(userId));
		try {
			result = future.get(MemcachedConstants.READ_TIMEOUT, MemcachedConstants.TIME_UNIT);
			return (List<Friend>)result;
		}
		catch (TimeoutException e) {
			return null;
		}
		catch (InterruptedException e) {
			return null;
		}
		catch (ExecutionException e) {
			return null;
		}
	}
	
	public static void setAllFriendsInCache(int userId, List<Friend> allFriendsList) {
		MemcachedClient client = MemcachedManager.getInstance().getMemcachedClient();
		client.set(MemcachedConstantsHelper.getKeyAllFriends(userId)
				, MemcachedConstants.CACHE_EXPIRATION, allFriendsList);
//		String key = MemcachedConstantsHelper.getKeyAllFriends(userId);
//		client.set(key, MemcachedConstants.CACHE_EXPIRATION, "xxxx");
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
	
	public int getFriendUserId() {
		return friendUserId;
	}
	
	public void setFriendUserId(int friendId) {
		this.friendUserId = friendId;
	}
}
