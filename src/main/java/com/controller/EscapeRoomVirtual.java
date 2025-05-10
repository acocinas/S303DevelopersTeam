package com.controller;

import com.menu.InventoryMenu;
import com.menu.RoomMenu;
import com.observer.EventManager;
import com.service.EscapeRoomService;
import com.service.InventoryService;

import java.util.ArrayList;
import java.util.Scanner;

public class EscapeRoomVirtual {
	private final EscapeRoomService escapeRoomRepository;
	private final InventoryService inventoryRepository;
	private final EventManager eventManager;
	private final Scanner scanner;
	private final RoomMenu roomMenu;
	private final InventoryMenu inventoryMenu;
	private String name;

	public EscapeRoomVirtual() {
		this.escapeRoomRepository = new EscapeRoomService();
		this.inventoryRepository = new InventoryService();
		this.eventManager = new EventManager(new ArrayList<>());
		this.scanner = new Scanner(System.in);

		this.roomMenu = new RoomMenu(inventoryRepository, scanner);
		this.inventoryMenu = new InventoryMenu(new com.service.InventoryContentService(inventoryRepository, scanner), scanner);
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
				case 2:
					roomMenu.manageRoom();
					break;
				case 3:
					inventoryMenu.manageInventory();
					break;
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

