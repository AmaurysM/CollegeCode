package controllers;

import java.io.IOException;
import java.util.LinkedList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Comment;
import model.User;
import model.UserBag;
import util.Utilities;

public class BlankListController {

	private TwitterRipController parentController;

	@FXML
	private GridPane rootGridPane;

	@FXML
	private VBox commentListVBox;

	@FXML
	private ScrollPane scrollPane;

	@FXML
	private Button searchButton;

	@FXML
	private TextField textField = new TextField();

	public GridPane getRootGridPane() {
		return rootGridPane;
	}

	public void setRootGridPane(GridPane rootGridPane) {
		this.rootGridPane = rootGridPane;
	}

	public VBox getCommentListVBox() {
		return commentListVBox;
	}

	public void setCommentListVBox(VBox commentListVBox) {
		this.commentListVBox = commentListVBox;
	}

	public ScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(ScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public void fillListWithComments(LinkedList<Comment> list, TwitterRipController twitterRipController)
			throws IOException {
		this.parentController = twitterRipController;
		for (int i = 0; i < list.size(); i++) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Comment.fxml"));
			loader.load();
			CommentController controller = loader.getController();
			controller.setUp(twitterRipController);
			controller.makeComment((Comment) list.get(i));
			commentListVBox.getChildren().addAll(controller.getRootHBox().getChildren());
		}
	}

	// This fills the Friends list
	public void fillFriendsList(TwitterRipController twitterRipController) throws IOException {
		this.parentController = twitterRipController;

		for (User user : UserBag.getCurrentUser().getFriends()) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ProfileWidget.fxml"));
			loader.load();
			ProfileWidgetController controller = loader.getController();
			controller.SetUp(twitterRipController);
			controller.makeWidget(user);
			commentListVBox.getChildren().addAll(controller.getGridPane());
		}
	}

	// This fills up the All Users Tab
	public void fillAllUsersTab(TwitterRipController twitterRipController) throws IOException {
		this.parentController = twitterRipController;
		textField.setOnKeyPressed(Utilities.pressEnterToSearchForUsers(this));

		for (User user : UserBag.getBag()) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ProfileWidget.fxml"));
			loader.load();
			ProfileWidgetController controller = loader.getController();
			controller.SetUp(twitterRipController);
			controller.makeWidget((User) user);
			commentListVBox.getChildren().addAll(controller.getRootPane().getChildren());
		}

	}

	public void clearListVBox() {
		commentListVBox.getChildren().clear();
	}

	public void lookUpUsers() throws IOException {
		if (textField.getText().equals("")) {
			clearListVBox();
			fillAllUsersTab(parentController);
			return;
		}

		if (UserBag.find(textField.getText()) == null) {
			return;
		}

		clearListVBox();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ProfileWidget.fxml"));
		loader.load();
		ProfileWidgetController controller = loader.getController();
		controller.SetUp(parentController);
		controller.makeWidget((User) UserBag.find(textField.getText()));
		commentListVBox.getChildren().addAll(controller.getRootPane().getChildren());
	}

}
