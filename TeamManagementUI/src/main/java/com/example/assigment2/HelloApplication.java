/**
* Name: Sebastian Sell
* Student number: 041147547
* course code: cst8412
* assignment name: Assignment2
* */

package com.example.assigment2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**Main class of the program that runs*/
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Loads the FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("CRUD Table Example");
        stage.setScene(scene);
        stage.show();
    }
}
