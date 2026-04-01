package com.example.assigment2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeController {

    @FXML
    private VBox root;

    @FXML
    private Button loginButton;

    @FXML
    private Button signupButton;

    @FXML
    private void openLogin() {

        SceneSwitcher.switchScene(loginButton, "login.fxml");

    }

    @FXML
    private void openSignup() {

        SceneSwitcher.switchScene(signupButton, "signup-view.fxml");

    }
}
