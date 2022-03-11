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

        // Vinh: Test: sortCitiesPopWorld():
        world.sortCitiesPopWorld();

        System.out.println();

        // Vinh: Test: sortCitiesPopContinent()
        world.sortCitiesPopContinent("Asia");

        System.out.println();

        // Vinh: Test: sortCitiesPopDistrict()
        world.sortCitiesPopDistrict("California");

        System.out.println();

        //Miguel: Test: sortCitiesPopRegion()
        world.sortCitiesPopRegion("Caribbean");

        System.out.println();

        //Haidi: Test: sortCitiesPopCountry()
        world.sortCitiesPopCountry("France");

        // Disconnect from MySQL
        world.disconnect();
    }
}
