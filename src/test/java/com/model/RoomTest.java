package com.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

 class RoomTest {

    @Test
     void testObserverReceivesNotificationWhenClueAdded() {
        Room room = Room.builder()
                .id(1)
                .name("Mystery Room")
                .theme("Mystery")
                .difficulty(null)
                .price(50.0)
                .build();

        ObserverTest observer = new ObserverTest();
        room.addObserver(observer);

        Clue clue = Clue.builder()
                .id(101)
                .description("First clue")
                .theme("Mystery")
                .price(0)
                .build();

        room.addClue(clue);

        assertTrue(observer.isNotified(), "Observer should have been notified");
        assertTrue(observer.getLastMessage().contains("A change occurred."), "Message should contain 'A change occurred.'");
    }
}
