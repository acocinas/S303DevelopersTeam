package com.service;

import com.dao.exception.DAOException;
import com.enums.Difficulty;
import com.enums.Material;

import java.util.Map;
import java.util.Scanner;

public class InventoryContentService {
	private final InventoryService inventoryService;
	private final Scanner scanner;

	public InventoryContentService(InventoryService inventoryService, Scanner scanner) {
		this.inventoryService = inventoryService;
		this.scanner = scanner;
	}

	public void showInventorySummary() {
		try {
			System.out.println("\n--- INVENTORY SUMMARY ---");

			System.out.println("\nRooms by Difficulty:");
			Map<Difficulty, Long> rooms = inventoryService.getRoomCountByDifficulty();

			if (rooms.isEmpty()) {
				System.out.println("  No rooms available.");
			} else {
				rooms.forEach((difficulty, count) ->
						System.out.println("  " + difficulty + ": " + count));
			}

			System.out.println("\nClues by Theme:");
			Map<String, Long> clues = inventoryService.getClueCountByTheme();

			if (clues.isEmpty()) {
				System.out.println("  No clues available.");
			} else {
				clues.forEach((theme, count) ->
						System.out.println("  " + theme + ": " + count));
			}

			System.out.println("\nDecorations by Material:");
			Map<Material, Long> decorations = inventoryService.getDecorationCountByMaterial();
			if (decorations.isEmpty()) {
				System.out.println("  No decoration items available.");
			} else {
				decorations.forEach((material, count) ->
						System.out.println("  " + material + ": " + count));
			}

			System.out.println("\n✅ Inventory summary displayed successfully.");

		} catch (DAOException e) {
			System.out.println("❌ Failed to retrieve inventory summary: " + e.getMessage());
		}
	}

	public void showInventoryValue() {
		try {
			System.out.println("\n--- INVENTORY VALUE ---");
			double totalValue = inventoryService.calculateTotalInventoryValue();
			System.out.printf("💰 Total inventory value: €%.2f%n", totalValue);
		} catch (DAOException e) {
			System.out.println("❌ Failed to calculate inventory value: " + e.getMessage());
		}
	}

	public void removeRoom() {
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

	public void removeClue() {
		try {
			System.out.print("Enter the ID of the clue to remove: ");
			while (!scanner.hasNextInt()) {
				System.out.print("Please enter a valid numeric ID: ");
				scanner.next();
			}
			int clueId = scanner.nextInt();
			scanner.nextLine();

			inventoryService.removeClueFromInventory(clueId);
			System.out.println("✅ Clue with ID " + clueId + " was successfully removed.");
		} catch (DAOException e) {
			System.out.println("❌ Could not remove clue: " + e.getMessage());
		}
	}

	public void removeDecoration() {
		try {
			System.out.print("Enter the ID of the decoration item to remove: ");
			while (!scanner.hasNextInt()) {
				System.out.print("Please enter a valid numeric ID: ");
				scanner.next();
			}
			int decorationId = scanner.nextInt();
			scanner.nextLine();

			inventoryService.removeDecorationFromInventory(decorationId);
			System.out.println("✅ Decoration item with ID " + decorationId + " was successfully removed.");
		} catch (DAOException e) {
			System.out.println("❌ Could not remove decoration item: " + e.getMessage());
		}
	}
}

