package com.example.finalUI;


import com.example.finalUI.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddUserController {

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

    private HelloController mainController;

    public void setMainController(HelloController controller){
        this.mainController = controller;
    }

    @FXML
    private void handleSave(){

        Person p = new Person(
                nameField.getText(),
                dobField.getText(),
                emailField.getText(),
                phoneField.getText(),
                addressField.getText()
        );

        mainController.addPerson(p);

        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCancel() {

        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }
}
