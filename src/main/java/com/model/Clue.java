package com.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Clue {
    private int id;
    private String description;
    private String theme;
    private double price;

    @Builder.Default
    private boolean revealed = false;

    public void reveal() {
        this.revealed = true;
    }
}
