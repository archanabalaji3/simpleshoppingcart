package com.niit.shoppingcart.samplebackend;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.shoppingcart.dao.UserDAO;
import com.niit.shoppingcart.domain.User;

public class UserDAOTestcase {
    private static AnnotationConfigApplicationContext context;
	@Autowired(required=true)
	private static UserDAO userDAO;
	@Autowired(required=true)
	private static User user;
	
	
	@BeforeClass
	public static void init()
	{
	context = new AnnotationConfigApplicationContext(); 
	context.scan("com.niit");    //scan the complete package and check for annotations like @Component, @Controller, @Repository, @Service
	context.refresh();  //clear the context(bean factory) and recreate all the instances of the classes which are there in com.niit with proper annotations.
	userDAO=(UserDAO)context.getBean("userDAO");                        //ask the context to get instance of UserDAO
	
	user = (User)context.getBean("user");
	}
	
	@Test
	public void saveUserTestCase()
	{
		user = new User();
		user.setEmail("priya123@gail.com");
		user.setMobile("9087654321");
		user.setName("priya");
		user.setPassword("123");
		
	  boolean status = 	userDAO.save(user);
	  
	  assertEquals("save user test case", true, status);
	}
}
