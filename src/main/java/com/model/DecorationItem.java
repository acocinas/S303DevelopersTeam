package com.model;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DecorationItem {
    private int id;
    private String name;
    private String material;
    private double price;

    @Builder.Default
    private boolean interactive = false;
}
