package com.util;

public class LogMessages {
	// Input validation
	public static final String INVALID_INPUT = "Invalid input detected";
	public static final String ENTER_VALID_ID = "Please enter a valid numeric ID: ";
	public static final String ENTER_ENTITY_ID = "Enter the ID of the {}: ";
	public static final String ENTER_ENTITY_ID_TO_REMOVE = "Enter the ID of the {} to remove: ";

	// Room interactions
	public static final String ENTER_ROOM_ID = "Enter the ID of the room to add a {} to: ";
	public static final String ROOM_NOT_FOUND = "Room not found with ID: {}";
	public static final String ITEM_ADDED = "{} added to room '{}' successfully.";
	public static final String ITEM_REMOVED = "{} with ID {} was successfully removed.";

	// Error logging
	// Generic pattern for error messages
	public static final String ITEM_ERROR = "Error while processing {}: {}";
	public static final String REMOVAL_ERROR = "Could not remove {}: {}";

	//Tickets and certificates interactions
	public static final String TICKET_SOLD = "Ticket successfully sold to player with ID {} for room ID {}";
	public static final String CERTIFICATE_ISSUED = "Certificate issued to player {} for room {}";
	// Generic pattern for processing interactions
	public static final String PROCESSING_ERROR = "Failed to process {}: {}";

	// Material parsing
	public static final String INVALID_MATERIAL = "Invalid material type: {}";

	private LogMessages() {

	}
}
