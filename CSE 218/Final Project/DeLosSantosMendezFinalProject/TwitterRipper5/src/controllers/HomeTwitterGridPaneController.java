package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.Comment;
import model.User;
import model.UserBag;
import util.ReadAndWriteFromTxtFile;
import util.Utilities;

public class HomeTwitterGridPaneController {

	@FXML
	private Label spellCheck = new Label();

	public Label getSpellCheckLabel() {
		return spellCheck;
	}

	private TwitterRipController parentController;

	@FXML
	private VBox holdCommentsVBox = new VBox();

	public VBox getHoldCommentsVBox() {
		return holdCommentsVBox;
	}

	@FXML
	private ScrollPane scrollPaneOfComments = new ScrollPane();

	public Text newText(String words) {
		Text wordsInComment = new Text(words);
		wordsInComment.setFont(Font.font("Verdana", FontWeight.LIGHT, 25));
		return wordsInComment;
	}

	@FXML
	private GridPane gridMain = new GridPane();

	public GridPane getGridMain() {
		return gridMain;
	}

	@FXML
	private TextField postField = new TextField();

	public TextField getPostField() {
		return postField;
	}

	@FXML
	private Button postButton = new Button();

	public void addComment(CommentController commentController) {

		if (commentController.getComment().isDeleted()) {
			UserBag.getCurrentUser().getComments()
					.remove(UserBag.getCurrentUser().getComments().indexOf(commentController.getComment()));
			return;
		}

		commentController.setUp(parentController, this);

		if (holdCommentsVBox.getChildren().size() == 0) {
			holdCommentsVBox.getChildren().add(commentController.getCommentPropPane());
		} else {
			holdCommentsVBox.getChildren().add(0, commentController.getCommentPropPane());
		}

		scrollPaneOfComments.setVvalue(0);

	}

	public void giveCommentsToFollowers(Comment comment) {

		for (User value : UserBag.getCurrentUser().getFollowers()) {
			value.addComment(comment);
		}

	}

	@FXML
	public void addToCommentArea() throws IOException {
		Comment newPost;
		FXMLLoader newComment;
		CommentController comment;

		if ((postField.getText().compareTo("") == 0) || (postField.getText().compareTo(" ") == 0)) {
			return;
		}

		newPost = new Comment(UserBag.getCurrentUser(), postField.getText());
		newComment = new FXMLLoader(getClass().getResource("/views/Comment.fxml"));
		newComment.load();
		comment = newComment.getController();

		UserBag.getCurrentUser().addComment(newPost);
		comment.makeComment(UserBag.getCurrentUser().getComments().getLast());

		giveCommentsToFollowers(UserBag.getCurrentUser().getComments().getLast());
		addComment(comment);

		postField.clear();
		postField.clear();

		ReadAndWriteFromTxtFile.writeUsersToTxtFile();
		ReadAndWriteFromTxtFile.writeCommentsToTxtFile();
	}
	// ===========

	@FXML
	private ListView<SmallProfileWidgetController> friendList = new ListView<>();

	@FXML
	private ListView<?> groupList;

	@FXML
	private ImageView searchButton;

	@FXML
	private TextField searchField;

	@FXML
	private VBox navigationVBox = new VBox();

	@FXML
	private HBox holderOfHome = new HBox();

	public HBox getHolderOfHome() throws IOException {
		return holderOfHome;
	}

	@FXML
	private HBox rootPain = new HBox();

	public HBox getRootHBox() {
		return rootPain;
	}

	private Pane holdPane = new Pane();

	public Pane getHoldPane() {
		return holdPane;
	}

	@FXML
	private ImageView exitButton = new ImageView();

	public void setUpScene(TwitterRipController parentController) throws IOException {

		this.parentController = parentController;
		FXMLLoader commentGrid;
		CommentController commentControler;
		for (int i = 0; i < UserBag.getCurrentUser().getComments().size(); i++) {

			commentGrid = new FXMLLoader(getClass().getResource("/views/Comment.fxml"));
			commentGrid.load();
			commentControler = commentGrid.getController();
			if (UserBag.getCurrentUser().getComments().get(i).isDeleted()) {
				UserBag.getCurrentUser().getComments().remove(i);
			} else {

				commentControler.makeComment(UserBag.getCurrentUser().getComments().get(i));
				addComment(commentControler);
			}

		}

		postField.setOnKeyPressed(Utilities.pressEnterToPost(this));

	}

}
