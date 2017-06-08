package com.ppshrimp.filmsystem.persistence.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class Cinema implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6173689947648900782L;
    
	private long cinemaId;
	private String ciname;
	private String city ; // 城市
	private String area; // 区
	private String address;
	private String phone;
	private float longitude; // 维度
	private float latitude; // 经度
	
	private Set<CinemaMoviePos> cmPos = new HashSet<>();
	
	public Cinema() {
		super();
	}
	
	public long getCinemaId() {
		return cinemaId;
	}
	public void setCinemaId(long cinemaId) {
		this.cinemaId = cinemaId;
	}
	public String getCiname() {
		return ciname;
	}
	public void setCiname(String ciname) {
		this.ciname = ciname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public Set<CinemaMoviePos> getCmPos() {
		return cmPos;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	

	// 不加@JsonIgnore则造成Json解析的双向循环，导致崩溃
	@JsonBackReference
	public void setCmPos(Set<CinemaMoviePos> cmPos) {
		this.cmPos = cmPos;
	}

}
