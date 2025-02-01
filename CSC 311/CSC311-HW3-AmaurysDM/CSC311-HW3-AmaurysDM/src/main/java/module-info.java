module com.csc311.assignment3amaurysdm {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens com.csc311.hw3amaurysdm to javafx.fxml, com.google.gson;
    exports com.csc311.hw3amaurysdm;
    exports com.csc311.hw3amaurysdm.controller;
    opens com.csc311.hw3amaurysdm.controller to com.google.gson, javafx.fxml;
    exports com.csc311.hw3amaurysdm.model;
    opens com.csc311.hw3amaurysdm.model to com.google.gson, javafx.fxml;
}