package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.User;
import model.UserBag;

public class TwitterRipController {

	@FXML
	private BorderPane borderpane = new BorderPane();

	@FXML
	private Button homeButton = new Button();

	@FXML
	private Button likesButton;

	@FXML
	private Button favoriteButton;

	@FXML
	private Button friendsButton;

	@FXML
	private TextFlow locationLabel;

	@FXML
	private HBox rootHBox;

	@FXML
	private Label userNameLabel;

	@FXML
	private ImageView userProfilePicture;

	@FXML
	private Label label = new Label();

	private Stage stage;

	public void setUp(Stage stage) {
		this.stage = stage;
		User user = UserBag.getCurrentUser();
		userNameLabel.setText("< " + user.getUsername() + " >");
	}

	public void goHome() throws IOException {

		FXMLLoader homeGrid = new FXMLLoader(getClass().getResource("/views/HomeTwitterGridPane.fxml"));
		homeGrid.load();
		HomeTwitterGridPaneController gridPane = homeGrid.getController();
		label.setText("<HOME>");
		gridPane.setUpScene(this);

		borderpane.setCenter(gridPane.getHolderOfHome());
	}

	public void goLikes() throws IOException {
		label.setText("<LIKES>");

		FXMLLoader homeGrid = new FXMLLoader(getClass().getResource("/views/BlankList.fxml"));
		homeGrid.load();
		BlankListController gridPane = homeGrid.getController();

		gridPane.fillListWithComments(UserBag.getCurrentUser().getLikes(), this);
		borderpane.setCenter(gridPane.getScrollPane());

	}

	public void goBookMarks() throws IOException {
		label.setText("<BOOKMARKS>");
		FXMLLoader homeGrid = new FXMLLoader(getClass().getResource("/views/BlankList.fxml"));
		homeGrid.load();
		BlankListController gridPane = homeGrid.getController();

		gridPane.fillListWithComments(UserBag.getCurrentUser().getFavorite(), this);
		borderpane.setCenter(gridPane.getScrollPane());
	}

	public void goToYourFriends() throws IOException {
		label.setText("<FRIENDS>");
		FXMLLoader homeGrid = new FXMLLoader(getClass().getResource("/views/BlankList.fxml"));
		homeGrid.load();
		BlankListController gridPane = homeGrid.getController();

		gridPane.fillFriendsList(this);
		borderpane.setCenter(gridPane.getScrollPane());

	}

	public void goToCommentView() throws IOException {
		label.setText("");
		FXMLLoader homeGrid = new FXMLLoader(getClass().getResource("/views/ViewCommentTreeView.fxml"));
		homeGrid.load();
		ViewCommentController gridPane = homeGrid.getController();

		gridPane.setUpCommentView(this);
		borderpane.setCenter(gridPane.getMainGrid());
	}

	public void goToProfilePage() throws IOException {
		label.setText("");
		FXMLLoader homeGrid = new FXMLLoader(getClass().getResource("/views/ProfilePage.fxml"));
		homeGrid.load();
		ProfilePageController gridPane = homeGrid.getController();
		gridPane.setUp(this);
		borderpane.setCenter(gridPane.getMainGridPane());
	}

	public void goToAllUsers() throws IOException {
		label.setText("USERS");
		FXMLLoader homeGrid = new FXMLLoader(getClass().getResource("/views/BlankList.fxml"));
		homeGrid.load();
		BlankListController gridPane = homeGrid.getController();
		gridPane.fillAllUsersTab(this);
		borderpane.setCenter(gridPane.getRootGridPane());
	}

	public void goToSettings() throws IOException {
		label.setText("Settings");
		FXMLLoader homeGrid = new FXMLLoader(getClass().getResource("/views/SettingsPage.fxml"));
		homeGrid.load();
		SettingsController gridPane = homeGrid.getController();
		gridPane.setUp(this, stage);
		borderpane.setCenter(gridPane.getRootVBox());

	}

	public void changeColorOnMouseEntered(MouseEvent event) {
		((Button) event.getSource()).setEffect(new DropShadow());
		((Button) event.getSource()).setStyle("-fx-background-color: rgb(70,70,70)");
	}

	public void changeColorOnMouseExited(MouseEvent event) {
		((Button) event.getSource()).setEffect(null);
		((Button) event.getSource()).setStyle("-fx-background-color: rgb(32,32,32)");
	}
}
