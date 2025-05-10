package com.service;

import java.util.Map;
import java.util.stream.Collectors;

import com.interfaces.ClueDAO;
import com.interfaces.DecorationItemDAO;
import com.interfaces.RoomDAO;
import com.dao.exception.DAOException;
import com.dao.DAOManager;
import com.enums.Difficulty;
import com.enums.Material;
import com.model.Clue;
import com.model.DecorationItem;
import com.model.Room;

import lombok.extern.slf4j.Slf4j;

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
    
    public void removeRoomFromInventory(Integer roomId) throws DAOException {
        log.info("Removing room from inventory: {}", roomId);
        roomDAO.deleteById(roomId);
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
