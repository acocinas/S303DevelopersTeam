package com.menu;

import com.service.InventoryContentService;

import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
			log.info("\n--- INVENTORY MANAGEMENT ---");
			log.info("1. Show inventory summary");
			log.info("2. Show total inventory value");
			log.info("3. Remove room");
			log.info("4. Remove clue");
			log.info("5. Remove decoration item");
			log.info("0. Back to main menu");
			log.info("Select an option: ");

			while (!scanner.hasNextInt()) {
				log.warn("Invalid input detected");
				log.info("Please enter a valid number: ");
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
					log.info("Returning to main menu...");
					break;
				default:
					log.warn("Invalid option selected: {}", option);
					log.info("Invalid option.");
			}
		} while (option != 0);
	}
}
