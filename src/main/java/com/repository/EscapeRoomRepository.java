package com.repository;

import java.util.Date;
import java.util.List;

import com.interfaces.CertificateDAO;
import com.interfaces.PlayerDAO;
import com.interfaces.RoomDAO;
import com.interfaces.TicketSaleDAO;
import com.dao.exception.DAOException;
import com.dao.DAOManager;
import com.model.Certificate;
import com.model.Player;
import com.model.Room;
import com.model.TicketSale;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EscapeRoomRepository {
    private final RoomDAO roomDAO;
    private final PlayerDAO playerDAO;
    private final TicketSaleDAO ticketSaleDAO;
    private final CertificateDAO certificateDAO;
    
    public EscapeRoomRepository() {
        roomDAO = DAOManager.getDAOFactory().getRoomDAO();
        playerDAO = DAOManager.getDAOFactory().getPlayerDAO();
        ticketSaleDAO = DAOManager.getDAOFactory().getTicketSaleDAO();
        certificateDAO = DAOManager.getDAOFactory().getCertificateDAO();
    }
    
    public TicketSale sellTicket(Integer playerId, Integer roomId, Date date) throws DAOException {
        log.info("Selling ticket for player {} to room {}", playerId, roomId);
        
        Player player = playerDAO.findById(playerId)
                .orElseThrow(() -> new DAOException("Player not found: " + playerId));
        Room room = roomDAO.findById(roomId)
                .orElseThrow(() -> new DAOException("Room not found: " + roomId));
        
        TicketSale ticketSale = TicketSale.builder()
                .player(player)
                .room(room)
                .date(date)
                .price(room.getPrice())
                .build();
        
        return ticketSaleDAO.create(ticketSale);
    }
    
    public Certificate issueCertificate(Integer playerId, Integer roomId, Date completionDate) throws DAOException {
        log.info("Issuing certificate for player {} for room {}", playerId, roomId);
        
        Player player = playerDAO.findById(playerId)
                .orElseThrow(() -> new DAOException("Player not found: " + playerId));
        Room room = roomDAO.findById(roomId)
                .orElseThrow(() -> new DAOException("Room not found: " + roomId));
        
        Certificate certificate = Certificate.builder()
                .player(player)
                .room(room)
                .completionDate(completionDate)
                .build();
        
        return certificateDAO.create(certificate);
    }
    
    public double calculateTotalRevenue() throws DAOException {
        double revenue = ticketSaleDAO.calculateTotalRevenue();
        log.info("Total revenue calculated: {}", revenue);
        return revenue;
    }
    
    public List<Certificate> getPlayerCertificates(Integer playerId) throws DAOException {
        log.debug("Getting certificates for player: {}", playerId);
        return certificateDAO.findByPlayerId(playerId);
    }
    
    public List<TicketSale> getPlayerPurchaseHistory(Integer playerId) throws DAOException {
        log.debug("Getting purchase history for player: {}", playerId);
        return ticketSaleDAO.findByPlayerId(playerId);
    }
}
