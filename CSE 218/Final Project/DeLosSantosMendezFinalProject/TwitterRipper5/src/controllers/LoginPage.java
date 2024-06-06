package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.User;
import model.UserBag;
import util.Utilities;

public class LoginPage {

	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
	private GridPane Background;

	@FXML
	private HBox InteractionArea;

	@FXML
	private Button LogginButton;

	@FXML
	private PasswordField Password;

	@FXML
	private Button SingupButton;

	@FXML
	private TextField Username;

	@FXML
	private Label errorLabel;

	@FXML
	private HBox rootHBox;

	@FXML
	void switchToForumPage(ActionEvent event) throws IOException {

		boolean abletoTologin = Utilities.ableToLoggin(errorLabel, Username, Password);
		User userIsFound = UserBag.find(Username.getText());

		if (abletoTologin) {
			UserBag.setCurrentUser(userIsFound);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/TwitterRip.fxml"));
			root = loader.load();
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root, stage.getWidth(), stage.getHeight());

			TwitterRipController Forum = loader.getController();
			Forum.setUp(stage);
			Forum.goHome();
			Forum.goHome();

			stage.setScene(scene);
			stage.show();

		}
		Password.clear();

	}

	@FXML
	void switchToSingupPage(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/SingupPage.fxml"));
		Parent loadSingupPage = loader.load();
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(loadSingupPage, stage.getWidth() - 16, stage.getHeight() - 39);
		scene.getStylesheets().add(getClass().getResource("/cssStyling/application.css").toExternalForm());
		stage.setScene(scene);

	}

	@FXML
	public void switchToLoginPage(ActionEvent event) throws IOException {

		root = FXMLLoader.load(getClass().getResource("/views/LoginPage.fxml"));

		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root, stage.getWidth() - 16, stage.getHeight() - 39);
		scene.getStylesheets().add(getClass().getResource("/cssStyling/application.css").toExternalForm());

		stage.setScene(scene);
	}

}
