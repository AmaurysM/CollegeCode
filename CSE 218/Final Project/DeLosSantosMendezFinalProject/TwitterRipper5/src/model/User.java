package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.TreeSet;

public class User implements Comparable<User>, Serializable {

	private String username;
	private String password;
	private String mail;
	private LinkedList<Comment> comments = new LinkedList<>();
	private LinkedList<Comment> likes = new LinkedList<>();
	private LinkedList<Comment> favorite = new LinkedList<>();
	private TreeSet<User> followers = new TreeSet<>();
	private TreeSet<User> friends = new TreeSet<>();
	
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private String dateOfAccountCreation;

	

	public User(String username, String password, String mail) {
		this.username = username;
		this.password = password;
		this.mail = mail;
		dateOfAccountCreation = formatter.format(new Date());

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = (username);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = (password);
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getDateOfAccountCreation() {
		return dateOfAccountCreation;
	}

	public void setDateOfAccountCreation(String dateOfAccountCreation) {
		this.dateOfAccountCreation = dateOfAccountCreation;
	}

	public LinkedList<Comment> getComments() {
		return comments;
	}

	public void addComment(Comment comment) {
		this.comments.add(comment);
		// CommentBag.addComment(comments.getLast());
	}

	public LinkedList<Comment> getLikes() {
		return likes;
	}

	public void addLike(Comment likedComment) {
		this.likes.add(likedComment);
	}

	public LinkedList<Comment> getFavorite() {
		return favorite;
	}

	public void addFavorite(Comment favorite) {
		this.favorite.add(favorite);
	}

	public TreeSet<User> getFriends() {
		return friends;
	}

	public void addFriends(User user) {
		friends.add(user);
	}
	
	public TreeSet<User> getFollowers() {
		return followers;
	}

	public void setFollowers(TreeSet<User> followers) {
		this.followers = followers;
	}

	public void addFollower(User user) {
		this.followers.add(user);
	}

	@Override
	public String toString() {
		return "UserName " + username + ", MailInfo= " + mail + " , dateOfAccountCreation=" + dateOfAccountCreation;
	}

	@Override
	public int compareTo(User o) {

		return getUsername().compareTo(o.getUsername());
	}

}
