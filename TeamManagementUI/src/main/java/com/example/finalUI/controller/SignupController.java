package com.example.finalUI.controller;


import com.example.finalUI.database.DatabaseConnection;
import com.example.finalUI.util.SceneSwitcher;
import com.example.finalUI.util.PasswordUtil;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * course code: cst8412
 *
 *
 * Controller class for the login.fxml file.
 *
 *
 * @author Sebastian Sell, Luca Beumer, Bennet Ireland
 * @version 1.0
 */
public class SignupController {

    @FXML
    private Label signupTitle;

    @FXML
    private Label formInstructionLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private TextField usernameField;

    @FXML
    private Label passwordLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label confirmPasswordLabel;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button backButton;

    @FXML
    private Button signupButton;

    @FXML
    private Label errorLabel;

    /**Initializes the page*/
    public void initialize(){
        signupTitle.setFocusTraversable(true);
        signupTitle.setAccessibleText("Sign up page");
        Platform.runLater(() -> signupTitle.requestFocus());
        usernameLabel.setLabelFor(usernameField);
        passwordLabel.setLabelFor(passwordField);
        confirmPasswordLabel.setLabelFor(confirmPasswordField);

        formInstructionLabel.setAccessibleText(
                "Account creation instructions");

        formInstructionLabel.setAccessibleHelp(
                "Enter username password and confirm password to create account");

        usernameLabel.setAccessibleText("Username label");
        usernameField.setAccessibleText("Username input field");
        usernameField.setAccessibleHelp(
                "Enter a unique username for the new account");

        passwordLabel.setAccessibleText("Password label");
        passwordField.setAccessibleText("Password input field");
        passwordField.setAccessibleHelp(
                "Enter a secure password");

        confirmPasswordLabel.setAccessibleText("Confirm password label");
        confirmPasswordField.setAccessibleText(
                "Confirm password input field");

        confirmPasswordField.setAccessibleHelp(
                "Re enter the password to confirm");

        signupButton.setAccessibleText("Create account button");
        signupButton.setAccessibleHelp("Press to create a new account");

        backButton.setAccessibleText("Back button");
        backButton.setAccessibleHelp("Return to the previous page");

        errorLabel.setAccessibleText(
                "Signup error message area");

        errorLabel.setAccessibleHelp(
                "Displays signup errors");

    }
    /**
     * Handles creating a new account
     * */
    @FXML
    private void handleSignup(){

        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirm = confirmPasswordField.getText();

        if(!password.equals(confirm)){
            showError("Passwords do not match");
            return;
        }

        String hashedPassword = PasswordUtil.hashPassword(password);

        String sql = "INSERT INTO users(username,password) VALUES(?,?)";

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);

            stmt.executeUpdate();

            showMessage("Account created!");

            // go back to login page
            SceneSwitcher.switchScene(signupButton, "login.fxml");

        }
        catch(SQLException e){

            if(e.getMessage().contains("UNIQUE")){
                showError("Username already exists");
            }else{
                e.printStackTrace();
            }
        }
    }

    /**
     * Shows a message
     * @param message
     * the desired message to show
     * */
    private void showMessage(String message){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    /**Outputs an error message
     * @param message
     * the desired error message to output*/
    private void showError(String message){

        errorLabel.setText(message);

        errorLabel.setAccessibleText(
                "Signup error: " + message);

        Platform.runLater(() -> errorLabel.requestFocus());
    }

    /**
     *
     * Returns to the welcome page
     * */
    @FXML
    private void handleCancel(){

        SceneSwitcher.switchScene(backButton, "welcome-view.fxml");

    }
}