package com.coderscampus.assignment03;

import java.io.IOException;

public class UserLoginApplication {

	public static void main(String[] args) {

		UserService userService = new UserService();
		try {
			userService.readFile("users.txt");
		} catch (IOException e) {
			System.out.println("\n" + "oh oh, I/O exception!");
			e.printStackTrace();
			System.exit(0);
		}

		User currentUser = userService.userLogin();
		if (currentUser != null) {
			try {
				userService.userMenu(currentUser);
			} catch (IOException e) {
				System.out.println("\n" + "oh oh, I/O exception!");
				e.printStackTrace();
				System.exit(0);
			}
		}
		userService.scanner.close();
	}
}