package com.model;

import lombok.*;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Puzzle {
    private int id;
    private String description;
    private String solution;

    @Builder.Default
    private boolean solved = false;

    @Builder.Default
    private List<Clue> clues = new ArrayList<>();

    public void addClue(Clue clue) {
        clues.add(clue);
    }

    public boolean attemptSolution(String attempt) {
        if (solution.equalsIgnoreCase(attempt)) {
            this.solved = true;
            return true;
        }
        return false;
    }
}
