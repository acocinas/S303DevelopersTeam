package com.menu;

import com.service.InventoryService;
import com.service.RoomContentService;
import com.service.RoomService;

import java.util.Scanner;

public class RoomMenu {
	private final InventoryService inventoryService;
	private final Scanner scanner;
	private final RoomService roomService;
	private final RoomContentService roomContentService;


	public RoomMenu(InventoryService inventoryService, Scanner scanner) {
		this.inventoryService = inventoryService;
		this.scanner = scanner;
		this.roomService = new RoomService(inventoryService, scanner);
		this.roomContentService = new RoomContentService(inventoryService, scanner);
	}

	private void manageRooms() {
		int option;
		do {
			System.out.println("\n--- ROOM MANAGEMENT ---");
			System.out.println("1. Create new room");
			System.out.println("2. Remove room by ID");
			System.out.println("3. Add thematic clue to a room");
			System.out.println("4. Add decoration item to a room");
			System.out.println("5. Add puzzle to a room");
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
					System.out.println("Returning to main menu...");
					break;
				default:
					System.out.println("Invalid option.");
			}
		} while (option != 0);
	}
}

