package com.dao;

import com.interfaces.DAOFactory;
import lombok.Getter;
import lombok.Setter;


public class DAOManager {
    private static final String DEFAULT_FACTORY_TYPE = "memory";
    
    @Getter @Setter
    private static String factoryType = DEFAULT_FACTORY_TYPE;
    
    public static DAOFactory getDAOFactory() {
        switch(factoryType.toLowerCase()) {
            case "memory":
                return InMemoryDAOFactory.getInstance();
            // Other factory types would be added here
            default:
                return InMemoryDAOFactory.getInstance();
        }
    }
}
