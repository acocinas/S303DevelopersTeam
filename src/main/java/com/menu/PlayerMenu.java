package com.menu;

import com.service.PlayerContentService;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class PlayerMenu {
	private final PlayerContentService playerContentService;
	private final Scanner scanner;

	public PlayerMenu(PlayerContentService playerContentService, Scanner scanner) {
		this.playerContentService = playerContentService;
		this.scanner = scanner;
	}

	public void managePlayers() {
		int option;
		do {
			log.info("\n--- PLAYER & CERTIFICATE MANAGEMENT ---");
			log.info("1. Sell ticket to player");
			log.info("2. Issue completion certificate");
			log.info("0. Back to main menu");
			log.info("Select an option: ");

			while (!scanner.hasNextInt()) {
				log.warn("Invalid input detected");
				log.info("Please enter a valid number:");
				scanner.next();
			}

			option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
				case 1:
					playerContentService.sellTicketToPlayer();
					break;
				case 2:
					playerContentService.issueCertificateToPlayer();
					break;
				case 0:
					log.info("Returning to main menu...");
					break;
				default:
					log.warn("Invalid option selected.");
			}
		} while (option != 0);
	}
}
