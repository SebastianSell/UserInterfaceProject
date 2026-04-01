module com.example.assigment2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;


    opens com.example.assigment2 to javafx.fxml;
    exports com.example.assigment2;
}