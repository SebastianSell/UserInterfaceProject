package com.example.finalUI.controller;

import com.example.finalUI.util.SceneSwitcher;
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
    private Label loginLabel;

    @FXML
    private Button loginButton;

    @FXML
    private Label signupLabel;

    @FXML
    private Button signupButton;
    /**Initializes the page*/
    public void initialize(){
        titleLabel.setAccessibleRoleDescription("Welcome to the Task management welcome page. Below are two buttons. Login or sign up. If you are new to app and have not made an account, click sign up. But if you already have created an account, click login");
        loginLabel.setAccessibleRoleDescription("Login");
        loginButton.setAccessibleText("Click to login");
        signupLabel.setAccessibleRoleDescription("Sign up");
        signupButton.setAccessibleText("Click to sign up");

        titleLabel.setAccessibleText("Welcome page");

        loginLabel.setAccessibleText("Login option");
        loginButton.setAccessibleText("Login button");
        loginButton.setAccessibleHelp("Press to go to the login page");

        signupLabel.setAccessibleText("Signup option");
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
