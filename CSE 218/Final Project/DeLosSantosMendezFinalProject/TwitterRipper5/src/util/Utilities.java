package util;

import java.io.IOException;

import controllers.BlankListController;
import controllers.CommentTreeViewController;
import controllers.HomeTwitterGridPaneController;
import controllers.ViewCommentController;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Comment;
import model.User;
import model.UserBag;

public class Utilities {

	public static void loggin(Stage controler, Label errorLabel, Button button, TextField username,
			PasswordField password) {

		if (ableToLoggin(errorLabel, username, password)) {

		}
		username.clear();
		password.clear();
	}

	public static boolean ableToLoggin(Label errorLabel, TextField username, PasswordField password) {

		if (username.getText().equals("")) {
			errorLabel.setText("You need to enter a Username");
			return false;
		}

		if (password.getText().equals("")) {
			errorLabel.setText("You need to enter a Password");
			return false;
		}

		if (UserBag.find(username.getText()) == null) {
			errorLabel.setText("This username does not exist");
			return false;
		}

		if ((UserBag.find(username.getText()).getPassword().compareTo(password.getText())) != 0) {
			errorLabel.setText("This account does not exist");
			return false;
		}

		return true;
	}

	public static void createNewUser(TextField name, PasswordField password, PasswordField passwordCheck,
			TextField mail, Label errorLabel) {
		if (ableToCreateUser(name, password, passwordCheck, mail, errorLabel)) {
			UserBag.addUser(new User(name.getText(), password.getText(), mail.getText()));
		}
		name.clear();
		password.clear();
		passwordCheck.clear();
		mail.clear();
	}

	public static boolean ableToCreateUser(TextField name, PasswordField password, PasswordField passwordCheck,
			TextField mail, Label errorLabel) {

		if (name.getText().equals("")) {
			errorLabel.setText("You Must Enter A Username");
			return false;
		}

		if (password.getText().equals("")) {
			errorLabel.setText("You Must Enter A Password");
			return false;
		}

		if (passwordCheck.getText().equals(null)) {
			errorLabel.setText("You Must Check That Both Password Match");
			return false;
		}

		if (!passwordCheck.getText().equals(password.getText())) {
			errorLabel.setText("These Passwords Dont Match");
			return false;
		}

		if (mail.getText().equals("")) {
			errorLabel.setText("Please Enter Your Email");
			return false;
		}

		if (UserBag.find(name.getText()) != null) {
			errorLabel.setText("A User With That Username Already Exists, Please Try another Username");
			return false;
		}

		return true;
	}

	public static void saveAllUsers() {
		ReadAndWriteFromTxtFile.writeUsersToTxtFile();
	}

	public static void loadAllUsers() {
		ReadAndWriteFromTxtFile.ReadUsersFromTxtFile();
	}

	public static void saveAllComments() {
		ReadAndWriteFromTxtFile.writeUsersToTxtFile();

	}

	public static void loadAllComments() {
		ReadAndWriteFromTxtFile.ReadUsersFromTxtFile();
	}

	public static EventHandler<KeyEvent> pressEnterToPost(HomeTwitterGridPaneController Forum) {
		EventHandler<KeyEvent> event = new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent key) {
				switch (key.getCode()) {
				case ENTER:
					try {
						Forum.addToCommentArea();
					} catch (IOException e) {
						e.printStackTrace();
					}
				case SPACE:

					String lastWord = Forum.getPostField().getText()
							.substring(Forum.getPostField().getText().lastIndexOf(" ") + 1);

					if (lastWord.compareTo("") == 0) {
						break;
					}

					if (!Hash.hasWord(lastWord)) {
						Forum.getSpellCheckLabel().setVisible(true);
						Forum.getSpellCheckLabel().setText(" \"" + lastWord + "\" is not a word");
					} else {
						Forum.getSpellCheckLabel().setVisible(false);
					}

				default:
					break;

				}
			}

		};

		return event;

	}

	public static EventHandler<KeyEvent> pressEnterToPostUnderComments(ViewCommentController Forum) {
		EventHandler<KeyEvent> event = new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent key) {
				switch (key.getCode()) {
				case ENTER:
					try {
						Forum.addCommentToList();
					} catch (IOException e) {
						// e.printStackTrace();
					}
				case SPACE:

					String lastWord = Forum.getPostField().getText()
							.substring(Forum.getPostField().getText().lastIndexOf(" ") + 1);

					if (lastWord.compareTo("") == 0) {
						break;
					}

					if (!Hash.hasWord(lastWord)) {
						Forum.getSpellCheckLabel().setText(" \"" + lastWord + "\" is not a word");
					} else {
						Forum.getSpellCheckLabel().setText("");
					}

				default:
					break;

				}
			}

		};

		return event;

	}

	public static EventHandler<KeyEvent> pressEnterToSearchForUsers(BlankListController Forum) {
		EventHandler<KeyEvent> event = new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent key) {
				switch (key.getCode()) {
				case ENTER:
					try {
						Forum.lookUpUsers();
					} catch (IOException e) {
						// e.printStackTrace();
					}

				default:
					break;

				}
			}

		};

		return event;

	}

	public static void treeCellFactory(TreeView<Comment> traceTree, ViewCommentController viewCommentController) {

		traceTree.setCellFactory(tree -> {
			TreeCell<Comment> cell = new TreeCell<>();

			cell.itemProperty().addListener((obs, oldItem, newItem) -> {
				if (newItem == null) {
					cell.setText(null);
					cell.setGraphic(null);
				} else {
					FXMLLoader homeGrid = new FXMLLoader(
							viewCommentController.getClass().getResource("/views/CommentTreeView.fxml"));
					try {
						homeGrid.load();
					} catch (IOException e) {
						e.printStackTrace();
					}
					CommentTreeViewController gridPane = homeGrid.getController();
					gridPane.make(newItem, viewCommentController);
					cell.setDisclosureNode(null);

					cell.setGraphic(gridPane.getGridPane());
					if (UserBag.getCurrentUser().getUsername()
							.compareTo(gridPane.getComment().getUser().getUsername()) != 0) {
						gridPane.getContextMenu().getItems().remove(2);
					}

				}
			});

			cell.setOnMouseClicked(event -> {
				if (!cell.isEmpty()) {
					TreeItem<Comment> treeItem = cell.getTreeItem();

					if (event.getClickCount() == 2) {
						treeItem.setExpanded(true);

						traceTree.refresh();

						StaticVeriables.setLastClikedCommentIndexInTreeItem(cell.getIndex());
						StaticVeriables
								.setLastClickedComment(traceTree.getSelectionModel().getSelectedItem().getValue());

					}
				}
			});
			return cell;
		});

	}

}
