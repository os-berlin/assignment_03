package com.coderscampus.assignment03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Arrays;
import java.util.Scanner;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UserService {

	private User[] users = new User[20];

	Scanner scanner = new Scanner(System.in);

	public void readFile(String fileName) throws IOException {

		int i = 0;

		try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName));) {

			String line;
			while ((line = fileReader.readLine()) != null) {
				String[] userDetails = line.split(", ");

				if (userDetails[3].equals("super_user")) {
					users[i] = new SuperUser(userDetails);
				} else {
					users[i] = new NormalUser(userDetails);
				}
				i++;
			}
		}
	}

	public User userLogin() {

		for (int i = 0; i < 5; i++) {
			System.out.println("Enter your email:" + "\n");
			String inputUsername = scanner.nextLine();
			System.out.println("\n" + "Enter your password:" + "\n");
			String inputPassword = scanner.nextLine();

			for (User user : users) {
				if (inputUsername.equalsIgnoreCase(user.getUsername()) && inputPassword.equals(user.getPassword())) {
					return user;
				}
			}

			System.out.println("\n" + "Invalid login, please try again" + "\n");
		}
		System.out.println("Too many failed login attempts, you are now locked out.");
		return null;
	}

	public void userMenu(User currentUser) throws IOException {

		boolean superUserPermission = false;

		System.out.println("\nWelcome: " + currentUser.getName());
		System.out.println("\n----------\n" + "\n" + "Please choose from the following options:\n" + "\n");

		if (currentUser instanceof SuperUser) {
			System.out.println("(0) Log in as another user\n");
			superUserPermission = true;
		}

		System.out.println("(1) Update username\n" + "\n" + "(2) Update password\n" + "\n" + "(3) Update name\n" + "\n"
				+ "(4) Exit" + "\n");

		Integer inputMenuItem = Integer.parseInt(scanner.nextLine());

		if (inputMenuItem.equals(0) && superUserPermission == true) {
			switchUser(currentUser);
		} else if (inputMenuItem.equals(1)) {
			System.out.println("\n" + "Please type in your new username:" + "\n");
			updateUserDetails(currentUser, "username", scanner.nextLine());
		} else if (inputMenuItem.equals(2)) {
			System.out.println("\n" + "Please type in your new password:" + "\n");
			updateUserDetails(currentUser, "password", scanner.nextLine());
		} else if (inputMenuItem.equals(3)) {
			System.out.println("\n" + "Please type in your new name:" + "\n");
			updateUserDetails(currentUser, "name", scanner.nextLine());
		} else if (inputMenuItem.equals(4)) {
			System.out.println("\n" + "Bye bye!");
		} else
			System.out.println("\n" + "Invalid input or super_user role required. Bye bye!");
	}

	private void switchUser(User currentUser) throws IOException {

		System.out.println("\n" + "Which user would you like to login as? (Type in a valid username)" + "\n");
		String inputUsername = scanner.nextLine();

		for (User switchToUser : users) {

			if (inputUsername.equalsIgnoreCase(switchToUser.getUsername())) {
				userMenu(switchToUser);
			}
		}
		System.out.println("Invalid username. Bye bye!");
	}

	private void updateUserDetails(User currentUser, String userDetail, String value) throws IOException {

		if (userDetail.equals("username")) {
			currentUser.setUsername(value);
		} else if (userDetail.equals("password")) {
			currentUser.setPassword(value);
		} else if (userDetail.equals("name")) {
			currentUser.setName(value);
		}

		writeFile("users.txt");
		userMenu(currentUser);
	}

	private void writeFile(String fileName) throws IOException {

		Arrays.sort(users);

		try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileName));) {

			String userRole;

			for (User user : users) {

				if (user instanceof SuperUser) {
					userRole = "super_user";
				} else {
					userRole = "normal_user";
				}

				fileWriter.write(user.getUsername() + ", " + user.getPassword() + ", " + user.getName() + ", "
						+ userRole + "\n");
			}
		}
	}
}
