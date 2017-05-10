package com.ppshrimp.filmsystem.persistence.dao;

import java.util.List;

import com.ppshrimp.filmsystem.persistence.entity.Movie;

public interface MovieDao {
    List<Movie> findAll();
    
    Movie findOneById(long id);
    
    Movie findOneByName(String name);
}
