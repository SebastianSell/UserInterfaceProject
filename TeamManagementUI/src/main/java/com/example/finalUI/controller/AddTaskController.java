package com.example.finalUI.controller;

import com.example.finalUI.model.TaskModel;
import com.example.finalUI.util.LanguageManager;
import com.example.finalUI.util.SceneSwitcher;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

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

    @FXML private Label formInstructionLabel;

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

    @FXML
    private Label errorLabel;

    private TaskController mainController;

    public void setMainController(TaskController controller){
        this.mainController = controller;
    }

    /**
     * Iniltializes the values for the dropdown options of task difficulty and task status
     */
    @FXML
    public void initialize(){
        createTitleLabel.setFocusTraversable(true);
        createTitleLabel.setAccessibleText("Create Page");
        Platform.runLater(() -> createTitleLabel.requestFocus());
        nameLabel.setAccessibleText("The name of the task");
        nameLabel.setLabelFor(taskNameField);
        taskNameField.setAccessibleText("Task name input field");
        taskNameField.setAccessibleHelp(
                "Enter a short descriptive name for the task. This field is required.");
        difficultyLabel.setAccessibleText("The difficulty of the task");
        difficultyLabel.setLabelFor(difficultyBox);
        difficultyBox.setAccessibleText("Task difficulty selection");
        difficultyBox.setAccessibleHelp(
                "Select how difficult the task is");
        memberLabel.setAccessibleText("The name of the member who is assigned to this task");
        memberLabel.setLabelFor(memberField);
        memberField.setAccessibleText("Assigned team member");
        memberField.setAccessibleHelp(
                "Enter the name of the team member responsible for the task");
        statusLabel.setAccessibleText("The current status of the task");
        statusLabel.setLabelFor(statusBox);
        statusBox.setAccessibleText("Task status selection");
        statusBox.setAccessibleHelp(
                "Select the current progress status of the task");
        dueDateLabel.setAccessibleText("The due date for the task");
        dueDateLabel.setLabelFor(dueDatePicker);
        dueDatePicker.setAccessibleText("Due date selection");
        dueDatePicker.setAccessibleHelp(
                "Select the due date for the task. This field is required");
        notesLabel.setAccessibleText("Notes for the task");
        notesLabel.setLabelFor(notesField);
        notesField.setAccessibleText("Task notes");
        notesField.setAccessibleHelp(
                "Optional notes for the task");
        saveButton.setAccessibleText("Create task button");
        saveButton.setAccessibleHelp(
                "Press to create the new task");
        cancelButton.setAccessibleText("Cancel button");
        cancelButton.setAccessibleHelp(
                "Return to task list without creating a task");

        errorLabel.setAccessibleText("Form error messages");
        errorLabel.setAccessibleHelp(
                "Displays validation errors when form input is incorrect");
        formInstructionLabel.setAccessibleText(
                "Task creation instructions");

        formInstructionLabel.setAccessibleHelp(
                "Fill in task information and press create");

        dueDatePicker.getEditor().textProperty().addListener((obs, oldDate, newDate) -> {
            if (newDate == null) {
                errorLabel.setText("Please enter a valid date format.");

                // WCAG: announce error
                errorLabel.setAccessibleText("Error: invalid date format");
            } else {
                errorLabel.setText("");
            }

        });






        ResourceBundle bundle =
                ResourceBundle.getBundle("com.example.finalUI/messages", LanguageManager.getLocale());

        // Store keys internally
        difficultyBox.setItems(FXCollections.observableArrayList(
                "easy", "medium", "hard"
        ));

        statusBox.setItems(FXCollections.observableArrayList(
                "notStarted", "inProgress", "completed"
        ));

        // Display translated values
        difficultyBox.setCellFactory(cb -> new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(bundle.getString("difficulty." + item));
                }
            }
        });

        difficultyBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(bundle.getString("difficulty." + item));
                }
            }
        });

        statusBox.setCellFactory(cb -> new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(bundle.getString("status." + item));
                }
            }
        });

        statusBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(bundle.getString("status." + item));
                }
            }
        });
    }

    /**
     * Saves the new task entered in the Add Task window
     * and adds it to the main task table.
     */
    @FXML
    private void handleSave(){
        if (dueDatePicker.getValue() == null) {
            errorLabel.setText("Please select a due date.");
            return;
        }
        errorLabel.setText("");

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String createdDate =
                LocalDateTime.now().format(formatter);
        TaskModel task = new TaskModel(
                new SimpleStringProperty(taskNameField.getText()),
                new SimpleStringProperty(difficultyBox.getValue()),
                new SimpleStringProperty(memberField.getText()),
                new SimpleStringProperty(statusBox.getValue()),
                new SimpleStringProperty(dueDatePicker.getValue().toString()),
                new SimpleStringProperty(createdDate),
                new SimpleStringProperty(notesField.getText())
        );
        mainController.addTask(task);
        SceneSwitcher.switchScene(taskNameField, "task-view.fxml");
    }
    /**
     * Closes the Add Task window to return to the View Task page without adding a task.
     * This is needed if the user changes their mind about adding a task.
     */
    @FXML
    private void handleCancel(){
        SceneSwitcher.switchScene(taskNameField, "task-view.fxml");
    }

    /**
     * Returns the Node that should receive initial focus when the page loads.
     */
    public Node getFirstFocusNode() {
        return createTitleLabel; // or the main label/button of the page
    }
}