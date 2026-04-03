package com.example.finalUI.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * course code: cst8412
 *
 *
 * Class helps the gui switch between pages.
 *
 *
 * @author Sebastian Sell, Luca Beumer, Bennet Ireland
 * @version 1.0
 */
public class SceneSwitcher {
    /**Switches to a page
     * @param node
     * The Ui elements variable
     * @param fxml
     * name of the fxml file and page destination*/
    public static void switchScene(Node node, String fxml) {

        try {

            Parent root = FXMLLoader.load(
                    SceneSwitcher.class.getResource("/com/example/finalUI/" + fxml)
            );

            Stage stage = (Stage) node.getScene().getWindow();
            stage.setMaximized(true);
            stage.setScene(new Scene(root));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
