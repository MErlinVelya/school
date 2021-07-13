package com.veresklia.school.dao.connector;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectorImpl implements DatabaseConnector {

    private final HikariDataSource dataSource;

    public DatabaseConnectorImpl (String configFile) {
        HikariConfig config = new HikariConfig(configFile);
        dataSource = new HikariDataSource(config);
    }

    @Override
    public Connection connect() throws SQLException {
        return dataSource.getConnection();
    }
}
