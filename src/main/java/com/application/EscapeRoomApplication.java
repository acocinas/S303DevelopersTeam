package com.application;

import com.controller.EscapeRoomVirtual;
import com.config.DatabaseConnectionManager;
import com.util.DatabaseSeeder;

import java.sql.Connection;
import java.util.logging.Logger;

public class EscapeRoomApplication {
	private EscapeRoomApplication() {}
	private static final Logger logger = Logger.getLogger(EscapeRoomApplication.class.getName());

	public static void initialize() {
		logger.info("[INIT] Starting Escape Room Application...");

		try {
			DatabaseConnectionManager connectionManager = new DatabaseConnectionManager();
			try (Connection connection = connectionManager.getConnection()) {
				logger.info("[INIT] Seeding database from init.sql...");
				DatabaseSeeder.seed(connection);
			}
		} catch (Exception e) {
			logger.severe(String.format("[ERROR] Could not initialize database: %s", e.getMessage()));
			return;
		}

		EscapeRoomVirtual escapeRoom = new EscapeRoomVirtual();
		escapeRoom.start();
	}
}
