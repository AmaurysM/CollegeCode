package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.Comment;
import model.UserBag;
import util.StaticVeriables;

public class CommentTreeViewController {

	private ViewCommentController parentController;

	@FXML
	private ContextMenu contextMenu;

	@FXML
	private MenuItem deleteMenuItem;

	@FXML
	private ImageView dislikesImageView;

	@FXML
	private ImageView likesImageView;

	@FXML
	private Label optionsLabel;

	@FXML
	private ImageView settingsImageView;

	@FXML
	private ImageView starImageView;

	@FXML
	private Label timeLabel;

	@FXML
	private MenuItem viewMoreMenuItem;

	private Comment comment;

	@FXML
	private Label UserNameLabel = new Label();

	@FXML
	private Label commentWordsLabel = new Label();

	@FXML
	private GridPane gridPane;

	@FXML
	private ImageView profilePictureImage;

	@FXML
	private TextFlow textFlowWithCommentWords = new TextFlow();

	@FXML
	private ImageView gradientImageView = new ImageView();

	public ContextMenu getContextMenu() {
		return contextMenu;
	}

	public void setContextMenu(ContextMenu contextMenu) {
		this.contextMenu = contextMenu;
	}

	public MenuItem getDeleteMenuItem() {
		return deleteMenuItem;
	}

	public void setDeleteMenuItem(MenuItem deleteMenuItem) {
		this.deleteMenuItem = deleteMenuItem;
	}

	public ImageView getDislikesImageView() {
		return dislikesImageView;
	}

	public void setDislikesImageView(ImageView dislikesImageView) {
		this.dislikesImageView = dislikesImageView;
	}

	public ImageView getLikesImageView() {
		return likesImageView;
	}

	public void setLikesImageView(ImageView likesImageView) {
		this.likesImageView = likesImageView;
	}

	public Label getOptionsLabel() {
		return optionsLabel;
	}

	public void setOptionsLabel(Label optionsLabel) {
		this.optionsLabel = optionsLabel;
	}

	public ImageView getSettingsImageView() {
		return settingsImageView;
	}

	public void setSettingsImageView(ImageView settingsImageView) {
		this.settingsImageView = settingsImageView;
	}

	public ImageView getStarImageView() {
		return starImageView;
	}

	public void setStarImageView(ImageView starImageView) {
		this.starImageView = starImageView;
	}

	public Label getTimeLabel() {
		return timeLabel;
	}

	public void setTimeLabel(Label timeLabel) {
		this.timeLabel = timeLabel;
	}

	public MenuItem getViewMoreMenuItem() {
		return viewMoreMenuItem;
	}

	public void setViewMoreMenuItem(MenuItem viewMoreMenuItem) {
		this.viewMoreMenuItem = viewMoreMenuItem;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public Label getUserNameLabel() {
		return UserNameLabel;
	}

	public void setUserNameLabel(Label userNameLabel) {
		UserNameLabel = userNameLabel;
	}

	public Label getCommentWordsLabel() {
		return commentWordsLabel;
	}

	public void setCommentWordsLabel(Label commentWordsLabel) {
		this.commentWordsLabel = commentWordsLabel;
	}

	public GridPane getGridPane() {
		return gridPane;
	}

	public void setGridPane(GridPane gridPane) {
		this.gridPane = gridPane;
	}

	public ImageView getProfilePictureImage() {
		return profilePictureImage;
	}

	public void setProfilePictureImage(ImageView profilePictureImage) {
		this.profilePictureImage = profilePictureImage;
	}

	public TextFlow getTextFlowWithCommentWords() {
		return textFlowWithCommentWords;
	}

	public void setTextFlowWithCommentWords(TextFlow textFlowWithCommentWords) {
		this.textFlowWithCommentWords = textFlowWithCommentWords;
	}

	public ImageView getGradientImageView() {
		return gradientImageView;
	}

	public void setGradientImageView(ImageView gradientImageView) {
		this.gradientImageView = gradientImageView;
	}

	public void make(Comment comment, ViewCommentController parentController) {
		this.parentController = parentController;
		this.comment = comment;

		UserNameLabel.setText(comment.getUser().getUsername());
		Text text = new Text(comment.getcommentWords());

		text.setFont(new Font("SansSerif Bold", 20));
		text.setFill(new Color(1, 1, 1, 1));
		textFlowWithCommentWords.getChildren().add(text);

		timeLabel.setText(comment.getDate());

	}
	
	public void likeImageViewClicked() {
		UserBag.getCurrentUser().getLikes().add(comment);
	}
	
	public void starImageViewClicked() {
		UserBag.getCurrentUser().getFavorite().add(comment);
	}
	
	public void viewMoreMenuItemClicked() throws IOException {
		StaticVeriables.setLastClickedPost(comment);
		parentController.getParentController().goToCommentView();
	}
	
	public void deleteItemMenuItemClicked() throws IOException {
		comment.setDeleted(true);
		parentController.getParentController().goToCommentView();
	}
	



}
