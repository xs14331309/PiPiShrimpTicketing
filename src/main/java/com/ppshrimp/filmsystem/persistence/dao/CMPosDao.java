package com.ppshrimp.filmsystem.persistence.dao;

import java.util.List;

import com.ppshrimp.filmsystem.persistence.entity.CinemaMoviePos;

public interface CMPosDao {
    public List<CinemaMoviePos> getAllbyCM(long cid, long mid);
    
    public String getPos(long cid, long mid, int tnum, int hnum);

	public boolean modifyPos(String newPos, long cid, long mid, int tnum, int hum);

 }
