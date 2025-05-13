package com.service;

import com.dao.exception.DAOException;
import com.enums.Difficulty;
import com.enums.Material;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Scanner;
import java.util.function.IntConsumer;

import lombok.extern.slf4j.Slf4j;

import static com.utils.LogMessages.*;

@Slf4j
public class InventoryContentService {
	private final InventoryService inventoryService;
	private final Scanner scanner;

	private static final String ITEM_COUNT_FORMAT = "  {}: {}";
	private static final DecimalFormat CURRENCY_FORMAT = new DecimalFormat("#,##0.00");

	public InventoryContentService(InventoryService inventoryService, Scanner scanner) {
		this.inventoryService = inventoryService;
		this.scanner = scanner;
	}

	/**
	 * Helper method to read and validate integer input from user
	 * @param entityType Type of entity to prompt for
	 * @return The valid integer input
	 */
	private int getValidIntInput(String entityType) {
		log.info(ENTER_ENTITY_ID_TO_REMOVE, entityType);
		while (!scanner.hasNextInt()) {
			log.warn(INVALID_INPUT);
			log.info(ENTER_VALID_ID);
			scanner.next();
		}
		int id = scanner.nextInt();
		scanner.nextLine(); // consume newline
		return id;
	}

	/**
	 * Generic method to handle removal of any inventory item
	 * @param itemType Type of item being removed (for logging)
	 * @param removalOperation IntConsumer that performs the removal operation
	 */
	private void processItemRemoval(String itemType, IntConsumer removalOperation) {
		try {
			int itemId = getValidIntInput(itemType);
			removalOperation.accept(itemId);
			log.info(ITEM_REMOVED, itemType, itemId);
		} catch (DAOException e) {
			log.error(REMOVAL_ERROR, itemType.toLowerCase(), e.getMessage(), e);
		}
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
			log.info("Total inventory value: €{}", CURRENCY_FORMAT.format(totalValue));
		} catch (DAOException e) {
			log.error("Failed to calculate inventory value: {}", e.getMessage(), e);
		}
	}

	public void removeRoom() {
		processItemRemoval("Room", inventoryService::removeRoomFromInventory);
	}

	public void removeClue() {
		processItemRemoval("Clue", inventoryService::removeClueFromInventory);
	}

	public void removeDecoration() {
		processItemRemoval("Decoration item", inventoryService::removeDecorationFromInventory);
	}
}

