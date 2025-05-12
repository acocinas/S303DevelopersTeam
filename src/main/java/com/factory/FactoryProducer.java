package com.factory;

import com.enums.Difficulty;
import com.interfaces.AbstractFactory;

public class FactoryProducer {
    
    private FactoryProducer() {
        throw new IllegalStateException("Utility class");
    }   
    public static AbstractFactory getFactory(Difficulty difficulty) {
        if (difficulty == null) {
            throw new IllegalArgumentException("Difficulty cannot be null");
        }
        
        return switch (difficulty) {
            case HARD -> new RoomFactoryHard();
            case MEDIUM -> new RoomFactoryMedium();
            case EASY -> new RoomFactoryEasy();
        };
    }

    public static AbstractFactory getFactoryByName(String difficultyName) {
        if (difficultyName == null || difficultyName.trim().isEmpty()) {
            throw new IllegalArgumentException("Difficulty name cannot be null or empty");
        }
        
        try {
            Difficulty difficulty = Difficulty.valueOf(difficultyName.toUpperCase());
            return getFactory(difficulty);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid difficulty name: " + difficultyName, e);
        }
    }
}
    
            