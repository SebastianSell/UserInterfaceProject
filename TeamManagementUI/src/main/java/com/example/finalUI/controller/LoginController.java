package com.example.finalUI.controller;

import com.example.finalUI.database.DatabaseConnection;
import com.example.finalUI.util.SceneSwitcher;
import com.example.finalUI.util.PasswordUtil;
import com.example.finalUI.util.Session;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class LoginController {

    @FXML
    private Label loginTitleLabel;

    @FXML
    private Label formInstructionLabel;

    @FXML
    private Label usernameLabel;


    @FXML
    private TextField usernameField;

    @FXML
    private Label usernameHelpLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label passwordHelpLabel;

    @FXML
    private Button loginButton;

    @FXML
    private Button backButton;

    @FXML
    private Label errorLabel;

    /**Initializes the Login page */
    @FXML
    private void initialize(){
        loginTitleLabel.setFocusTraversable(true);
        loginTitleLabel.setAccessibleText("Login page");
        Platform.runLater(() -> loginTitleLabel.requestFocus());
        usernameLabel.setLabelFor(usernameField);
        passwordLabel.setLabelFor(passwordField);
        formInstructionLabel.setAccessibleText(
                "Instructions for logging in");
        formInstructionLabel.setAccessibleHelp(
                "Enter your username and password to log into the system");



        usernameField.setAccessibleText("Username input field");
        usernameField.setAccessibleHelp(
                "Enter your account username. This field is required.");

        usernameHelpLabel.setAccessibleText(
                "Username help text");
        usernameHelpLabel.setAccessibleHelp(
                "Explains what to enter in the username field");
        passwordField.setAccessibleText("Password input field");
        passwordField.setAccessibleHelp(
                "Enter your account password. This field is required.");

        passwordHelpLabel.setAccessibleText(
                "Password help text");
        passwordHelpLabel.setAccessibleHelp(
                "Explains what to enter in the password field");
        loginButton.setAccessibleText("Login button");
        loginButton.setAccessibleHelp(
                "Press to log into the application");

        backButton.setAccessibleText("Back button");
        backButton.setAccessibleHelp(
                "Return to the welcome page");

        errorLabel.setAccessibleText("Login error message area");
        errorLabel.setAccessibleHelp(
                "Displays login errors such as incorrect username or password");

        errorLabel.setText("");
        errorLabel.requestFocus();




    }
    /**Handles the login feature for both the front end and back end*/
    @FXML
    private void handleLogin(){

        String username = usernameField.getText();
        String password = passwordField.getText();

        String hashedInput = PasswordUtil.hashPassword(password);

        String sql = "SELECT id,password FROM users WHERE username=?";

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){

                String passwordFromDatabase = rs.getString("password");

                if(hashedInput.equals(passwordFromDatabase)){

                    Session.currentUserId = rs.getInt("id");
                    SceneSwitcher.switchScene(loginButton, "task-view.fxml");

                }else{
                    showError("Incorrect password");
                }

            }else{
                showError("User not found");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    /**Outputs an error message
     * @param message
     * the desired error message to output*/
    private void showError(String message){

        errorLabel.setText(message);

        // Update accessibility description so screen readers read the new error
        errorLabel.setAccessibleText("Login error: " + message);

        Platform.runLater(() -> errorLabel.requestFocus());
        System.out.println(message);
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