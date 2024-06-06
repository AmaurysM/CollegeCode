package com.example.hw1_311.sceneControllers;

import com.example.hw1_311.HW1_CSC311_Amaurys;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * This class contains methods to handle incoming requests from clients
 * interacting with the ErrorView.fxml file.
 * @author Amaurys De Los Santos Mendez
 */
public class WarningController {

    Scene scene = null;
    Stage stage = null;

    @FXML
    private Label errorLabel;

    @FXML
    private Label errorDescription;

    /**
     * Gives the error label.
     * @return Label the type of error that has occurred.
     */
    public Label getErrorLabel() {
        return errorLabel;
    }

    /**
     * Sets the text of the error label.
     * @param errorLabel The type of error that has occurred as a string.
     */
    public void setErrorLabel(String errorLabel) {
        this.errorLabel.setText(errorLabel);
    }

    /**
     * Gives the errorDescription Label
     * @return Label A detailed description of the error that accorded.
     */
    public Label getErrorDescription() {
        return errorDescription;
    }

    /**
     * Sets the text of the error description.
     * @param errorDescription A detailed description of the error that accorded.
     */
    public void setErrorDescription(String errorDescription) {
        this.errorDescription.setText(errorDescription);
    }

    /**
     * Closes the warning window.
     */
    @FXML
    void exitWarning() {
        stage.close();
    }

    /**
     * Sets the stage of the window.
     * @param stage The stage of this window.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
