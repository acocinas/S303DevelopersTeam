package com.interfaces;

import java.util.Date;
import java.util.List;
import com.dao.exception.DAOException;
import com.model.TicketSale;

public interface TicketSaleDAO extends GenericDAO<TicketSale, Integer> {
    List<TicketSale> findByPlayerId(Integer playerId) throws DAOException;
    List<TicketSale> findByRoomId(Integer roomId) throws DAOException;
    List<TicketSale> findByDateRange(Date startDate, Date endDate) throws DAOException;
    double calculateTotalRevenue() throws DAOException;
    double calculateTotalRevenueByRoom(Integer roomId) throws DAOException;
}
