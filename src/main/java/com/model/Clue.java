package com.model;

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

    public int getIdClue() {
        return idClue;
    }

    public String getDescriptionClue() {
        return descriptionClue;
    }

    public String getThemeClue() {
        return themeClue;
    }

    public boolean isRevealedClue() {
        return revealedClue;
    }

    public double getPriceClue() {
        return priceClue;
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
                ", revealedClue=" + revealedClue +
                ", priceClue=" + priceClue +
                '}';
    }
}
