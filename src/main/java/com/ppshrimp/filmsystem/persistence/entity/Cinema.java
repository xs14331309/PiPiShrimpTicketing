package com.ppshrimp.filmsystem.persistence.entity;

import java.io.Serializable;

public class Cinema implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6173689947648900782L;
    
	private long cinemaId;
	private String ciname;
	private String address;
	private String phone;
	
	public Cinema() {
		super();
	}
	
	public Cinema(String ciname, String address, String phone) {
		this.ciname = ciname;
		this.address = address;
		this.phone = phone;
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

}
