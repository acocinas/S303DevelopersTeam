package com.dao;

import com.enums.Difficulty;
import com.model.Room;
import com.dao.exception.DAOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RoomDAOImplTest {

    private RoomDAOImpl roomDAO;

    @BeforeEach
    void setUp() {
        roomDAO = new RoomDAOImpl();
    }

    @Test
    void testGetRoomCountByDifficulty() throws DAOException {
        Room room1 = Room.builder()
                .id(1)
                .name("Ancient Egypt")
                .difficulty(Difficulty.EASY)
                .price(50.0)
                .build();

        Room room2 = Room.builder()
                .id(2)
                .name("Haunted Mansion")
                .difficulty(Difficulty.HARD)
                .price(90.0)
                .build();

        Room room3 = Room.builder()
                .id(3)
                .name("Jungle Escape")
                .difficulty(Difficulty.EASY)
                .price(60.0)
                .build();

        roomDAO.create(room1);
        roomDAO.create(room2);
        roomDAO.create(room3);

        Map<Difficulty, Long> result = roomDAO.findAll().stream()
                .collect(java.util.stream.Collectors.groupingBy(
                        Room::getDifficulty,
                        java.util.stream.Collectors.counting()
                ));

        assertEquals(2, result.get(Difficulty.EASY));
        assertEquals(1, result.get(Difficulty.HARD));
        assertNull(result.get(Difficulty.MEDIUM)); // No hay salas MEDIUM
    }
}
