package com.service;

import com.dao.exception.DAOException;
import com.enums.Difficulty;
import com.enums.Material;
import com.factory.FactoryProducer;
import com.interfaces.AbstractFactory;
import com.model.Clue;
import com.model.DecorationItem;
import com.model.Puzzle;
import com.model.Room;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.Scanner;
import java.util.function.Predicate;

import static com.utils.LogMessages.*;

@Slf4j
public class RoomContentService {
	private final InventoryService inventoryService;
	private final Scanner scanner;

	public RoomContentService(InventoryService inventoryService, Scanner scanner) {
		this.inventoryService = inventoryService;
		this.scanner = scanner;
	}

	/**
	 * Helper method to get valid room ID and retrieve the room
	 *
	 * @param itemType The type of item being added (for logging)
	 * @return The room if found, null otherwise
	 */
	private Room getValidRoom(String itemType) {
		log.info(ENTER_ROOM_ID, itemType);

		while (!scanner.hasNextInt()) {
			log.warn(INVALID_INPUT);
			log.info(ENTER_VALID_ID);
			scanner.next();
		}
		int roomId = scanner.nextInt();
		scanner.nextLine();

		Room room = inventoryService.getRoomById(roomId);
		if (room == null) {
			log.warn(ROOM_NOT_FOUND, roomId);
		}
		return room;
	}

	/**
	 * Generic method to handle adding any item to a room
	 *
	 * @param itemType    The type of item being added (for logging)
	 * @param itemCreator Predicate that creates and adds the item to the room
	 */
	private void processRoomItem(String itemType, Predicate<Room> itemCreator) {
		try {
			Room room = getValidRoom(itemType);
			if (room == null) {
				return;
			}

			// Execute the specific item creation logic
			if (itemCreator.test(room)) {
				inventoryService.updateRoom(room);
				log.info(ITEM_ADDED, itemType, room.getName());
			}
		} catch (DAOException e) {
			log.error(ITEM_ERROR, itemType, e.getMessage(), e);
		}
	}

	public void addClueToRoom() {
		processRoomItem("Clue", room -> {
			log.info("Enter the clue description:");
			String description = scanner.nextLine().trim();

			log.info("Enter the clue theme:");
			String theme = scanner.nextLine().trim();

			Difficulty difficulty = room.getDifficulty();
			AbstractFactory factory = FactoryProducer.getFactory(difficulty);

			Clue clue = factory.createClue(description, theme);
			room.getClues().add(clue);
			inventoryService.addClue(clue);
			return true;
		});
	}

	public void addDecorationItemToRoom() {
		processRoomItem("Decoration item", room -> {
			double price;
			log.info("Enter the name of the decoration item:");
			String name = scanner.nextLine().trim();

			log.info("Enter the material (WOOD, METAL, PLASTIC, PAPER, GLASS, FABRIC, STONE, ELECTRONIC):");
			String materialInput = scanner.nextLine().trim().toUpperCase();

			Optional<Material> materialOptional = parseMaterial(materialInput);
			if (materialOptional.isEmpty()) {
				return false;
			}
			Material material = materialOptional.get();

			Difficulty difficulty = room.getDifficulty();
			AbstractFactory factory = FactoryProducer.getFactory(difficulty);

			DecorationItem item = factory.createDecorationItem(name, material);

			do {
				log.info("Enter the price for this decoration item (e.g. 12.50):");
				while (!scanner.hasNextDouble()) {
					log.warn("Invalid input detected");
					log.info("Please enter a valid decimal number for the price:");
					scanner.next();
				}
				price = scanner.nextDouble();
				scanner.nextLine();
				if (price < 0) {
					log.warn("Price cannot be negative.");
				}
			} while (price < 0);

			item.setPrice(price);
			room.getDecorationItems().add(item);
			inventoryService.addDecoration(item);
			return true;
		});
	}

	private Optional<Material> parseMaterial(String materialInput) {
		try {
			return Optional.of(Material.valueOf(materialInput));
		} catch (IllegalArgumentException e) {
			log.warn(INVALID_MATERIAL, materialInput);
			return Optional.empty();
		}
	}

	public void addPuzzleToRoom() {
		processRoomItem("Puzzle", room -> {
			log.info("Enter the puzzle description:");
			String description = scanner.nextLine().trim();

			log.info("Enter the puzzle solution:");
			String solution = scanner.nextLine().trim();

			Puzzle puzzle = Puzzle.builder()
					.description(description)
					.solution(solution)
					.build();

			room.addPuzzle(puzzle);
			return true;
		});
	}
}
