package com.example.hw1_311;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HW1_CSC311_Amaurys extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HW1_CSC311_Amaurys.class.getResource("DBEditorView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 340);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}