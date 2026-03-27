module com.example.assigment2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.assigment2 to javafx.fxml;
    exports com.example.assigment2;
}