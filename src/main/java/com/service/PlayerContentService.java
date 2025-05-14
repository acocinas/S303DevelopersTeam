package com.service;

import com.dao.exception.DAOException;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Scanner;
import java.util.function.BiPredicate;

import static com.util.LogMessages.*;

@Slf4j
public class PlayerContentService {
	private final EscapeRoomService escapeRoomService;
	private final Scanner scanner;

	public PlayerContentService(EscapeRoomService escapeRoomService, Scanner scanner) {
		this.escapeRoomService = escapeRoomService;
		this.scanner = scanner;
	}

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

	private void processPlayerActivity(String itemType, String successMessage,
									   BiPredicate<Integer, Integer> processor) {
		boolean success = false;

		while (!success) {
			int playerId = getValidIntInput("player");
			int roomId = getValidIntInput("room");

			try {
				if (processor.test(playerId, roomId)) {
					log.info(successMessage, playerId, roomId);
					success = true;
				}
			} catch (DAOException e) {
				String message = e.getMessage().toLowerCase();

				if (message.contains("player not found")) {
					log.warn("Player ID {} not found. Please enter a valid player ID.", playerId);
				} else if (message.contains("room not found")) {
					log.warn(" Room ID {} not found. Please enter a valid room ID.", roomId);
				} else {
					log.error(PROCESSING_ERROR, itemType, e.getMessage(), e);
					break; // solo salimos del bucle en errores graves
				}
			}
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
