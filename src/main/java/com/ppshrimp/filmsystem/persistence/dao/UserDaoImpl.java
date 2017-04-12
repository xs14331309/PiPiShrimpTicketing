package com.ppshrimp.filmsystem.persistence.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ppshrimp.filmsystem.persistence.entity.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<User>  findAll() {
    	Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        return (List<User>)criteria.list();
    }
    
    public User findOne(String name) {
        String hql = "from User u where u.name=?";  
        Query query = sessionFactory.getCurrentSession().createQuery(hql);  
        query.setString(0, name);  
        System.out.println(((User)query.uniqueResult()).toString());
        return (User)query.uniqueResult();  
    	
    }
    
    public int addOne(User user) {
        return (Integer) sessionFactory.getCurrentSession().save(user);	
    }
    
    public int deleteOne(String name) {
    	return 0;
    }
}
