package com.service;

import com.dao.exception.DAOException;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Scanner;

@Slf4j
public class PlayerContentService {
	private final EscapeRoomService escapeRoomService;
	private final Scanner scanner;

	public PlayerContentService(EscapeRoomService escapeRoomService, Scanner scanner) {
		this.escapeRoomService = escapeRoomService;
		this.scanner = scanner;
	}

	public void sellTicketToPlayer() {
		try {
			log.info("Enter the ID of the player:");
			while (!scanner.hasNextInt()) {
				log.warn("Invalid input detected");
				log.info("Please enter a valid numeric ID:");
				scanner.next();
			}
			int playerId = scanner.nextInt();
			scanner.nextLine();

			log.info("Enter the ID of the room:");
			while (!scanner.hasNextInt()) {
				log.warn("Invalid input detected");
				log.info("Please enter a valid numeric ID:");
				scanner.next();
			}
			int roomId = scanner.nextInt();
			scanner.nextLine();

			Date date = new Date();

			escapeRoomService.sellTicket(playerId, roomId, date);

			log.info("✅ Ticket successfully sold to player with ID {} for room ID {}", playerId, roomId);

		} catch (DAOException e) {
			log.error("❌ Failed to sell ticket: {}", e.getMessage(), e);
		}
	}

	public void issueCertificateToPlayer() {
		try {
			log.info("Enter the ID of the player:");
			while (!scanner.hasNextInt()) {
				log.warn("Invalid input detected");
				log.info("Please enter a valid numeric ID:");
				scanner.next();
			}
			int playerId = scanner.nextInt();
			scanner.nextLine();

			log.info("Enter the ID of the completed room:");
			while (!scanner.hasNextInt()) {
				log.warn("Invalid input detected");
				log.info("Please enter a valid numeric ID:");
				scanner.next();
			}
			int roomId = scanner.nextInt();
			scanner.nextLine();

			Date completionDate = new Date();

			escapeRoomService.issueCertificate(playerId, roomId, completionDate);

			log.info("✅ Certificate issued to player {} for room {}", playerId, roomId);

		} catch (DAOException e) {
			log.error("❌ Failed to issue certificate: {}", e.getMessage(), e);
		}
	}
}
