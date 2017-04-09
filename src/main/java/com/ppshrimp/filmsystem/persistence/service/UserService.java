package com.ppshrimp.filmsystem.persistence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppshrimp.filmsystem.persistence.dao.UserDao;
import com.ppshrimp.filmsystem.persistence.entity.User;

@Service("userService")
public class UserService {
	@Autowired
	private UserDao userDao;
        
	public UserService(UserDao dao) {
		userDao = dao;
	}
	
	public void create(final User user) {
	    userDao.addOne(user);
	}
	
	public List<User> findAll() {
		return userDao.findAll();
	}
	
	public User findByName(final String name) {
		return userDao.findOne(name);
	}
}
