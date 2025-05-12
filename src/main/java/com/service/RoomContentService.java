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

import static com.utils.LogMessages.*;

@Slf4j
public class RoomContentService {
	private final InventoryService inventoryService;
	private final Scanner scanner;

	public RoomContentService(InventoryService inventoryService, Scanner scanner) {
		this.inventoryService = inventoryService;
		this.scanner = scanner;
	}

	public void addClueToRoom() {
		try {
			log.info(ENTER_ROOM_ID, "clue");
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
				return;
			}

			log.info("Enter the clue description:");
			String description = scanner.nextLine().trim();

			log.info("Enter the clue theme:");
			String theme = scanner.nextLine().trim();

			Difficulty difficulty = room.getDifficulty();
			AbstractFactory factory = FactoryProducer.getFactory(difficulty);

			Clue clue = factory.createClue(description, theme);

			room.getClues().add(clue);
			inventoryService.updateRoom(room);

			log.info(ITEM_ADDED, "Clue", room.getName());

		} catch (DAOException e) {
			log.error(ADDING_CLUE_ERROR, e.getMessage(), e);
		}
	}

	public void addDecorationItemToRoom() {
		try {
			log.info(ENTER_ROOM_ID, "decoration item");
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
				return;
			}

			log.info("Enter the name of the decoration item:");
			String name = scanner.nextLine().trim();

			log.info("Enter the material (WOOD, METAL, PLASTIC, PAPER, GLASS, FABRIC, STONE, ELECTRONIC):");
			String materialInput = scanner.nextLine().trim().toUpperCase();

			Optional<Material> materialOptional = parseMaterial(materialInput);
			if (materialOptional.isEmpty()) {
				return;
			}
			Material material = materialOptional.get();

			Difficulty difficulty = room.getDifficulty();
			AbstractFactory factory = FactoryProducer.getFactory(difficulty);

			DecorationItem item = factory.createDecorationItem(name, material);
			room.getDecorationItems().add(item);

			inventoryService.updateRoom(room);

			log.info(ITEM_ADDED, "Decoration item", room.getName());

		} catch (DAOException e) {
			log.error(ADDING_DECORATION_ERROR, e.getMessage(), e);
		}
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
		try {
			log.info(ENTER_ROOM_ID, "puzzle");
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
				return;
			}

			log.info("Enter the puzzle description:");
			String description = scanner.nextLine().trim();

			log.info("Enter the puzzle solution:");
			String solution = scanner.nextLine().trim();

			Puzzle puzzle = Puzzle.builder()
					.description(description)
					.solution(solution)
					.build();

			room.addPuzzle(puzzle);
			inventoryService.updateRoom(room);

			log.info(ITEM_ADDED, "Puzzle", room.getName());

		} catch (DAOException e) {
			log.error(ADDING_PUZZLE_ERROR, e.getMessage(), e);
		}
	}
}
