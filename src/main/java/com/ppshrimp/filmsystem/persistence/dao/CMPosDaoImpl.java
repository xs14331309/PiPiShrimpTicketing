package com.ppshrimp.filmsystem.persistence.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ppshrimp.filmsystem.persistence.entity.CinemaMoviePos;

@SuppressWarnings("unchecked")
@Repository
public class CMPosDaoImpl implements CMPosDao {

	@Autowired
    private SessionFactory sessionFactory;
	
	
	
	@Override
	public List<CinemaMoviePos> getAllbyCM(long cid, long mid) {
		// hql查询表名为实体名，注意大小写
		String hql = "from CinemaMoviePos c where c.cinema.cinemaId = :cid and c.movie.movieId = :mid order by tnum asc";

		List<CinemaMoviePos> cmPos = sessionFactory.getCurrentSession()
        		.createQuery(hql)
        		.setLong("cid", cid)
        		.setLong("mid", mid)
        		.list();
		
		return cmPos;
	}

}
