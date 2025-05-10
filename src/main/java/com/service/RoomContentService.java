package com.service;

import com.dao.exception.DAOException;
import com.enums.Difficulty;
import com.enums.Material;
import com.factory.FactoryProducer;
import com.interfaces.AbstractFactory;
import com.model.Clue;
import com.model.DecorationItem;
import com.model.Room;

import java.util.Scanner;

public class RoomContentService {
	private final InventoryService inventoryService;
	private final Scanner scanner;

	public RoomContentService(InventoryService inventoryService, Scanner scanner) {
		this.inventoryService = inventoryService;
		this.scanner = scanner;
	}

	public void addClueToRoom() {
		try {

			System.out.print("Enter the ID of the room to add a clue to: ");
			while (!scanner.hasNextInt()) {
				System.out.print("Please enter a valid numeric ID: ");
				scanner.next();
			}
			int roomId = scanner.nextInt();
			scanner.nextLine();

			Room room = inventoryService.getRoomById(roomId);
			if (room == null) {
				System.out.println("❌ Room not found with ID: " + roomId);
				return;
			}

			System.out.print("Enter the clue description: ");
			String description = scanner.nextLine().trim();

			System.out.print("Enter the clue theme: ");
			String theme = scanner.nextLine().trim();

			Difficulty difficulty = room.getDifficulty();
			AbstractFactory factory = FactoryProducer.getFactory(difficulty);

			Clue clue = factory.createClue(description, theme);

			room.getClues().add(clue);

			inventoryService.updateRoom(room);

			System.out.println("✅ Clue added to room '" + room.getName() + "' successfully.");

		} catch (DAOException e) {
			System.out.println("❌ Error while adding clue: " + e.getMessage());
		}
	}

	public void addDecorationItemToRoom() {
		try {
			System.out.print("Enter the ID of the room to add a decoration item to: ");
			while (!scanner.hasNextInt()) {
				System.out.print("Please enter a valid numeric ID: ");
				scanner.next();
			}
			int roomId = scanner.nextInt();
			scanner.nextLine();

			Room room = inventoryService.getRoomById(roomId);
			if (room == null) {
				System.out.println("❌ Room not found with ID: " + roomId);
				return;
			}

			System.out.print("Enter the name of the decoration item: ");
			String name = scanner.nextLine().trim();

			System.out.print("Enter the material (WOOD, METAL, PLASTIC, PAPER, GLASS, FABRIC, STONE, ELECTRONIC): ");
			String materialInput = scanner.nextLine().trim().toUpperCase();

			Material material;
			try {
				material = Material.valueOf(materialInput);
			} catch (IllegalArgumentException e) {
				System.out.println("❌ Invalid material type.");
				return;
			}

			Difficulty difficulty = room.getDifficulty();
			AbstractFactory factory = FactoryProducer.getFactory(difficulty);

			DecorationItem item = factory.createDecorationItem(name, material);

			room.getDecorationItems().add(item);

			inventoryService.updateRoom(room);

			System.out.println("✅ Decoration item added to room '" + room.getName() + "' successfully.");
		} catch (DAOException e) {
			System.out.println("❌ Error while adding decoration item: " + e.getMessage());
		}
	}

}

