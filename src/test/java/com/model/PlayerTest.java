package com.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testAddTicketAddsToList() {
        Player player = Player.builder()
                .id(1)
                .name("Carlos")
                .email("carlos@example.com")
                .build();

        Room room = Room.builder()
                .id(101)
                .name("Haunted Mansion")
                .theme("Terror")
                .difficulty(null)
                .price(30.0)
                .build();

        TicketSale ticket = TicketSale.builder()
                .id(5001)
                .player(player)
                .room(room)
                .price(30.0)
                .saleDate(LocalDateTime.now())
                .build();

        player.addTicket(ticket);

        List<TicketSale> tickets = player.getTickets();
        assertEquals(1, tickets.size());
        assertTrue(tickets.contains(ticket), "The ticket should be added to the player's list.");
    }

    @Test
    void testAdvanceRoomProgressIncrementsValue() {
        Player player = Player.builder()
                .id(2)
                .name("Laura")
                .email("laura@example.com")
                .roomProgress(0)
                .build();

        player.advanceRoomProgress();
        assertEquals(1, player.getRoomProgress(), "Room progress should increment by 1.");
    }

    @Test
    void testGetNotificationLogsMessage() {
        Player player = Player.builder()
                .id(3)
                .name("Mario")
                .email("mario@example.com")
                .build();

        assertDoesNotThrow(() -> player.getNotification("🧩 Has desbloqueado una pista"));
    }
}
