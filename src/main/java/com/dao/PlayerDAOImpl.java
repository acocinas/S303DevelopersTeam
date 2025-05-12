package com.dao;

import java.util.Comparator;
import java.util.List;

import com.interfaces.PlayerDAO;
import com.dao.exception.DAOException;
import com.model.Player;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PlayerDAOImpl extends BaseDAO<Player, Integer> implements PlayerDAO {
    
    @Override
    public Player create(Player entity) throws DAOException {
        Integer id = idSequence.getAndIncrement();
        setEntityId(entity, id);
        entities.put(id, entity);
        log.info("Created player with id: {}", id);
        return entity;
    }

    @Override
    public Player update(Player entity) throws DAOException {
        Integer id = getEntityId(entity);
        if (id != null && entities.containsKey(id)) {
            entities.put(id, entity);
            log.info("Updated player with id: {}", id);
            return entity;
        }
        log.error("Player not found for update: {}", id);
        throw new DAOException("Player not found for update: " + id);
    }

    @Override
    public List<Player> findByName(String name) throws DAOException {
        log.debug("Finding players by name: {}", name);
        return entities.values().stream()
                .filter(player -> player.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    @Override
    public List<Player> findTopPlayers(int limit) throws DAOException {
        log.debug("Finding top {} players", limit);
        return entities.values().stream()
                .sorted(Comparator.comparing(Player::getRoomProgress).reversed())
                .limit(limit)
                .toList();
    }

    @Override
    protected Integer getEntityId(Player entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(Player entity, Integer id) {
        entity.setId(id);
    }
}