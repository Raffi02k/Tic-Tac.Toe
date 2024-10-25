package com.example.sistalab3.controller;

import com.example.sistalab3.model.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class TicTacToeController {

    @FXML
    private Label playerXScore, playerOScore, turnLabel, resultLabel;

    @FXML
    private Label countdownLabel;
    private int countdown;

    @FXML
    private Button button1, button2, button3, button4, button5, button6, button7, button8, button9;

    @FXML
    private Button restartButton;
    private Timeline turnTimer;

    private String difficulty;
    private boolean isXTurn = true;
    private boolean isGameOver = false;
    private Model model = new Model();

    private Image xImage = new Image(getClass().getResourceAsStream("/com/example/sistalab3/image/X Background Removed.png"));
    private Image oImage = new Image(getClass().getResourceAsStream("/com/example/sistalab3/image/O Background Removed.png"));

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
        System.out.println("Svårighetsgrad satt till: " + difficulty);

        // Beroende på svårighetsgrad, implementera olika AI-strategier senare.
        switch (difficulty) {
            case "easy":
                model.setAIType(Model.AIType.EASY);
                break;
            case "medium":
                model.setAIType(Model.AIType.MEDIUM);
                break;
            case "hard":
                model.setAIType(Model.AIType.HARD);
                break;
        }
    }
    public void setCoopMode() {
        model.setAIType(Model.AIType.CO_OP);
        isXTurn = true;
        updateTurnLabel();
    }

    private void updateTurnLabel() {
        String currentTurn = model.isXTurn() ? "X's Turn" : "O's Turn";
        turnLabel.setText(currentTurn);
    }


    @FXML
    private void initialize() {
        turnLabel.setText("X's Turn");
        restartButton.setVisible(false);
        resultLabel.setText("");

        countdownLabel.setVisible(false);

        turnTimer = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if (countdown > 0) {
                countdownLabel.setText(String.valueOf(countdown));
                countdown--;
            } else {

                resultLabel.setText("Time's up! Game over.");
                if (model.isXTurn()) {
                    model.incrementOScore();
                } else {
                    model.incrementXScore();
                }
                updateScore();
                showRestartButton();
                isGameOver = true;
                turnTimer.stop();
            }
        }));
        turnTimer.setCycleCount(Timeline.INDEFINITE);
    }

    @FXML
    private void handleButtonClick(javafx.event.ActionEvent event) {
        if (isGameOver) {
            return;
        }

        Button clickedButton = (Button) event.getSource();
        if (clickedButton.getGraphic() != null) {
            return; // Om knappen redan har en symbol, gör inget
        }

        countdown = 3;
        countdownLabel.setText(String.valueOf(countdown));
        countdownLabel.setVisible(true);
        turnTimer.playFromStart();

        int index = Integer.parseInt(clickedButton.getId().replace("button", "")) - 1;
        String currentPlayer = model.getCurrentPlayer();
        model.makeMove(index, currentPlayer); // Gör draget

        ImageView imageView = new ImageView(model.isXTurn() ? xImage : oImage);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        imageView.setPreserveRatio(true);
        clickedButton.setGraphic(imageView);
        clickedButton.setUserData(model.isXTurn() ? "X" : "O");

        String winner = model.checkWinner();
        if (winner != null) {
            if (winner.equals("Draw")) {
                resultLabel.setText("It's a draw!");
                // Inga poäng läggs till vid oavgjort
            } else {
                resultLabel.setText("The winner is " + winner + "!");
                if (winner.equals("X")) {
                    model.incrementXScore();
                } else {
                    model.incrementOScore();
                }
                updateScore();
            }

            showRestartButton();
            isGameOver = true;
            return;
        }

        model.switchTurn();
        updateTurnLabel();

        // Gör AI-draget om det är O:s tur och inte Co-op-läget
        if (model.getAIType() != Model.AIType.CO_OP && !isGameOver) {
            makeAIMove();
        }
    }




    private void updateScore() {
        playerXScore.setText("Player X: " + model.getXScore());
        playerOScore.setText("Player O: " + model.getOScore());
    }

    @FXML
    private void resetBoard() {
        // Återställ grafiken och användardata för alla knappar
        for (Button button : new Button[]{button1, button2, button3,
                button4, button5, button6, button7, button8, button9}) {
            button.setGraphic(null);
            button.setUserData(null);
        }
        turnLabel.setText("X's Turn");
        resultLabel.setText("");
        restartButton.setVisible(false);
        countdownLabel.setVisible(false);
        model.resetGame();
        isGameOver = false;
        turnTimer.stop();
    }


    private void makeAIMove() {
        int move = model.getAIMove(); // Hämta AI:s drag
        String currentPlayer = model.getCurrentPlayer();
        model.makeMove(move, currentPlayer); // Gör draget i modellen

        Button aiButton = getButtonByIndex(move);
        ImageView imageView = new ImageView(oImage);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        imageView.setPreserveRatio(true);
        aiButton.setGraphic(imageView);
        aiButton.setUserData("O");

        // Kolla om AI har vunnit
        String winner = model.checkWinner();
        if (winner != null) {
            resultLabel.setText("The winner is " + winner + "!");
            if (winner.equals("X")) {
                model.incrementXScore();
            } else {
                model.incrementOScore();
            }
            updateScore();
            showRestartButton();
            isGameOver = true;
            return;
        }

        model.switchTurn();
        updateTurnLabel();
    }

    @FXML
    private Button homeButton;

    @FXML
    private void goToHome() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sistalab3/Menu.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) homeButton.getScene().getWindow();
            stage.setScene(new Scene(root, 600, 550));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showRestartButton() {
        restartButton.setVisible(true);
        turnLabel.setVisible(false);  // Dölj turtexten när spelet är över
        turnTimer.stop();
    }

    private Button getButtonByIndex(int index) {
        switch (index) {
            case 0:
                return button1;
            case 1:
                return button2;
            case 2:
                return button3;
            case 3:
                return button4;
            case 4:
                return button5;
            case 5:
                return button6;
            case 6:
                return button7;
            case 7:
                return button8;
            case 8:
                return button9;
            default:
                return null;
        }

    }
}
