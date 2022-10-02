package com.IdfcBank.App.Controller;

import java.util.Scanner;

import com.IdfcBank.App.model.User;
import com.IdfcBank.App.service.Connectivity;
import com.IdfcBank.App.service.getRepository;

public class UserController {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws Exception {

		System.out.println("Welcome to the IdfcFirst Bank");
		System.out.println();
		System.out.println("create your account first");
		User user = createAccount();

		System.out.println("select appropriate number for your transactions ... ");

		for (int i = 0; i < 9; i++) {
			System.out.println("" + "" + "1 : Deposit Amount  \n" + "2 : Transer Amount  \n" + "3 : ViewBalance  \n"
					+ "4 : Withdraw Amount  \n" + "5 : Exit");

			int transaction = sc.nextInt();

			switch (transaction) {

			case 1:
				DepositAmount(user);
				break;

			case 2:
				TransferAmount(user);
				break;

			case 3:
				ViewBalance(user);
				break;

			case 4:
				WithdrawAmount(user);
				break;

			case 5:
				System.out.println("transaction cancelled");
				System.out.println("visit again Thank you!! :) ");
				System.exit(transaction);

			default:
				System.out.println("enter valid transaction number");

			}
		}

	}

	private static void WithdrawAmount(User user) {

		System.out.println("Enter your pin");
		int pin = sc.nextInt();
		if (pin == user.getPin()) {
			System.out.println("enter amount to withdraw");
			int amount = sc.nextInt();
			if (amount < user.getBalance()) {

				int flag = new Connectivity().WithdrawAmount(user, amount);
				if (flag > 0) {
					System.out.println("collect your amount !! amount withdrawl successfully ");
					user.setBalance(user.getBalance() - amount);
				} else {
					System.out.println("incorrect credetials failed to connect server try again later");
				}
			} else {
				System.out.println("your account balance is lower than withdrawl amount");
			}
		} else {
			System.out.println("pin is incorrect !!! :( ");
		}

	}

	private static void ViewBalance(User user) {

		System.out.println("for view your balance enter your pin ");
		int pin = sc.nextInt();

		if (pin == user.getPin()) {
			System.out.println("your balance : " + user.getBalance());
		} else {
			System.out.println("pin is incorrect !!! :( ");
		}

	}

	private static void DepositAmount(User user) {

		System.out.println("enter amount to deposit");
		Integer amount = sc.nextInt();
		System.out.println("enter your 4 digit pin");
		int pin = sc.nextInt();
		System.out.println(user.getPin());

		if (pin == user.getPin()) {
			int flag = new Connectivity().DepositAmout(user, amount);
			if (flag > 0) {
				System.out.println("amount deposited successfully with given details");
				user.setBalance(user.getBalance() + amount);
			} else {
				System.out.println("incorrect credetials failed to connect server try again later");
			}
		} else {

			System.out.println("pin is incorrect enter valid pin :( ");
		}

	}

	private static User createAccount() {

		System.out.println("enter details to create your account with Idfc");
		User user = new User();
		System.out.println("enter unique userId");
		user.setUserId(sc.nextInt());
		System.out.println("enter First name");
		user.setFirstName(sc.next());
		System.out.println("enter last name");
		user.setLastName(sc.next());
		System.out.println("enter your phoneNumber");
		user.setPhoneNumber(sc.nextLong());
		System.out.println("enter your userName");
		user.setUsername(sc.next());
		System.out.println("create strong password");
		user.setPassword(sc.next());
		System.out.println("create four digit pin");
		user.setPin(sc.nextInt());
		System.out.println("for creation of account with Idfc pay 2000 with base amount ");
		user.setBalance(2000);
		System.out.println("paid 2000 successfully");

		int flag = new Connectivity().insertData(user);
		if (flag > 0) {
			System.out.println("account created successfully with given details");
		} else {
			System.out.println("incorrect credetials");
		}
		return user;

	}

	private static void TransferAmount(User user) {

		System.out.println("enter you pin");
		int pin = sc.nextInt();

		System.out.println("Enter username of user which you have to transfer your money");
		String uname = sc.next();
		System.out.println("enter amount to transfer");
		int amount = sc.nextInt();

		if (amount < user.getBalance()) {
			if (pin == user.getPin()) {
				User TransferUser = new getRepository().getUser(uname);
				if (TransferUser != null) {
					int flag = new Connectivity().transferAmount(user, TransferUser, amount);
				} else {
					System.out.println("user not valid");
				}
			} else {
				System.out.println("enter valid pin !! ");
			}

		} else {
			System.out.println("Account balance is not enought to precess your request !! :( ");
		}

	}

}
