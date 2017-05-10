package com.ppshrimp.filmsystem.persistence.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ppshrimp.filmsystem.persistence.entity.Movie;

@Repository
public class MovieDaoImpl implements MovieDao {
	
    @Autowired
    private SessionFactory sessionFactory;
    
    @SuppressWarnings("unchecked")
    @Override
	public List<Movie> findAll() {
    	Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Movie.class);
        return (List<Movie>)criteria.list();
    }
    
    @Override
    public Movie findOneById(long id) {
    	Movie movie = (Movie) sessionFactory.getCurrentSession().get(Movie.class, id);
    	return movie;
    }
   
	@SuppressWarnings("unchecked")
	@Override
	public Movie findOneByName(String name) {
    	String hql = "from Movie m where m.moviename=?";  
        Query query = sessionFactory.getCurrentSession().createQuery(hql);  
        query.setString(0, name);  
        List<Movie> movies = query.list();
        return movies.isEmpty() ? null : movies.get(0);
	}
}
