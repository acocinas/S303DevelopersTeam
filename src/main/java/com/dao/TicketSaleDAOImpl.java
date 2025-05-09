package com.dao;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.interfaces.TicketSaleDAO;
import com.dao.exception.DAOException;
import com.model.TicketSale;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TicketSaleDAOImpl extends BaseDAO<TicketSale, Integer> implements TicketSaleDAO {
    
    @Override
    public TicketSale create(TicketSale entity) throws DAOException {
        Integer id = idSequence.getAndIncrement();
        setEntityId(entity, id);
        entities.put(id, entity);
        log.info("Created ticket sale with id: {}", id);
        return entity;
    }

    @Override
    public TicketSale update(TicketSale entity) throws DAOException {
        Integer id = getEntityId(entity);
        if (id != null && entities.containsKey(id)) {
            entities.put(id, entity);
            log.info("Updated ticket sale with id: {}", id);
            return entity;
        }
        log.error("Ticket sale not found for update: {}", id);
        throw new DAOException("Ticket sale not found for update: " + id);
    }

    @Override
    public List<TicketSale> findByPlayerId(Integer playerId) throws DAOException {
        log.debug("Finding ticket sales by player id: {}", playerId);
        return entities.values().stream()
                .filter(sale -> sale.getPlayer().getId() == playerId)
                .toList();
    }

    @Override
    public List<TicketSale> findByRoomId(Integer roomId) throws DAOException {
        log.debug("Finding ticket sales by room id: {}", roomId);
        return entities.values().stream()
                .filter(sale -> sale.getRoom().getId() == roomId)
                .toList();
    }

    @Override
    public List<TicketSale> findByDateRange(Date startDate, Date endDate) throws DAOException {
        log.debug("Finding ticket sales between {} and {}", startDate, endDate);
        return entities.values().stream()
                .filter(sale -> {
                    Date saleDate = java.sql.Timestamp.valueOf(sale.getSaleDate());
                    return !saleDate.before(startDate) && !saleDate.after(endDate);
                })
                .toList();
    }

    @Override
    public double calculateTotalRevenue() throws DAOException {
        double total = entities.values().stream()
                .mapToDouble(TicketSale::getPrice)
                .sum();
        log.debug("Calculated total revenue: {}", total);
        return total;
    }

    @Override
    public double calculateTotalRevenueByRoom(Integer roomId) throws DAOException {
        double total = entities.values().stream()
                .filter(sale -> sale.getRoom().getId() == roomId)
                .mapToDouble(TicketSale::getPrice)
                .sum();
        log.debug("Calculated total revenue for room {}: {}", roomId, total);
        return total;
    }

    @Override
    protected Integer getEntityId(TicketSale entity) {
        return entity.getId();
    }

    @Override
    protected void setEntityId(TicketSale entity, Integer id) {
        if (id != null) {
            TicketSale newSale = new TicketSale(
                id,
                entity.getPlayer(),
                entity.getRoom(),
                entity.getPrice(),
                entity.getSaleDate()
            );
            
            entities.put(id, newSale);
        }
    }
}