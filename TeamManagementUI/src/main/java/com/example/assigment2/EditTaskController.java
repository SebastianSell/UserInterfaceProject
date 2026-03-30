package com.example.assigment2;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class EditTaskController {

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

    private Task task;

    public void setTask(Task task){

        this.task = task;

        taskNameField.setText(task.getTaskName());
        difficultyBox.setValue(task.getTaskDifficulty());
        memberField.setText(task.getMemberAssigned());
        statusBox.setValue(task.getStatus());
        dueDatePicker.setValue(LocalDate.parse(task.getDueDate()));
        //createdDateField.setText(task.getCreatedDate());
        notesField.setText(task.getTaskNotes());
    }

    @FXML
    private void handleSave(){

        task.setTaskName(taskNameField.getText());
        task.setTaskDifficulty(difficultyBox.getValue());
        task.setMemberAssigned(memberField.getText());
        task.setStatus(statusBox.getValue());
        task.setDueDate(dueDatePicker.getValue().toString());
        //task.setCreatedDate(createdDateField.getText());
        task.setTaskNotes(notesField.getText());

        Stage stage = (Stage) taskNameField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCancel(){
        Stage stage = (Stage) taskNameField.getScene().getWindow();
        stage.close();
    }
}