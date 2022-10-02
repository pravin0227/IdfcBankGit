package com.IdfcBank.App.model;

import java.util.List;

public class IdfcAccount {

	private String branch;
	private String location;
	private List<User> users = List.of(

			new User(1, "Pravin", "More", 8828651418L, "Pravin0227", "PravinMore0227@"),
			new User(2, "Nitin", "More", 9702234467L, "Nitinmore", "NitinMore3110"),
			new User(23, "Shakir", "Ali", 8999667447L, "ShakirAli", "ShakirAli1210@")

	);

	public IdfcAccount(String branch, String location) {
		super();
		this.branch = branch;
		this.location = location;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
