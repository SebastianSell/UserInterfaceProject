package com.example.finalUI.controller;

import com.example.finalUI.util.SceneSwitcher;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * course code: cst8412
 *
 *
 * Controller class for the welcome-view.fxml file.
 *
 *
 * @author Sebastian Sell, Luca Beumer, Bennet Ireland
 * @version 1.0
 */
public class WelcomeController {

    @FXML
    private VBox root;

    @FXML
    private Label titleLabel;

    @FXML
    private Button loginButton;

    @FXML
    private Button signupButton;
    /**Initializes the page*/
    public void initialize(){
        titleLabel.setFocusTraversable(true);
        titleLabel.setAccessibleText("Login page");
        Platform.runLater(() -> titleLabel.requestFocus());
        titleLabel.setAccessibleText("Welcome to the Task management welcome page");
        loginButton.setAccessibleText("Login button");
        loginButton.setAccessibleHelp("Press to go to the login page");
        signupButton.setAccessibleText("Signup button");
        signupButton.setAccessibleHelp("Press to go to the signup page");


    }
    /**handles the action of going into the login page*/
    @FXML
    private void openLogin() {

        SceneSwitcher.switchScene(loginButton, "login.fxml");

    }
    /**handles the action of going into the login page*/
    @FXML
    private void openSignup() {

        SceneSwitcher.switchScene(signupButton, "signup-view.fxml");

    }
}
