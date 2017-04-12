package com.ppshrimp.filmsystem.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class User implements Serializable  {
     
	/**
	 * 
	 */
	private static final long serialVersionUID = 2828011182748819042L;
	
	@Id
	@GeneratedValue
	@Column
	private long id;
	
	@Column(name="name", length=32)
	private String name;
	
	@Column(name="password",length=32)
	private String password;
	
	public User() {
		super();
	}
	
	public User(String name, String password) {
	    this.name = name;
	    this.password = password;
	}
	
	public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		String toString = "[ id = " + id + ", name= " + name + ", password = " + password + "]";
		return toString;
	}
}
