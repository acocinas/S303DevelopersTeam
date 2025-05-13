package com.integration;

import com.config.DatabaseConnectionManager;
import com.config.exception.DatabaseConnectionException;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DatabaseConnectionManagerTest {

    private DatabaseConnectionManager manager;
    private Connection conn;

    @BeforeAll
    void init() {
        try {
            manager = new DatabaseConnectionManager(); // ← usará "database.properties"
        } catch (DatabaseConnectionException e) {
            fail(" Error initializing database: " + e.getMessage());
        }
    }

    @BeforeEach
    void setUp() {
        try {
            conn = manager.getConnection();
            conn.setAutoCommit(false); // ⚠️ muy importante: rollback automático
        } catch (SQLException e) {
            fail(" No se pudo obtener conexión: " + e.getMessage());
        }
    }

    @AfterEach
    void tearDown() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.rollback(); //  limpia todos los cambios del test
                conn.close();
            }
        } catch (SQLException e) {
            System.err.println("⚠ No se pudo hacer rollback o cerrar conexión: " + e.getMessage());
        }
    }

    @AfterAll
    void shutdown() {
        if (manager != null && !manager.isClosed()) {
            manager.shutdown();
        }
    }

    @Test
    void testConnectionIsValid() {
        try {
            assertNotNull(conn);
            assertTrue(conn.isValid(2), " La conexión debería ser válida");
        } catch (SQLException e) {
            fail(" Conexión no válida: " + e.getMessage());
        }
    }

    @Test
    void testPoolStatusReturnsInfo() {
        String status = manager.getPoolStatus();
        assertTrue(status.contains("HikariCP Pool Stats"), " Debería devolver estadísticas del pool");
    }
}
