package com.example.assigment2;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditTaskController {

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

    private Task task;

    public void setTask(Task task){

        this.task = task;

        taskNameField.setText(task.getTaskName());
        difficultyField.setText(task.getTaskDifficulty());
        memberField.setText(task.getMemberAssigned());
        statusField.setText(task.getStatus());
        dueDateField.setText(task.getDueDate());
        createdDateField.setText(task.getCreatedDate());
        notesField.setText(task.getTaskNotes());
    }

    @FXML
    private void handleSave(){

        task.setTaskName(taskNameField.getText());
        task.setTaskDifficulty(difficultyField.getText());
        task.setMemberAssigned(memberField.getText());
        task.setStatus(statusField.getText());
        task.setDueDate(dueDateField.getText());
        task.setCreatedDate(createdDateField.getText());
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