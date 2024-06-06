package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;
import model.UserBag;
import util.ReadAndWriteFromTxtFile;

public class SettingsController {

	@FXML
	private ContextMenu editEmailContextMenu;

	@FXML
	private ContextMenu editPasswordContextMenu;

	@FXML
	private MenuItem editUsernameMenuItem;

	@FXML
	private Label emailLabel;

	@FXML
	private StackPane emailTackPane;

	@FXML
	private TextField emailTextField;

	@FXML
	private Button logoutButton;

	@FXML
	private Label passwordLabel;

	@FXML
	private TextField passwordTextField;

	@FXML
	private StackPane passwordtackPane;

	@FXML
	private VBox rootVBox;

	@FXML
	private Label usernameLabel;

	@FXML
	private StackPane usernameStackPane;

	@FXML
	private TextField usernameTextField;

	@FXML
	private MenuItem editPasswordMenuItem;

	@FXML
	private MenuItem editEmailMenuItem;

	private TwitterRipController twitterRipController;

	public ContextMenu getEditEmailContextMenu() {
		return editEmailContextMenu;
	}

	public void setEditEmailContextMenu(ContextMenu editEmailContextMenu) {
		this.editEmailContextMenu = editEmailContextMenu;
	}

	public ContextMenu getEditPasswordContextMenu() {
		return editPasswordContextMenu;
	}

	public void setEditPasswordContextMenu(ContextMenu editPasswordContextMenu) {
		this.editPasswordContextMenu = editPasswordContextMenu;
	}

	public MenuItem getEditUsernameMenuItem() {
		return editUsernameMenuItem;
	}

	public void setEditUsernameMenuItem(MenuItem editUsernameMenuItem) {
		this.editUsernameMenuItem = editUsernameMenuItem;
	}

	public Label getEmailLabel() {
		return emailLabel;
	}

	public void setEmailLabel(Label emailLabel) {
		this.emailLabel = emailLabel;
	}

	public StackPane getEmailTackPane() {
		return emailTackPane;
	}

	public void setEmailTackPane(StackPane emailTackPane) {
		this.emailTackPane = emailTackPane;
	}

	public TextField getEmailTextField() {
		return emailTextField;
	}

	public void setEmailTextField(TextField emailTextFiled) {
		this.emailTextField = emailTextFiled;
	}

	public Button getLogoutButton() {
		return logoutButton;
	}

	public void setLogoutButton(Button logoutButton) {
		this.logoutButton = logoutButton;
	}

	public Label getPasswordLabel() {
		return passwordLabel;
	}

	public void setPasswordLabel(Label passwordLabel) {
		this.passwordLabel = passwordLabel;
	}

	public TextField getPasswordTextField() {
		return passwordTextField;
	}

	public void setPasswordTextField(TextField passwordTextField) {
		this.passwordTextField = passwordTextField;
	}

	public StackPane getPasswordtackPane() {
		return passwordtackPane;
	}

	public void setPasswordtackPane(StackPane passwordtackPane) {
		this.passwordtackPane = passwordtackPane;
	}

	public VBox getRootVBox() {
		return rootVBox;
	}

	public void setRootVBox(VBox rootVBox) {
		this.rootVBox = rootVBox;
	}

	public Label getUsernameLabel() {
		return usernameLabel;
	}

	public void setUsernameLabel(Label usernameLabel) {
		this.usernameLabel = usernameLabel;
	}

	public StackPane getUsernameStackPane() {
		return usernameStackPane;
	}

	public void setUsernameStackPane(StackPane usernameStackPane) {
		this.usernameStackPane = usernameStackPane;
	}

	public TextField getUsernameTextField() {
		return usernameTextField;
	}

	public void setUsernameTextField(TextField usernameTextField) {
		this.usernameTextField = usernameTextField;
	}

	public MenuItem getEditPasswordMenuItem() {
		return editPasswordMenuItem;
	}

	public void setEditPasswordMenuItem(MenuItem editPasswordMenuItem) {
		this.editPasswordMenuItem = editPasswordMenuItem;
	}

	public MenuItem getEditEmailMenuItem() {
		return editEmailMenuItem;
	}

	public void setEditEmailMenuItem(MenuItem editEmailMenuItem) {
		this.editEmailMenuItem = editEmailMenuItem;
	}

	public void setUp(TwitterRipController twitterRipController, Stage stage) {
		this.twitterRipController = twitterRipController;

		this.usernameLabel.setText(UserBag.getCurrentUser().getUsername());
		this.passwordLabel.setText(UserBag.getCurrentUser().getPassword());
		this.emailLabel.setText(UserBag.getCurrentUser().getMail());

	}

	public void logout(ActionEvent event) throws IOException {
		Stage stage;
		Scene scene;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/LoginPage.fxml"));
		Parent loadSingupPage = loader.load();
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(loadSingupPage, stage.getWidth()-16, stage.getHeight()-39);
		scene.getStylesheets().add(getClass().getResource("/cssStyling/application.css").toExternalForm());

		stage.setScene(scene);
	}
	
	public void editUsername() {
		usernameTextField.setVisible(true);
		usernameTextField.setDisable(false);
		usernameTextField.setText(UserBag.getCurrentUser().getUsername());
	}
	
	public void editPassword() {
		passwordTextField.setVisible(true);
		passwordTextField.setDisable(false);
		passwordTextField.setText(UserBag.getCurrentUser().getPassword());
	}
	
	public void editEmail() {
		emailTextField.setVisible(true);
		emailTextField.setDisable(false);
		emailTextField.setText(UserBag.getCurrentUser().getMail());
	}
	
	public void confirmNewUsername(KeyEvent event) throws IOException {
		if(event.getCode() == KeyCode.ENTER) {
			
			if(usernameTextField.isVisible()) {
				UserBag.getCurrentUser().setUsername(usernameTextField.getText());
				usernameTextField.setVisible(false);
				usernameTextField.setDisable(true);
				
				User editedUser = UserBag.getCurrentUser();
				UserBag.getBag().remove(UserBag.getCurrentUser());
				UserBag.addUser(editedUser);
				ReadAndWriteFromTxtFile.writeUsersToTxtFile();
				twitterRipController.goToSettings();
			}
			
		}
	}
	
	public void confirmNewPassword(KeyEvent event) throws IOException {
		if(event.getCode() == KeyCode.ENTER) {
			
			if(passwordTextField.isVisible()) {
				UserBag.getCurrentUser().setPassword(passwordTextField.getText());
				passwordTextField.setVisible(false);
				passwordTextField.setDisable(true);
				User editedUser = UserBag.getCurrentUser();
				UserBag.getBag().remove(UserBag.getCurrentUser());
				UserBag.addUser(editedUser);
				ReadAndWriteFromTxtFile.writeUsersToTxtFile();
				twitterRipController.goToSettings();
			}
			
		}
	}
	
	public void confirmNewEmail(KeyEvent event) throws IOException {
		if(event.getCode() == KeyCode.ENTER) {
			
			if(emailTextField.isVisible()) {
				UserBag.getCurrentUser().setMail(emailTextField.getText());;
				emailTextField.setVisible(false);
				emailTextField.setDisable(true);
				User editedUser = UserBag.getCurrentUser();
				UserBag.getBag().remove(UserBag.getCurrentUser());
				UserBag.addUser(editedUser);
				ReadAndWriteFromTxtFile.writeUsersToTxtFile();
				twitterRipController.goToSettings();
			}
			
		}
	}
	
	public void removeUser() throws IOException {
		UserBag.getBag().remove(UserBag.getCurrentUser());
		twitterRipController.goToSettings();
	}
	 
}
