package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Comment;
import model.User;
import util.StaticVeriables;

public class ProfileWidgetController {

	private User user;

	private TwitterRipController twitterRipController;

	@FXML
	private Pane rootPane;

	@FXML
	private ImageView profilePictureImageView;

	@FXML
	private GridPane profileWidget;

	@FXML
	private Label userNameLabel = new Label();

	public Pane getRootPane() {
		return rootPane;
	}

	public void setRootPane(Pane rootPane) {
		this.rootPane = rootPane;
	}

	public void SetUp(TwitterRipController twitterRipController) {
		this.twitterRipController = twitterRipController;
	}

	public void makeWidget(User user) {
		this.user = user;
		userNameLabel.setText(user.getUsername());
	}

	public GridPane getGridPane() {
		return profileWidget;
	}

	public void goToProfilePage() throws IOException {
		StaticVeriables.setLastClickedPost(new Comment(user, "null"));
		twitterRipController.goToProfilePage();
	}

}
