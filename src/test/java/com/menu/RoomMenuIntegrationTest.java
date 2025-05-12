package com.menu;

import com.model.Room;
import com.service.InventoryService;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class RoomMenuIntegrationTest {

    @Test
    void testFullRoomMenuFlow_createAndDeleteRoom() throws Exception {
        /*
         * Flujo:
         * 1 → Crear Room: tema = "Terror", dificultad = "EASY"
         * 2 → Eliminar Room con ID = 1
         * 0 → Salir
         */
        String simulatedInput = String.join("\n",
                "1", "Terror", "EASY",  // Crear Room
                "2", "1",               // Eliminar Room
                "0",// Salir
                ""// Evitamos el error que da el test ya que no se aprieta enter y la limpieza de buffer da error
        );

        Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes(StandardCharsets.UTF_8)));
        InventoryService inventoryService = new InventoryService();
        RoomMenu roomMenu = new RoomMenu(inventoryService, scanner);

        roomMenu.manageRoom();

        // 🔍 Assert real: la Room debe haber sido eliminada
        Room deletedRoom = inventoryService.getRoomById(1);
        assertNull(deletedRoom, "La sala con ID 1 debería haber sido eliminada del inventario.");
    }
}
