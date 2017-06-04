package com.ppshrimp.filmsystem.persistence.dao;

import java.sql.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ppshrimp.filmsystem.persistence.entity.Movie;

@Repository
public class MovieDaoImpl implements MovieDao {
	
    @Autowired
    private SessionFactory sessionFactory;
    
    @SuppressWarnings("unchecked")
    @Override
	public List<Movie> findAll() throws ClassCastException {
    	Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Movie.class);
        return (List<Movie>)criteria.list();
    }
    
    // date在上映期间
	@Override
	public List<Movie> findAllOnShow(Date date) {
		String hql = "from Movie m where ? between m.releaseTime and m.shelfTime Order by m.releaseTime desc";
		
		@SuppressWarnings("unchecked")
		List<Movie> movies = sessionFactory.getCurrentSession()
        		.createQuery(hql)
        		.setParameter(0, date)
        		.list();  
		
		return movies;
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
    	List<Movie> movies = sessionFactory.getCurrentSession()
						        		.createQuery(hql)
						        		.setString(0, name)
						        		.list();  
        
        return movies.isEmpty() ? null : movies.get(0);
	}

	@Override
	public List<Movie> findTopTen(Date date) {
        String hql = "from Movie m where ? between m.releaseTime and m.shelfTime Order by m.score desc";
		
		@SuppressWarnings("unchecked")
		List<Movie> movies = sessionFactory.getCurrentSession()
        		.createQuery(hql)
        		.setParameter(0, date)
        		.list();  
		
		return movies;
	}


}
