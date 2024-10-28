package com.example.sistalab3.controller;

import com.example.sistalab3.utils.MediaManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    private MediaManager mediaManager;

    @FXML
    private Button singlePlayerButton;
    @FXML
    private Button multiplayerButton;
    @FXML
    private Button scoreboardButton;
    @FXML
    private Button startGameButton; // Lägg till den här raden

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mediaManager = new MediaManager();
        mediaManager.playBackgroundMusic("/com/example/sistalab3/music/elevatorMusic.mp3", true);
    }

    private void stopBackgroundMusic() {
        mediaManager.stopBackgroundMusic(); // Stoppa musiken
    }

    @FXML
    private void handleSinglePlayer() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistalab3/Difficulty.fxml"));
            Parent root = loader.load();

            mediaManager.stopBackgroundMusic();
            Stage stage = (Stage) singlePlayerButton.getScene().getWindow();

            stage.setScene(new Scene(root, 600, 550));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void startGame() {
        try {
            // Ladda spelet här, exempelvis:
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistalab3/Game.fxml"));
            Parent root = loader.load();

            mediaManager.stopBackgroundMusic(); // Stoppa eventuell musik från föregående scen
            mediaManager.playBackgroundMusic("/com/example/sistalab3/music/backgroudMusic.mp3", true);

            Stage stage = (Stage) startGameButton.getScene().getWindow(); // Använd startGameButton här
            stage.setScene(new Scene(root, 800, 600)); // Justera storleken som behövs
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleMultiplayer() {
        System.out.println("Multiplayer button clicked!");
        // Multiplayer kommer senare
    }

    @FXML
    private void handleScoreboard() {
        System.out.println("Scoreboard button clicked!");
        // Scoreboard kommer senare
    }
}
