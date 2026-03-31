package com.example.assigment2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;

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
    private Label errorLabel;


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

                    openTaskPage();

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


    @FXML
    private void handleSignup(){

        String username = usernameField.getText();
        String password = passwordField.getText();

        String sql = "INSERT INTO users(username,password) VALUES(?,?)";

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            stmt.executeUpdate();

            showMessage("Account created!");

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private void openTaskPage() {

        try {

            FXMLLoader loader = new FXMLLoader(
                    HelloApplication.class.getResource("task-view.fxml")
            );

            Parent root = loader.load();

            Stage stage = (Stage) usernameField.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Task Manager");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showError(String message){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    private void showMessage(String message){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}
