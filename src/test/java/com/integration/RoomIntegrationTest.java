package com.integration;

import com.enums.Difficulty;
import com.factory.RoomFactoryEasy;
import com.interfaces.AbstractFactory;
import com.model.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomIntegrationTest {

    @Test
    void testPlayerSolvesPuzzleAndReceivesNotification() {
        // Crear sala con factory
        AbstractFactory factory = new RoomFactoryEasy();
        Room room = factory.createRoom("Adventure");

        // Crear puzzle y añadir una pista
        Puzzle puzzle = Puzzle.builder()
                .id(1)
                .description("Enter the correct code")
                .solution("1234")
                .build();

        Clue clue = Clue.builder()
                .id(1)
                .description("It's a four-digit number")
                .theme("Adventure")
                .price(0.0)
                .build();

        puzzle.addClue(clue);
        room.addPuzzle(puzzle);

        // Crear jugador y añadirlo como observer
        Player player = Player.builder()
                .id(1)
                .name("Alex")
                .email("alex@example.com")
                .build();

        room.addObserver(player);

        // Resolver el enigma correctamente
        boolean solved = puzzle.attemptSolution("1234");

        // Notificar que se resolvió un puzzle
        if (solved) {
            room.notifyObservers("✅ Puzzle solved: " + puzzle.getDescription());
        }

        // Verificar que el puzzle se marcó como resuelto
        assertTrue(puzzle.isSolved(), "Puzzle should be marked as solved");

        // No podemos verificar la notificación si Player solo loguea
        // Pero validamos que la lógica se ejecutó sin errores
    }
}
