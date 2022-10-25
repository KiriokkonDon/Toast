module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.base;
    requires javafx.swing;
    requires kotlin.stdlib;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}