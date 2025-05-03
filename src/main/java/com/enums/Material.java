package com.enums;

public enum Material {
    WOOD("Wood"),
    METAL("Metal"),
    PLASTIC("Plastic"),
    PAPER("Paper"),
    GLASS("Glass"),
    FABRIC("Fabric"),
    STONE("Stone"),
    ELECTRONIC("Electronic");
    
    private final String displayName;
    
    Material(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}
