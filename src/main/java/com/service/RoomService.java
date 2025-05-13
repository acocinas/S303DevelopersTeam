package com.service;

import com.enums.Difficulty;
import com.factory.FactoryProducer;
import com.interfaces.AbstractFactory;
import com.model.Room;
import com.dao.exception.DAOException;

import java.util.Scanner;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RoomService {
	private final InventoryService inventoryService;
	private final Scanner scanner;
	
	private static final String ENTER_VALID_ID = "Please enter a valid numeric ID: ";
	private static final String INVALID_INPUT = "Invalid input detected";
	private static final String ROOM_REMOVED = "✅ Room with ID {} was successfully removed.";

	public RoomService(InventoryService inventoryService, Scanner scanner) {
		this.inventoryService = inventoryService;
		this.scanner = scanner;
	}

	public void createRoom() {
		try {
			log.info("Enter the room theme: ");
			String theme = scanner.nextLine().trim();

			if (theme.isEmpty()) {
				log.warn("Theme cannot be empty.");
				return;
			}

			log.info("Select difficulty (EASY, MEDIUM, HARD): ");
			String input = scanner.nextLine().trim().toUpperCase();

			Optional<Difficulty> difficultyOptional = parseDifficulty(input);
			if (difficultyOptional.isEmpty()) {
				return;
			}
			Difficulty difficulty = difficultyOptional.get();

			AbstractFactory factory = FactoryProducer.getFactory(difficulty);
			Room room = factory.createRoom(theme);

			inventoryService.addRoom(room);

			log.info("✅ Room created successfully with ID: {}", room.getId());
		} catch (DAOException e) {
			log.error("Failed to create room: {}", e.getMessage(), e);
		}
	}
	
	private Optional<Difficulty> parseDifficulty(String input) {
		try {
			return Optional.of(Difficulty.valueOf(input));
		} catch (IllegalArgumentException e) {
			log.warn("Invalid difficulty: {}. Please enter EASY, MEDIUM, or HARD.", input);
			return Optional.empty();
		}
	}

	public void removeRoomById() {
		try {
			log.info("Enter the ID of the room to remove: ");
			while (!scanner.hasNextInt()) {
				log.warn(INVALID_INPUT);
				log.info(ENTER_VALID_ID);
				scanner.next();
			}

			int roomId = scanner.nextInt();
			scanner.nextLine();

			inventoryService.removeRoomFromInventory(roomId);

			log.info(ROOM_REMOVED, roomId);
		} catch (DAOException e) {
			log.error("Could not remove room: {}", e.getMessage(), e);
		}
	}
}
