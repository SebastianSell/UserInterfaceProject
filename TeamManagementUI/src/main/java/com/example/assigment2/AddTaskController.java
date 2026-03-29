package com.example.assigment2;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddTaskController {

    @FXML
    private TextField taskNameField;

    @FXML
    private TextField difficultyField;

    @FXML
    private TextField memberField;

    @FXML
    private TextField statusField;

    @FXML
    private TextField dueDateField;

    @FXML
    private TextField createdDateField;

    @FXML
    private TextField notesField;

    private TaskController mainController;

    public void setMainController(TaskController controller){
        this.mainController = controller;
    }

    @FXML
    private void handleSave(){

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        String createdDate =
                LocalDateTime.now().format(formatter);

        Task task = new Task(
                new SimpleStringProperty(taskNameField.getText()),
                new SimpleStringProperty(difficultyField.getText()),
                new SimpleStringProperty(memberField.getText()),
                new SimpleStringProperty(statusField.getText()),
                new SimpleStringProperty(dueDateField.getText()),
                new SimpleStringProperty(createdDate),
                new SimpleStringProperty(notesField.getText())
        );

        mainController.addTask(task);

        Stage stage = (Stage) taskNameField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCancel(){
        Stage stage = (Stage) taskNameField.getScene().getWindow();
        stage.close();
    }
}