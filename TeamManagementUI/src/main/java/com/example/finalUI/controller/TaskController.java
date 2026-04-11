package com.example.finalUI.controller;

import com.example.finalUI.database.DatabaseConnection;
import com.example.finalUI.util.SceneSwitcher;
import com.example.finalUI.model.TaskModel;
import com.example.finalUI.util.Session;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import java.util.Locale;
import java.util.ResourceBundle;

import com.example.finalUI.util.LanguageManager;


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
    private Menu fileMenu;

    @FXML
    private Menu editMenu;

    @FXML
    private Menu helpMenu;

    @FXML
    private Menu pagesMenu;

    @FXML
    private Menu extraMenu;

    @FXML
    private MenuItem closeMenuItem;

    @FXML
    private MenuItem deleteMenuItem;

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private MenuItem viewUsersMenuItem;

    @FXML
    private MenuItem logoutMenuItem;

    @FXML
    private Label tableViewLabel;

    @FXML
    private TableView<TaskModel> tableView;

    @FXML
    private TableColumn<TaskModel,String> taskNameColumn;

    @FXML
    private TableColumn<TaskModel,String> taskDifficultyColumn;

    @FXML
    private TableColumn<TaskModel,String> memberAssignedColumn;

    @FXML
    private TableColumn<TaskModel,String> statusColumn;

    @FXML
    private TableColumn<TaskModel,String> dueDateColumn;

    @FXML
    private TableColumn<TaskModel,String> createdDateColumn;

    @FXML
    private TableColumn<TaskModel,String> notesColumn;


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

    @FXML
    private Label tableTitleLabel;


    private ObservableList<TaskModel> data = FXCollections.observableArrayList();

    private ResourceBundle bundle;

    /**
     * Iniltialization for the class
     */
    @FXML
    public void initialize() {
        tableTitleLabel.setFocusTraversable(true);
        tableTitleLabel.setAccessibleText("Task list page");
        Platform.runLater(() -> tableTitleLabel.requestFocus());
        tableView.setAccessibleText("Table listing all tasks for the current user");
        tableView.setAccessibleHelp("Displays all tasks assigned to the team");




        taskNameColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getTaskName())
        );

        taskDifficultyColumn.setCellValueFactory(c -> c.getValue().taskDifficultyProperty());
        memberAssignedColumn.setCellValueFactory(c -> c.getValue().memberAssignedProperty());
        statusColumn.setCellValueFactory(c -> c.getValue().statusProperty());
        dueDateColumn.setCellValueFactory(c -> c.getValue().dueDateProperty());
        createdDateColumn.setCellValueFactory(c -> c.getValue().createdDateProperty());
        notesColumn.setCellValueFactory(c -> c.getValue().taskNotesProperty());
        createButton.setAccessibleText("Create a new task");
        updateButton.setAccessibleText("Update selected task");
        deleteButton.setAccessibleText("Delete selected task");

        ResourceBundle bundle = ResourceBundle.getBundle("com.example.finalUI.messages", LanguageManager.getLocale());

        taskNameColumn.setText(bundle.getString("taskName"));
        taskDifficultyColumn.setText(bundle.getString("taskDifficulty"));
        memberAssignedColumn.setText(bundle.getString("assignedTo"));
        statusColumn.setText(bundle.getString("taskStatus"));
        dueDateColumn.setText(bundle.getString("dueDate"));
        createdDateColumn.setText(bundle.getString("createdDate"));
        notesColumn.setText(bundle.getString("notes"));

        tableView.setAccessibleText(
                "Task table listing all tasks");

        tableView.setAccessibleHelp(
                "Each row represents a task. Use arrow keys to move between rows");


        menuNav.setAccessibleText("Application menu");
        menuNav.setAccessibleHelp(
                "Use this menu to access application options");


        tableView.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldVal, newVal) -> {

                    if(newVal != null){
                        tableView.setAccessibleText(
                                "Selected task " + newVal.getTaskName());
                    }

                });

        updateButton.disableProperty().bind(
                tableView.getSelectionModel().selectedItemProperty().isNull());

        deleteButton.disableProperty().bind(
                tableView.getSelectionModel().selectedItemProperty().isNull());

        data.clear();
        Platform.runLater(() -> tableView.requestFocus());
        tableView.setItems(data);
        loadTasks();

        String sql = "SELECT * FROM tasks WHERE user_id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            stmt.setInt(1, Session.currentUserId);

            while (rs.next()) {

                TaskModel task = new TaskModel(
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Something went wrong");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    /**Adds a task to the database*/
    public void addTask(TaskModel t){
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Something went wrong");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    /**updates a task in the tableview*/
    public void updateTask(TaskModel t){

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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Something went wrong");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    /**Deletes a task from the tableview*/
    public void deleteTask(TaskModel t){

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
    @FXML
    private void loadTasks(){

        Task<List<TaskModel>> loadTask = new Task<>() {
            @Override
            protected java.util.List<TaskModel> call() throws Exception {
                java.util.List<TaskModel> loadedTasks = new java.util.ArrayList<>();
                String sql = "SELECT * FROM tasks WHERE user_id=?";
                try(Connection conn = DatabaseConnection.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(sql)){
                    stmt.setInt(1, Session.currentUserId);
                    ResultSet rs = stmt.executeQuery();
                    while(rs.next()){
                        TaskModel task = new TaskModel(
                                new SimpleStringProperty(rs.getString("task_name")),
                                new SimpleStringProperty(rs.getString("difficulty")),
                                new SimpleStringProperty(rs.getString("member_assigned")),
                                new SimpleStringProperty(rs.getString("status")),
                                new SimpleStringProperty(rs.getString("due_date")),
                                new SimpleStringProperty(rs.getString("created_date")),
                                new SimpleStringProperty(rs.getString("notes"))
                        );

                        loadedTasks.add(task);
                    }

                }

                return loadedTasks;
            }
        };
        loadTask.setOnSucceeded(event -> {
            data.clear();
            data.addAll(loadTask.getValue());
        });

        loadTask.setOnFailed(event -> {
            loadTask.getException().printStackTrace();
        });

        Thread thread = new Thread(loadTask);
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * Handles the creation of a new task.
     * Opens the Add Task window where the user can
     * enter task information.
     */
    @FXML
    private void handleCreate() {

        FXMLLoader loader = SceneSwitcher.switchScene(tableView, "add-task.fxml");

        if(loader != null) {
            AddTaskController controller = loader.getController();
            controller.setMainController(this);
        }
    }
    /**
     * Handles the update of an existing task.
     * Opens the Edit Task window where the user can
     * enter task information to update a task.
     */
    @FXML
    private void handleUpdate(){

        TaskModel selected = tableView.getSelectionModel().getSelectedItem();

        if(selected == null){

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Task Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a task to update.");
            alert.showAndWait();

            return;
        }

        FXMLLoader loader = SceneSwitcher.switchScene(tableView, "edit-task.fxml");

        if(loader != null){
            EditTaskController controller = loader.getController();
            controller.setTask(selected);
        }
    }
    /**
     * Handles the deletion of a task
     */
    @FXML
    private void handleDelete(){

        TaskModel selectedTask = tableView.getSelectionModel().getSelectedItem();

        if(selectedTask == null){
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Task");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("This record will be permanently deleted.");

        alert.getDialogPane().getStylesheets().add(
                getClass().getResource("/com/example/finalUI/style.css").toExternalForm()
        );

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
    /**Sets the UI text to English*/
    @FXML
    private void setEnglish() {
        LanguageManager.setLocale(Locale.ENGLISH);
        SceneSwitcher.switchScene(menuNav, "task-view.fxml");
    }
    /**Sets the UI text to French*/
    @FXML
    private void setFrench() {
        LanguageManager.setLocale(Locale.FRENCH);
        SceneSwitcher.switchScene(menuNav, "task-view.fxml");
    }

    /**
     * Switches all UI text to the given locale.
     *
     * @param locale the locale to switch the UI to
     */
    public void switchLanguage(Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.messages", locale);

        taskNameColumn.setText(bundle.getString("taskName"));
        taskDifficultyColumn.setText(bundle.getString("taskDifficulty"));
        memberAssignedColumn.setText(bundle.getString("assignedTo"));
        statusColumn.setText(bundle.getString("taskStatus"));
        dueDateColumn.setText(bundle.getString("dueDate"));
        createdDateColumn.setText(bundle.getString("createdDate"));
        notesColumn.setText(bundle.getString("notes"));

        tableTitleLabel.setText(bundle.getString("task"));
        createButton.setText(bundle.getString("create"));
        updateButton.setText(bundle.getString("update"));
        deleteButton.setText(bundle.getString("delete"));
        fileMenu.setText(bundle.getString("file"));
        helpMenu.setText(bundle.getString("help"));
        extraMenu.setText(bundle.getString("extra"));
        logoutMenuItem.setText(bundle.getString("logout"));
        closeMenuItem.setText(bundle.getString("closeApplication"));
        aboutMenuItem.setText(bundle.getString("about"));
    }

    /**
     * Returns the Node that should receive initial focus when the page loads.
     */
    public Node getFirstFocusNode() {
        return tableTitleLabel; // or the main label/button of the page
    }
}