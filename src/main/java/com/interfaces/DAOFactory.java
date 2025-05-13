package com.interfaces;

public interface DAOFactory {
    RoomDAO getRoomDAO();
    ClueDAO getClueDAO();
    DecorationItemDAO getDecorationItemDAO();
    PlayerDAO getPlayerDAO();
    CertificateDAO getCertificateDAO();
    TicketSaleDAO getTicketSaleDAO();
}
