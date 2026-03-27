package com.example.assigment2;

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
    private TableColumn<Person, String> ageColumn;

    @FXML
    private TextField nameField;

    @FXML
    private TextField ageField;

    @FXML
    private Button createButton;

    @FXML
    private Button readButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    private ObservableList<Person> data = FXCollections.observableArrayList();


    @FXML
    /**Initialize the program*/
    public void initialize() {

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        ageColumn.setCellValueFactory(cellData -> cellData.getValue().ageProperty());

        tableView.setItems(data);

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        updateButton.disableProperty().bind(
                tableView.getSelectionModel().selectedItemProperty().isNull());

        deleteButton.disableProperty().bind(
                tableView.getSelectionModel().selectedItemProperty().isNull());

        readButton.disableProperty().bind(
                tableView.getSelectionModel().selectedItemProperty().isNull());
    }

    @FXML
    /**Creates a new record and places it on the table view*/
    private void handleCreate() {

        String name = nameField.getText();
        String age = ageField.getText();

        if (!name.isEmpty() && !age.isEmpty()) {
            data.add(new Person(name, age));
        }

        nameField.clear();
        ageField.clear();
    }

    @FXML
    /** Puts the values of a selected record from the table onto the two text fields*/
    private void handleRead() {

        Person selected = tableView.getSelectionModel().getSelectedItem();

        if (selected != null) {
            nameField.setText(selected.getName());
            ageField.setText(selected.getAge());
        }
    }

    @FXML
    /**Updates a record on the table*/
    private void handleUpdate() {

        Person selected = tableView.getSelectionModel().getSelectedItem();

        if (selected != null) {
            selected.setName(nameField.getText());
            selected.setAge(ageField.getText());
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
