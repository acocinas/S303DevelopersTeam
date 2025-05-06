package com.interfaces;

import com.interfaces.ClueDAO;
import com.interfaces.CertificateDAO;
import com.interfaces.DecorationItemDAO;
import com.interfaces.PlayerDAO;
import com.interfaces.RoomDAO;
import com.interfaces.TicketSaleDAO;

public interface DAOFactory {
    RoomDAO getRoomDAO();
    ClueDAO getClueDAO();
    DecorationItemDAO getDecorationItemDAO();
    PlayerDAO getPlayerDAO();
    CertificateDAO getCertificateDAO();
    TicketSaleDAO getTicketSaleDAO();
}
