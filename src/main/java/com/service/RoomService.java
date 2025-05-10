package com.service;

import com.enums.Difficulty;
import com.factory.FactoryProducer;
import com.interfaces.AbstractFactory;
import com.model.Clue;
import com.model.Room;
import com.service.InventoryService;
import com.dao.exception.DAOException;

import java.util.Scanner;

public class RoomService {
	private final InventoryService inventoryService;
	private final Scanner scanner;

	public RoomService(InventoryService inventoryService, Scanner scanner) {
		this.inventoryService = inventoryService;
		this.scanner = scanner;
	}

	public void createRoom() {
		try {
			System.out.print("Enter the room theme: ");
			String theme = scanner.nextLine().trim();

			if (theme.isEmpty()) {
				System.out.println("⚠️ Theme cannot be empty.");
				return;
			}

			System.out.print("Select difficulty (EASY, MEDIUM, HARD): ");
			String input = scanner.nextLine().trim().toUpperCase();

			Difficulty difficulty;
			try {
				difficulty = Difficulty.valueOf(input);
			} catch (IllegalArgumentException e) {
				System.out.println("⚠️ Invalid difficulty. Please enter EASY, MEDIUM, or HARD.");
				return;
			}

			AbstractFactory factory = FactoryProducer.getFactory(difficulty);
			Room room = factory.createRoom(theme);

			inventoryService.addRoom(room);

			System.out.println("✅ Room created successfully with ID: " + room.getId());
		} catch (DAOException e) {
			System.out.println("❌ Failed to create room: " + e.getMessage());
		}
	}

	public void removeRoomById() {
		try {
			System.out.print("Enter the ID of the room to remove: ");
			while (!scanner.hasNextInt()) {
				System.out.print("Please enter a valid numeric ID: ");
				scanner.next();
			}

			int roomId = scanner.nextInt();
			scanner.nextLine();

			inventoryService.removeRoomFromInventory(roomId);

			System.out.println("✅ Room with ID " + roomId + " was successfully removed.");
		} catch (DAOException e) {
			System.out.println("❌ Could not remove room: " + e.getMessage());
		}
	}
}
