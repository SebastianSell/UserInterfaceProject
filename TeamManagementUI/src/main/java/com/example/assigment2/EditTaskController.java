package com.example.assigment2;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

/**
 * course code: cst8412
 *
 *
 * Controller class for the edit-task.fxml file.
 *
 *
 * @author Sebastian Sell, Luca Beumer, Bennet Ireland
 * @version 1.0
 */
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

    /**
     * Attaches the existing values of the selected task onto the Edit Page input fields
     */
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
    /**
     * Saves the new updated task entered in the Edit Task window
     * and adds it to the main task table.
     */
    @FXML
    private void handleSave(){

        task.setTaskName(taskNameField.getText());
        task.setTaskDifficulty(difficultyBox.getValue());
        task.setMemberAssigned(memberField.getText());
        task.setStatus(statusBox.getValue());
        task.setDueDate(dueDatePicker.getValue().toString());
        task.setTaskNotes(notesField.getText());

        Stage stage = (Stage) taskNameField.getScene().getWindow();
        stage.close();
    }
    /**
     * Closes the Edit Task window to return to the View Task page without adding a task.
     * This is needed if the user changes their mind about updating a task.
     */
    @FXML
    private void handleCancel(){
        Stage stage = (Stage) taskNameField.getScene().getWindow();
        stage.close();
    }
}