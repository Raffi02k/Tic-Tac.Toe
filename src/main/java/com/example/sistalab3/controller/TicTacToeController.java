package com.example.sistalab3.controller;

import com.example.sistalab3.model.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TicTacToeController {

    @FXML
    private Label playerXScore, playerOScore, turnLabel;

    @FXML
    private Button button1, button2, button3, button4, button5, button6, button7, button8, button9;

    @FXML
    private Button restartButton;

    private Model model = new Model();

    private Image xImage = new Image(getClass().getResourceAsStream("/com/example/sistalab3/image/X Background Removed.png"));
    private Image oImage = new Image(getClass().getResourceAsStream("/com/example/sistalab3/image/O Background Removed.png"));

    @FXML
    private void initialize() {
        turnLabel.setText("X's Turn"); // Starta spelet med X:s tur
        restartButton.setVisible(false); // Dölj restart-knappen tills spelet är över
    }

    @FXML
    private void handleButtonClick(javafx.event.ActionEvent event) {
        Button clickedButton = (Button) event.getSource();

        // Kontrollera om knappen redan har en bild, om så gör ingenting
        if (clickedButton.getGraphic() != null) {
            return;
        }

        // Skapa en ImageView baserat på vilken tur det är
        ImageView imageView = new ImageView(model.isXTurn() ? xImage : oImage);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        imageView.setPreserveRatio(true);

        // Sätt bilden på knappen
        clickedButton.setGraphic(imageView);
        // Sätt "X" eller "O" som identifierare för att använda i vinnarkontrollen
        clickedButton.setUserData(model.isXTurn() ? "X" : "O");

        // Kontrollera om någon har vunnit
        checkWinner();
        if (!restartButton.isVisible()) {  // Om spelet inte är slut, byt tur
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
                turnLabel.setText("Player " + winner + " wins!");

                // Uppdatera poäng
                if (winner.equals("X")) {
                    model.incrementXScore();
                } else {
                    model.incrementOScore();
                }

                updateScore();
                showRestartButton();
                return;
            }
        }

        // Kontrollera om alla knappar är fyllda, då är det en oavgjord match
        boolean isDraw = true;
        for (Button button : new Button[]{button1, button2, button3,
                button4, button5, button6, button7, button8, button9}) {
            if (button.getGraphic() == null) {
                isDraw = false;
                break;
            }
        }

        if (isDraw) {
            turnLabel.setText("It's a Draw!");
            showRestartButton();

        }
    }

    private void updateScore() {
        playerXScore.setText("Player X: " + model.getXScore());
        playerOScore.setText("Player O: " + model.getOScore());
    }

    @FXML
    private void resetBoard() {
        button1.setGraphic(null);
        button2.setGraphic(null);
        button3.setGraphic(null);
        button4.setGraphic(null);
        button5.setGraphic(null);
        button6.setGraphic(null);
        button7.setGraphic(null);
        button8.setGraphic(null);
        button9.setGraphic(null);

        // Rensa UserData för alla knappar
        button1.setUserData(null);
        button2.setUserData(null);
        button3.setUserData(null);
        button4.setUserData(null);
        button5.setUserData(null);
        button6.setUserData(null);
        button7.setUserData(null);
        button8.setUserData(null);
        button9.setUserData(null);
        turnLabel.setText("X's Turn"); // Återställ till standard "X's Turn"
        restartButton.setVisible(false); // Dölj restart-knappen

        model.resetGame();
    }

    private void showRestartButton() {
        restartButton.setVisible(true);
        turnLabel.setVisible(false);
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
