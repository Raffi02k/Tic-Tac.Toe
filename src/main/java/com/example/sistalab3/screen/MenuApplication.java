package com.example.sistalab3.screen;

import com.example.sistalab3.utils.MediaManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MenuApplication extends Application {

    private MediaManager mediaManager;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/sistalab3/Menu.fxml"));  // Rätt sökväg
        Scene scene = new Scene(fxmlLoader.load(), 600, 550);
        stage.setTitle("Welcome to the FastTick!");
        stage.setScene(scene);
        stage.show();
        mediaManager = new MediaManager();
        mediaManager.playBackgroundMusic("/com/example/sistalab3/music/elevatorMusic.mp3", true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
