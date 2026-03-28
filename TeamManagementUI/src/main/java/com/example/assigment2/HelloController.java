package com.example.assigment2;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.ObservableList;

/**
 * Name: Sebastian Sell
 * Student number: 041147547
 * course code: cst8412
 * assignment name: Assignment2
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

        String name = nameField.getText();
        String dob = dobField.getText();
        String email = nameField.getText();
        String phone = dobField.getText();
        String address = nameField.getText();

        if (!name.isEmpty() && !dob.isEmpty()) {
            data.add(new Person(name, dob, email, phone, address));
        }

        nameField.clear();
        dobField.clear();
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

        if (selected != null) {
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
