package com.ppshrimp.filmsystem.persistence.dao;

import java.util.List;

import com.ppshrimp.filmsystem.persistence.entity.User;

public interface UserDao {
    List<User>  findAll();
    
    User findOne(String name);
    
    void addOne(User user);
}
