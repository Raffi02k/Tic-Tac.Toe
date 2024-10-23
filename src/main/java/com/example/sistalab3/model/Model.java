package com.example.sistalab3.model;

public class Model {

    private boolean isXTurn = true; // Startar med att vara X's tur
    private int xScore = 0;
    private int oScore = 0;

    public boolean isXTurn() {
        return isXTurn;
    }

    public void switchTurn() {
        isXTurn = !isXTurn;
    }

    public int getXScore() {
        return xScore;
    }

    public int getOScore() {
        return oScore;
    }

    public void incrementXScore() {
        xScore++;
    }

    public void incrementOScore() {
        oScore++;
    }

    public void resetGame() {
        isXTurn = true;  // Återställ så att X börjar
    }
}
