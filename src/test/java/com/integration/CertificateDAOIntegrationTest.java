package com.integration;

import com.dao.CertificateDAOImpl;
import com.model.Certificate;
import com.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CertificateDAOIntegrationTest {

    private CertificateDAOImpl certificateDAO;

    @BeforeEach
    void setUp() {
        certificateDAO = new CertificateDAOImpl();
    }

    @Test
    void testCreateAndRetrieveCertificate() {
        Player player = Player.builder()
                .id(2)
                .name("Marco")
                .email("marco@example.com")
                .build();

        Certificate certificate = Certificate.builder()
                .player(player)
                .roomName("Final Chamber")
                .certificateDate(LocalDateTime.now())
                .build();

        Certificate saved = certificateDAO.create(certificate);
        assertNotNull(saved);
        assertEquals("Marco", saved.getPlayer().getName());

        List<Certificate> certificates = certificateDAO.findByPlayerId(2);
        assertFalse(certificates.isEmpty());
        assertEquals("Final Chamber", certificates.get(0).getRoomName());
    }
}
