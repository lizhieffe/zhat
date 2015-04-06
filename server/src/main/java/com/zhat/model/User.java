package com.zhat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.zhat.hibernate.MySQLSessionFactory;
import com.zhat.http.exceptions.ZLHttpRequestContentException;

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
	
	@Column(name = "firstname")
	private String firstName;
	
	@Column(name = "lastname")
	private String lastName;
		
	private String sex;
	private String email;

	public User() {}
	
	public static void addUser(String firstName, String lastName, String sex, String email)
			throws ZLHttpRequestContentException {
		Session session = MySQLSessionFactory.openSession();
    	session.beginTransaction();
    	
    	User user = new User();
    	user.setFirstName(firstName);
    	user.setLastName(lastName);
    	
    	if (!sex.equalsIgnoreCase(SEX_MALE) && !sex.equalsIgnoreCase(SEX_FEMALE)
    			&& !sex.equalsIgnoreCase(SEX_UNKNOWN))
    		throw new ZLHttpRequestContentException("Wrong sex value.");
    	
    	user.setEmail(email);
    	user.setSex(sex);
    	session.save(user);
    	
    	session.getTransaction().commit();
    	session.flush();
    	session.close();
	}
	
	public static User getUserById(int id) {
		Session session = MySQLSessionFactory.openSession();
    	session.beginTransaction();
    	
    	Criteria criteria = session.createCriteria(User.class);
    	User result = (User) criteria.add(Restrictions.eq("id", id)).uniqueResult();
    	
    	session.getTransaction().commit();
    	session.flush();
    	session.close();
    	
    	return result;
	}
	
	public static User getUserByEmail(String email) {
		Session session = MySQLSessionFactory.openSession();
    	session.beginTransaction();
    	
    	Criteria criteria = session.createCriteria(User.class);
    	User result = (User) criteria.add(Restrictions.eq("email", email)).uniqueResult();
    	
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
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String name) {
		this.firstName = name;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String name) {
		this.lastName = name;
	}
	
	public String getSex() {
		return sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}
