package com.service;

import com.dao.exception.DAOException;
import com.enums.Difficulty;
import com.enums.Material;

import java.util.Map;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InventoryContentService {
	private final InventoryService inventoryService;
	private final Scanner scanner;

	private static final String ITEM_COUNT_FORMAT = "  {}: {}";
	private static final String INVALID_INPUT = "Invalid input detected";
	private static final String ENTER_VALID_ID = "Please enter a valid numeric ID: ";

	public InventoryContentService(InventoryService inventoryService, Scanner scanner) {
		this.inventoryService = inventoryService;
		this.scanner = scanner;
	}

	public void showInventorySummary() {
		try {
			log.info("\n--- INVENTORY SUMMARY ---");

			log.info("\nRooms by Difficulty:");
			Map<Difficulty, Long> rooms = inventoryService.getRoomCountByDifficulty();

			if (rooms.isEmpty()) {
				log.info("  No rooms available.");
			} else {
				rooms.forEach((difficulty, count) ->
						log.info(ITEM_COUNT_FORMAT, difficulty, count));
			}

			log.info("\nClues by Theme:");
			Map<String, Long> clues = inventoryService.getClueCountByTheme();

			if (clues.isEmpty()) {
				log.info("  No clues available.");
			} else {
				clues.forEach((theme, count) ->
						log.info(ITEM_COUNT_FORMAT, theme, count));
			}

			log.info("\nDecorations by Material:");
			Map<Material, Long> decorations = inventoryService.getDecorationCountByMaterial();
			if (decorations.isEmpty()) {
				log.info("  No decoration items available.");
			} else {
				decorations.forEach((material, count) ->
						log.info(ITEM_COUNT_FORMAT, material, count));
			}

			log.info("\nInventory summary displayed successfully.");

		} catch (DAOException e) {
			log.error("Failed to retrieve inventory summary: {}", e.getMessage(), e);
		}
	}

	public void showInventoryValue() {
		try {
			log.info("\n--- INVENTORY VALUE ---");
			double totalValue = inventoryService.calculateTotalInventoryValue();
			log.info("Total inventory value: €{:.2f}", totalValue);
		} catch (DAOException e) {
			log.error("Failed to calculate inventory value: {}", e.getMessage(), e);
		}
	}

	public void removeRoom() {
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
			log.info("Room with ID {} was successfully removed.", roomId);
		} catch (DAOException e) {
			log.error("Could not remove room: {}", e.getMessage(), e);
		}
	}

	public void removeClue() {
		try {
			log.info("Enter the ID of the clue to remove: ");
			while (!scanner.hasNextInt()) {
				log.warn(INVALID_INPUT);
				log.info(ENTER_VALID_ID);
				scanner.next();
			}
			int clueId = scanner.nextInt();
			scanner.nextLine();

			inventoryService.removeClueFromInventory(clueId);
			log.info("Clue with ID {} was successfully removed.", clueId);
		} catch (DAOException e) {
			log.error("Could not remove clue: {}", e.getMessage(), e);
		}
	}

	public void removeDecoration() {
		try {
			log.info("Enter the ID of the decoration item to remove: ");
			while (!scanner.hasNextInt()) {
				log.warn(INVALID_INPUT);
				log.info(ENTER_VALID_ID);
				scanner.next();
			}
			int decorationId = scanner.nextInt();
			scanner.nextLine();

			inventoryService.removeDecorationFromInventory(decorationId);
			log.info("Decoration item with ID {} was successfully removed.", decorationId);
		} catch (DAOException e) {
			log.error("Could not remove decoration item: {}", e.getMessage(), e);
		}
	}
}

