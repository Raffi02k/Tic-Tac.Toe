package com.example.sistalab3.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;

public class DifficultyController {

    @FXML
    private Button easyButton, mediumButton, hardButton;

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

    private void loadGameScreen(String difficulty) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Difficulty.fxml"));
            Parent root = loader.load();

            // Hämta kontroller för spelet och skicka svårighetsinställningen
            TicTacToeController controller = loader.getController();
            controller.setDifficulty(difficulty);

            Stage stage = (Stage) easyButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
