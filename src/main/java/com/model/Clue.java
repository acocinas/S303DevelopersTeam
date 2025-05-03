package com.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Clue {
    private int idClue;
    private String descriptionClue;
    private String themeClue;
    private double priceClue;
    private boolean revealedClue;

    public Clue(int idClue, String descriptionClue, String themeClue, double priceClue) {
        this.idClue = idClue;
        this.descriptionClue = descriptionClue;
        this.themeClue = themeClue;
        this.priceClue = priceClue;
        this.revealedClue = false;
    }

    public Clue() {
        this.revealedClue = false;
    }

    public void revealClue() {
        this.revealedClue = true;
    }

    @Override
    public String toString() {
        return "Clue{" +
                "idClue=" + idClue +
                ", descriptionClue='" + descriptionClue + '\'' +
                ", themeClue='" + themeClue + '\'' +
                ", priceClue=" + priceClue +
                ", revealedClue=" + revealedClue +
                '}';
    }
}
