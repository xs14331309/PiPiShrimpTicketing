package com.ppshrimp.filmsystem.util;

import java.io.Serializable;

import com.ppshrimp.filmsystem.persistence.entity.CinemaMoviePos;

public class ThHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6173877781603642904L;
	
    private long movieId;
	private int tnum;
	private int hnum;
	private float price;
	private String time;
	
	public ThHelper() {
		this.movieId = -1;
		this.tnum = -1;
		this.hnum = -1;
	}
	
	public ThHelper(long movieId, int tnum, int hnum, float price,  String time) {
		this.movieId = movieId;
		this.tnum = tnum;
		this.hnum = hnum;
	}
	
	public ThHelper (CinemaMoviePos m) {
		this(m.getMovie().getMovieId(), m.getTnum(), m.getHnum(), m.getPrice(), m.getTime());
	}
	
	// getter and setter
	public long getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public int getTnum() {
		return tnum;
	}
	public void setTnum(int tnum) {
		this.tnum = tnum;
	}
	public int getHnum() {
		return hnum;
	}
	public void setHnum(int hnum) {
		this.hnum = hnum;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
    
}
