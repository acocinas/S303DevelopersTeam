package com.dao;

import com.interfaces.*;

public class JdbcDAOFactory implements DAOFactory {

    @Override
    public PlayerDAO getPlayerDAO() {
        return new PlayerDAOImpl();
    }

    @Override
    public RoomDAO getRoomDAO() {
        return new RoomDAOImpl();
    }

    @Override
    public TicketSaleDAO getTicketSaleDAO() {
        return new TicketSaleDAOImpl();
    }

    @Override
    public CertificateDAO getCertificateDAO() {
        return new CertificateDAOImpl();
    }

    @Override
    public ClueDAO getClueDAO() {
        return new ClueDAOImpl();
    }

    @Override
    public DecorationItemDAO getDecorationItemDAO() {
        return new DecorationItemDAOImpl();
    }
}
