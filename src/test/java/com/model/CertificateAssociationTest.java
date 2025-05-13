package com.model;

import com.enums.Difficulty;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CertificateAssociationTest {

    @Test
    void testTicketAndCertificateAssociation() {
        // Crear jugador
        Player player = Player.builder()
                .id(1)
                .name("Emma")
                .email("emma@example.com")
                .build();

        // Crear sala
        Room room = Room.builder()
                .id(1)
                .name("Mystery Library")
                .difficulty(Difficulty.MEDIUM)
                .build();

        // Crear ticket
        TicketSale ticket = TicketSale.builder()
                .id(1)
                .player(player)
                .room(room)
                .price(20.0)
                .saleDate(LocalDateTime.now())
                .build();

        // Crear certificado
        Certificate certificate = Certificate.builder()
                .id(1)
                .player(player)
                .roomName(room.getName())
                .certificateDate(LocalDateTime.now())
                .build();

        // Validaciones
        assertEquals("Emma", ticket.getPlayer().getName(), "Ticket should be linked to player Emma");
        assertEquals("Mystery Library", ticket.getRoom().getName(), "Room should be linked to the ticket");
        assertEquals("Mystery Library", certificate.getRoomName(), "Certificate room name should match");
        assertEquals(player, certificate.getPlayer(), "Certificate should be linked to the player");
    }
}
