package com.ppshrimp.filmsystem.persistence.entity;

import java.io.Serializable;

public class User implements Serializable  {
     
	/**
	 * 
	 */
	private static final long serialVersionUID = 2828011182748819042L;
	
	private long userId;
	
	private String username;
	
	private String password;
	
	private String nickname;
	
	private boolean iChat;
	
	public User() {
		super();
	}
	
	public User(String username, String password) {
	    this.setUsername(username);
	    this.setPassword(password);
	    this.setNickname(username);
	    this.setiChat(true);
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public boolean isiChat() {
		return iChat;
	}

	public void setiChat(boolean iChat) {
		this.iChat = iChat;
	}
	
}
