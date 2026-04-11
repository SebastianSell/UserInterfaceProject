package com.example.finalUI.controller;

import com.example.finalUI.util.SceneSwitcher;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
    private Label instructionLabel;

    @FXML
    private Button loginButton;

    @FXML
    private Label loginHelpLabel;

    @FXML
    private Button signupButton;

    @FXML
    private Label signupHelpLabel;


    /**Initializes the page*/
    public void initialize(){
        titleLabel.setFocusTraversable(true);
        titleLabel.setAccessibleText("Welcome page title");
        Platform.runLater(() -> titleLabel.requestFocus());
        instructionLabel.setFocusTraversable(true);
        instructionLabel.setAccessibleText("Welcome page instructions");
        instructionLabel.setAccessibleHelp("Choose login if you already have an account or signup to create one");
        signupButton.setAccessibleText("Signup button");
        signupButton.setAccessibleHelp("Press to go to the signup page");


    }
    /**Handles the action of going into the login page*/
    @FXML
    private void openLogin() {
        SceneSwitcher.switchScene(loginButton, "login.fxml");
    }
    /**Handles the action of going into the login page*/
    @FXML
    private void openSignup() {
        SceneSwitcher.switchScene(signupButton, "signup-view.fxml");
    }

    /**
     * Returns the Node that should receive initial focus when the page loads.
     */
    public Node getFirstFocusNode() {
        return titleLabel; // or the main label/button of the page
    }
}
