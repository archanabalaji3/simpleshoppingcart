package com.niit.shoppingcart.dao;

import java.util.List;
import com.niit.shoppingcart.domain.User;

public interface UserDAO {

	public boolean save(User user);                       //Create new user
	public boolean update(User user);                     //Update the existing user
	public User get(String email);                        //Get particular user detail
	public boolean delete(String email);                  //Delete the selected user
	public List<User> list();                             //Get all user list
	public User validate(String email, String password);  //Validate whether the credentials correct or not
	
}
