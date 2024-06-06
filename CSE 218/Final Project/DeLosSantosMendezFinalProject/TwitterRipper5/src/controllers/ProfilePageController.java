package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.User;
import model.UserBag;
import util.ReadAndWriteFromTxtFile;
import util.StaticVeriables;

public class ProfilePageController {

	private TwitterRipController twitterRipController;

	@FXML
	private VBox likesVBox = new VBox();

	@FXML
	private VBox favoritesVBox = new VBox();

	@FXML
	private ImageView exitButton = new ImageView();

	@FXML
	private Tab Favorites;

	@FXML
	private ListView<CommentController> LikesListView;

	@FXML
	private Label UserNameLabel;

	@FXML
	private ListView<CommentController> favoritesListView;

	@FXML
	private ImageView followButton = new ImageView();

	@FXML
	private Tab likesTab;

	@FXML
	private GridPane mainGridPane;

	@FXML
	private ImageView pfpImageView;

    @FXML
    private Pane rootPane;

	@FXML
	private TabPane tabPane;

	public Tab getFavorites() {
		return Favorites;
	}

	public void setFavorites(Tab favorites) {
		Favorites = favorites;
	}

	public ListView<CommentController> getLikesListView() {
		return LikesListView;
	}

	public void setLikesListView(ListView<CommentController> likesListView) {
		LikesListView = likesListView;
	}

	public Label getUserNameLabel() {
		return UserNameLabel;
	}

	public void setUserNameLabel(Label userNameLabel) {
		UserNameLabel = userNameLabel;
	}

	public ListView<CommentController> getFavoritesListView() {
		return favoritesListView;
	}

	public void setFavoritesListView(ListView<CommentController> favoritesListView) {
		this.favoritesListView = favoritesListView;
	}

	public ImageView getFollowButton() {
		return followButton;
	}

	public void setFollowButton(ImageView followButton) {
		this.followButton = followButton;
	}

	public Tab getLikesTab() {
		return likesTab;
	}

	public void setLikesTab(Tab likesTab) {
		this.likesTab = likesTab;
	}

	public GridPane getMainGridPane() {
		return mainGridPane;
	}

	public void setMainGridPane(GridPane mainGridPane) {
		this.mainGridPane = mainGridPane;
	}

	public ImageView getPfpImageView() {
		return pfpImageView;
	}

	public void setPfpImageView(ImageView pfpImageView) {
		this.pfpImageView = pfpImageView;
	}

	public Pane getRootPane() {
		return rootPane;
	}

	public void setRootPane(Pane rootPane) {
		this.rootPane = rootPane;
	}

	public TabPane getTabPane() {
		return tabPane;
	}

	public void setTabPane(TabPane tabPane) {
		this.tabPane = tabPane;
	}

	public ImageView getExitButton() {
		return exitButton;
	}

	public void setExitButton(ImageView exitButton) {
		this.exitButton = exitButton;
	}

	public void setUp(TwitterRipController twitterRipController) throws IOException {
		this.twitterRipController = twitterRipController;

		UserNameLabel.setText(StaticVeriables.lastClickedPost().getUser().getUsername());
		followButton.setVisible(true);
		followButton.setDisable(false);

		followButton.setImage(
				new Image("C:\\Users\\amaur\\eclipse-workspace\\TwitterRipper5\\TwitterRipper5\\images\\star(Blank).png"));
		for (User user : UserBag.getCurrentUser().getFriends()) {
			if (user.getUsername().compareTo(UserNameLabel.getText()) == 0) {
				followButton.setImage(
						new Image("C:\\Users\\amaur\\eclipse-workspace\\TwitterRipper5\\TwitterRipper5\\images\\star(gold).png"));
				followButton.setVisible(true);
				followButton.setDisable(false);
			} 

		}

		for (int i = 0; i < StaticVeriables.lastClickedPost().getUser().getLikes().size(); i++) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Comment.fxml"));
			loader.load();
			CommentController control = loader.getController();

			control.setUp(twitterRipController);
			control.makeComment(StaticVeriables.lastClickedPost().getUser().getLikes().get(i));
			likesVBox.getChildren().add(control.getRootHBox().getChildren().get(0));
		}

		for (int i = 0; i < StaticVeriables.lastClickedPost().getUser().getFavorite().size(); i++) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Comment.fxml"));
			loader.load();
			CommentController control = loader.getController();
			control.setUp(twitterRipController);
			control.makeComment(StaticVeriables.lastClickedPost().getUser().getFavorite().get(i));
			favoritesVBox.getChildren().add(control.getRootHBox().getChildren().get(0));
		}
	}

	public void followImageClicked() {
		if (followButton.getImage().getUrl().compareTo(
				"C:\\Users\\amaur\\eclipse-workspace\\TwitterRipper5\\TwitterRipper5\\images\\star(Blank).png") == 0) {
			UserBag.getCurrentUser().getFriends().add(StaticVeriables.lastClickedPost().getUser());
			StaticVeriables.lastClickedPost().getUser().getFollowers().add(UserBag.getCurrentUser());
			ReadAndWriteFromTxtFile.writeUsersToTxtFile();
		}

	}

	public void exitButtonClicked() throws IOException {
		twitterRipController.goHome();
	}

}
