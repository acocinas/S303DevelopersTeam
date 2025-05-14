package com.dao;

import com.interfaces.DAOFactory;
import lombok.Getter;
import lombok.Setter;


public class DAOManager {
    private static final String DEFAULT_FACTORY_TYPE = "jdbc"; // ahora por defecto usamos JDBC

    @Getter @Setter
    private static String factoryType = DEFAULT_FACTORY_TYPE;

    private DAOManager() {
    }

    public static DAOFactory getDAOFactory() {
        return switch (factoryType.toLowerCase()) {
            case "jdbc" -> new JdbcDAOFactory();
            case "memory" -> InMemoryDAOFactory.getInstance();
            default -> throw new IllegalArgumentException("Unknown factory type: " + factoryType);
        };
    }
}
