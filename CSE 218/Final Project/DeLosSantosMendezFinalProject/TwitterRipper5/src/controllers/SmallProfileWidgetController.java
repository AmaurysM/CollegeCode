package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.User;

public class SmallProfileWidgetController {
	
	private User ownerOfWidget;
	
	@FXML
	private GridPane mainGridPane;

	@FXML
	private ImageView pfpImageView;

	@FXML
	private HBox rootHBox = new HBox();

	@FXML
	private Label userNameLabel;
	
	public User getWidgetUser() {
		return ownerOfWidget;
	}
	
	public HBox getRootHBox() {
		return rootHBox;
	}

	public GridPane getMainGridPane() {
		return mainGridPane;
	}

	public ImageView getPfpImageView() {
		return pfpImageView;
	}

	public Label getUserNameLabel() {
		return userNameLabel;
	}
	
    public void makeWidget(User user) {
    	ownerOfWidget = user;
    	userNameLabel.setText(user.getUsername());
    }
    
}
