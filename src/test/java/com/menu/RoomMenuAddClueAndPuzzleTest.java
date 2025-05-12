package com.menu;

import com.model.Clue;
import com.model.Puzzle;
import com.model.Room;
import com.service.InventoryService;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class RoomMenuAddClueAndPuzzleTest {

    @Test
    void testAddClueAndPuzzleToRoom() {
        String simulatedInput = String.join("\n",
                "1",                     // Create room
                "Aventura",              // Room theme
                "EASY",                  // Difficulty

                "3",                     // Add clue
                "1",                     // Room ID
                "La llave está en la caja", // Clue description
                "Misterio",              // Clue theme

                "5",                     // Add puzzle
                "1",                     // Room ID
                "Resuelve el acertijo",  // Puzzle description
                "1234",                  // Puzzle solution

                "0",                     // Exit
                ""                       // Avoid scanner error
        );
        Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes(StandardCharsets.UTF_8)));
        InventoryService inventoryService = new InventoryService();
        RoomMenu roomMenu = new RoomMenu(inventoryService, scanner);

        roomMenu.manageRoom();

        Room room = inventoryService.getRoomById(1);
        assertNotNull(room);

        List<Clue> clues = room.getClues();
        List<Puzzle> puzzles = room.getPuzzles();

        assertFalse(clues.isEmpty());
        assertEquals("La llave está en la caja", clues.getFirst().getDescription());

        assertFalse(puzzles.isEmpty());
        assertEquals("Resuelve el acertijo", puzzles.getFirst().getDescription());
    }
}
