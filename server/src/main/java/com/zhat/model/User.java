package com.zhat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;

import com.zhat.hibernate.MySQLSessionFactory;
import com.zhat.model.exceptions.UserSexException;

@Entity
@Table(name = "user")
public class User {
	
	public static String SEX_MALE = "m";
	public static String SEX_FEMALE = "f";
	public static String SEX_UNKNOWN = "u";
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	private String sex;
	
	public User() {}
	
	public static void addUser(String name, String sex) throws UserSexException {
		Session session = MySQLSessionFactory.openSession();
    	session.beginTransaction();
    	
    	User user = new User();
    	user.setName(name);
    	
    	if (!sex.equalsIgnoreCase(SEX_MALE) && !sex.equalsIgnoreCase(SEX_FEMALE)
    			&& !sex.equalsIgnoreCase(SEX_UNKNOWN))
    		throw new UserSexException();
    	
    	user.setSex(sex);
    	session.save(user);
    	
    	session.getTransaction().commit();
    	session.flush();
    	session.close();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSex() {
		return sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
}
