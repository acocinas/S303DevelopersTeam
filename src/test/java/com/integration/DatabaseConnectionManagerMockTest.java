package com.integration;

import com.config.DatabaseConnectionManager;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DatabaseConnectionManagerMockTest {

    private HikariDataSource mockDataSource;
    private DatabaseConnectionManager manager;

    @BeforeEach
    void setUp() throws SQLException {
        mockDataSource = mock(HikariDataSource.class);
        Connection mockConnection = mock(Connection.class);

        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.isValid(anyInt())).thenReturn(true);

        // Usamos el nuevo constructor para inyectar el mock
        manager = new DatabaseConnectionManager(mockDataSource);
    }

    @Test
    void testGetConnectionReturnsValidConnection() throws SQLException {
        Connection conn = manager.getConnection();
        assertNotNull(conn);
        assertTrue(conn.isValid(1));
        verify(mockDataSource).getConnection();
    }

    @Test
    void testShutdownClosesDataSource() {
        when(mockDataSource.isClosed()).thenReturn(false);
        manager.shutdown();
        verify(mockDataSource).close();
    }

    @Test
    void testPoolStatusReturnsMockedInfo() {
        var poolBean = mock(com.zaxxer.hikari.HikariPoolMXBean.class);
        when(mockDataSource.getHikariPoolMXBean()).thenReturn(poolBean);
        when(poolBean.getActiveConnections()).thenReturn(1);
        when(poolBean.getIdleConnections()).thenReturn(2);
        when(poolBean.getThreadsAwaitingConnection()).thenReturn(0);
        when(poolBean.getTotalConnections()).thenReturn(3);

        String status = manager.getPoolStatus();
        assertTrue(status.contains("HikariCP Pool Stats"));
    }
}
