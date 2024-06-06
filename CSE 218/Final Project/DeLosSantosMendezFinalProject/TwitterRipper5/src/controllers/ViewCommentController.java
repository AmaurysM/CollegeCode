package controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Comment;
import model.UserBag;
import util.ReadAndWriteFromTxtFile;
import util.StaticVeriables;
import util.TreeHelper;
import util.Utilities;

public class ViewCommentController {

	@FXML
	private TreeView<Comment> treeViewOfComments = new TreeView<>();

	private TwitterRipController twitterRipController;

	public TwitterRipController getParentController() {
		return twitterRipController;
	}

	@FXML
	private ImageView exitButton = new ImageView();

	private TreeView lastPressedTree;

	private TreeItem<Comment> pressedTreeView;

	private boolean pressedComment = false;
	@FXML
	private Label UserNameLabel;

	@FXML
	private Label commentWordsLabel;

	private Comment commentPressed = new Comment(null, null);

	public void setComment(Comment commentPressed) {
		this.commentPressed = commentPressed;
	}

	@FXML
	private TextArea comment = new TextArea();

	@FXML
	private ImageView commentProfilePicture;

	@FXML
	private ListView<Comment> commentsUnderCommentsList = new ListView<>();

	@FXML
	private GridPane mainGrid;

	@FXML
	private Button postButton;

	@FXML
	private TextField postField;

	@FXML
	private HBox rootHbox;

	@FXML
	private VBox commentVBox = new VBox();

	@FXML
	private ScrollPane commetScrollPane = new ScrollPane();

	@FXML
	private Label spellCheckLabel = new Label();

	public HBox getRootHBox() {
		return rootHbox;
	}

	public ListView<Comment> getCommentsUnderCommentsList() {
		return commentsUnderCommentsList;
	}

	public TextField getPostField() {
		return postField;
	}

	public GridPane getMainGrid() {
		return mainGrid;
	}

	public void setMainGrid(GridPane mainGrid) {
		this.mainGrid = mainGrid;
	}

	public Label getSpellCheckLabel() {
		return spellCheckLabel;
	}

	public void setSpellCheckLabel(Label spellCheck) {
		this.spellCheckLabel = spellCheck;
	}

	public void currentUserProfilePictureClicked() throws IOException {
		twitterRipController.goToProfilePage();
	}

	public void currentCommentWordsClicked() {
		StaticVeriables.setLastClickedComment(StaticVeriables.lastClickedPost());
	}

	public void setUpCommentView(TwitterRipController twitterRipController) throws IOException {
		this.twitterRipController = twitterRipController;

		TreeItem<Comment> rootItem = new TreeItem<Comment>(StaticVeriables.lastClickedPost());
		rootItem.setExpanded(true);
		StaticVeriables.setLastClickedComment(StaticVeriables.lastClickedPost());
		UserNameLabel.setText(StaticVeriables.lastClickedPost().getUser().getUsername());
		commentWordsLabel.setText(StaticVeriables.lastClickedPost().getcommentWords());

		treeViewOfComments.setRoot(rootItem);
		Utilities.treeCellFactory(treeViewOfComments, this);
//		TreeHelper.makeTree(treeViewOfComments);
		TreeHelper.makeLeaf(treeViewOfComments.getRoot());
		treeViewOfComments.scrollTo(StaticVeriables.getLastClikedCommentIndexInTreeItem() + 1);
		postField.setOnKeyPressed(Utilities.pressEnterToPostUnderComments(this));

	}

	public void addCommentToList() throws IOException {
		Comment hold = new Comment(UserBag.getCurrentUser(), postField.getText());

		if (StaticVeriables.lastClickedComment() == (StaticVeriables.lastClickedPost())) {

			StaticVeriables.lastClickedPost().addCommentToComment(hold);
		} else {
			StaticVeriables.lastClickedComment().addCommentToComment(hold);

		}

		ReadAndWriteFromTxtFile.writeUsersToTxtFile();
		ReadAndWriteFromTxtFile.writeCommentsToTxtFile();

		twitterRipController.goToCommentView();

		postField.clear();
	}

	public void resetCommentPressed() {
		pressedComment = false;
	}

	public ImageView getExitButton() {
		return exitButton;
	}

	public void exitCommentView() throws IOException {
		StaticVeriables.setLastClikedCommentIndexInTreeItem(0);
		twitterRipController.goHome();

	}

}
