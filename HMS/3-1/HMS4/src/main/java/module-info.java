module com.example.hms4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.hms4 to javafx.fxml;
    exports com.example.hms4;
}