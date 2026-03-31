package com.example.assigment2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeController {

    @FXML
    private VBox root;

    @FXML
    private void openLogin() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Scene scene = new Scene(loader.load());

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    private void openSignup() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("signup-view.fxml"));
        Scene scene = new Scene(loader.load());

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
    }
}
