package com.IdfcBank.App.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.IdfcBank.App.model.User;

public class Connectivity {

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "Pravinmore0227@");

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;

	}

	public int WithdrawAmount(User user, int amount) {
		Connection con = Connectivity.getConnection();
		String insert = "UPDATE IdfcBank.Userdata SET Balance = ? WHERE pin = ?";
		int count = 0;
		PreparedStatement pstmt1;
		try {
			pstmt1 = con.prepareStatement(insert);
			pstmt1.setInt(1, user.getBalance() - amount);
			pstmt1.setInt(2, user.getPin());
			count = pstmt1.executeUpdate();
			if (count > 0) {
				System.out.println("Data inserted in the server");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return count;

	}

	public int DepositAmout(User user, int amount) {

		Connection con = Connectivity.getConnection();
		String insert = "UPDATE IdfcBank.Userdata SET Balance = ? WHERE pin = ?";
		int count = 0;
		PreparedStatement pstmt1;
		try {
			pstmt1 = con.prepareStatement(insert);
			pstmt1.setInt(1, user.getBalance() + amount);
			pstmt1.setInt(2, user.getPin());
			count = pstmt1.executeUpdate();
			if (count > 0) {
				System.out.println("Data inserted in the server");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return count;

	}

	public int transferAmount(User user, User TransferUser, int amount) {
		Connection con = Connectivity.getConnection();
		String insert = "UPDATE IdfcBank.Userdata SET Balance = ? WHERE pin = ?";
		String transfer = "UPDATE IdfcBank.Userdata SET Balance = ? WHERE UserName = ?";
		String select = "SELECT * FROM IdfcBank.Userdata where UserName = ?";
		int count = 0;
		int count2 = 0;
		float balance = 0;
		PreparedStatement pstmt3;
		try {
			pstmt3 = con.prepareStatement(select);
			pstmt3.setString(1, TransferUser.getUsername());
			ResultSet rs = pstmt3.executeQuery();
			rs.next();
			balance = rs.getFloat(8);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement pstmt2;
		try {
			pstmt2 = con.prepareStatement(transfer);
			pstmt2.setFloat(1, (balance + amount));
			pstmt2.setString(2, TransferUser.getUsername());
			count = pstmt2.executeUpdate();
			if (count > 0) {
				System.out.println("Money transferring to the user " + TransferUser.getFirstName());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement pstmt1;
		try {
			pstmt1 = con.prepareStatement(insert);
			pstmt1.setInt(1, user.getBalance() - amount);
			user.setBalance(user.getBalance() - amount);
			pstmt1.setInt(2, user.getPin());
			count2 = pstmt1.executeUpdate();
			if (count2 > 0) {
				System.out.println("Money transferred successfully !! :) from the server");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return count;

	}

	public int insertData(User user) {

		Connection con = Connectivity.getConnection();
		String insert = "INSERT INTO IdfcBank.Userdata VALUES(?,?,?,?,?,?,?,?)";
		int count = 0;
		PreparedStatement pstmt1;
		try {
			pstmt1 = con.prepareStatement(insert);
			pstmt1.setInt(1, user.getUserId());
			pstmt1.setString(2, user.getFirstName());
			pstmt1.setString(3, user.getLastName());
			pstmt1.setLong(4, user.getPhoneNumber());
			pstmt1.setString(5, user.getUsername());
			pstmt1.setString(6, user.getPassword());
			pstmt1.setInt(7, user.getPin());
			pstmt1.setFloat(8, user.getBalance());
			count = pstmt1.executeUpdate();
			if (count > 0) {
				System.out.println("Data inserted in the server");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return count;

	}

	public static void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
