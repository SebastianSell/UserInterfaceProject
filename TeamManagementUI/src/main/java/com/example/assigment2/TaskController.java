package com.example.assigment2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

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

    @FXML
    public void initialize() {

        taskNameColumn.setCellValueFactory(c -> c.getValue().taskNameProperty());
        taskDifficultyColumn.setCellValueFactory(c -> c.getValue().taskDifficultyProperty());
        memberAssignedColumn.setCellValueFactory(c -> c.getValue().memberAssignedProperty());
        statusColumn.setCellValueFactory(c -> c.getValue().statusProperty());
        dueDateColumn.setCellValueFactory(c -> c.getValue().dueDateProperty());
        statusColumn.setCellValueFactory(c -> c.getValue().createdDateProperty());
        notesColumn.setCellValueFactory(c -> c.getValue().taskNotesProperty());

        tableView.setItems(data);

        updateButton.disableProperty().bind(
                tableView.getSelectionModel().selectedItemProperty().isNull());

        deleteButton.disableProperty().bind(
                tableView.getSelectionModel().selectedItemProperty().isNull());
    }

    public void addTask(Task t){
        data.add(t);
    }

    @FXML
    private void handleCreate(){

        try{

            FXMLLoader loader = new FXMLLoader(
                    HelloApplication.class.getResource("add-task.fxml")
            );

            Parent root = loader.load();

            AddTaskController controller = loader.getController();
            //controller.setMainController(this);

            Stage stage = new Stage();
            stage.setTitle("Add Task");
            stage.setScene(new Scene(root));
            stage.show();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

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
            //controller.setTask(selected);

            Stage stage = new Stage();
            stage.setTitle("Edit Task");
            stage.setScene(new Scene(root));
            stage.show();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

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

    @FXML
    private void handleExit(){
        System.exit(0);
    }

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