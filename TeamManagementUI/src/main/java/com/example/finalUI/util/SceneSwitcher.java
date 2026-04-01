package com.example.finalUI.util;


import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneSwitcher {

    public static void switchScene(Node node, String fxml) {

        try {

            Parent root = FXMLLoader.load(
                    SceneSwitcher.class.getResource("/com/example/finalUI/" + fxml)
            );

            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
