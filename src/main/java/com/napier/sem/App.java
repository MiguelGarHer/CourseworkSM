package com.napier.sem;

public class App
{
    public static void main(String[] args)
    {
        // Create World
        World world = new World();

        //Connect to MySQL

        if (args.length < 1) {
            world.connect("localhost:33060", 15000);
        } else{
            world.connect(args[0], Integer.parseInt(args[1]));
        }

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

        System.out.println();


        // Vinh Test: nPopCitiesContinent():
        world.nPopCitiesContinent("Asia", 5);

        System.out.println();

        // Edu Test: nPopCitiesWorld
        world.nPopCitiesWorld(5);

        System.out.println();

        // Edu Test: sortCapCitiesPopRegion
        world.sortCapCitiesPopRegion("South America");

        System.out.println();

        //Edu Test : sortCountriesPopRegion():
        world.sortCountriesPopRegion("Caribbean");

        System.out.println();


        // Miguel Test: sortCapCitiesPopContinent
        world.sortCapCitiesPopContinent("Asia");

        System.out.println();

        // Miguel Test: nPopCapCitiesContinent
        world.nPopCapCitiesContinent("Asia", 5);

        System.out.println();

        // Miguel Test: sortCapCitiesPopRegion
        world.sortCapCitiesPopRegion("South America");

        System.out.println();

        // Haidi Test: nPopCountriesWorld
        world.nPopCountriesWorld(5);

        System.out.println();

        // Haidi Test: sortCapCitiesPopWorld
        world.sortCapCitiesPopWorld();

        System.out.println();
    }
    /**
     * Testing for sprint 3 methods
     * @param world world object
     */
    private static void sprint_3(World world) {
        // Edu Test: nPopCountriesRegion
        world.nPopCountriesRegion("Caribbean", 5);

        System.out.println();
    }

}
