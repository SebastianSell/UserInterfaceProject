module com.example.assigment2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;


    opens com.example.assigment2 to javafx.fxml;
    exports com.example.assigment2;
    exports com.example.assigment2.model;
    opens com.example.assigment2.model to javafx.fxml;
    exports com.example.assigment2.util;
    opens com.example.assigment2.util to javafx.fxml;
    exports com.example.assigment2.controller;
    opens com.example.assigment2.controller to javafx.fxml;
    exports com.example.assigment2.database;
    opens com.example.assigment2.database to javafx.fxml;
    exports com.example.assigment2.app;
    opens com.example.assigment2.app to javafx.fxml;
}