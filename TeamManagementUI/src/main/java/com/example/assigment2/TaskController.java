package com.example.assigment2;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;





/**
 * course code: cst8412
 *
 *
 * Controller class for the task-view.fxml file.
 *
 *
 * @author Sebastian Sell, Luca Beumer, Bennet Ireland
 * @version 1.0
 */
public class TaskController {

    @FXML
    private TableView<Task> tableView;

    @FXML
    private TableColumn<Task,String> taskNameColumn;

    @FXML
    private TableColumn<Task,String> taskDifficultyColumn;

    @FXML
    private TableColumn<Task,String> memberAssignedColumn;

    @FXML
    private TableColumn<Task,String> statusColumn;

    @FXML
    private TableColumn<Task,String> dueDateColumn;

    @FXML
    private TableColumn<Task,String> createdDateColumn;

    @FXML
    private TableColumn<Task,String> notesColumn;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    private ObservableList<Task> data = FXCollections.observableArrayList();

    /**
     * Iniltialization for the class
     */
    @FXML
    public void initialize() {

        taskNameColumn.setCellValueFactory(c -> c.getValue().taskNameProperty());
        taskDifficultyColumn.setCellValueFactory(c -> c.getValue().taskDifficultyProperty());
        memberAssignedColumn.setCellValueFactory(c -> c.getValue().memberAssignedProperty());
        statusColumn.setCellValueFactory(c -> c.getValue().statusProperty());
        dueDateColumn.setCellValueFactory(c -> c.getValue().dueDateProperty());
        createdDateColumn.setCellValueFactory(c -> c.getValue().createdDateProperty());
        notesColumn.setCellValueFactory(c -> c.getValue().taskNotesProperty());

        tableView.setItems(data);

        updateButton.disableProperty().bind(
                tableView.getSelectionModel().selectedItemProperty().isNull());

        deleteButton.disableProperty().bind(
                tableView.getSelectionModel().selectedItemProperty().isNull());

        data.clear();

        String sql = "SELECT * FROM tasks WHERE user_id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            stmt.setInt(1, Session.currentUserId);

            while (rs.next()) {

                Task task = new Task(
                        new SimpleStringProperty(rs.getString("task_name")),
                        new SimpleStringProperty(rs.getString("difficulty")),
                        new SimpleStringProperty(rs.getString("member_assigned")),
                        new SimpleStringProperty(rs.getString("status")),
                        new SimpleStringProperty(rs.getString("due_date")),
                        new SimpleStringProperty(rs.getString("created_date")),
                        new SimpleStringProperty(rs.getString("notes"))
                );

                data.add(task);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTask(Task t){

        data.add(t);

        String sql = """
        INSERT INTO tasks
        (task_name,difficulty,member_assigned,status,due_date,created_date,notes,user_id)
        VALUES(?,?,?,?,?,?,?,?)
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t.getTaskName());
            stmt.setString(2, t.getTaskDifficulty());
            stmt.setString(3, t.getMemberAssigned());
            stmt.setString(4, t.getStatus());
            stmt.setString(5, t.getDueDate());
            stmt.setString(6, t.getCreatedDate());
            stmt.setString(7, t.getTaskNotes());
            stmt.setInt(8, Session.currentUserId);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the creation of a new task.
     * Opens the Add Task window where the user can
     * enter task information.
     */
    @FXML
    private void handleCreate(){

        try{

            FXMLLoader loader = new FXMLLoader(
                    HelloApplication.class.getResource("add-task.fxml")
            );

            Parent root = loader.load();

            AddTaskController controller = loader.getController();
            controller.setMainController(this);

            Stage stage = new Stage();
            stage.setTitle("Add Task");
            stage.setScene(new Scene(root));
            stage.show();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    /**
     * Handles the update of a existing task.
     * Opens the Edit Task window where the user can
     * enter task information to update a task.
     */
    @FXML
    private void handleUpdate(){

        Task selected = tableView.getSelectionModel().getSelectedItem();

        if(selected == null){
            return;
        }

        try{

            FXMLLoader loader = new FXMLLoader(
                    HelloApplication.class.getResource("edit-task.fxml")
            );

            Parent root = loader.load();

            EditTaskController controller = loader.getController();
            controller.setTask(selected);

            Stage stage = new Stage();
            stage.setTitle("Edit Task");
            stage.setScene(new Scene(root));
            stage.show();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    /**
     * Handles the deletion of a task
     */
    @FXML
    private void handleDelete(){

        Task selected = tableView.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Task");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("This record will be permanently deleted.");

        if(alert.showAndWait().get() == ButtonType.OK){
            data.remove(selected);
        }
    }

    @FXML
    private void handleLogout(){

        try{

            FXMLLoader loader = new FXMLLoader(
                    HelloApplication.class.getResource("login.fxml")
            );

            Parent root = loader.load();

            Stage stage = (Stage) tableView.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Login");

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    /**
     * Handles the closing of the app
     */
    @FXML
    private void handleExit(){
        System.exit(0);
    }
    /**
     * Handles the about pop-up
     */
    @FXML
    private void handleAbout(){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("About");
        alert.setHeaderText("Task Manager Application");
        alert.setContentText(
                "Assignment 2\n" +
                        "Simple User and Task Manager\n" +
                        "Created using JavaFX."
        );

        alert.showAndWait();
    }
    /**
     * Handles viewing the user viewtable page
     */
    @FXML
    private void handleViewUsers(){

        try{

            FXMLLoader loader = new FXMLLoader(
                    HelloApplication.class.getResource("Assigment2-View.fxml")
            );

            Parent root = loader.load();

            Stage stage = (Stage) tableView.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("User Manager");

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}