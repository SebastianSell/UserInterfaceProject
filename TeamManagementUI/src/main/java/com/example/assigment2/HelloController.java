package com.example.assigment2;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

/**
 * Name: Sebastian Sell, Luca Beumer, Bennet Ireland
 * Student number: 041147547
 * course code: cst8412
 * assignment name: FinalProject
 * */

public class HelloController {

    @FXML
    private TableView<Person> tableView;

    @FXML
    private TableColumn<Person, String> nameColumn;

    @FXML
    private TableColumn<Person, String> dobColumn;

    @FXML
    private TableColumn<Person, String> emailColumn;

    @FXML
    private TableColumn<Person, String> phoneColumn;

    @FXML
    private TableColumn<Person, String> addressColumn;


    @FXML
    private TextField nameField;

    @FXML
    private TextField dobField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField addressField;

    @FXML
    private Button createButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    private ObservableList<Person> data = FXCollections.observableArrayList();


    @FXML
    /**Initialize the program*/
    public void initialize() {

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        dobColumn.setCellValueFactory(cellData -> cellData.getValue().dobProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());

        tableView.setItems(data);

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        updateButton.disableProperty().bind(
                tableView.getSelectionModel().selectedItemProperty().isNull());

        deleteButton.disableProperty().bind(
                tableView.getSelectionModel().selectedItemProperty().isNull());
    }

    @FXML
    /**Creates a new record and places it on the table view*/
    private void handleCreate() {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("add-user.fxml"));
            Parent root = loader.load();

            AddUserController controller = loader.getController();
            controller.setMainController(this);

            Stage stage = new Stage();
            stage.setTitle("Add User");
            stage.setScene(new Scene(root));
            stage.show();

        } catch(Exception e){
            e.printStackTrace();
        }

        /*String name = nameField.getText();
        String dob = dobField.getText();
        String email = nameField.getText();
        String phone = dobField.getText();
        String address = nameField.getText();

        if (!name.isEmpty() && !dob.isEmpty()) {
            data.add(new Person(name, dob, email, phone, address));
        }

        nameField.clear();
        dobField.clear();*/
    }


    @FXML
    /** Puts the values of a selected record from the table onto the two text fields*/
    private void handleRead() {

        Person selected = tableView.getSelectionModel().getSelectedItem();

        if (selected != null) {
            nameField.setText(selected.getName());
            dobField.setText(selected.getDob());
        }
    }

    @FXML
    /**Updates a record on the table*/
    private void handleUpdate() {

        Person selected = tableView.getSelectionModel().getSelectedItem();


        try{

            FXMLLoader loader = new FXMLLoader(getClass().getResource("edit-user.fxml"));
            Parent root = loader.load();

            EditUserController controller = loader.getController();
            controller.setMainController(this);
            controller.setPerson(selected);

            Stage stage = new Stage();
            stage.setTitle("Edit User");
            stage.setScene(new Scene(root));
            stage.show();

        } catch(Exception e){
            e.printStackTrace();
        }



        if (selected != null) {
            selected.setName(nameField.getText());
            selected.setDob(dobField.getText());
            tableView.refresh();
        }
    }

    @FXML
    /**Deletes a record from the table*/
    private void handleDelete() {

        Person selected = tableView.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete User");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("This record will be permanently deleted.");

        if(alert.showAndWait().get() == ButtonType.OK){
            data.remove(selected);
        }
    }

    @FXML
    /**Lets the user close the window*/
    private void handleExit() {
        System.exit(0);
    }


    @FXML
    /**Provides a pop-up window explaining what the GUI is*/
    private void handleAbout() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("About");
        alert.setHeaderText("JavaFX CRUD Application");
        alert.setContentText("Example application for CST assignment.");

        alert.showAndWait();
    }






}
