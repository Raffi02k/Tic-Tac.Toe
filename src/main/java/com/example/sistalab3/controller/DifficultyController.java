package com.example.sistalab3.controller;

import com.example.sistalab3.utils.MediaManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class DifficultyController {

    @FXML
    private Button easyButton, mediumButton, hardButton, coopButton;

    @FXML
    private void handleCoop() {
        loadGameScreen("coop");
    }

    @FXML
    private void handleEasy() {
        loadGameScreen("easy");
    }

    @FXML
    private void handleMedium() {
        loadGameScreen("medium");
    }

    @FXML
    private void handleHard() {
        loadGameScreen("hard");
    }

    private void loadGameScreen(String mode) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistalab3/hello-view.fxml"));
            Parent root = loader.load();
            TicTacToeController controller = loader.getController();
            if (mode.equals("coop")) {
                controller.setCoopMode();
            } else {
                controller.setDifficulty(mode);
            }

            Stage stage = (Stage) easyButton.getScene().getWindow();
            stage.setScene(new Scene(root, 600, 550));

            } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
