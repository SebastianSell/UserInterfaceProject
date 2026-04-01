package com.example.assigment2;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditUserController {


    @FXML
    private TextField nameField;

    @FXML
    private TextField dobField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField addressField;

    private Person person;

    public void setPerson(Person p){

        person = p;

        nameField.setText(p.getName());
        dobField.setText(p.getDob());
        emailField.setText(p.getEmail());
        phoneField.setText(p.getPhone());
        addressField.setText(p.getAddress());
    }

    @FXML
    private void handleSave(){

        person.setName(nameField.getText());
        person.setDob(dobField.getText());
        person.setEmail(emailField.getText());
        person.setPhone(phoneField.getText());
        person.setAddress(addressField.getText());

        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCancel(){

        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }
}