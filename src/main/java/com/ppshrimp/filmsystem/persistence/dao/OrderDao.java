package com.ppshrimp.filmsystem.persistence.dao;

import java.util.List;

import com.ppshrimp.filmsystem.persistence.entity.Order;

public interface OrderDao {
    boolean create(Order o);
    
    List<Order> getOrdersByUsername(String name);
    
}
