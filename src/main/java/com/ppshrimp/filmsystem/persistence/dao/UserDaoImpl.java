package com.ppshrimp.filmsystem.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ppshrimp.filmsystem.persistence.entity.User;

@Repository
public class UserDaoImpl implements UserDao {
	
    public List<User>  findAll() {
    	List<User> users = new ArrayList<User> ();
    	User user1 = new User("123", "123");
    	User user2 = new User("hello", "hello");
    	users.add(user1);
    	users.add(user2);
    	return users;
    }
    
    public User findOne(String name) {
/*    	for (User user : users) {
			if (user.getName().equals(name))
				return user;
		}*/
    	User user1 = new User("123", "123");
    	return user1;
    }
    
    public void addOne(User user) {
        //users.add(user);	
    }
}
