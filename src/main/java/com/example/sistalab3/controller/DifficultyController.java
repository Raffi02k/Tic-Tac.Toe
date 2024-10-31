package com.example.sistalab3.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class DifficultyController {

    @FXML
    private Button easyButton;

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
