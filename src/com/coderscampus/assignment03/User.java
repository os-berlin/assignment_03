package com.coderscampus.assignment03;

public class User implements Comparable<User> {
	private String username;
	private String password;
	private String name;
	private String role;

	User(String[] userDetails) {
		this.username = userDetails[0];
		this.password = userDetails[1];
		this.name = userDetails[2];
		this.role = userDetails[3];
/* 
 * runtime error in 'compareTo' method if this.role is missing in the constructor.
 * Doesn't this defeat the purpose of having subclasses (SuperUser & NormalUser)?
 * Couldn't get my head around implementing a compareTo method without it..
 */ 
	}

	@Override
	public int compareTo(User that) {
		if (this.role.compareTo(that.role) == 0) {
			return this.getUsername().compareTo(that.getUsername());
		} else
			return that.role.compareTo(this.role);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}