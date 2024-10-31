package com.example.sistalab3.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Model {

    public enum AIType {
        EASY, MEDIUM, HARD, CO_OP
    }

    private AIType aiType;
    private boolean isXTurn = true; // Startar med att vara X's tur
    private int xScore = 0;
    private int oScore = 0;
    private String[] board = new String[9]; // Håll koll på brädet

    public AIType getAIType() {
        return aiType;
    }
    public void setAIType(AIType aiType) {
        this.aiType = aiType;
    }

    public int getAIMove() {
        if (aiType == AIType.CO_OP) {
            return -1; // Ingen AI i Co-op-läget
        }
        switch (aiType) {
            case EASY:
                return getRandomAvailableMove();
            case MEDIUM:
                return getMediumMove();
            case HARD:
                return getHardMove();
            default:
                throw new IllegalArgumentException("Invalid AI type");
        }
    }

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
        isXTurn = true;
        board = new String[9]; // Återställ brädet
    }


    public void makeMove(int index, String player) {
        if (board[index] == null) { // Kontrollera om rutan är tom
            board[index] = player; // Sätt spelaren på brädet
        }
    }
    public String[] getBoard() {
        return board;
    }

    public String checkWinner() {
        int[][] winningCombinations = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}
        };

        for (int[] combination : winningCombinations) {
            if (board[combination[0]] != null &&
                    board[combination[0]].equals(board[combination[1]]) &&
                    board[combination[0]].equals(board[combination[2]])) {
                return board[combination[0]]; // Returnera vinnaren
            }
        }

        for (String cell : board) {
            if (cell == null) {
                return null;
            }
        }
        return "Draw";
    }

    public String getCurrentPlayer() {
        return isXTurn ? "X" : "O";
    }

    public int getRandomAvailableMove() {
        List<Integer> availableMoves = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            if (board[i] == null) {
                availableMoves.add(i);
            }
        }


        if (!availableMoves.isEmpty()) {
            Random random = new Random();
            return availableMoves.get(random.nextInt(availableMoves.size()));
        }

        return -1; // Ingen ledig ruta
    }

    private int getMediumMove() {
        for (int i = 0; i < board.length; i++) {
            if (board[i] == null) { // Om rutan är ledig
                board[i] = "O"; // Sätt AI:s drag
                if (checkWinner() != null) {
                    board[i] = null; // Ångra draget
                    return i; // Returnera vinnande drag
                }
                board[i] = null; // Ångra draget
            }
        }

        // 2. Blockera spelaren från att vinna
        for (int i = 0; i < board.length; i++) {
            if (board[i] == null) { // Om rutan är ledig
                board[i] = "X"; // Sätt spelarens drag
                if (checkWinner() != null) {
                    board[i] = null; // Ångra draget
                    return i; // Blockera spelarens drag
                }
                board[i] = null; // Ångra draget
            }
        }

        // 3. Om ingen kan vinna, gör ett slumpmässigt drag
        return getRandomAvailableMove();
    }

    private int getHardMove() {
        int bestScore = Integer.MIN_VALUE;
        int bestMove = -1;

        for (int i = 0; i < board.length; i++) {
            if (board[i] == null) { // Kontrollera om rutan är tom
                board[i] = "O"; // Gör AI-draget
                int score = minimax(false); // Beräkna poängen för detta drag
                board[i] = null; // Återställ rutan
                if (score > bestScore) {
                    bestScore = score; // Håll reda på det bästa draget
                    bestMove = i;
                }
            }
        }
        return bestMove;
    }

    // Minimax-algoritm
    private int minimax(boolean isMaximizing) {
        String result = checkWinner();
        if (result != null) {
            if (result.equals("O")) return 1; // AI:n vinner
            else if (result.equals("X")) return -1; // Motståndaren vinner
            else return 0; // Oavgjort
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < board.length; i++) {
                if (board[i] == null) {
                    board[i] = "O"; // Gör AI-draget
                    int score = minimax(false); // Räkna poängen för nästa drag
                    board[i] = null; // Återställ rutan
                    bestScore = Math.max(score, bestScore); // Håll reda på bästa poäng
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < board.length; i++) {
                if (board[i] == null) {
                    board[i] = "X"; // Gör motståndarens drag
                    int score = minimax(true); // Räkna poängen för nästa drag
                    board[i] = null; // Återställ rutan
                    bestScore = Math.min(score, bestScore); // Håll reda på bästa poäng
                }
            }
            return bestScore;
        }
    }
}
