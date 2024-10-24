package com.example.sistalab3.screen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/sistalab3/Menu.fxml"));  // Rätt sökväg
        Scene scene = new Scene(fxmlLoader.load(), 600, 550);
        stage.setTitle("Welcome to the FastTick!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
