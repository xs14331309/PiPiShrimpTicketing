package com.ppshrimp.filmsystem.persistence.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ppshrimp.filmsystem.persistence.entity.Order;

@Repository
public class OrderDaoImpl implements OrderDao {
    
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean create(Order o) {
		try{
			Session session = sessionFactory.getCurrentSession();
			session.save(o);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getOrdersByUsername(String name) {
		String hql = "From Order o where o.username = :name";
		List<Order> orders = (List<Order>)sessionFactory.getCurrentSession()
				.createQuery(hql)
				.setString("name", name)
				.list();
		return orders;
	}

}
