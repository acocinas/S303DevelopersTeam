package com.menuFunctions;

import com.repository.InventoryRepository;

import java.util.Scanner;

public class RoomManager {
	private final InventoryRepository inventoryRepository;
	private final Scanner scanner;

	public RoomManager(InventoryRepository inventoryRepository, Scanner scanner) {
		this.inventoryRepository = inventoryRepository;
		this.scanner = scanner;
	}

	private void manageRooms() {
		int option;
		do {
			System.out.println("\n--- ROOM MANAGEMENT ---");
			System.out.println("1. Create new room");
			System.out.println("2. Remove room by ID");
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
					createRoom();
					break;
				case 2:
					removeRoom();
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

