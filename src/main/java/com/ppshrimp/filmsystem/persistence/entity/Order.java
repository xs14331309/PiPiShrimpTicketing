package com.ppshrimp.filmsystem.persistence.entity;

import java.io.Serializable;

public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5164105003305135233L;
	
    
	private long orderId;
	private String username;
	private long cinemaId;
	private long movieId;
	private int tnum;
	private int hnum;
	private int seat;

	// 待定
	// private float price;
	
	public Order() {
		super();
	}
	
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
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

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public long getCinemaId() {
		return cinemaId;
	}

	public void setCinemaId(long cinemaId) {
		this.cinemaId = cinemaId;
	}

	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}


}
