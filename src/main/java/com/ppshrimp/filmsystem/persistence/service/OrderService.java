package com.ppshrimp.filmsystem.persistence.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppshrimp.filmsystem.persistence.dao.OrderDao;
import com.ppshrimp.filmsystem.persistence.entity.Order;

@Service("orderService")
@Transactional
public class OrderService {
    
	@Autowired
	private OrderDao orderDao;
	
	public boolean create(Order o) {
		return orderDao.create(o);
	}
	
	public List<Order> getOrderByUserName(String name) {
		return orderDao.getOrdersByUsername(name);
	}
}
