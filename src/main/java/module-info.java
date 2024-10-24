module com.example.sistalab3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;

    opens com.example.sistalab3.controller to javafx.fxml; 
    exports com.example.sistalab3.controller;
    opens com.example.sistalab3.screen to javafx.fxml;
    exports com.example.sistalab3.screen;
    opens com.example.sistalab3.model;
}
