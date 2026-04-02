package com.example.finalUI.controller;

import com.example.finalUI.database.DatabaseConnection;
import com.example.finalUI.model.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
    private Label editTitleLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField taskNameField;

    @FXML
    private Label difficultyLabel;

    @FXML
    private ComboBox<String> difficultyBox;

    @FXML
    private Label memberLabel;

    @FXML
    private TextField memberField;

    @FXML
    private Label statusLabel;

    @FXML
    private ComboBox<String> statusBox;

    @FXML
    private Label dueDateLabel;

    @FXML
    private DatePicker dueDatePicker;

    @FXML
    private TextField createdDateField;

    @FXML
    private Label notesLabel;

    @FXML
    private TextField notesField;

    @FXML
    private Button saveTaskButton;

    @FXML
    private Button cancelButton;

    private Task task;

    /**Initializes the Edit Page*/
    @FXML
    public void initialize(){
        editTitleLabel.setAccessibleRoleDescription("heading of the Edit Page. In this page you will update the exact fields for the existing task you selected");
        nameLabel.setAccessibleRoleDescription("The name of the task");
        taskNameField.setAccessibleText("Enter the name of the task");
        difficultyLabel.setAccessibleRoleDescription("The difficulty of the task");
        difficultyBox.setAccessibleText("Pick an option for the difficulty of the task");
        memberLabel.setAccessibleRoleDescription("The name of the member who is assigned to this task");
        memberField.setAccessibleText("Enter the name of a team member to be assigned for this task");
        statusLabel.setAccessibleRoleDescription("The current status of the task");
        statusBox.setAccessibleText("Pick an option for the current status of the task");
        dueDateLabel.setAccessibleRoleDescription("The due date for the task");
        dueDatePicker.setAccessibleText("pick the date for the the due date of this task");
        notesLabel.setAccessibleRoleDescription("Notes for the task");
        notesField.setAccessibleText("Type here to enter any notes for this task");
        saveTaskButton.setAccessibleText("Click to update the task");
        cancelButton.setAccessibleText("Click to cancel editing the task");



        editTitleLabel.setAccessibleText("Edit task page");

        taskNameField.setAccessibleText("Task name field");
        taskNameField.setAccessibleHelp("Edit the name of the task");

        difficultyBox.setAccessibleText("Task difficulty selector");

        memberField.setAccessibleText("Assigned member field");

        statusBox.setAccessibleText("Task status selector");

        dueDatePicker.setAccessibleText("Task due date picker");

        createdDateField.setAccessibleText("Created date field");

        notesField.setAccessibleText("Task notes field");

        saveTaskButton.setAccessibleText("Save changes button");

        cancelButton.setAccessibleText("Cancel button");


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

        String sql = """
        UPDATE tasks
        SET task_name=?, difficulty=?, member_assigned=?, status=?, due_date=?, notes=?
        WHERE task_name=?
    """;

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, task.getTaskName());
            stmt.setString(2, task.getTaskDifficulty());
            stmt.setString(3, task.getMemberAssigned());
            stmt.setString(4, task.getStatus());
            stmt.setString(5, task.getDueDate());
            stmt.setString(6, task.getTaskNotes());
            stmt.setString(7, task.getTaskName()); // used for WHERE

            stmt.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }

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