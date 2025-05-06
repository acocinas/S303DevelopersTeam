package com.dao;

import com.interfaces.ClueDAO;
import com.interfaces.CertificateDAO;
import com.interfaces.DecorationItemDAO;
import com.interfaces.PlayerDAO;
import com.interfaces.RoomDAO;
import com.interfaces.TicketSaleDAO;
import com.dao.ClueDAOImpl;
import com.dao.CertificateDAOImpl;
import com.dao.DecorationItemDAOImpl;
import com.dao.PlayerDAOImpl;
import com.dao.RoomDAOImpl;
import com.dao.TicketSaleDAOImpl;
import com.interfaces.DAOFactory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InMemoryDAOFactory implements DAOFactory {
    private static final InMemoryDAOFactory INSTANCE = new InMemoryDAOFactory();
    
    @Getter private final RoomDAO roomDAO = new RoomDAOImpl();
    @Getter private final ClueDAO clueDAO = new ClueDAOImpl();
    @Getter private final DecorationItemDAO decorationItemDAO = new DecorationItemDAOImpl();
    @Getter private final PlayerDAO playerDAO = new PlayerDAOImpl();
    @Getter private final CertificateDAO certificateDAO = new CertificateDAOImpl();
    @Getter private final TicketSaleDAO ticketSaleDAO = new TicketSaleDAOImpl();
    
    public static InMemoryDAOFactory getInstance() {
        return INSTANCE;
    }
}
