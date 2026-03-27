package com.example.lab8;

import javafx.fxml.FXML;
import javafx.scene.AccessibleRole;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;

public class HelloController {

    @FXML
    private Label welcomeText;

    @FXML
    private Button helloButton;

    @FXML
    private TextField nameField;

    @FXML
    private Label nameLabel;

    @FXML
    private Circle circleShape;

    @FXML
    public void initialize() {

        // Accessible role description
        helloButton.setAccessibleRoleDescription("Action Button");

        // Accessible help
        helloButton.setAccessibleHelp("Press this button to show a welcome message");

        // Accessible text for textfield
        nameField.setAccessibleText("Enter your name here");

        // Associate label with text field
        nameLabel.setLabelFor(nameField);

        // Make shape focusable
        circleShape.setFocusTraversable(true);

        // Give shape an accessible role
        circleShape.setAccessibleRole(AccessibleRole.BUTTON);
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}