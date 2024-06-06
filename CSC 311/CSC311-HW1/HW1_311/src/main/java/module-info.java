module com.example.hw1_311 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.healthmarketscience.jackcess;
    requires java.sql;
    requires com.google.gson;

    opens com.example.hw1_311 to javafx.fxml, com.google.gson;
    exports com.example.hw1_311;
    exports com.example.hw1_311.sceneControllers;
    opens com.example.hw1_311.sceneControllers to javafx.fxml, com.google.gson;
}