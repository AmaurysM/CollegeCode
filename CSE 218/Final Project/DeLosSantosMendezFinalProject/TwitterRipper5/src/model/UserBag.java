package model;

import java.util.TreeSet;

public class UserBag {

	private static User currentUser;

	private static int maxBagSizeArray = 10;
	private static int numberOfElements = 0;
	private static TreeSet<User> bagOfUsers = new TreeSet<>();

	public static void addUser(User user) {
		bagOfUsers.add(user);
	}

	public static void setTree(TreeSet<User> tree) {
		bagOfUsers = tree;
	}

	public static boolean isThereSpaceForANewUser() {
		return numberOfElements < maxBagSizeArray;
	}

	public static User find(String username) {

		if (bagOfUsers.ceiling(new User(username, username, username)) == null) {
			return null;
		}

		if (bagOfUsers.floor(new User(username, username, username)) == null) {
			return null;
		}

		if ((bagOfUsers.ceiling(new User(username, username, username))
				.compareTo(bagOfUsers.floor(new User(username, username, username)))) == 0) {
			return bagOfUsers.ceiling(new User(username, username, username));
		}
		return null;
	}

	public static User getCurrentUser() {
		return currentUser;
	}

	public static void setCurrentUser(User user) {
		currentUser = user;
	}

	public static int size() {
		return numberOfElements;
	}

	public static TreeSet<User> getBag() {
		return bagOfUsers;
	}

	public static boolean isEmpty() {
		return bagOfUsers.isEmpty();
	}
}
