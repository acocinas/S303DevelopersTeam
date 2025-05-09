package com.dao;

import com.interfaces.ClueDAO;
import com.interfaces.CertificateDAO;
import com.interfaces.DecorationItemDAO;
import com.interfaces.PlayerDAO;
import com.interfaces.RoomDAO;
import com.interfaces.TicketSaleDAO;
import com.interfaces.DAOFactory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InMemoryDAOFactory implements DAOFactory {
    private static final InMemoryDAOFactory INSTANCE = new InMemoryDAOFactory();
    
    private final RoomDAO roomDAO = new RoomDAOImpl();
    private final ClueDAO clueDAO = new ClueDAOImpl();
    private final DecorationItemDAO decorationItemDAO = new DecorationItemDAOImpl();
    private final PlayerDAO playerDAO = new PlayerDAOImpl();
    private final CertificateDAO certificateDAO = new CertificateDAOImpl();
    private final TicketSaleDAO ticketSaleDAO = new TicketSaleDAOImpl();
    
    public static InMemoryDAOFactory getInstance() {
        return INSTANCE;
    }
}
