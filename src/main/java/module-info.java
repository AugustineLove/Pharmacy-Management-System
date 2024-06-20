module com.example.pharmacymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires mysql.connector.j;

    opens com.example.pharmacymanagementsystem.models to javafx.base;
    opens com.example.pharmacymanagementsystem.controllers to javafx.fxml;


    opens com.example.pharmacymanagementsystem to javafx.fxml;
    exports com.example.pharmacymanagementsystem;
}