module com.example.assigment2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.assigment2 to javafx.fxml;
    exports com.example.assigment2;
}