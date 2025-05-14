package com.dao;

import com.config.DatabaseConnectionManager;
import com.dao.exception.DAOException;
import com.enums.Difficulty;
import com.interfaces.RoomDAO;
import com.model.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomDAOImpl implements RoomDAO {

    private final DatabaseConnectionManager connectionManager;

    public RoomDAOImpl() {
        try {
            this.connectionManager = new DatabaseConnectionManager();
        } catch (Exception e) {
            throw new DAOException("Failed to initialize DB connection", e);
        }
    }

    @Override
    public Optional<Room> findById(Integer id) throws DAOException {
        String sql = "SELECT id, name, difficulty_id, price, theme FROM rooms WHERE id = ?";

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(mapResultSetToRoom(rs));
            }
            return Optional.empty();

        } catch (Exception e) {
            throw new DAOException("Error finding room by ID", e);
        }
    }

    @Override
    public Room create(Room room) throws DAOException {
        String sql = "INSERT INTO rooms (name, difficulty_id, price, theme) VALUES (?, ?, ?, ?)";

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, room.getName());
            stmt.setInt(2, room.getDifficulty().ordinal() + 1); // Asumiendo que el ID es 1 = EASY, 2 = MEDIUM...
            stmt.setDouble(3, room.getPrice());
            stmt.setString(4, room.getTheme());

            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();

            if (keys.next()) {
                room.setId(keys.getInt(1));
            }

            return room;

        } catch (Exception e) {
            throw new DAOException("Error creating room", e);
        }
    }

    @Override
    public Room update(Room room) throws DAOException {
        String sql = "UPDATE rooms SET name = ?, difficulty_id = ?, price = ?, theme = ? WHERE id = ?";

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, room.getName());
            stmt.setInt(2, room.getDifficulty().ordinal() + 1);
            stmt.setDouble(3, room.getPrice());
            stmt.setString(4, room.getTheme());
            stmt.setInt(5, room.getId());

            int affected = stmt.executeUpdate();
            if (affected == 0) {
                throw new DAOException("No room found with id: " + room.getId());
            }

            return room;

        } catch (Exception e) {
            throw new DAOException("Error updating room", e);
        }
    }

    @Override
    public List<Room> findByDifficulty(Difficulty difficulty) throws DAOException {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT id, name, difficulty_id, price, theme FROM rooms WHERE difficulty_id = ?";

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, difficulty.ordinal() + 1);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                rooms.add(mapResultSetToRoom(rs));
            }

            return rooms;

        } catch (Exception e) {
            throw new DAOException("Error finding rooms by difficulty", e);
        }
    }

    @Override
    public List<Room> findByTheme(String theme) throws DAOException {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT id, name, difficulty_id, price, theme FROM rooms WHERE theme LIKE ?";

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + theme + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                rooms.add(mapResultSetToRoom(rs));
            }

            return rooms;

        } catch (Exception e) {
            throw new DAOException("Error finding rooms by theme", e);
        }
    }

    @Override
    public List<Room> findRoomsWithPriceRange(double minPrice, double maxPrice) throws DAOException {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT id, name, difficulty_id, price, theme FROM rooms WHERE price BETWEEN ? AND ?";

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, minPrice);
            stmt.setDouble(2, maxPrice);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                rooms.add(mapResultSetToRoom(rs));
            }

            return rooms;

        } catch (Exception e) {
            throw new DAOException("Error finding rooms by price range", e);
        }
    }

    @Override
    public double calculateTotalRoomValue() throws DAOException {
        String sql = "SELECT SUM(price) AS total FROM rooms";

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getDouble("total");
            }
            return 0.0;

        } catch (Exception e) {
            throw new DAOException("Error calculating total room value", e);
        }
    }

    private Room mapResultSetToRoom(ResultSet rs) throws SQLException {
        return Room.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .difficulty(Difficulty.values()[rs.getInt("difficulty_id") - 1])
                .price(rs.getDouble("price"))
                .theme(rs.getString("theme"))
                .build();
    }

    // Métodos no implementados aún
    @Override
    public void addClueToRoom(Integer roomId, Integer clueId) throws DAOException {
        throw new UnsupportedOperationException("addClueToRoom not implemented");
    }

    @Override
    public void addDecorationToRoom(Integer roomId, Integer decorationId) throws DAOException {
        throw new UnsupportedOperationException("addDecorationToRoom not implemented");
    }

    @Override
    public List<Room> findAll() throws DAOException {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT id, name, difficulty_id, price, theme FROM rooms";

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                rooms.add(mapResultSetToRoom(rs));
            }

            return rooms;

        } catch (Exception e) {
            throw new DAOException("Error retrieving all rooms", e);
        }
    }

    @Override
    public void delete(Room room) throws DAOException {
        deleteById(room.getId());
    }

    @Override
    public void deleteById(Integer id) throws DAOException {
        String sql = "DELETE FROM rooms WHERE id = ?";

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int affected = stmt.executeUpdate();
            if (affected == 0) {
                throw new DAOException("No room found to delete with id: " + id);
            }

        } catch (Exception e) {
            throw new DAOException("Error deleting room by id", e);
        }
    }
}
