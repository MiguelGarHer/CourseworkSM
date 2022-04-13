package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration Test Class: Class testing World class
 */
class WorldIntegrationTest
{
    static World world; // World object

    /**
     * Initializing method, executed before each test
     */
    @BeforeAll
    static void init()
    {
        world = new World();
        world.connect("localhost:33060", 15000);
    }

    /**
     * Integration Test: Confirm that application is connected to database
     * Logic: Query the database, and check that the first table and its column are not null
     * @throws SQLException exception whenever a query was unable to be executed
     */
    @Test
    void testGetResultSetNotNull() throws SQLException {
        String tableQuery = "SHOW TABLES;";
        ResultSet tablesResultSet = world.getResultSet(tableQuery);
        if (tablesResultSet.next())
        {
            String firstTable = tablesResultSet.getString(1);
            String columnQuery = "SHOW COLUMNS FROM " + firstTable + ";";
            ResultSet columnsResultSet = world.getResultSet(columnQuery);
            if (columnsResultSet.next()) {
                assertNotNull(columnsResultSet.getString(1), "Table column is equal to null");
            }
        }


    }
}