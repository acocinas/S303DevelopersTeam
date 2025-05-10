package com.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClueTest {

    @Test
    void testRevealSetsRevealedTrue() {
        Clue clue = Clue.builder()
                .id(1)
                .description("Behind the painting")
                .theme("Mystery")
                .price(0.0)
                .build();

        assertFalse(clue.isRevealed(), "Clue should not be revealed by default");

        clue.reveal();

        assertTrue(clue.isRevealed(), "Clue should be marked as revealed after calling reveal()");
    }

    @Test
    void testClueProperties() {
        Clue clue = Clue.builder()
                .id(2)
                .description("Hidden in drawer")
                .theme("Horror")
                .price(5.0)
                .build();

        assertEquals(2, clue.getId());
        assertEquals("Hidden in drawer", clue.getDescription());
        assertEquals("Horror", clue.getTheme());
        assertEquals(5.0, clue.getPrice());
    }
}
