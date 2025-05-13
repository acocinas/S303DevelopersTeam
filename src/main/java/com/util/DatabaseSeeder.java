package com.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class DatabaseSeeder {

    private static final Logger logger = Logger.getLogger(DatabaseSeeder.class.getName());

    // Constructor privado para evitar instanciación
    private DatabaseSeeder() {}

    public static void seed(Connection connection) {
        String path = "src/main/resources/init.sql";

        try {
            String sql = Files.readString(Paths.get(path));
            String[] queries = sql.split(";");

            try (Statement stmt = connection.createStatement()) {
                for (String query : queries) {
                    String trimmed = query.trim();
                    if (!trimmed.isBlank()) {
                        stmt.execute(trimmed);
                        if (logger.isLoggable(Level.INFO)) {
                            logger.info(String.format("[SEED] Executed: %s", trimmed));
                        }
                    }
                }
                logger.info("[INFO] Database seeded successfully.");
            }
        } catch (IOException e) {
            logger.severe(String.format("[ERROR] Failed to read SQL file: %s", e.getMessage()));
        } catch (SQLException e) {
            logger.severe(String.format("[ERROR] Failed to execute SQL: %s", e.getMessage()));
        }
    }
}
