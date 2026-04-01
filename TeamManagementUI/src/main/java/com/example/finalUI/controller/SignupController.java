package com.example.finalUI.controller;


import com.example.finalUI.database.DatabaseConnection;
import com.example.finalUI.util.SceneSwitcher;
import com.example.finalUI.util.PasswordUtil;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignupController {


    @FXML
    private Label signupTitle;

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


    public void initialize(){
        signupTitle.setAccessibleRoleDescription("This is the sign up page. Here you will need to create an account to use this application.");
        usernameLabel.setAccessibleRoleDescription("Username");
        usernameField.setAccessibleText("Enter a username for your account");
        passwordLabel.setAccessibleRoleDescription("Password");
        passwordField.setAccessibleText("Enter a password for your account");
        confirmPasswordLabel.setAccessibleRoleDescription("Confirm password");
        confirmPasswordField.setAccessibleText("Re enter your password for verification");
        signupButton.setAccessibleText("Click to create the account");
        backButton.setAccessibleText("Click to cancel creating a new account");

    }

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


    private void showMessage(String message){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }


    private void showError(String message){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }


    @FXML
    private void goBack(){

        SceneSwitcher.switchScene(backButton, "welcome-view.fxml");

    }
}