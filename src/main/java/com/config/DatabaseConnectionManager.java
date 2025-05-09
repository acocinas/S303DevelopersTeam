package com.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DatabaseConnectionManager {
    private static final Logger LOGGER = Logger.getLogger(DatabaseConnectionManager.class.getName());
    private static final String DEFAULT_CONFIG_FILE = "database.properties";
    private static final String JDBC_URL = "db.url";
    private static final String JDBC_USER = "db.username";
    private static final String JDBC_PASSWORD = "db.password";
    
    // Connection pool configuration parameters
    private static final int MAX_POOL_SIZE = 10;
    private static final int MIN_IDLE = 5;
    private static final int CONNECTION_TIMEOUT = 30000; // 30 seconds
    private static final int IDLE_TIMEOUT = 600000; // 10 minutes
    private static final int MAX_LIFETIME = 1800000; // 30 minutes
    
    // HikariCP data source
    private HikariDataSource dataSource;
    private String configFile;
    
    
    public DatabaseConnectionManager() {
        this(DEFAULT_CONFIG_FILE);
    }
    
    
    public DatabaseConnectionManager(String configFile) {
        this.configFile = configFile;
        try {
            initializeDataSource();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to initialize connection pool", e);
            throw new RuntimeException("Database connection initialization failed", e);
        }
    }
    
    
    private void initializeDataSource() throws IOException {
        Properties props = loadDatabaseProperties();
        
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(props.getProperty(JDBC_URL));
        config.setUsername(props.getProperty(JDBC_USER));
        config.setPassword(props.getProperty(JDBC_PASSWORD));
        
        // Pool configuration
        config.setMaximumPoolSize(MAX_POOL_SIZE);
        config.setMinimumIdle(MIN_IDLE);
        config.setConnectionTimeout(CONNECTION_TIMEOUT);
        config.setIdleTimeout(IDLE_TIMEOUT);
        config.setMaxLifetime(MAX_LIFETIME);
        
        // Connection testing
        config.setConnectionTestQuery("SELECT 1");
        config.setValidationTimeout(5000);
        
        // Performance optimization
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", "true");
        
        dataSource = new HikariDataSource(config);
        LOGGER.info("HikariCP connection pool initialized successfully");
    }
    
    
    private Properties loadDatabaseProperties() throws IOException {
        Properties props = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(configFile)) {
            if (inputStream == null) {
                throw new IOException("Cannot find " + configFile + " in the classpath");
            }
            props.load(inputStream);
            
            // Validate required properties
            if (!props.containsKey(JDBC_URL) || !props.containsKey(JDBC_USER) || !props.containsKey(JDBC_PASSWORD)) {
                throw new IOException("Missing required database configuration properties");
            }
            
            return props;
        }
    }
    
    
    public Connection getConnection() throws SQLException {
        try {
            Connection connection = dataSource.getConnection();
            if (connection == null) {
                throw new SQLException("Failed to obtain database connection from pool");
            }
            return connection;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error obtaining connection from pool", e);
            throw e;
        }
    }
    
    
    public void shutdown() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
            LOGGER.info("HikariCP connection pool has been shutdown");
        }
    }
    
   
    public String getPoolStatus() {
        if (dataSource != null) {
            return String.format(
                "HikariCP Pool Stats: Active=%d, Idle=%d, Waiting=%d, Total=%d",
                dataSource.getHikariPoolMXBean().getActiveConnections(),
                dataSource.getHikariPoolMXBean().getIdleConnections(),
                dataSource.getHikariPoolMXBean().getThreadsAwaitingConnection(),
                dataSource.getHikariPoolMXBean().getTotalConnections()
            );
        }
        return "Pool not initialized";
    }
    
    
    public boolean isClosed() {
        return dataSource == null || dataSource.isClosed();
    }
}
