package com.integration;

import com.enums.Difficulty;
import com.enums.Material;
import com.model.Clue;
import com.model.DecorationItem;
import com.model.Room;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryIntegrationTest {

    @Test
    void testRoomInventoryIntegration() {
        // Crear una sala
        Room room = Room.builder()
                .id(1)
                .name("Haunted Mansion")
                .difficulty(Difficulty.MEDIUM)
                .build();

        // Crear pista y decoración
        Clue clue = Clue.builder()
                .id(1)
                .description("Look behind the mirror")
                .theme("Horror")
                .price(0.0)
                .room(room)
                .build();

        DecorationItem item = DecorationItem.builder()
                .id(1)
                .name("Old Mirror")
                .material(Material.GLASS)
                .room(room)
                .build();

        // Asignar objetos a la sala
        room.addClue(clue);
        room.addDecorationItem(item);

        // Validaciones
        assertEquals(1, room.getClues().size(), "Room should contain 1 clue");
        assertEquals(1, room.getDecorationItems().size(), "Room should contain 1 decoration item");

        // Verificamos contenido específico
        assertEquals("Look behind the mirror", room.getClues().get(0).getDescription());
        assertEquals("Old Mirror", room.getDecorationItems().get(0).getName());

        // Validamos que los objetos estén correctamente vinculados
        assertEquals(room, room.getDecorationItems().get(0).getRoom(), "Decoration item should be linked to the room");
        assertEquals(room, item.getRoom(), "Decoration item should be linked to the room");

    }
}
