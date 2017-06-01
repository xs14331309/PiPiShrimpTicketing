package com.ppshrimp.filmsystem.persistence.entity;

import java.io.Serializable;

public class CinemaMoviePos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3419330332566186343L;
	
	private long cmId;
	private Cinema cinema;
	private Movie movie;
	private String pos; // '01010'
	private int tnum; // 时间场次
	private int hnum; // 影厅
	private float price;
	
	public CinemaMoviePos() {
		super();
	}

	public long getCmId() {
		return cmId;
	}

	public void setCmId(long cmId) {
		this.cmId = cmId;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
