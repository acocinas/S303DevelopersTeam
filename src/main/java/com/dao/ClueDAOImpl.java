package com.dao;

import java.util.List;
import java.util.stream.Collectors;

import com.interfaces.ClueDAO;
import com.dao.exception.DAOException;
import com.model.Clue;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClueDAOImpl extends BaseDAO<Clue, Integer> implements ClueDAO {

    @Override
    public Clue create(Clue entity) throws DAOException {
        Integer id = idSequence.getAndIncrement();
        setEntityId(entity, id);
        entities.put(id, entity);
        log.info("Created clue with id: {}", id);
        return entity;
    }

    @Override
    public Clue update(Clue entity) throws DAOException {
        Integer id = getEntityId(entity);
        if (id != null && entities.containsKey(id)) {
            entities.put(id, entity);
            log.info("Updated clue with id: {}", id);
            return entity;
        }
        log.error("Clue not found for update: {}", id);
        throw new DAOException("Clue not found for update: " + id);
    }

    @Override
    public List<Clue> findByTheme(String theme) throws DAOException {
        log.debug("Finding clues by theme: {}", theme);
        return entities.values().stream()
                .filter(clue -> theme.equalsIgnoreCase(clue.getTheme()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Clue> findByVisibility(boolean visibility) throws DAOException {
        log.debug("Finding clues by visibility: {}", visibility);
        return entities.values().stream()
                .filter(clue -> clue.isRevealed() == visibility)
                .collect(Collectors.toList());
    }

    @Override
    public List<Clue> findCluesForRoom(Integer roomId) throws DAOException {
        log.debug("Finding clues for room: {}", roomId);
        // Implementation depends on how clues are linked to rooms
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    protected Integer getEntityId(Clue entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(Clue entity, Integer id) {
        entity.setId(id);
    }
}
