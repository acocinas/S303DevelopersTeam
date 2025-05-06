package com.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Clue {
    private int idClue;
    private String descriptionClue;
    private String themeClue;
    private double priceClue;

    @Builder.Default
    private boolean revealedClue = false;

    public void revealClue() {
        this.revealedClue = true;
    }
}
