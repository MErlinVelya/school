package com.veresklia.school.dao;

import com.ibatis.common.jdbc.ScriptRunner;
import com.veresklia.school.dao.connector.DatabaseConnector;
import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

public class UtilityBeforeEach {
    public static void beforeEach (DatabaseConnector databaseConnector){
        try (Connection connection = databaseConnector.connect()) {
            Reader reader = Resources.getResourceAsReader("tablesCreateFill.sql");
            ScriptRunner runner = new ScriptRunner(connection, false, true);
            runner.runScript(reader);
        } catch (IOException | SQLException throwables) {
            System.out.println("Negative");
            throwables.printStackTrace();
        }
    }
}
