package com.dao;

import com.config.DatabaseConnectionManager;
import com.config.exception.DatabaseConnectionException;
import com.dao.exception.DAOException;
import com.interfaces.PlayerDAO;
import com.model.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlayerDAOImpl implements PlayerDAO {

    private final DatabaseConnectionManager connectionManager;

public PlayerDAOImpl() {
    try {
        this.connectionManager = new DatabaseConnectionManager();
    } catch (DatabaseConnectionException e) {
        throw new DAOException("Failed to initialize DatabaseConnectionManager", e);
    }
}


    @Override
    public Optional<Player> findById(Integer id) throws DAOException {
        String sql = "SELECT id, name, email, room_progress FROM players WHERE id = ?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(mapResultSetToPlayer(rs));
            }
            return Optional.empty();

        } catch (Exception e) {
            throw new DAOException("Error finding player by ID", e);
        }
    }

    @Override
    public Player create(Player player) throws DAOException {
        String sql = "INSERT INTO players (name, email, room_progress) VALUES (?, ?, ?)";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, player.getName());
            stmt.setString(2, player.getEmail());
            stmt.setInt(3, player.getRoomProgress());
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                player.setId(keys.getInt(1));
            }

            return player;

        } catch (Exception e) {
            throw new DAOException("Error creating player", e);
        }
    }

    @Override
    public Player update(Player player) throws DAOException {
        String sql = "UPDATE players SET name = ?, email = ?, room_progress = ? WHERE id = ?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, player.getName());
            stmt.setString(2, player.getEmail());
            stmt.setInt(3, player.getRoomProgress());
            stmt.setInt(4, player.getId());

            int rows = stmt.executeUpdate();
            if (rows == 0) {
                throw new DAOException("No player found with id: " + player.getId());
            }

            return player;

        } catch (Exception e) {
            throw new DAOException("Error updating player", e);
        }
    }

    @Override
    public List<Player> findByName(String name) throws DAOException {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT id, name, email, room_progress FROM players WHERE name LIKE ?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                players.add(mapResultSetToPlayer(rs));
            }

            return players;

        } catch (Exception e) {
            throw new DAOException("Error finding players by name", e);
        }
    }

    @Override
    public List<Player> findTopPlayers(int limit) throws DAOException {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT id, name, email, room_progress FROM players ORDER BY room_progress DESC LIMIT ?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, limit);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                players.add(mapResultSetToPlayer(rs));
            }

            return players;

        } catch (Exception e) {
            throw new DAOException("Error retrieving top players", e);
        }
    }

    private Player mapResultSetToPlayer(ResultSet rs) throws SQLException {
        return Player.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .email(rs.getString("email"))
                .roomProgress(rs.getInt("room_progress"))
                .build();
    }
    @Override
    public List<Player> findAll() throws DAOException {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT id, name, email, room_progress FROM players";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                players.add(mapResultSetToPlayer(rs));
            }

            return players;

        } catch (Exception e) {
            throw new DAOException("Error retrieving all players", e);
        }
    }
    @Override
    public void delete(Player player) throws DAOException {
        String sql = "DELETE FROM players WHERE id = ?";

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, player.getId());
            int affected = stmt.executeUpdate();

            if (affected == 0) {
                throw new DAOException("No player found to delete with ID: " + player.getId());
            }

        } catch (Exception e) {
            throw new DAOException("Error deleting player", e);
        }

    }
    @Override
    public void deleteById(Integer id) throws DAOException {
        String sql = "DELETE FROM players WHERE id = ?";

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int affected = stmt.executeUpdate();

            if (affected == 0) {
                throw new DAOException("No player found to delete with ID: " + id);
            }

        } catch (Exception e) {
            throw new DAOException("Error deleting player by ID", e);
        }
    }



}
