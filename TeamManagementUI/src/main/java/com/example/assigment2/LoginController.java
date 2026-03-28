package com.example.assigment2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;

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

        if(username.equals("admin") && password.equals("admin")){

            try{

                FXMLLoader loader = new FXMLLoader(
                        HelloApplication.class.getResource("hello-view.fxml")
                );

                Parent root = loader.load();

                Stage stage = (Stage) usernameField.getScene().getWindow();

                stage.setScene(new Scene(root));
                stage.setTitle("Dashboard");

            }catch(Exception e){
                e.printStackTrace();
            }

        }else{
            errorLabel.setText("Invalid login");
        }
    }
}
