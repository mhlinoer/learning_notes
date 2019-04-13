package com.linoer.model;

public class User {
	public String username;
	public String password;
	public User(String username, String password){
		this.username = username;
		this.password = password;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
