package com.example.finalUI.controller;

import com.example.finalUI.database.DatabaseConnection;
import com.example.finalUI.app.HelloApplication;
import com.example.finalUI.util.SceneSwitcher;
import com.example.finalUI.model.Task;
import com.example.finalUI.util.Session;
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
    private Label tableViewLabel;

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
    private Button createButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private MenuBar menuNav;

    @FXML
    private MenuItem logoutButton;

    private ObservableList<Task> data = FXCollections.observableArrayList();

    /**
     * Iniltialization for the class
     */
    @FXML
    public void initialize() {

        tableView.setAccessibleText("Table listing all tasks for the current user");


        taskNameColumn.setCellValueFactory(c -> c.getValue().taskNameProperty());
        taskDifficultyColumn.setCellValueFactory(c -> c.getValue().taskDifficultyProperty());
        memberAssignedColumn.setCellValueFactory(c -> c.getValue().memberAssignedProperty());
        statusColumn.setCellValueFactory(c -> c.getValue().statusProperty());
        dueDateColumn.setCellValueFactory(c -> c.getValue().dueDateProperty());
        createdDateColumn.setCellValueFactory(c -> c.getValue().createdDateProperty());
        notesColumn.setCellValueFactory(c -> c.getValue().taskNotesProperty());


        tableViewLabel.setAccessibleText("Task list");

        tableView.setAccessibleText("Task table");
        tableView.setAccessibleHelp("Displays all tasks assigned to the team");


        tableView.setItems(data);

        updateButton.disableProperty().bind(
                tableView.getSelectionModel().selectedItemProperty().isNull());

        deleteButton.disableProperty().bind(
                tableView.getSelectionModel().selectedItemProperty().isNull());

        data.clear();
        loadTasks();

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
    /**Adds a task to the database*/
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
    /**updates a task in the tableview*/
    public void updateTask(Task t){

        String sql = """
        UPDATE tasks
        SET task_name=?, difficulty=?, member_assigned=?, status=?, due_date=?, notes=?
        WHERE created_date=? AND user_id=?
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t.getTaskName());
            stmt.setString(2, t.getTaskDifficulty());
            stmt.setString(3, t.getMemberAssigned());
            stmt.setString(4, t.getStatus());
            stmt.setString(5, t.getDueDate());
            stmt.setString(6, t.getTaskNotes());
            stmt.setString(7, t.getCreatedDate());
            stmt.setInt(8, Session.currentUserId);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**Deletes a task from the tableview*/
    public void deleteTask(Task t){

        String sql = """
        DELETE FROM tasks
        WHERE created_date=? AND user_id=?
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t.getCreatedDate());
            stmt.setInt(2, Session.currentUserId);

            stmt.executeUpdate();

            data.remove(t);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**Loads tasks into the tableview upon running the program and logging in*/
    private void loadTasks(){

        data.clear();

        String sql = "SELECT * FROM tasks WHERE user_id=?";

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, Session.currentUserId);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){

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

        }catch(Exception e){
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

        Task selectedTask = tableView.getSelectionModel().getSelectedItem();

        if(selectedTask == null){
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Task");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("This record will be permanently deleted.");

        if(alert.showAndWait().get() == ButtonType.OK){

            String sql = "DELETE FROM tasks WHERE task_name = ?";

            try(Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)){

                stmt.setString(1, selectedTask.getTaskName());

                stmt.executeUpdate();

                data.remove(selectedTask);

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    /**
     * Handles logging out of an account
     * */
    @FXML
    private void handleLogout(){

        Session.currentUserId = 0;

        SceneSwitcher.switchScene(menuNav, "welcome-view.fxml");
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