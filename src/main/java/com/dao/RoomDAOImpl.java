package com.dao;

import java.util.List;
import java.util.stream.Collectors;

import com.interfaces.RoomDAO;
import com.dao.exception.DAOException;
import com.enums.Difficulty;
import com.model.Room;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RoomDAOImpl extends BaseDAO<Room, Integer> implements RoomDAO {
    
    @Override
    public Room create(Room entity) throws DAOException {
        Integer id = idSequence.getAndIncrement();
        setEntityId(entity, id);
        entities.put(id, entity);
        log.info("Created room with id: {}", id);
        return entity;
    }

    @Override
    public Room update(Room entity) throws DAOException {
        Integer id = getEntityId(entity);
        if (id != null && entities.containsKey(id)) {
            entities.put(id, entity);
            log.info("Updated room with id: {}", id);
            return entity;
        }
        log.error("Room not found for update: {}", id);
        throw new DAOException("Room not found for update: " + id);
    }

    @Override
    public List<Room> findByDifficulty(Difficulty difficulty) throws DAOException {
        log.debug("Finding rooms by difficulty: {}", difficulty);
        return entities.values().stream()
                .filter(room -> room.getDifficulty().equals(difficulty))
                .collect(Collectors.toList());
    }

    @Override
    public List<Room> findByTheme(String theme) throws DAOException {
        log.debug("Finding rooms by theme: {}", theme);
        return entities.values().stream()
                .filter(room -> theme.equalsIgnoreCase(room.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Room> findRoomsWithPriceRange(double minPrice, double maxPrice) throws DAOException {
        log.debug("Finding rooms with price range: {} - {}", minPrice, maxPrice);
        return entities.values().stream()
                .filter(room -> room.getPrice() >= minPrice && room.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    @Override
    public double calculateTotalRoomValue() throws DAOException {
        double total = entities.values().stream()
                .mapToDouble(Room::getPrice)
                .sum();
        log.debug("Calculated total room value: {}", total);
        return total;
    }

    @Override
    public void addClueToRoom(Integer roomId, Integer clueId) throws DAOException {
        log.info("Adding clue {} to room {}", clueId, roomId);
        // Implementation would depend on how Rooms and Clues are linked
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void addDecorationToRoom(Integer roomId, Integer decorationId) throws DAOException {
        log.info("Adding decoration {} to room {}", decorationId, roomId);
        // Implementation would depend on how Rooms and Decorations are linked
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    protected Integer getEntityId(Room entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(Room entity, Integer id) {
        if (id != null) {
            Room newRoom = new Room(
                id, 
                entity.getName(),
                entity.getDifficulty(),
                entity.getPrice()
            );
            
            entity.getPuzzles().forEach(newRoom::addPuzzle);
            entity.getClues().forEach(newRoom::addClue);
            entity.getDecorationItems().forEach(newRoom::addDecorationItem);
            
            entities.put(id, newRoom);
        }
    }
}
