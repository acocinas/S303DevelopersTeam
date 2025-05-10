package com.menu;

import com.service.InventoryContentService;

import java.util.Scanner;

public class InventoryMenu {
	private final InventoryContentService inventoryContentService;
	private final Scanner scanner;

	public InventoryMenu(InventoryContentService inventoryContentService, Scanner scanner) {
		this.inventoryContentService = inventoryContentService;
		this.scanner = scanner;
	}

	public void manageInventory() {
		int option;
		do {
			System.out.println("\n--- INVENTORY MANAGEMENT ---");
			System.out.println("1. Show inventory summary");
			System.out.println("2. Show total inventory value");
			System.out.println("3. Remove room");
			System.out.println("4. Remove clue");
			System.out.println("5. Remove decoration item");
			System.out.println("0. Back to main menu");
			System.out.print("Select an option: ");

			while (!scanner.hasNextInt()) {
				System.out.print("Please enter a valid number: ");
				scanner.next();
			}

			option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
				case 1:
					inventoryContentService.showInventorySummary();
					break;
				case 2:
					inventoryContentService.showInventoryValue();
					break;
				case 3:
					inventoryContentService.removeRoom();
					break;
				case 4:
					inventoryContentService.removeClue();
					break;
				case 5:
					inventoryContentService.removeDecoration();
					break;
				case 0:
					System.out.println("Returning to main menu...");
					break;
				default:
					System.out.println("Invalid option.");
			}
		} while (option != 0);
	}
}
