package com.dao;

import com.interfaces.DAOFactory;
import lombok.Getter;
import lombok.Setter;


public class DAOManager {
    private static final String DEFAULT_FACTORY_TYPE = "memory";
    
    @Getter @Setter
    private static String factoryType = DEFAULT_FACTORY_TYPE;
    
    
    private DAOManager() {
        
    }
    
    public static DAOFactory getDAOFactory() {
        
        return InMemoryDAOFactory.getInstance();
    }
}
