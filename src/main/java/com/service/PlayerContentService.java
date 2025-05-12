package com.service;

import com.dao.exception.DAOException;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Scanner;
import java.util.function.BiPredicate;

import static com.utils.LogMessages.*;

@Slf4j
public class PlayerContentService {
	private final EscapeRoomService escapeRoomService;
	private final Scanner scanner;

	public PlayerContentService(EscapeRoomService escapeRoomService, Scanner scanner) {
		this.escapeRoomService = escapeRoomService;
		this.scanner = scanner;
	}

	/**
	 * Helper method to read and validate integer input from user
	 * @param entityType Type of entity to prompt for (e.g., "player", "room")
	 * @return The valid integer input
	 */
	private int getValidIntInput(String entityType) {
		log.info(ENTER_ENTITY_ID, entityType);
		while (!scanner.hasNextInt()) {
			log.warn(INVALID_INPUT);
			log.info(ENTER_VALID_ID);
			scanner.next();
		}
		int id = scanner.nextInt();
		scanner.nextLine(); // consume newline
		return id;
	}

	/**
	 * Generic method to handle player activities involving rooms
	 * @param itemType Type of activity (for logging)
	 * @param successMessage Log message format for successful operation
	 * @param processor Predicate that processes the player and room IDs
	 */
	private void processPlayerActivity(String itemType, String successMessage, 
	                                 BiPredicate<Integer, Integer> processor) {
		try {
			int playerId = getValidIntInput("player");
			int roomId = getValidIntInput("room");

			if (processor.test(playerId, roomId)) {
				log.info(successMessage, playerId, roomId);
			}
		} catch (DAOException e) {
			log.error(PROCESSING_ERROR, itemType, e.getMessage(), e);
		}
	}

	public void sellTicketToPlayer() {
		processPlayerActivity("ticket", TICKET_SOLD, 
			(playerId, roomId) -> {
				Date date = new Date();
				escapeRoomService.sellTicket(playerId, roomId, date);
				return true;
			}
		);
	}

	public void issueCertificateToPlayer() {
		processPlayerActivity("certificate", CERTIFICATE_ISSUED,
			(playerId, roomId) -> {
				Date completionDate = new Date();
				escapeRoomService.issueCertificate(playerId, roomId, completionDate);
				return true;
			}
		);
	}
}
