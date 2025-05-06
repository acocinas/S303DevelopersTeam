package com.factory;

import com.enums.Difficulty;
import com.interfaces.AbstractFactory;

public class FactoryProducer {
    
        public static AbstractFactory getFactory(Difficulty difficulty) {
        if (difficulty == null) {
            throw new IllegalArgumentException("Difficulty cannot be null");
        }
        
        switch (difficulty) {
            case HARD:
                return new RoomFactoryHard();
            case MEDIUM:
                return new RoomFactoryMedium();
            case EASY:
                return new RoomFactoryEasy();
            default:
                throw new IllegalArgumentException("Unsupported difficulty level: " + difficulty);
        }
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
            