package com.example.finalUI.controller;

import com.example.finalUI.database.DatabaseConnection;
import com.example.finalUI.model.TaskModel;
import com.example.finalUI.util.SceneSwitcher;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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

    @FXML
    private Label errorLabel;

    private TaskModel task;

    /**Initializes the Edit Page*/
    @FXML
    public void initialize(){
        editTitleLabel.setFocusTraversable(true);
        editTitleLabel.setAccessibleText("Edit Task Page");
        Platform.runLater(() -> editTitleLabel.requestFocus());
        nameLabel.setAccessibleText("The name of the task");
        nameLabel.setLabelFor(taskNameField);
        taskNameField.setAccessibleText("Task name field");
        taskNameField.setAccessibleHelp("Edit the name of the task");
        difficultyLabel.setAccessibleText("Task difficulty selector");
        difficultyLabel.setLabelFor(difficultyBox);
        difficultyBox.setAccessibleText("Pick an option for the difficulty of the task");
        memberLabel.setAccessibleText("The name of the member who is assigned to this task");
        memberLabel.setLabelFor(memberField);
        memberField.setAccessibleText("Enter the name of a team member to be assigned for this task");
        statusLabel.setAccessibleText("The current status of the task");
        statusLabel.setLabelFor(statusBox);
        statusBox.setAccessibleText("Pick an option for the current status of the task");
        dueDateLabel.setAccessibleText("The due date for the task");
        dueDateLabel.setLabelFor(dueDatePicker);
        dueDatePicker.setAccessibleText("pick the date for the the due date of this task");
        notesLabel.setAccessibleText("Notes for the task");
        notesLabel.setLabelFor(notesField);
        notesField.setAccessibleText("Type here to enter any notes for this task");
        saveTaskButton.setAccessibleText("Click to update the task");
        cancelButton.setAccessibleText("Click to cancel editing the task");


        // options for the status and difficulty dropdowns
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
        //Makes dueDatePicker uneditable
        dueDatePicker.setEditable(false);
    }

    /**
     * Attaches the existing values of the selected task onto the Edit Page input fields
     */
    public void setTask(TaskModel task){

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
        if (dueDatePicker.getValue() == null) {
            errorLabel.setText("Please select a due date.");
            return;
        }

        errorLabel.setText("");
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

        SceneSwitcher.switchScene(taskNameField, "task-view.fxml");
    }
    /**
     * Closes the Edit Task window to return to the View Task page without adding a task.
     * This is needed if the user changes their mind about updating a task.
     */
    @FXML
    private void handleCancel(){
        SceneSwitcher.switchScene(taskNameField, "task-view.fxml");
    }
}