package com.example.assigment2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    private TableView<Person> tableView;

    @FXML
    private TableColumn<Person,String> nameColumn;

    @FXML
    private TableColumn<Person,String> dobColumn;

    @FXML
    private TableColumn<Person,String> emailColumn;

    @FXML
    private TableColumn<Person,String> phoneColumn;

    @FXML
    private TableColumn<Person,String> addressColumn;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    private ObservableList<Person> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        nameColumn.setCellValueFactory(c -> c.getValue().nameProperty());
        dobColumn.setCellValueFactory(c -> c.getValue().dobProperty());
        emailColumn.setCellValueFactory(c -> c.getValue().emailProperty());
        phoneColumn.setCellValueFactory(c -> c.getValue().phoneProperty());
        addressColumn.setCellValueFactory(c -> c.getValue().addressProperty());

        tableView.setItems(data);

        updateButton.disableProperty().bind(
                tableView.getSelectionModel().selectedItemProperty().isNull());

        deleteButton.disableProperty().bind(
                tableView.getSelectionModel().selectedItemProperty().isNull());
    }

    public void addPerson(Person p){
        data.add(p);
    }

    @FXML
    private void handleCreate(){

        try{

            FXMLLoader loader = new FXMLLoader(
                    HelloApplication.class.getResource("add-user.fxml")
            );

            Parent root = loader.load();

            AddUserController controller = loader.getController();
            controller.setMainController(this);

            Stage stage = new Stage();
            stage.setTitle("Add User");
            stage.setScene(new Scene(root));
            stage.show();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void handleUpdate(){

        Person selected = tableView.getSelectionModel().getSelectedItem();

        if(selected == null){
            return;
        }

        try{

            FXMLLoader loader = new FXMLLoader(
                    HelloApplication.class.getResource("edit-user.fxml")
            );

            Parent root = loader.load();

            EditUserController controller = loader.getController();
            controller.setPerson(selected);

            Stage stage = new Stage();
            stage.setTitle("Edit User");
            stage.setScene(new Scene(root));
            stage.show();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDelete(){

        Person selected = tableView.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete User");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("This record will be permanently deleted.");

        if(alert.showAndWait().get() == ButtonType.OK){
            data.remove(selected);
        }
    }
}