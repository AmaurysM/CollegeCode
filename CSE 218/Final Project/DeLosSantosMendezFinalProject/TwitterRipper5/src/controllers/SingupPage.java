package controllers;
//import UserBag;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import util.Utilities;

public class SingupPage {

//	private Stage stage;
//	private Scene scene;
//	private Parent root;
	
	
    @FXML
    private GridPane Background;

    @FXML
    private Pane InteractionArea;

    @FXML
    private Button backToLogin;

    @FXML
    private TextField eMailArea;

    @FXML
    private PasswordField firstPasswordArea;

    @FXML
    private PasswordField secondPasswordArea = new PasswordField();

    @FXML
    private Button singup;

    @FXML
    private TextField usernameArea;
    
    @FXML
    private HBox rootHBox;
    
    @FXML
    private Label errorLabel = new Label();

    @FXML
    void makeUser(ActionEvent event) {
    	Utilities.createNewUser( usernameArea, firstPasswordArea, secondPasswordArea, eMailArea, errorLabel);
    	Utilities.saveAllUsers();
    }

    @FXML
    void switchToLoginPage(ActionEvent event) throws IOException {
    	LoginPage scene = new LoginPage();
    	scene.switchToLoginPage(event);
    }
    
    
}

  	