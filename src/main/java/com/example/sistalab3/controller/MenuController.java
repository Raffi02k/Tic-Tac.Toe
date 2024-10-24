    package com.example.sistalab3.controller;

    import javafx.fxml.FXML;
    import javafx.scene.control.Button;
    import javafx.stage.Stage;
    import javafx.scene.Scene;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Parent;
    import java.io.IOException;

    public class MenuController {

        @FXML
        private Button singlePlayerButton, multiplayerButton, scoreboardButton;

        @FXML
        private void handleSinglePlayer() {
            try {
                // Ladda svårighetsmenyn
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistalab3/Difficulty.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) singlePlayerButton.getScene().getWindow();

                stage.setScene(new Scene(root, 600, 550));
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
