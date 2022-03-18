package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class WorldIntegrationTest
{
    static World world;

    @BeforeAll
    static void init()
    {
        world = new World();
        world.connect("localhost:33060", 15000);
    }

    /**
     * Tests read to database by looking at the first table and its column are not null
     * @throws SQLException
     */
    @Test
    void testGetResultSetNotNull() throws SQLException {
        String tableQuery = "SHOW TABLES;";
        ResultSet tablesResultSet = world.getResultSet(tableQuery);
        if (tablesResultSet.next())
        {
            String firstTable = tablesResultSet.getString(1);
            assertNotNull(firstTable);
            String columnQuery = "SHOW COLUMNS FROM " + firstTable + ";";
            ResultSet columnsResultSet = world.getResultSet(columnQuery);
            if (columnsResultSet.next()) {
                assertNotNull(columnsResultSet.getString(1));
            }
        }


    }
}