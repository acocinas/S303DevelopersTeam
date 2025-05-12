package com.utils;

public class LogMessages {
	// Input validation
	public static final String INVALID_INPUT = "Invalid input detected";
	public static final String ENTER_VALID_ID = "Please enter a valid numeric ID: ";

	// Room interactions
	public static final String ENTER_ROOM_ID = "Enter the ID of the room to add a {} to: ";
	public static final String ROOM_NOT_FOUND = "Room not found with ID: {}";
	public static final String ITEM_ADDED = "{} added to room '{}' successfully.";

	// Error logging
	public static final String ADDING_CLUE_ERROR = "Error while adding clue: {}";
	public static final String ADDING_DECORATION_ERROR = "Error while adding decoration item: {}";
	public static final String ADDING_PUZZLE_ERROR = "Error while adding puzzle: {}";

	//Tickets and certificates interactions
	public static final String TICKET_SOLD = "Ticket successfully sold to player with ID {} for room ID {}";
	public static final String CERTIFICATE_ISSUED = "Certificate issued to player {} for room {}";
	public static final String TICKET_SALE_ERROR = "Failed to sell ticket: {}";
	public static final String CERTIFICATE_ERROR = "Failed to issue certificate: {}";

	// Material parsing
	public static final String INVALID_MATERIAL = "Invalid material type: {}";

	private LogMessages() {

	}
}
