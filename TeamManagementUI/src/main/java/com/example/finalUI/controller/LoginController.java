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
        errorLabel.setText("");
        loginTitleLabel.setAccessibleText("This is the login page. You will need to verify your account to continue the app with this account.");
        usernameLabel.setAccessibleText("Username label");
        usernameField.setAccessibleText("Username input field");
        usernameField.setAccessibleHelp("Enter the username for your account");
        passwordLabel.setAccessibleText("Password label");
        passwordField.setAccessibleText("Password input field");
        passwordField.setAccessibleHelp("Enter the password for your account");
        loginButton.setAccessibleText("Login button");
        loginButton.setAccessibleHelp("Press to log into the application");
        backButton.setAccessibleText("Back button");
        backButton.setAccessibleHelp("Return to the previous page");

        errorLabel.setAccessibleText("Login error message area");
        errorLabel.setAccessibleHelp("Displays errors such as incorrect password or user not found");




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
                    //errorLabel.setText("Incorrect password");
                }

            }else{
                showError("User not found");
                //errorLabel.setText("User not found");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    /**Shows error message*/
    private void showError(String message){

        errorLabel.setText(message);

        // Update accessibility description so screen readers read the new error
        errorLabel.setAccessibleText("Login error: " + message);

        System.out.println(message);
    }
}