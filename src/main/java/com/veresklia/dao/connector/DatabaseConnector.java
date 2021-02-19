package com.veresklia.dao.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    public Connection connect (String databaseName, String login, String password) {
        Connection con = null;

        try {
            con = DriverManager.getConnection(databaseName, login, password);
            System.out.println("Connected to " + con.toString());
        }
        catch (SQLException ex) {
            System.out.println(ex.toString());
        }

        return con;
    }
}
