package com.dao;

import java.util.List;


import com.interfaces.DecorationItemDAO;
import com.dao.exception.DAOException;
import com.enums.Material;
import com.model.DecorationItem;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DecorationItemDAOImpl extends BaseDAO<DecorationItem, Integer> implements DecorationItemDAO {
    
    @Override
    public DecorationItem create(DecorationItem entity) throws DAOException {
        Integer id = idSequence.getAndIncrement();
        setEntityId(entity, id);
        entities.put(id, entity);
        log.info("Created decoration item with id: {}", id);
        return entity;
    }

    @Override
    public DecorationItem update(DecorationItem entity) throws DAOException {
        Integer id = getEntityId(entity);
        if (id != null && entities.containsKey(id)) {
            entities.put(id, entity);
            log.info("Updated decoration item with id: {}", id);
            return entity;
        }
        log.error("Decoration item not found for update: {}", id);
        throw new DAOException("Decoration item not found for update: " + id);
    }

    @Override
    public List<DecorationItem> findByMaterial(Material material) throws DAOException {
        log.debug("Finding decoration items by material: {}", material);
        return entities.values().stream()
                .filter(item -> item.getMaterial().equals(material))
                .toList();
    }

    @Override
    public List<DecorationItem> findByInteractivity(boolean isInteractive) throws DAOException {
        log.debug("Finding decoration items by interactivity: {}", isInteractive);
        return entities.values().stream()
                .filter(item -> item.isInteractive() == isInteractive)
                .toList();
    }

    @Override
    public double calculateTotalDecorationValue() throws DAOException {
        double total = entities.values().stream()
                .mapToDouble(DecorationItem::getPrice)
                .sum();
        log.debug("Calculated total decoration value: {}", total);
        return total;
    }

    @Override
    public List<DecorationItem> findDecorationItemsForRoom(Integer roomId) throws DAOException {
        log.debug("Finding decoration items for room: {}", roomId);
        throw new UnsupportedOperationException("Method findDecorationItemsForRoom not implemented yet");
    }

    @Override
    protected Integer getEntityId(DecorationItem entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(DecorationItem entity, Integer id) {
        entity.setId(id);
    }
}
