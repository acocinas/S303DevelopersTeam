package com.integration;

import com.dao.TicketSaleDAOImpl;
import com.enums.Difficulty;
import com.model.Player;
import com.model.Room;
import com.model.TicketSale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TicketSaleDAOIntegrationTest {

    private TicketSaleDAOImpl ticketSaleDAO;

    @BeforeEach
    void setup() {
        ticketSaleDAO = new TicketSaleDAOImpl();
    }

    @Test
    void testCreateAndRetrieveTicketByPlayer() {
        // Crear player y room
        Player player = Player.builder()
                .id(1)
                .name("Lucas")
                .email("lucas@example.com")
                .build();

        Room room = Room.builder()
                .id(1)
                .name("Pharaoh's Tomb")
                .difficulty(Difficulty.HARD)
                .build();

        // Crear ticket
        TicketSale ticket = TicketSale.builder()
                .player(player)
                .room(room)
                .price(30.0)
                .saleDate(LocalDateTime.now())
                .build();

        TicketSale created = ticketSaleDAO.create(ticket);
        assertNotNull(created, "Ticket should be created");
        assertEquals("Lucas", created.getPlayer().getName());

        // Buscar por playerId
        List<TicketSale> sales = ticketSaleDAO.findByPlayerId(1);
        assertEquals(1, sales.size(), "Player should have 1 ticket sale");
        assertEquals(room.getName(), sales.get(0).getRoom().getName());
    }
}
