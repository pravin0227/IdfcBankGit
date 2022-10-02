package com.IdfcBank.App.model;

public class User {
	private Integer UserId;
	private String firstName;
	private String LastName;
	private long PhoneNumber;
	private String Username;
	private String password;
	private Integer pin;
	private Integer Balance;

	public User(Integer UserId, String firstName, String lastName, long phoneNumber, String username, String password,
			Integer pin, Integer balance) {
		super();
		this.UserId = UserId;
		this.firstName = firstName;
		LastName = lastName;
		PhoneNumber = phoneNumber;
		Username = username;
		this.password = password;
		this.pin = pin;
		this.Balance = balance;
	}

	public User(Integer userId, String firstName, String lastName, long phoneNumber, String username, String password) {
		super();
		UserId = userId;
		this.firstName = firstName;
		LastName = lastName;
		PhoneNumber = phoneNumber;
		Username = username;
		this.password = password;
	}

	public User() {
		// TODO Auto-generated constructor stub

	}

	public Integer getUserId() {
		return UserId;
	}

	public Integer getBalance() {
		return Balance;
	}

	public void setBalance(Integer balance) {
		Balance = balance;
	}

	public void setUserId(Integer userId) {
		UserId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public long getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getPin() {
		return pin;
	}

	public void setPin(Integer pin) {
		this.pin = pin;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", LastName=" + LastName + ", PhoneNumber=" + PhoneNumber
				+ ", Username=" + Username + ", password=" + password + ", pin=" + pin + "]";
	}

}
