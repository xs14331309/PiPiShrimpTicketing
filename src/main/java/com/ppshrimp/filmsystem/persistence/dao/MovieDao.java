package com.ppshrimp.filmsystem.persistence.dao;


import java.sql.Date;
import java.util.List;

import com.ppshrimp.filmsystem.persistence.entity.Movie;

public interface MovieDao {
    List<Movie> findAll();
    
    List<Movie> findAllOnShow(Date date);
    
    Movie findOneById(long id);
    
    Movie findOneByName(String name);
    
}
