package com.example.lab8;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        Locale locale = new Locale("es", "ES"); // test Spanish locale
        ResourceBundle bundle = ResourceBundle.getBundle("com.example.lab8.messages", locale);


        FXMLLoader fxmlLoader = new FXMLLoader(
                HelloApplication.class.getResource("hello-view.fxml"),
                bundle
        );

        DateTimeFormatter formatter =
                DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                        .withLocale(Locale.forLanguageTag("es"));

        System.out.println(LocalDate.now().format(formatter));

        Scene scene = new Scene(fxmlLoader.load(), 400, 300);

        stage.setTitle(bundle.getString("title"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}