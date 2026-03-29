package com.example.assigment2;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

public class AddTaskController {

    @FXML
    private TextField taskNameField;

    @FXML
    private ComboBox<String> difficultyBox;

    @FXML
    private TextField memberField;

    @FXML
    private ComboBox<String> statusBox;

    @FXML
    private DatePicker dueDatePicker;

    @FXML
    private TextField createdDateField;

    @FXML
    private TextField notesField;

    private TaskController mainController;

    public void setMainController(TaskController controller){
        this.mainController = controller;
    }

    @FXML
    public void initialize(){

        difficultyBox.getItems().addAll(
                "Easy",
                "Medium",
                "Hard"
        );

        statusBox.getItems().addAll(
                "Not Started",
                "In Progress",
                "Completed"
        );
    }

    @FXML
    private void handleSave(){

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        String createdDate =
                LocalDateTime.now().format(formatter);

        Task task = new Task(
                new SimpleStringProperty(taskNameField.getText()),
                new SimpleStringProperty(difficultyBox.getValue()),
                new SimpleStringProperty(memberField.getText()),
                new SimpleStringProperty(statusBox.getValue()),
                new SimpleStringProperty(dueDatePicker.getValue().toString()),
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