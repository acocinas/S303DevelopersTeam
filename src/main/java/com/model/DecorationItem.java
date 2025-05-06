package com.model;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DecorationItem {
    private int idDecorationItem;
    private String nameDecorationItem;
    private String materialDecorationItem;
    private double priceDecorationItem;
}
