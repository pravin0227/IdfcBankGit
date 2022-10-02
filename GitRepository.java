package com.IdfcBank.App.service;

import com.IdfcBank.App.model.IdfcAccount;
import com.IdfcBank.App.model.User;

public class getRepository {
	IdfcAccount list = new IdfcAccount("Sion","Mumbai");
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return this.list.getUsers().stream().filter(user -> user.getUsername().equals(username)).findAny().orElse(null);
	}
	
}
