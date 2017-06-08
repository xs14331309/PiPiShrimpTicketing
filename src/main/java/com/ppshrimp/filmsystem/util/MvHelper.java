package com.ppshrimp.filmsystem.util;

import java.io.Serializable;

import com.ppshrimp.filmsystem.persistence.entity.Movie;

public class MvHelper implements Serializable, Comparable<MvHelper> {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -7488497381564493504L;
	
	private long movieId;
    private String url;
    
    public MvHelper() {
    	super();
    }
    
    public MvHelper(Movie m) {
    	setMovieId(m.getMovieId());
    	setUrl(m.getImgUrl());
    }

	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public int compareTo(MvHelper o) {
		long otherMovieId = o.getMovieId();
	    return Long.compare(this.movieId, otherMovieId);
	}
}
