package com.napier.sem;

import java.sql.*;

public class App
{
    public static void main(String[] args)
    {
        // Create World
        World world = new World();

        //Connect to MySQL
        world.connect();

        // Read all countries
        world.getCountries();

        // Disconnect from MySQL
        world.disconnect();
    }
}
