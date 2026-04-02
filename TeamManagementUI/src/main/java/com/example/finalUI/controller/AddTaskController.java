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
    private Label notesLabel;

    @FXML
    private TextField notesField;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    private TaskController mainController;

    public void setMainController(TaskController controller){
        this.mainController = controller;
    }

    /**
     * Iniltializes the values for the dropdown options of task difficulty and task status
     */
    @FXML
    public void initialize(){
        createTitleLabel.setAccessibleText("heading of the Create Page. In this page you will create a task by entering info into these exact fields below");
        nameLabel.setAccessibleText("The name of the task");
        taskNameField.setAccessibleText("Enter the name of the task");
        difficultyLabel.setAccessibleText("The difficulty of the task");
        difficultyBox.setAccessibleText("Pick an option for the difficulty of the task");
        memberLabel.setAccessibleText("The name of the member who is assigned to this task");
        memberField.setAccessibleText("Enter the name of a team member to be assigned for this task");
        statusLabel.setAccessibleText("The current status of the task");
        statusBox.setAccessibleText("Pick an option for the current status of the task");
        dueDateLabel.setAccessibleText("The due date for the task");
        dueDatePicker.setAccessibleText("pick the date for the the due date of this task");
        notesLabel.setAccessibleText("Notes for the task");
        notesField.setAccessibleText("Type here to enter any notes for this task");
        saveButton.setAccessibleText("Click to create the task");
        cancelButton.setAccessibleText("Click to cancel creating the task");


        createTitleLabel.setAccessibleText("Create task page");






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