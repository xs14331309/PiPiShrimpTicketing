package com.ppshrimp.filmsystem.persistence.dao;


import java.sql.Date;
import java.util.List;

import com.ppshrimp.filmsystem.persistence.entity.Movie;

public interface MovieDao {
    List<Movie> findAll();
    
    List<Movie> findAllOnShow(Date date);
    
    //List<Movie> findTopTen(Date date);
    
    List<Movie> findRecommend(Date date);
    
    List<Movie> findCommingSoon(Date now, Date After);
    
    List<Movie> searchMovieName(String msg);
    
    List<Movie> searchDirector(String msg);
    
    List<Movie> searchActor(String msg);
    
    List<Movie> searchType(String msg);
    
    Movie findOneById(long id);
    
    Movie findOneByName(String name);
    
}
