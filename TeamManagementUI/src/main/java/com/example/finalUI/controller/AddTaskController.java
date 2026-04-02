package com.example.finalUI.controller;

import com.example.finalUI.model.Task;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * course code: cst8412
 *
 *
 * Controller class for the add-task.fxml file.
 *
 *
 * @author Sebastian Sell, Luca Beumer, Bennet Ireland
 * @version 1.0
 */
public class AddTaskController {
    @FXML
    private Label createTitleLabel;

    @FXML
    private Label NameLabel;

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
    private Label notesLabel;

    @FXML
    private TextField notesField;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    private TaskController mainController;

    /*public void setMainController(TaskController controller){
        this.mainController = controller;
    }*/

    /**
     * Iniltializes the values for the dropdown options of task difficulty and task status
     */
    @FXML
    public void initialize(){
        createTitleLabel.setAccessibleRoleDescription("heading of the Create Page. In this page you will create a task by entering info into these exact fields below");
        NameLabel.setAccessibleRoleDescription("The name of the task");
        difficultyBox.setAccessibleRoleDescription("Enter the name of the task");
        difficultyLabel.setAccessibleRoleDescription("The difficulty of the task");
        difficultyBox.setAccessibleRoleDescription("Pick an option for the difficulty of the task");
        memberLabel.setAccessibleRoleDescription("The name of the member who is assigned to this task");
        memberField.setAccessibleRoleDescription("Enter the name of a team member to be assigned for this task");
        statusLabel.setAccessibleRoleDescription("The current status of the task");
        statusBox.setAccessibleRoleDescription("Pick an option for the current status of the task");
        dueDateLabel.setAccessibleRoleDescription("The due date for the task");
        dueDatePicker.setAccessibleRoleDescription("pick the date for the the due date of this task");
        notesLabel.setAccessibleRoleDescription("Notes for the task");
        notesField.setAccessibleRoleDescription("Type here to enter any notes for this task");
        saveButton.setAccessibleRoleDescription("Click to create the task");
        cancelButton.setAccessibleRoleDescription("Click to cancel creating the task");
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
     * Saves the new task entered in the Add Task window
     * and adds it to the main task table.
     */
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
    /**
     * Closes the Add Task window to return to the View Task page without adding a task.
     * This is needed if the user changes their mind about adding a task.
     */
    @FXML
    private void handleCancel(){
        Stage stage = (Stage) taskNameField.getScene().getWindow();
        stage.close();
    }
}