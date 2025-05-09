package com.controller;

import com.repository.EscapeRoomRepository;
import com.repository.InventoryRepository;
import com.utils.EventManager;

import java.util.ArrayList;
import java.util.Scanner;

public class EscapeRoomVirtual {
	private String name;
	private final EscapeRoomRepository escapeRoomRepository;
	private final InventoryRepository inventoryRepository;
	private final EventManager eventManager;
	private final Scanner scanner;

	public EscapeRoomVirtual() {
		this.escapeRoomRepository = new EscapeRoomRepository();
		this.inventoryRepository = new InventoryRepository();
		this.eventManager = new EventManager(new ArrayList<>());
		this.scanner = new Scanner(System.in);
	}

	public void start() {
		int option;
		do {
			System.out.println("\n--- ESCAPE ROOM VIRTUAL MAIN MENU ---");
			System.out.println("1. Create Escape Room");
			System.out.println("2. Manage Rooms");
			System.out.println("3. Manage Inventory");
			System.out.println("4. Manage Players & Certificates");
			System.out.println("5. View Revenue");
			System.out.println("6. Notifications");
			System.out.println("0. Exit");
			System.out.print("Select an option: ");

			while (!scanner.hasNextInt()) {
				System.out.print("Please enter a valid number: ");
				scanner.next();
			}

			option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
				case 1:
					createEscapeRoom();
					break;
				// case 2: manageRooms();
				// break;
				// case 3: manageInventory();
				// break;
				// case 4: managePlayers();
				// break;
				// case 5: viewRevenue();
				// break;
				// case 6: handleNotifications();
				// break;
				case 0:
					System.out.println("Exiting Escape Room Virtual system...");
					break;
				default:
					System.out.println("Invalid option. Please try again.");
			}
		} while (option != 0);
	}

	private void createEscapeRoom() {
		System.out.print("Enter the name of the new Escape Room: ");
		this.name = scanner.nextLine().trim();

		if (this.name.isEmpty()) {
			System.out.println("Escape Room name cannot be empty.");
			return;
		}

		System.out.println("✅ Escape Room created with name: \"" + name + "\"");
	}
}

