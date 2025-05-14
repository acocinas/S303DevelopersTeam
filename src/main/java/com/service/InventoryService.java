package com.service;

import com.dao.DAOManager;
import com.dao.exception.DAOException;
import com.enums.Difficulty;
import com.enums.Material;
import com.interfaces.ClueDAO;
import com.interfaces.DecorationItemDAO;
import com.interfaces.RoomDAO;
import com.model.Clue;
import com.model.DecorationItem;
import com.model.Room;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class InventoryService {
	private final RoomDAO roomDAO;
	private final ClueDAO clueDAO;
	private final DecorationItemDAO decorationItemDAO;

	public InventoryService() {
		roomDAO = DAOManager.getDAOFactory().getRoomDAO();
		clueDAO = DAOManager.getDAOFactory().getClueDAO();
		decorationItemDAO = DAOManager.getDAOFactory().getDecorationItemDAO();
	}

	public double calculateTotalInventoryValue() throws DAOException {
		double roomValue = roomDAO.calculateTotalRoomValue();
		double decorationValue = decorationItemDAO.calculateTotalDecorationValue();

		double total = roomValue + decorationValue;
		log.info("Total inventory value calculated: {}", total);
		return total;
	}

	public Map<Difficulty, Long> getRoomCountByDifficulty() throws DAOException {
		log.debug("Getting room count by difficulty");
		return roomDAO.findAll().stream()
				.collect(Collectors.groupingBy(
						Room::getDifficulty,
						Collectors.counting()));
	}

	public Map<Material, Long> getDecorationCountByMaterial() throws DAOException {
		log.debug("Getting decoration count by material");
		return decorationItemDAO.findAll().stream()
				.collect(Collectors.groupingBy(
						DecorationItem::getMaterial,
						Collectors.counting()
				));
	}

	public Map<String, Long> getClueCountByTheme() throws DAOException {
		log.debug("Getting clue count by theme");
		return clueDAO.findAll().stream()
				.collect(Collectors.groupingBy(Clue::getTheme, Collectors.counting()));
	}

	public void addClue(Clue clue) throws DAOException {
		log.info("Adding clue to inventory: {}", clue.getDescription());
		clueDAO.create(clue);
	}

	public void addDecoration(DecorationItem item) throws DAOException {
		log.info("Adding decoration to inventory: {}", item.getName());
		decorationItemDAO.create(item);
	}

	public boolean removeRoomFromInventory(Integer roomId) {
		log.info("Removing room from inventory: {}", roomId);
		try {
			roomDAO.deleteById(roomId);
			log.info("Room with ID {} was successfully removed from inventory.", roomId);
			return true;
		} catch (DAOException e) {
			String causeMsg = e.getCause() != null ? e.getCause().getMessage().toLowerCase() : "";

			if (causeMsg.contains("no room found")) {
				log.warn("No room exists with ID {}. Please try with a valid one.", roomId);
			} else {
				log.error("Unexpected error while removing room: {}", e.getMessage(), e);
			}
			return false;
		}
	}


	public void removeClueFromInventory(Integer clueId) throws DAOException {
		log.info("Removing clue from inventory: {}", clueId);
		clueDAO.deleteById(clueId);
	}

	public void removeDecorationFromInventory(Integer decorationId) throws DAOException {
		log.info("Removing decoration from inventory: {}", decorationId);
		decorationItemDAO.deleteById(decorationId);
	}

	public void addRoom(Room room) throws DAOException {
		log.info("Adding room to inventory: {}", room.getName());
		roomDAO.create(room);
	}

	public Room getRoomById(int id) throws DAOException {
		return roomDAO.findById(id).orElse(null);
	}

	public void updateRoom(Room room) throws DAOException {
		log.info("Updating room with ID: {}", room.getId());
		roomDAO.update(room);
	}
}
