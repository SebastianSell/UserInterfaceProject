package com.example.assigment2;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignupController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button backButton;

    @FXML
    private Button signupButton;


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