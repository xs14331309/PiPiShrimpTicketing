package com.ppshrimp.filmsystem.persistence.dao;

import java.util.ArrayList;
import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ppshrimp.filmsystem.persistence.entity.Cinema;
import com.ppshrimp.filmsystem.persistence.entity.CinemaMoviePos;

@Repository
public class CinemaDaoImpl implements CinemaDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cinema> getAll() throws ClassCastException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Cinema.class);
        return (List<Cinema>)criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cinema> getByAddr(String city) {
		String hql = "from Cinema c where c.address like :param" ;  
    	List<Cinema> cinemas = (List<Cinema>)sessionFactory.getCurrentSession()
						        		.createQuery(hql)
						        		.setString("param", "%" + city + "%")
						        		.list();
    	return cinemas;
	}
	
	@Override
	public List<Cinema> getByPos(float lo, float la, float threshold) {
		// TODO Auto-generated method stub
		float lo_d = lo - threshold;
		float lo_u = lo + threshold;
		float la_d = la - threshold / 2;
		float la_u = la + threshold / 2;
		String hql = "from Cinema c where (c.longitude between :lo_d and :lo_u) and (c.latitude between :la_d and :la_u)" ;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("lo_d", lo_d)
		     .setParameter("lo_u", lo_u)
		     .setParameter("la_d", la_d)
		     .setParameter("la_u", la_u);
		@SuppressWarnings("unchecked")
		List<Cinema> cinemas = (List<Cinema>) query.list();
		return cinemas;
	}

	@Override
	public List<CinemaMoviePos> getById(long id) {
		Cinema cinema = (Cinema) sessionFactory.getCurrentSession().get(Cinema.class, id);
    	List<CinemaMoviePos> cmPos = new ArrayList<>(cinema.getCmPos());
    	System.out.print(cmPos.size());
		return cmPos;
	}



}
