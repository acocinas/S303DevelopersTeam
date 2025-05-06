package com.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clue {
    private int idClue;
    private String descriptionClue;
    private String themeClue;
    private double priceClue;
    private boolean revealedClue = false;


    public void revealClue() {

        this.revealedClue = true;
    }
}
