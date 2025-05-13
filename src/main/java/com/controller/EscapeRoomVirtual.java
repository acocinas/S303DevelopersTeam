package com.controller;

import com.dao.exception.DAOException;
import com.menu.InventoryMenu;
import com.menu.PlayerMenu;
import com.menu.RoomMenu;
import com.service.EscapeRoomService;
import com.service.InventoryService;
import com.service.PlayerContentService;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class EscapeRoomVirtual {
	private final InventoryService inventoryRepository;
	private final Scanner scanner;
	private final RoomMenu roomMenu;
	private final InventoryMenu inventoryMenu;
	private final EscapeRoomService escapeRoomService;
	private final PlayerMenu playerMenu;

	public EscapeRoomVirtual() {
		this.inventoryRepository = new InventoryService();
		this.scanner = new Scanner(System.in);

		this.roomMenu = new RoomMenu(inventoryRepository, scanner);
		this.inventoryMenu = new InventoryMenu(new com.service.InventoryContentService(inventoryRepository, scanner), scanner);
		this.escapeRoomService = new EscapeRoomService();
		this.playerMenu = new PlayerMenu(
				new PlayerContentService(escapeRoomService, scanner),
				scanner
		);
	}

	public void start() {
		int option;
		do {
			log.info("Displaying main menu");
			log.debug("Showing escape room virtual main menu options");

			log.info("\n--- ESCAPE ROOM VIRTUAL MAIN MENU ---");
			log.info("1. Create Escape Room");
			log.info("2. Manage Rooms");
			log.info("3. Manage Inventory");
			log.info("4. Manage Players & Certificates");
			log.info("5. View Revenue");
			log.info("6. Notifications");
			log.info("0. Exit");
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
					createEscapeRoom();
					break;
				case 2:
					roomMenu.manageRoom();
					break;
				case 3:
					inventoryMenu.manageInventory();
					break;
				case 4:
					playerMenu.managePlayers();
					break;
				case 5:
					viewRevenue();
					break;
				// case 6: handleNotifications();
				// break;
				case 0:
					log.info("Exiting Escape Room Virtual system...");
					break;
				default:
					log.warn("Invalid option selected: {}", option);
					log.info("Invalid option. Please try again.");
			}
		} while (option != 0);
	}

	private void createEscapeRoom() {
		log.info("Enter the name of the new Escape Room: ");
		String name = scanner.nextLine().trim();

		if (name.isEmpty()) {
			log.warn("Empty room name provided");
			log.info("Escape Room name cannot be empty.");
			return;
		}

		log.info("Escape Room created with name: \"{}\"", name);
	}

	private void viewRevenue() {
		try {
			double revenue = escapeRoomService.calculateTotalRevenue();
			log.info("Total revenue generated from ticket sales: €{}", String.format("%.2f", revenue));
		} catch (DAOException e) {
			log.error("Failed to calculate revenue: {}", e.getMessage(), e);
		}
	}

}

