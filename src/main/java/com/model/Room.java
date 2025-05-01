package com.model;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private int id;
    private String name;
    private String difficulty;
    private double price;
    private List<Puzzle> puzzles;
    private List<Clue> clues;
    private List<DecorationItem> decorationItems;

    public Room(int id, String name, String difficulty, double price) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.price = price;
        this.puzzles = new ArrayList<>();
        this.clues = new ArrayList<>();
        this.decorationItems = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public double getPrice() {
        return price;
    }

    public List<Puzzle> getPuzzles() {
        return puzzles;
    }

    public List<Clue> getClues() {
        return clues;
    }

    public List<DecorationItem> getDecorationItems() {
        return decorationItems;
    }

    public void addPuzzle(Puzzle puzzle) {
        puzzles.add(puzzle);
    }

    public void addClue(Clue clue) {
        clues.add(clue);
    }

    public void addDecorationItem(DecorationItem item) {
        decorationItems.add(item);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", price=" + price +
                ", puzzles=" + puzzles.size() +
                ", clues=" + clues.size() +
                ", decorationItems=" + decorationItems.size() +
                '}';
    }
}

