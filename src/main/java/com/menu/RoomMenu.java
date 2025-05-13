package com.menu;

import com.service.InventoryService;
import com.service.RoomContentService;
import com.service.RoomService;

import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RoomMenu {
	private final Scanner scanner;
	private final RoomService roomService;
	private final RoomContentService roomContentService;


	public RoomMenu(InventoryService inventoryService, Scanner scanner) {
		this.scanner = scanner;
		this.roomService = new RoomService(inventoryService, scanner);
		this.roomContentService = new RoomContentService(inventoryService, scanner);
	}

	public void manageRoom() {
		int option;
		do {
			log.info("\n--- ROOM MANAGEMENT ---");
			log.info("1. Create new room");
			log.info("2. Remove room by ID");
			log.info("3. Add thematic clue to a room");
			log.info("4. Add decoration item to a room");
			log.info("5. Add puzzle to a room");
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
					roomService.createRoom();
					break;
				case 2:
					roomService.removeRoomById();
					break;
				case 3:
					roomContentService.addClueToRoom();
					break;
				case 4:
					roomContentService.addDecorationItemToRoom();
					break;
				case 5:
					roomContentService.addPuzzleToRoom();
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

