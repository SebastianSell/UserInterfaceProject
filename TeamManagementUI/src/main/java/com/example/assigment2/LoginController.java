package com.example.assigment2;



import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

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
                }

            }else{
                showError("User not found");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private void showError(String message){
        System.out.println(message);
    }
}