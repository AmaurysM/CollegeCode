package util;

import model.Comment;

public class StaticVeriables {
	
	private static int lastClikedCommentIndexInTreeItem;
	
	public static int getLastClikedCommentIndexInTreeItem() {
		return lastClikedCommentIndexInTreeItem;
	}

	public static void setLastClikedCommentIndexInTreeItem(int lastClikedCommentIndexInTreeItem) {
		StaticVeriables.lastClikedCommentIndexInTreeItem = lastClikedCommentIndexInTreeItem;
	}

	private static Comment lastClickedComment = new Comment(null,null);
	
	public static Comment lastClickedComment() {
		return lastClickedComment;
	}

	public static void setLastClickedComment(Comment pressedComment) {
		lastClickedComment = pressedComment;
	}
	
	private static Comment lastClickedPost;


	public static Comment lastClickedPost() {
		return lastClickedPost;
	}

	public static void setLastClickedPost(Comment pressedComment) {
		lastClickedPost = pressedComment;
	}


	

}
