package com.example.finalUI.util;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;

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
    public static FXMLLoader switchScene(Node node, String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    SceneSwitcher.class.getResource("/com/example/finalUI/" + fxml)
            );
            Parent root = loader.load();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setMaximized(true);

            Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
            stage.setWidth(bounds.getWidth());
            stage.setHeight(bounds.getHeight());

            Platform.runLater(root::requestFocus);
            System.out.println(stage.isMaximized());
            return loader;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
