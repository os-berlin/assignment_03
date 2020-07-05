package com.coderscampus.assignment03;

public class NormalUser extends User {

	String role;

	NormalUser(String[] userDetails) {
		super(userDetails);
		this.role = "normal_user";
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
