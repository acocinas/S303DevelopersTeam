package com.model;

import lombok.*;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Puzzle {
    private int idPuzzle;
    private String descriptionPuzzle;
    private String solutionPuzzle;

    @Builder.Default
    private boolean solvedPuzzle = false;

    @Builder.Default
    private List<Clue> clues = new ArrayList<>();

    public void addClue(Clue clue) {
        clues.add(clue);
    }

    public boolean attemptSolutionPuzzle(String attempt) {
        if (solutionPuzzle.equalsIgnoreCase(attempt)) {
            this.solvedPuzzle = true;
            return true;
        }
        return false;
    }

}
