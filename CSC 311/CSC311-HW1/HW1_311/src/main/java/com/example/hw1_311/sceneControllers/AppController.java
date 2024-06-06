package com.example.hw1_311.sceneControllers;

import com.example.hw1_311.DBController;
import com.example.hw1_311.Grade;
import com.example.hw1_311.HW1_CSC311_Amaurys;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class contains methods to handle incoming requests from clients
 * interacting with the DBEditorView.fxml file.
 * @author Amaurys De Los Santos Mendez
 */
public class AppController {
    private Connection conn = null;
    private String dbFilePath = ".//GradeBook.accdb";
    private String databaseURL = "jdbc:ucanaccess://" + dbFilePath;
    FileChooser fileChooser = new FileChooser();

    File currentJSONfile = new File("src/main/java/jsonFiles/grades.json");

    @FXML
    private Button addGradeButton;

    @FXML
    private TextField categoryTextField;

    @FXML
    private Button displayGradesButton;

    @FXML
    private MenuItem exitButton;

    @FXML
    private MenuItem loadDBMenuItem;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField scoreTextField;

    @FXML
    private ListView<Grade> listOfGrades;

    /**
     * Adds a grade to the DB and json file.
     * <p>
     * Calls {@link #checkNameInput}, {@link #checkCategoryInput} & {@link #checkScoreInput}
     * respectively to check if the input for all of these categories are valid.
     * Creates a grade object from the name, category and score text fields.
     * Adds this grade to the destined database
     * and to its corresponding json file.
     * It then displays it in a listview.
     */
    @FXML
    void addGrade() {
        if (!checkNameInput() || !checkCategoryInput() || !checkScoreInput()){
            return;
        }

        Grade grade = new Grade(nameTextField.getText(), categoryTextField.getText(), Integer.valueOf(scoreTextField.getText()));
        DBController.insertDataToDB(conn, grade);
        DBController.fillJSONfromDB(currentJSONfile, conn);
        displayGrades();

        nameTextField.clear();
        categoryTextField.clear();
        scoreTextField.clear();
    }

    /**
     * Checks if the inputted name is not blank.
     * @return boolean, true if it passed false if it failed.
     */
    private boolean checkNameInput(){

        if(nameTextField.getText().trim().isEmpty()){
            createPopup("Invalid Input","Unable to add a new grade to the database. \n" +
                    "Name cannot be empty.");
            return false;
        }

        return true;
    }

    /**
     * Checks if the inputted category is not blank, has any digits or blank spaces.
     * @return boolean, true if it passed false if it failed.
     */
    private boolean checkCategoryInput(){
        Pattern pattern1 = Pattern.compile("\\d"); // checks for digits
        Pattern pattern2 = Pattern.compile("\\s"); // checks for spaces
        Matcher matcher1 = pattern1.matcher(categoryTextField.getText());
        Matcher matcher2 = pattern2.matcher(categoryTextField.getText());

        if(categoryTextField.getText().trim().isEmpty()){

            createPopup("Invalid Input","Unable to add a new grade to the database. \n" +
                    "Category cannot be empty.");
            return false;
        }

        if(matcher1.find() || matcher2.find()){
            createPopup("Invalid Input","Unable to add a new grade to the database. \n" +
                    "Category can't contain any digits or spaces.");
            return false;
        }


        return true;
    }

    /**
     * Checks if the inputted score is not blank, has any letters or blank spaces.
     * @return boolean, true if it passed false if the input is blank, has any letters or blank spaces.
     */
    private boolean checkScoreInput(){
        Pattern pattern1 = Pattern.compile("[a-zA-Z]"); // checks for upper or lower case letters
        Pattern pattern2 = Pattern.compile("\\s"); // checks for spaces
        Matcher matcher1 = pattern1.matcher(scoreTextField.getText());
        Matcher matcher2 = pattern2.matcher(scoreTextField.getText());

        if(scoreTextField.getText().trim().isEmpty()){
            createPopup("Invalid Input","Unable to add a new grade to the database. \n" +
                    "Score cannot be empty.");
            return false;
        }

        if(matcher1.find() || matcher2.find()){
            createPopup("Invalid Input","Unable to add a new grade to the database. \n" +
                    "Score can only contain digits. No spaces or letters.");
            return false;
        }


        return true;
    }

    /**
     * Populates the list view.
     * Calls {@link DBController#getData(Connection)} to get a linked list from the database.
     * The listview is cleared. Then the linkedList is used to populate the listView.
     *
     */
    @FXML
    void displayGrades() {
        DBController.fillDBfromJSON(conn,currentJSONfile);
        LinkedList<Grade> grades = DBController.getData(conn);
        listOfGrades.getItems().clear();
        listOfGrades.getItems().addAll(grades);
    }

    /**
     * Closes the application.
     *
     */
    @FXML
    void exitApp() {
        System.exit(0);
    }

    /**
     * Opens file explorer and lets you chose a json file to view and edit.
     * Calls {@link DBController#fillDBfromJSON(Connection, File)}
     * to fill the database with information from the file.
     * Calls {@link #displayGrades()} to load that information into the listview.
     *
     */
    @FXML
    void loadDBfromJSON() {
        currentJSONfile = fileChooser.showOpenDialog(new Stage());
        DBController.fillDBfromJSON(conn, currentJSONfile);
        displayGrades();
    }

    /**
     * Creates a new window when an input Error occurs.
     *
     * @param errorLabel Displays the type of error that has occurred.
     * @param errorDescription Displays detailed information about hte error.
     */
    private void createPopup(String errorLabel, String errorDescription)  {
        FXMLLoader fxmlLoader = new FXMLLoader(HW1_CSC311_Amaurys.class.getResource("ErrorView.fxml"));

        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 700, 240);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        WarningController tempController = ((WarningController)fxmlLoader.getController());

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        tempController.setErrorLabel(errorLabel);
        tempController.setErrorDescription(errorDescription);
        tempController.setStage(stage);

    }

    /**
     * Creates the initial connection to the database.
     * Sets the initial file chooser directory.
     */
    public void initialize(){

        try {
            System.out.println(databaseURL);
            conn = DriverManager.getConnection(databaseURL);
        } catch (SQLException e) {

            DBController.createDBAndTable();
            initialize();
            throw new RuntimeException(e);
        }

        fileChooser.setInitialDirectory(new File("C:\\Users\\amaur\\IdeaProjects\\HW1_311\\HW1_311\\src\\main\\java\\jsonFiles"));
        // DBController.fillDBfromJSON(conn,currentJSONfile);

    }

}