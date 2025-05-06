package com.interfaces;

import java.util.List;
import com.dao.exception.DAOException;
import com.enums.Difficulty;
import com.model.Room;

public interface RoomDAO {
    List<Room> findByDifficulty(Difficulty difficulty) throws DAOException;
    List<Room> findByTheme(String theme) throws DAOException;
    List<Room> findRoomsWithPriceRange(double minPrice, double maxPrice) throws DAOException;
    double calculateTotalRoomValue() throws DAOException;
    void addClueToRoom(Integer roomId, Integer clueId) throws DAOException;
    void addDecorationToRoom(Integer roomId, Integer decorationId) throws DAOException;
}
