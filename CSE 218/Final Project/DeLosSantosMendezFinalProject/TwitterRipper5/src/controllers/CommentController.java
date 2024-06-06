package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.Comment;
import model.UserBag;
import util.ReadAndWriteFromTxtFile;
import util.StaticVeriables;

public class CommentController {

	private HomeTwitterGridPaneController homeController;

	private TwitterRipController parentController;

	private Comment comment = new Comment(null, null);

	@FXML
	private Pane backgroundStylingPane;

	@FXML
	private ImageView deleteButton = new ImageView();

	@FXML
	private Label commentAreaLabel = new Label();

	@FXML
	private GridPane holdCommentPropPane = new GridPane();

	@FXML
	private ImageView profilePictureImageView = new ImageView();

	@FXML
	private Label usernameLabel = new Label();

	@FXML
	private Pane rootPane = new HBox();

	@FXML
	private ImageView dislikeButton;

	@FXML
	private ImageView favoriteButton;

	@FXML
	private ImageView likeButton = new ImageView();

	@FXML
	private TextFlow textFlowHoldesLabelComment = new TextFlow();

	@FXML
	private Label commentSettings = new Label();

	@FXML
	private ContextMenu contextMenu = new ContextMenu();

	@FXML
	private MenuItem deleteItem = new MenuItem();

	@FXML
	private MenuItem editItem = new MenuItem();

	@FXML
	private MenuItem goToCommentViewMenuItem = new MenuItem();

	public Label getCommentSettings() {
		return commentSettings;
	}

	public void setCommentSettings(Label commentSettings) {
		this.commentSettings = commentSettings;
	}

	public ContextMenu getContextMenu() {
		return contextMenu;
	}

	public void setContextMenu(ContextMenu contextMenu) {
		this.contextMenu = contextMenu;
	}

	public MenuItem getDeleteItem() {
		return deleteItem;
	}

	public void setDeleteItem(MenuItem deleteItem) {
		this.deleteItem = deleteItem;
	}

	public void setDeleteButton(ImageView deleteButton) {
		this.deleteButton = deleteButton;
	}

	@FXML
	private Label timeOfPostLabel = new Label();

	public Label getTimeOfPostLabel() {
		return timeOfPostLabel;
	}

	public void setTimeOfPostLabel(Label timeOfPostLabel) {
		this.timeOfPostLabel = timeOfPostLabel;
	}

	public void setUp(TwitterRipController parentController) {
		this.parentController = parentController;
	}

	public void setUp(TwitterRipController parentController, HomeTwitterGridPaneController homeController) {
		this.parentController = parentController;
		this.homeController = homeController;
	}

	public TextFlow getFlowThatHoldsComment() {
		return textFlowHoldesLabelComment;
	}

	public GridPane getCommentPropPane() {
		return holdCommentPropPane;
	}

	public TextFlow getTextFlowSize() {
		return textFlowHoldesLabelComment;
	}

	public void setCommentLabel(String words) {
		commentAreaLabel.setText(words);
	}

	public Pane getRootHBox() {
		return rootPane;
	}

	public Label getUsernameLabel() {
		return usernameLabel;
	}

	public ImageView getProfilePictureImageView() {
		return profilePictureImageView;
	}

	public Label getCommentAreaLabel() {
		return commentAreaLabel;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public Comment getComment() {
		return comment;
	}

	public Pane getBackgroundStylingPane() {
		return backgroundStylingPane;
	}

	public void setBackgroundStylingPane(Pane backgroundStylingPane) {
		this.backgroundStylingPane = backgroundStylingPane;
	}

	public void makeComment(Comment comment) {
		this.comment = comment;

		Text text = new Text(comment.getcommentWords());
		text.setFont(new Font("SanaSerif", 20));
		text.setFill(new Color(1, 1, 1, 1));
		textFlowHoldesLabelComment.getChildren().add(text);

		usernameLabel.setText(comment.getUser().getUsername());
		timeOfPostLabel.setText(comment.getDate());

		if (UserBag.getCurrentUser().getUsername().compareTo(comment.getUser().getUsername()) != 0) {
			contextMenu.getItems().remove(2);
		}

	}

	public void addLike() {
		UserBag.getCurrentUser().addLike(comment);
		comment.addLike();
		ReadAndWriteFromTxtFile.writeUsersToTxtFile();
	}

	public void addDislike() {
		comment.addDislike();
		ReadAndWriteFromTxtFile.writeUsersToTxtFile();
	}

	public void addFavorite() {
		UserBag.getCurrentUser().addFavorite(comment);
		comment.addStar();
		ReadAndWriteFromTxtFile.writeUsersToTxtFile();
	}

	public void clickPost() throws IOException {
		StaticVeriables.setLastClickedPost(comment);
		parentController.goToCommentView();

	}

	public void pressProfilePicture() throws IOException {
		StaticVeriables.setLastClickedPost(comment);
		parentController.goToProfilePage();
	}

	public ImageView getDeleteButton() {
		return deleteButton;
	}

	public void deleteCommentButtonPressed() throws IOException {

		StaticVeriables.setLastClickedPost(comment);
		homeController.getHoldCommentsVBox().getChildren()
				.remove(homeController.getHoldCommentsVBox().getChildren().indexOf(this.getCommentPropPane()));
		comment.getUser().getComments().remove(comment.getUser().getComments().indexOf(this.getComment()));

		comment.setDeleted(true);
		ReadAndWriteFromTxtFile.writeUsersToTxtFile();

	}

}
