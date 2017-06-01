package com.ppshrimp.filmsystem.persistence.dao;

import java.util.List;

import com.ppshrimp.filmsystem.persistence.entity.Cinema;
import com.ppshrimp.filmsystem.persistence.entity.CinemaMoviePos;

public interface CinemaDao {
    List<Cinema> getAll();
    
    List<Cinema> getByAddr(String city);
    
    List<CinemaMoviePos> getById(long id);
}
