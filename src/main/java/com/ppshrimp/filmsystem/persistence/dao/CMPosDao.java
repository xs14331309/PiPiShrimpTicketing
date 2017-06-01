package com.ppshrimp.filmsystem.persistence.dao;

import java.util.List;

import com.ppshrimp.filmsystem.persistence.entity.CinemaMoviePos;

public interface CMPosDao {
    public List<CinemaMoviePos> getAllbyCM(long cid, long mid);
}
