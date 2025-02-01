package com.csc311.hw3amaurysdm.controller;

import com.csc311.hw3amaurysdm.model.Grade;
import com.csc311.hw3amaurysdm.model.GradeComparator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;
/**
 * This class contains methods to store grade information.
 * @author Amaurys De Los Santos Mendez
 */
public class  HelloController <E> implements Initializable {

    private static Stage mainStage;
    private Queue<Grade> queue = new LinkedList<>();
    private FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
    private File selectedFile;

    @FXML
    private Button addGradeButton;

    @FXML
    private TextField lastActionTextField;

    @FXML
    private TextField newCategoryTextField;

    @FXML
    private TextField newNameTextField;

    @FXML
    private TextField newScoreTextField;

    @FXML
    private TextField queueTextField;

    @FXML
    private TableColumn<Grade, Integer> scoreColumn = new TableColumn<>("score");

    @FXML
    private TableColumn<Grade, String> nameColumn = new TableColumn<>("name");

    @FXML
    private TableColumn<Grade, String> categoryColumn = new TableColumn<>("category");

    @FXML
    private TableView<Grade> tableView;
    /**
     * Sets the main stage of the application
     *
     */
    public static void setStage(Stage stage) {
        mainStage = stage;
    }
    /**
     * Clears the queue and updates the last action to "Queue Cleared"
     *
     */
    @FXML
    void clearQueue() {
        queue.clear();
        lastActionTextField.setText("Queue Cleared");
    }
    /**
     * Closes the application
     *
     */
    @FXML
    void closeApplication(ActionEvent event) {
        Platform.exit();
    }

    /**
     * Parses the file given and puts them in the queue.
     * Also updates the last action to be "Json File Opened".
     * @param file , file to be parsed
     *
     */
    private void parseJsonToQueue(File file){
        if(file == null) {
            return;
        }
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        clearQueue();

        try {

            FileReader fr = new FileReader(file);
            addGrades(gson.fromJson(fr, new TypeToken<ArrayList<Grade>>() {}.getType()));

        } catch (FileNotFoundException e) {

        }
        lastActionTextField.setText("Json File Opened");
    }
    /**
     * Sets the type of queue to be used (normal queue) and calls {@link #parseJsonToQueue(File)} to fill it upt with info.
     *
     */
    @FXML
    void openToNormalQueue() {
        selectedFile = fileChooser.showOpenDialog(mainStage);
        queue = new LinkedList<>();
        parseJsonToQueue(selectedFile);
        queueTextField.setText("Normal Queue");

    }
    /**
     * Sets the type of queue to be used (priorityQueue) and calls {@link #parseJsonToQueue(File)} to fill it upt with info.
     *
     */
    @FXML
    void openToPriorityQueue() {
        selectedFile = fileChooser.showOpenDialog(mainStage);
        Comparator GradeComparator = new GradeComparator();
        queue = new PriorityQueue<>(GradeComparator);
        parseJsonToQueue(selectedFile);
        queueTextField.setText("Priority Queue");
    }

    /**
     * Saves the queue to a json file as an array and updates the last action to "Save Queue to JSON File"
     *
     */
    @FXML
    void saveToJSON(ActionEvent event) {
        selectedFile = fileChooser.showSaveDialog(mainStage);

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        String jsonString = gson.toJson(queue.toArray());

        PrintStream ps = null;
        try {
            ps = new PrintStream(selectedFile);
            ps.println(jsonString);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        lastActionTextField.setText("Save Queue to JSON File");
    }
    /**
     * Checks that the input boxes for a new user are filled.
     * If they are it will return true else false;
     *
     */
    private boolean checkInput(){
        if(newNameTextField.getText().isEmpty()){
            return false;
        }
        if(newCategoryTextField.getText().isEmpty()){
            return false;
        }
        if(newScoreTextField.getText().isEmpty()){
            return false;
        }
        if(!newScoreTextField.getText().matches("\\d+")){
            return false;
        }

        return true;
    }
    /**
     * Adds a grade to the queue and updates the tableview.
     * Also updates the last action to be "Grade Added".
     *
     */
    @FXML
    void addGrade() {
        if(!checkInput()){
            return;
        }

        queue.add(
                new Grade(
                        newNameTextField.getText(),
                        newCategoryTextField.getText(),
                        Integer.valueOf(newScoreTextField.getText())
                )
        );
        //observableList.setAll(currentQueue);
        tableView.getItems().setAll(queue);
        lastActionTextField.setText("Grade Added");
    }
    /**
     * Adds an arrayList to the queue and array list.
     * @param grades, arraylist to be added.
     */
    void addGrades(ArrayList<Grade> grades) {
        queue.addAll(grades);
        tableView.getItems().setAll(queue);
    }
    /**
     * Creates an information popup.
     * @param title, title of the popup
     * @param message, message of the popup
     *
     */
    private static void showWarning(String title, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Queue Update");
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.show();
    }

    /**
     * Remove grade based on the type of queue.
     *
     */
    @FXML
    void removeGrade(ActionEvent event) {
        showWarning("Remove Grade From Queue",queue.peek().toString());
        queue.poll();
        tableView.getItems().setAll(queue);
        lastActionTextField.setText("Grade Removed");
    }
/*
    private void cellFactory(){
        tableView.setRowFactory(lv -> {
            TableRow<Grade> row = new TableRow<>();
            if(row.isEmpty()){
                //row.setStyle("-fx-background-color: transparent");
            }else{
                row.setStyle("-fx-padding: 5px ;" +
                        "-fx-background-insets: 0px, 2px ;" +
                        "-fx-background-radius: 5;" +
                        "-fx-background-color: RED, #549159; "
                );
            }
            return row;
        });
    }*/
    /**
     * Runs at start, sets the cell value factory for all the columns in the table view.
     * Sets the default file location for file chooser.
     *
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        nameColumn.setCellValueFactory(new PropertyValueFactory<Grade,String>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Grade,String>("category"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<Grade,Integer>("score"));

        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(new File(".\\"));

    }
}