package com.tu.sofia.bg.model;

/**
 * This class use {@link ThreadLocal} class to store the current user and
 * provides an easy way for clients to establish the current user of the system
 * 
 */

public class UserUtil {
	private static ThreadLocal<User> currentUser = new ThreadLocal<User>();

	/**
	 * @param user
	 *            The current User
	 */
	public static void setCurrentUser(User user) {
		currentUser.set(user);
	}

	/**
	 * @return Current User
	 */
	public static User getCurrentUser() {
		return currentUser.get();
	}
}
