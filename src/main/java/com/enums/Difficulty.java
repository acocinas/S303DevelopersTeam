package com.enums;


public enum Difficulty {
    EASY("Easy", 60),  
    MEDIUM("Medium", 50), 
    HARD("Hard", 45);  
    
    private final String displayName;
    private final int timeLimit;
    
    Difficulty(String displayName, int timeLimit) {
        this.displayName = displayName;
        this.timeLimit = timeLimit;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public int getTimeLimit() {
        return timeLimit;
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}
