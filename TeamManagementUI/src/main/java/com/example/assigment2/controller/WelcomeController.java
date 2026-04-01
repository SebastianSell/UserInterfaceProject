package com.example.assigment2.controller;

import com.example.assigment2.util.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

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

    public void initialize(){
        titleLabel.setAccessibleRoleDescription("Welcome to the Task management welcome page. Below are two buttons. Login or sign up. If you are new to app and have not made an account, click sign up. But if you already have created an account, click login");
        loginLabel.setAccessibleRoleDescription("Login");
        loginButton.setAccessibleText("Click to login");
        signupLabel.setAccessibleRoleDescription("Sign up");
        signupButton.setAccessibleText("Click to sign up");

    }

    @FXML
    private void openLogin() {

        SceneSwitcher.switchScene(loginButton, "login.fxml");

    }

    @FXML
    private void openSignup() {

        SceneSwitcher.switchScene(signupButton, "signup-view.fxml");

    }
}
