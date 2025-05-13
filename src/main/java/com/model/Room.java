package com.model;

import com.enums.Difficulty;
import com.interfaces.Observer;
import com.observer.EventManager;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {
    private int id;
    private String name;
    private String theme;
    private Difficulty difficulty;
    private double price;

    @Builder.Default
    private List<Puzzle> puzzles = new ArrayList<>();

    @Builder.Default
    private List<Clue> clues = new ArrayList<>();

    @Builder.Default
    private List<DecorationItem> decorationItems = new ArrayList<>();

    @Builder.Default
    private EventManager eventManager = new EventManager(new ArrayList<>());

    public void addPuzzle(Puzzle puzzle) {
        puzzles.add(puzzle);
        eventManager.notifyObservers("🧠 Puzzle added to the room: " + name);
    }

    public void addClue(Clue clue) {
        clues.add(clue);
        eventManager.notifyObservers("🔍 Clue added to the room: " + name);
    }

    public void addDecorationItem(DecorationItem item) {
        decorationItems.add(item);
        eventManager.notifyObservers("🎨 Decoration added to the room: " + name);
    }

    public void addObserver(Observer observer) {
        eventManager.addObserver(observer);
    }

    public void notifyObservers(String message) {
        eventManager.notifyObservers(message);
    }

    public void removeObserver(Observer observer) {
        eventManager.removeObserver(observer);
    }
}
