package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class Comment implements Serializable {
	
	private boolean deleted = false;
	
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy h:mm:ss zzzz");
	private User user = new User(null, null, null);
	private String commentWords;
	private int likes;
	private int dislikes;
	private int stars;
	private String dateOfAccountCreation;
	private LinkedList<Comment> commentsInComment = new LinkedList<>();

	public void addLike() {
		likes++;
	}

	public int getLikes() {
		return likes;
	}

	public void addDislike() {
		dislikes++;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void addStar() {
		stars++;
	}

	public int getStars() {
		return stars;
	}

	public Comment(User user, String comment) {
		super();
		this.user = user;
		this.commentWords = comment;
		dateOfAccountCreation = formatter.format(new Date());
	}

	public void addCommentToComment(Comment comment) {
		commentsInComment.add(comment);
	}

	public LinkedList<Comment> getAllCommentsInComment() {
		return commentsInComment;
	}

	public String getcommentWords() {
		return commentWords;
	}

	public void changeComment(String comment) {
		this.commentWords = (comment);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDate() {
		return dateOfAccountCreation;
	}

	public Comment getComment() {
		return this;
	}

	@Override
	public String toString() {
		return "<" + user.getUsername() + "> /" + commentWords + "   |--| " + dateOfAccountCreation + "]";
	}
}
