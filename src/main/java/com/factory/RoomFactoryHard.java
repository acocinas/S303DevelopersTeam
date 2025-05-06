package com.factory;

import java.util.*;
import com.enums.*;
import com.model.*;
import com.interfaces.AbstractFactory;

public class RoomFactoryHard implements AbstractFactory {

    public RoomFactoryHard() {

    }

    @Override
    public Room createRoom(String theme) {
        validateTheme(theme);

        Room room = new Room();
        room.setId(generateUniqueId());
        room.setName("Hard " + theme + " Room");
        room.setTheme(theme);
        room.setDifficulty(Difficulty.HARD);
        room.setPrice(125.0);
        room.setPuzzles(new ArrayList<>());
        room.setDecorationItems(new ArrayList<>());

        return room;
    }

    @Override
    public Clue createClue(String description, String theme) {
        validateDescription(description);
        validateTheme(theme);

        Clue clue = new Clue();
        clue.setId(generateUniqueId());
        clue.setName("Hard Clue");
        clue.setDescription(description);
        clue.setTheme(theme);
        clue.setVisibility(false);

        return clue;
    }

    @Override
    public DecorationItem createDecorationItem(String name, Material material) {
        validateName(name);
        validateMaterial(material);

        DecorationItem item = new DecorationItem();
        item.setId(generateUniqueId());
        item.setName(name);
        item.setMaterial(material);
        item.setIsInteractive(true);

        return item;
    }

    @Override
    public Difficulty getDifficulty() {
        return Difficulty.HARD;
    }

    protected int generateUniqueId() {
        return Math.abs(UUID.randomUUID().hashCode());
    }

    protected void validateTheme(String theme) {
        if (theme == null || theme.trim().isEmpty()) {
            throw new IllegalArgumentException("Theme cannot be null or empty");
        }
    }

    protected void validateDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
    }

    protected void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
    }

    protected void validateMaterial(Material material) {
        if (material == null) {
            throw new IllegalArgumentException("Material cannot be null");
        }
    }
}
