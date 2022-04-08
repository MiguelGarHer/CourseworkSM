package com.napier.sem;

import java.util.ArrayList;


public class App
{
    public static void main(String[] args)
    {
        // Create World
        World world = new World();

        //Connect to MySQL

        if (args.length < 1) {
            world.connect("localhost:33060", 0);
        } else{
            world.connect(args[0], Integer.parseInt(args[1]));
        }

        // Read all countries
        world.getCountries();

        // Disconnect from MySQL
        world.disconnect();

        // Testing for country reports
        countryReports(world);

        // Testing for city reports
        cityReports(world);

        // Testing for cap city reports
        capCityReports(world);

        // Testing for population reports
        populationReports(world);

        // Testing for language reports
        languageReports(world);

        world.nPopCitiesDistrict("California", 2);
    }

    /**
     * Helper method which calls all country related reports
     * @param world
     */
    private static void countryReports(World world) {
        world.sortCountriesPopContinent("Asia");
        world.sortCountriesPopRegion("Caribbean");
        world.sortCountriesPopWorld();

        world.nPopCountriesWorld(10);
        world.nPopCountriesContinent("Asia", 5);
        world.nPopCountriesRegion("Caribbean", 5);
    }

    /**
     * Helper method which calls all city related reports
     * @param world
     */
    private static void cityReports(World world) {
        world.sortCitiesPopWorld();
        world.sortCitiesPopContinent("Asia");
        world.sortCitiesPopRegion("Caribbean");
        world.sortCitiesPopCountry("France");
        world.sortCitiesPopDistrict("California");

        world.nPopCitiesWorld(50);
        world.nPopCitiesContinent("Asia", 20);
        world.nPopCitiesRegion("Caribbean", 5);
        world.nPopCitiesCountry("France", 5);
        world.nPopCitiesDistrict("California", 5);
    }

    /**
     * Helper method which calls all capital city related reports
     * @param world
     */
    private static void capCityReports(World world) {
        world.sortCapCitiesPopWorld();
        world.sortCapCitiesPopRegion("Caribbean");
        world.sortCapCitiesPopContinent("Asia");

        world.nPopCapCitiesRegion("Caribbean", 5);
        world.nPopCapCitiesContinent("Asia", 10);
        world.nPopCapCitiesWorld(50);
    }

    /**
     * Helper method which calls all population related reports
     * @param world
     */
    private static void populationReports(World world) {
        // Get all continent population reports
        world.populationReportAllContinents();
        world.populationReportAllCountries();
        world.populationReportAllRegions();

        world.populationAllCities();
        world.populationAllDistricts();
        world.populationWorld();
    }

    /**
     * Helper method which calls all language related reports
     * @param world
     */
    private static void languageReports(World world) {

        ArrayList<String> requirementLanguages = new ArrayList<>();
        requirementLanguages.add("Chinese");
        requirementLanguages.add("English");
        requirementLanguages.add("Hindi");
        requirementLanguages.add("Spanish");
        requirementLanguages.add("Arabic");
        world.languageReportSelectedLanguages(requirementLanguages);

        world.languageReportAllLanguages();
    }

}
