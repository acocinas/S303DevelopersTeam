package com.interfaces;

import com.dao.ClueDAO;
import com.dao.CertificateDAO;
import com.dao.DecorationItemDAO;
import com.dao.PlayerDAO;
import com.dao.RoomDAO;
import com.dao.TicketSaleDAO;

public interface DAOFactory {
    RoomDAO getRoomDAO();
    ClueDAO getClueDAO();
    DecorationItemDAO getDecorationItemDAO();
    PlayerDAO getPlayerDAO();
    CertificateDAO getCertificateDAO();
    TicketSaleDAO getTicketSaleDAO();
}
