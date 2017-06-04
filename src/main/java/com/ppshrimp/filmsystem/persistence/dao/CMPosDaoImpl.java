package com.ppshrimp.filmsystem.persistence.dao;

import java.util.List;

import javax.management.Query;

import org.aspectj.apache.bcel.generic.ReturnaddressType;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ppshrimp.filmsystem.persistence.entity.CinemaMoviePos;

import aj.org.objectweb.asm.Type;

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

	@Override
	public String getPos(long cid, long mid, int tnum, int hnum) {
		try{
			String hql = "select pos from CinemaMoviePos c where c.cinema.cinemaId = :cid and c.movie.movieId = :mid "
					+ "and c.tnum = :tnum and c.hnum = :hnum";
			String pos = (String) sessionFactory.getCurrentSession()
					.createQuery(hql)
					.setLong("cid", cid)
					.setLong("mid", mid)
					.setInteger("tnum", tnum)
					.setInteger("hnum", hnum)
					.uniqueResult();
			return pos;
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public boolean modifyPos(String newPos, long cid, long mid, int tnum, int hnum) {
		try{
			String hql = "Update CinemaMoviePos c set c.pos = :newPos where c.cinema.cinemaId = :cid and c.movie.movieId = :mid "
					+ "and c.tnum = :tnum and c.hnum = :hnum";
			int res = sessionFactory.getCurrentSession().createQuery(hql)
					    .setString("newPos", newPos)
						.setLong("cid", cid)
						.setLong("mid", mid)
						.setInteger("tnum", tnum)
						.setInteger("hnum", hnum)
						.executeUpdate();
		    return true;
		} catch (Exception e) {
			return false;
		}
	}

}
