package com.example.assigment2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignupController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;


    @FXML
    private void goToLogin() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @FXML
    private void handleSignup(){

        String username = usernameField.getText();
        String password = passwordField.getText();

        String hashedPassword = PasswordUtil.hashPassword(password);

        String sql = "INSERT INTO users(username,password) VALUES(?,?)";

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);

            stmt.executeUpdate();

            showMessage("Account created!");

        }
        catch(SQLException e){

            if(e.getMessage().contains("UNIQUE")){
                showError("Username already exists");
            }else{
                e.printStackTrace();
            }
        }
    }

    private void showMessage(String s) {
    }

    private void showError(String message){

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    @FXML
    private void goBack() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("welcome-view.fxml"));
        Scene scene = new Scene(loader.load());

        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.setScene(scene);
    }
}



