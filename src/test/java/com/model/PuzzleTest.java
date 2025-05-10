package com.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PuzzleTest {

    @Test
    void testInitialSolvedIsFalse() {
        Puzzle puzzle = Puzzle.builder()
                .id(1)
                .description("Code box")
                .solution("1234")
                .build();

        assertFalse(puzzle.isSolved(), "Puzzle should not be solved by default");
    }

    @Test
    void testAttemptCorrectSolutionMarksAsSolved() {
        Puzzle puzzle = Puzzle.builder()
                .solution("OpenSesame")
                .build();

        boolean result = puzzle.attemptSolution("opensesame");

        assertTrue(result, "Correct solution should return true");
        assertTrue(puzzle.isSolved(), "Puzzle should be marked as solved");
    }

    @Test
    void testAttemptWrongSolutionReturnsFalse() {
        Puzzle puzzle = Puzzle.builder()
                .solution("42")
                .build();

        boolean result = puzzle.attemptSolution("43");

        assertFalse(result, "Incorrect solution should return false");
        assertFalse(puzzle.isSolved(), "Puzzle should remain unsolved");
    }

    @Test
    void testAddClueAddsToList() {
        Puzzle puzzle = Puzzle.builder().build();
        Clue clue = Clue.builder()
                .id(1)
                .description("Check under the table")
                .theme("Mystery")
                .price(0)
                .build();

        puzzle.addClue(clue);

        assertEquals(1, puzzle.getClues().size(), "Clue list should contain one clue");
        assertTrue(puzzle.getClues().contains(clue), "Clue should be in the clue list");
    }
}
