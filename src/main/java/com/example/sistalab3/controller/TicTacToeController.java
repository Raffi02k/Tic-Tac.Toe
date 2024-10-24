package com.example.sistalab3.controller;

import com.example.sistalab3.model.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

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
    private boolean isGameOver = false;
    private Model model = new Model();

    private Image xImage = new Image(getClass().getResourceAsStream("/com/example/sistalab3/image/X Background Removed.png"));
    private Image oImage = new Image(getClass().getResourceAsStream("/com/example/sistalab3/image/O Background Removed.png"));

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
        System.out.println("Svårighetsgrad satt till: " + difficulty);  // För debug, skriv ut svårighetsgraden
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
        if (isGameOver) {  // Om spelet är över, gör ingenting
            return;
        }

        Button clickedButton = (Button) event.getSource();

        // Kontrollera om knappen redan har en bild, om så gör ingenting
        if (clickedButton.getGraphic() != null) {
            return;
        }


        countdown = 3;
        countdownLabel.setText(String.valueOf(countdown));
        countdownLabel.setVisible(true);
        turnTimer.playFromStart();

        ImageView imageView = new ImageView(model.isXTurn() ? xImage : oImage);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        imageView.setPreserveRatio(true);

        // Sätt bilden på knappen
        clickedButton.setGraphic(imageView);
        // Sätt "X" eller "O" som identifierare för att använda i vinnarkontrollen
        clickedButton.setUserData(model.isXTurn() ? "X" : "O");

        // Kontrollera om någon har vunnit eller om det är oavgjort
        checkWinner();

        // Om spelet inte är slut, byt tur
        if (!isGameOver) {
            model.switchTurn();  // Byt tur i Model
            turnLabel.setText(model.isXTurn() ? "X's Turn" : "O's Turn");  // Uppdatera texten med vems tur det är
        }
    }

    private void checkWinner() {
        int[][] winningCombinations = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}
        };

        for (int[] combination : winningCombinations) {
            Button b1 = getButtonByIndex(combination[0]);
            Button b2 = getButtonByIndex(combination[1]);
            Button b3 = getButtonByIndex(combination[2]);

            // Kontrollera om alla tre knappar i kombinationen har samma UserData (dvs "X" eller "O")
            if (b1.getUserData() != null &&
                    b1.getUserData().equals(b2.getUserData()) &&
                    b1.getUserData().equals(b3.getUserData())) {

                String winner = b1.getUserData().toString();
                resultLabel.setText("The winner is " + winner + "!");  // Visa vinnaren

                // Uppdatera poängen för vinnaren
                if (winner.equals("X")) {
                    model.incrementXScore();
                } else {
                    model.incrementOScore();
                }

                updateScore();  // Uppdatera poängtavlan
                showRestartButton(); // Visa restart-knappen
                isGameOver = true;  // Markera att spelet är slut
                return;
            }
        }

        // Kontrollera om alla knappar är fyllda utan vinnare, då är det oavgjort
        boolean isDraw = true;
        for (Button button : new Button[]{button1, button2, button3, button4, button5, button6, button7, button8, button9}) {
            if (button.getGraphic() == null) {
                isDraw = false;
                break;
            }
        }

        if (isDraw) {
            resultLabel.setText("It's a Draw!");  // Visa "oavgjort"-meddelande
            showRestartButton(); // Visa restart-knappen
            isGameOver = true;  // Markera att spelet är slut
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
