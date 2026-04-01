package com.example.assigment2;


import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitcher {

    public static void switchScene(Node node, String fxml) {

        try {

            Parent root = FXMLLoader.load(
                    SceneSwitcher.class.getResource("/com/example/assigment2/" + fxml)
            );

            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
