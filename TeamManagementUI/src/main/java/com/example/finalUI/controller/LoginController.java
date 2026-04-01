package com.example.finalUI.controller;



import com.example.finalUI.database.DatabaseConnection;
import com.example.finalUI.util.SceneSwitcher;
import com.example.finalUI.util.PasswordUtil;
import com.example.finalUI.util.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private Label loginTitleLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private TextField usernameField;

    @FXML
    private Label passwordLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button backButton;

    @FXML
    private Label errorLabel;

    /**Initializes the Login page */
    @FXML
    private void initialize(){
        loginTitleLabel.setAccessibleRoleDescription("This is the login page. You will need to verify your account to continue the app with this account.");
        usernameLabel.setAccessibleRoleDescription("Username");
        usernameField.setAccessibleText("Enter a username for your account");
        passwordLabel.setAccessibleRoleDescription("Password");
        passwordField.setAccessibleText("Enter a password for your account");
        loginButton.setAccessibleText("Click to login");
        backButton.setAccessibleText("Click to cancel logging in");
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

                    // SCENE SWITCH HERE
                    SceneSwitcher.switchScene(loginButton, "task-view.fxml");

                }else{
                    showError("Incorrect password");
                    errorLabel.setText("Incorrect password");
                }

            }else{
                showError("User not found");
                errorLabel.setText("User not found");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private void showError(String message){
        System.out.println(message);
    }
}