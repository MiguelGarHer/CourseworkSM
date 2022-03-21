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

        // Testing for sprint 1 methods
        //sprint_1(world);

        // Testing for sprint 2 methods
        sprint_2(world);

        // Disconnect from MySQL
        world.disconnect();
    }

    /**
     * Testing for sprint 1 methods
     * @param world world object
     */
    private static void sprint_1(World world) {
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

        System.out.println();

    }

    /**
     * Testing for sprint 2 methods
     * @param world world object
     */
    private static void sprint_2(World world) {
        // Vinh Test: sortCountriesPopContinent():
        world.sortCountriesPopContinent("Asia");

        world.sortCapCitiesPopContinent("Asia");

        System.out.println();


    }
}
