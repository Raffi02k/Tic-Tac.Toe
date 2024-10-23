    package com.example.sistalab3.controller;

    import javafx.fxml.FXML;
    import javafx.scene.control.Button;
    import javafx.scene.control.Label;
    import javafx.scene.image.Image;
    import javafx.scene.image.ImageView;

    public class TicTacToeController {

        @FXML
        private Label playerXScore, playerOScore;

        @FXML
        private Button button1, button2, button3, button4, button5, button6, button7, button8, button9;

        private boolean isXTurn = true; // Håller reda på vems tur det är
        private int xScore = 0;
        private int oScore = 0;

        // Bilder för X och O
        private Image xImage = new Image(getClass().getResourceAsStream("/com/example/sistalab3/image/X Background Removed.png"));
        private Image oImage = new Image(getClass().getResourceAsStream("/com/example/sistalab3/image/O Background Removed.png"));



        @FXML
        private void handleButtonClick(javafx.event.ActionEvent event) {
            Button clickedButton = (Button) event.getSource();

            // Kontrollera om knappen redan har en bild, om så gör ingenting
            if (clickedButton.getGraphic() != null) {
                return;
            }


            ImageView imageView = new ImageView(isXTurn ? xImage : oImage);
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            imageView.setPreserveRatio(true);

            clickedButton.setGraphic(imageView); // Sätt bilden på knappen

            isXTurn = !isXTurn;


            checkWinner();
        }

        private void checkWinner() {
            // Här implementerar du logiken för att kontrollera vinnare
            // Om någon vinner, uppdatera poäng och återställ brädet
        }

        private void updateScore() {
            playerXScore.setText("Player X: " + xScore);
            playerOScore.setText("Player O: " + oScore);
        }

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
        }

    }
