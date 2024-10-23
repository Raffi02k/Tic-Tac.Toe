module com.example.sistalab3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.sistalab3 to javafx.fxml;
    exports com.example.sistalab3;
    exports com.example.sistalab3.controller;
    opens com.example.sistalab3.controller to javafx.fxml;
}
