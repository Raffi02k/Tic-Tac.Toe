package com.example.sistalab3.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ModelTest {
    private Model model;

    @BeforeEach
    void setUp() {
        model = new Model();
        model.resetGame();
    }

    @Test
    void checkWinner() {
        model.makeMove(0,"X");
        model.makeMove(1,"X");
        model.makeMove(2,"X");
        assertEquals("X",model.checkWinner());

        model.resetGame();
        model.makeMove(0,"O");
        model.makeMove(3,"O");
        model.makeMove(6,"O");
        assertEquals("O",model.checkWinner());

        model.resetGame();
        model.makeMove(0,"X");
        model.makeMove(1,"O");
        model.makeMove(2,"X");
        model.makeMove(3,"O");
        model.makeMove(4,"X");
        model.makeMove(5,"O");
        model.makeMove(6,"O");
        model.makeMove(7,"X");
        model.makeMove(8,"O");
        assertEquals("Draw",model.checkWinner());

        model.resetGame();
        model.makeMove(0,"X");
        model.makeMove(1,"X");
        assertNull(model.checkWinner());
    }

    @Test
    void makeMove() {
        model.makeMove(0,"X");
        assertEquals("X", model.getBoard()[0]);

        model.makeMove(0,"O");
        assertEquals("X", model.getBoard()[0]);

        model.makeMove(1,"O");
        assertEquals("O", model.getBoard()[1]);

    }
}


