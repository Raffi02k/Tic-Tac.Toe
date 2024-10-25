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

//    @FXML
//    private Button coopButton;

    @FXML
    private void handleCoop() {
        loadGameScreen("coop"); // Ladda co-op-läget
    }

    @FXML
    private void handleEasy() {
        loadGameScreen("easy"); // Ladda easy-läget
    }

    @FXML
    private void handleMedium() {
        loadGameScreen("medium"); // Ladda medium-läget
    }

    @FXML
    private void handleHard() {
        loadGameScreen("hard"); // Ladda hard-läget
    }

    private void loadGameScreen(String mode) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistalab3/hello-view.fxml"));
            Parent root = loader.load();

            // Hämta TicTacToeController och ställ in läget
            TicTacToeController controller = loader.getController();

            if (mode.equals("coop")) {
                controller.setCoopMode(); // Ställ in co-op-läget (AI inaktiverad)
            } else {
                controller.setDifficulty(mode); // Sätt svårighetsgrad och aktivera AI
            }

            Stage stage = (Stage) easyButton.getScene().getWindow();
            stage.setScene(new Scene(root, 600, 550));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
