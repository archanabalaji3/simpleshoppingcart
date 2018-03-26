package com.niit.shoppingcart.daoimpl;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.shoppingcart.domain.User;

@Transactional
@Repository                                            //Create instance UserDAOimpl
public class UserDAOimpl {
    @Autowired
	private SessionFactory sessionFactory;
    @Autowired
	private User user;
    
    public boolean save(User user)
    {try 
		{   user.setRole('C');
			user.setRegisteredDate(new Date(System.currentTimeMillis())+"");
			sessionFactory.getCurrentSession().save(user);
			return true;
		}
    catch (HibernateException e) 
        { e.printStackTrace();
		  return false;
		}
    }
    
    public boolean update(User user)
    {try 
		{sessionFactory.getCurrentSession().update(user);
		  return true;
		}
	 catch (HibernateException e)
        {e.printStackTrace();
        return false;
        }
	}
    
    private User get(String email)    //To fetch the user details based on email and stored in User 
    {return sessionFactory.getCurrentSession().get(User.class, email);}
    
    public boolean delete(String email)
    {try 
		{ user= get(email);
		  if(user==null)
		  {
			  return false;
		  }
		  sessionFactory.getCurrentSession().delete(user);
		  return true;
		}
	catch (HibernateException e) 
       {e.printStackTrace();
	   return false;
       }
	 }

    public List<User> list()
    {
    	return sessionFactory.getCurrentSession().createQuery("from User").list();
    }
   
    public User Validate(String email, String password)
    {
    return(User)sessionFactory.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("Email", email)).add(Restrictions.eq("Password", password)).uniqueResult();
    }

}	