package com.tek.springmvcums.model;

public class User {
	
	private Long id;
	private String userName;
	private String address;
	private String email;
	
	public User() {
		
	}

	public User(Long id,String userName, String address, String email) {
		this.id=id;
		this.userName = userName;
		this.address = address;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", address=" + address + ", email=" + email + "]";
	}
	
	

}
