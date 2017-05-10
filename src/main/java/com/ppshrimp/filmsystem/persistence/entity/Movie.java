package com.ppshrimp.filmsystem.persistence.entity;

import java.io.Serializable;

public class Movie implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7373578329130106831L;
	
	// 依次是id、电影名、宣传照地址、导演、主演、简介、电影长度、评分
	private long movieId;
	private String moviename;
	private String imgUrl;
	private String director;
    private String actor;
    private String brief;
    private int length;
    private float score;
    
    /*
     * private String type;
     * private String language;
     * */
    
    public Movie() {
    	super();
    }
    
	public Movie(String moviename, String actor, String brief, String director, int length, float score) {
		this.moviename = moviename;
		this.actor = actor;
		this.brief = brief;
		this.director = director;
		this.length = length;
		this.score = score;
	}
	
	public long getMovieId() {
		return movieId;
	}
	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}
	public String getMoviename() {
		return moviename;
	}
	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
}
