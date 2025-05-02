package com.model;

import java.util.ArrayList;
import java.util.List;

public class Puzzle {
    private int idPuzzle;
    private String descriptionPuzzle;
    private String solutionPuzzle;
    private boolean solvedPuzzle;
    private List<Clue> clues;

    public Puzzle(int idPuzzle, String descriptionPuzzle, String solutionPuzzle) {
        this.idPuzzle = idPuzzle;
        this.descriptionPuzzle = descriptionPuzzle;
        this.solutionPuzzle = solutionPuzzle;
        this.solvedPuzzle = false;
        this.clues = new ArrayList<>();
    }

    public int getIdPuzzle() {
        return idPuzzle;
    }

    public String getDescriptionPuzzle() {
        return descriptionPuzzle;
    }

    public String getSolutionPuzzle() {
        return solutionPuzzle;
    }

    public boolean isSolvedPuzzle() {
        return solvedPuzzle;
    }

    public List<Clue> getClues() {
        return clues;
    }

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

    @Override
    public String toString() {
        return "Puzzle{" +
                "idPuzzle=" + idPuzzle +
                ", descriptionPuzzle='" + descriptionPuzzle + '\'' +
                ", solutionPuzzle='" + solutionPuzzle + '\'' +
                ", solvedPuzzle=" + solvedPuzzle +
                ", clues=" + clues.size() +
                '}';
    }
}
