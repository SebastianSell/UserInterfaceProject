module com.example.finalUI {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;


    opens com.example.finalUI to javafx.fxml;
    exports com.example.finalUI;
    exports com.example.finalUI.model;
    opens com.example.finalUI.model to javafx.fxml;
    exports com.example.finalUI.util;
    opens com.example.finalUI.util to javafx.fxml;
    exports com.example.finalUI.controller;
    opens com.example.finalUI.controller to javafx.fxml;
    exports com.example.finalUI.database;
    opens com.example.finalUI.database to javafx.fxml;
    exports com.example.finalUI.app;
    opens com.example.finalUI.app to javafx.fxml;
}