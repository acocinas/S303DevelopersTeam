package com.model;

import com.enums.*;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DecorationItem {
    private int id;
    private String name;
    private Material material;
    private double price;
    private Room room;

    @Builder.Default
    private boolean interactive = false;
}
