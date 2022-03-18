package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;

public class World {

    // Connection to MySQL database
    private Connection con = null;

    // ArrayList containing all the data of the countries
    private final ArrayList<Country> countries = new ArrayList<>();

    /**
     * Constructor for World
     * <br>
     * Initiates database communication and reading of data
     */
    public World(){
        // Connect to MySQL

    }

    /**
     * Connect to the MySQL database.
     */
    public void connect(String location, int delay) {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(delay);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location
                                + "/world?allowPublicKeyRetrieval=true&useSSL=false",
                        "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " +                                  Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

    /**
     * Read all countries from database
     */
    public void getCountries()
    {
        try
        {
            // Create an SQL statement using connection
            Statement statement = con.createStatement();
            // Create SQL statement string for SQL statement
            String strSelect =
                    "SELECT *" +
                    "FROM country;";
            // Execute SQL statement
            ResultSet resultSet = statement.executeQuery(strSelect);
            // For each row returned, add new Country to list
            while (resultSet.next())
            {
                Country country = new Country(getCities(resultSet.getString(1)), getLanguages(resultSet.getString(1)),resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getDouble(5),resultSet.getInt(6),resultSet.getInt(7),resultSet.getDouble(8),resultSet.getDouble(9),resultSet.getDouble(10),resultSet.getString(11),resultSet.getString(12),resultSet.getString(13),resultSet.getInt(14),resultSet.getString(15));
                countries.add(country);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve country details");
        }
    }

    /**
     * Read all cities in a country
     * @param countryCode country code
     * @return list of City objects
     */
    private ArrayList<City> getCities(String countryCode) {
        ArrayList<City> cities = new ArrayList<>();
        try
        {
            // Create an SQL statement using connection
            Statement statement = con.createStatement();
            // Create SQL statement string for SQL statement
            String strSelect =
                    "SELECT * FROM city WHERE countrycode = '" + countryCode + "';";
            // Execute SQL statement
            ResultSet resultSet = statement.executeQuery(strSelect);
            // For each row returned, add new city to list
            while (resultSet.next())
            {
                City city = new City(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3),resultSet.getString(4), resultSet.getInt(5));
                cities.add(city);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve city details");
        }
        return cities;
    }

    /**
     * Read all languages in a country
     * @param countryCode country code
     * @return list of Language objects
     */
    private ArrayList<Language> getLanguages(String countryCode) {
        ArrayList<Language> languages = new ArrayList<>();
        try
        {
            // Create an SQL statement using connection
            Statement statement = con.createStatement();
            // Create SQL statement string for SQL statement
            String strSelect =
                    "SELECT * FROM countrylanguage WHERE CountryCode = '" + countryCode + "';";
            // Execute SQL statement
            ResultSet resultSet = statement.executeQuery(strSelect);
            // For each row returned, add new language to list
            while (resultSet.next())
            {
                boolean isOfficial = resultSet.getString(3).equals("T");
                Language language = new Language(resultSet.getString(2),isOfficial, resultSet.getDouble(4));
                languages.add(language);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve language details");
        }
        return languages;

    }

    /**
     * Prints all country details
     * @param country name of country
     */
    public void printCountry(Country country) {
        if (country != null) {
            System.out.println(country);
        }
    }

    /**
     * Generate country report
     * @param countryName name of country
     */
    public void getCountryRep(String countryName){
    }

    /**
     * Generate city report
     * @param cityName name of city
     */
    public void getCityReport(String cityName){
    }

    /**
     * Generate capital city report
     * @param capCityName name of capital city
     */
    public void getCapCityRep(String capCityName){
    }

    /**
     * Generate population report
     */
    public void getPopulationRep(){
    }

    /**
     * Print all countries in the world
     * Sorting: Largest population to smallest
     */
    public void sortCountriesPopWorld(){
    }

    /**
     * Print all countries in a continent
     * Sorting: Largest population to smallest
     * @param continentName name of continent
     */
    public void sortCountriesPopContinent(String continentName){
        // Get all cities in each continent and add to temporary list
        ArrayList<Country> continentCountries = new ArrayList<>();
        for (Country country : countries) {
            if (country.getContinent().equals(continentName)) {
                continentCountries.add(country);
            }
        }

        // Null check
        if (continentCountries.isEmpty()) {
            System.out.println("No countries");
            return;
        }

        // Sort temporary list - https://www.baeldung.com/java-8-comparator-comparing
        countries.sort(Comparator.comparing(Country::getPopulation).reversed());

        // Print sorted list
        System.out.println("All countries in " + continentName + ", sorted by population");
        for (Country country : countries) {
            System.out.println(country);
        }
    }

    /**
     * Print all countries in a region
     * Sorting: Largest population to smallest
     * @param regionName name of region
     */
    public void sortCountriesPopRegion(String regionName){
    }

    /**
     * Print top N countries in the world
     * Sorting: Largest population to smallest
     * @param n top N
     */
    public void nPopCountriesWorld(int n){
    }

    /**
     * Print top N countries in a continent
     * Sorting: Largest population to smallest
     * @param continentName name of continent
     * @param n top N
     */
    public void nPopCountriesContinent(String continentName, int n){
    }

    /**
     * Print top N countries in a region
     * Sorting: Largest population to smallest
     * @param regionName name of region
     * @param n top N
     */
    public void nPopCountriesRegion(String regionName, int n){
    }

    /**
     * Print all cities in the world
     * Sorting: Largest population to smallest
     */
    public void sortCitiesPopWorld(){
        ArrayList<City> allCities = new ArrayList<>();
        for (Country country: countries) {
            allCities.addAll(country.getCities());
        }

        allCities.sort(Comparator.comparing(City::getPopulation).reversed());

        System.out.println("All cities in the world, sorted by population");
        for (City city : allCities) {
            System.out.println(city);
        }
    }

    /**
     * Print all cities in a continent <br>
     * Sorting: Largest population to smallest
     * @param continentName name of continent
     */
    public void sortCitiesPopContinent(String continentName){
        // Get all cities in each continent and add to temporary list
        ArrayList<City> continentCities = new ArrayList<>();
        for (Country country : countries) {
            if (country.getContinent().equals(continentName)) {
                continentCities.addAll(country.getCities());
            }
        }

        if (continentCities.isEmpty()) {
            System.out.println("No cities");
            return;
        }

        // Sort temporary list - https://www.baeldung.com/java-8-comparator-comparing
        continentCities.sort(Comparator.comparing(City::getPopulation).reversed());

        // Print sorted list
        System.out.println("All cities in " + continentName + ", sorted by population");
        for (City city : continentCities) {
            System.out.println(city);
        }
    }

    /**
     * Print all cities in a region <br>
     * Sorting: Largest population to smallest
     * @param regionName name of region
     */
    public void sortCitiesPopRegion(String regionName){

        ArrayList<City> sortCities = new ArrayList<City>();

        for(Country c : countries) {
            if (c.getRegion().equals(regionName)) {
                sortCities.addAll(c.getCities());
            }
        }

        if (sortCities.isEmpty()) {
            System.out.println("No cities");
            return;
        }

        sortCities.sort(Comparator.comparingInt(City::getPopulation).reversed());

        System.out.println("All cities in " + regionName + ", sorted by population");
        for(City c: sortCities){
            System.out.println(c.toString());
        }

    }

    /**
     * Print all cities in a country <br>
     * Sorting: Largest population to smallest
     * @param countryName name of country
     */
    public void sortCitiesPopCountry(String countryName){
        //Get all cities in every country and add to list
        ArrayList<City> countryCities = new ArrayList<>();
        for (Country country : countries) {
            if (country.getName().equals(countryName)) {
                countryCities.addAll(country.getCities());
            }
        }

        if (countryCities.isEmpty()) {
            System.out.println("No cities");
            return;
        }

        //Sort
        countryCities.sort(Comparator.comparing(City::getPopulation).reversed());

        //Print cities
        System.out.println("All cities in " + countryName + ", sorted by population");
        for (City city : countryCities) {
            System.out.println(city);
        }

    }

    /**
     * Print all cities in a district <br>
     * Sorting: Largest population to smallest
     * @param districtName name of district
     */
    public void sortCitiesPopDistrict(String districtName){
        //Get all cities in every country and add to list
        ArrayList<City> districtCities = new ArrayList<>();
        for (Country country : countries) {
            for (City city : country.getCities()) {
                if (city.getDistrict().equals(districtName)) {
                    districtCities.add(city);
                }
            }
        }

        if (districtCities.isEmpty()) {
            System.out.println("No cities");
            return;
        }

        //Sort
        districtCities.sort(Comparator.comparing(City::getPopulation).reversed());

        //Print cities
        System.out.println("All cities in " + districtName + ", sorted by population");
        for (City city : districtCities) {
            System.out.println(city);
        }
    }

    /**
     * Print top N cities in the world
     * Sorting: Largest population to smallest
     * @param n top N
     */
    public void nPopCitiesWorld(int n){

    }

    /**
     * Print top N cities in a continent
     * Sorting: Largest population to smallest
     * @param continentName name of continent
     * @param n top N
     */
    public void nPopCitiesContinent(String continentName, int n){
    }

    /**
     * Print top N cities in a region
     * Sorting: Largest population to smallest
     * @param regionName name of region
     * @param n top N
     */
    public void nPopCitiesRegion(String regionName, int n){
    }

    /**
     * Print top N cities in a country
     * Sorting: Largest population to smallest
     * @param countryName name of country
     * @param n top N
     */
    public void nPopCitiesCountry(String countryName, int n){
    }

    /**
     * Print top N cities in a district
     * @param districtName name of district
     * @param n top N
     */
    public void nPopCitiesDistrict(String districtName, int n){
    }

    /**
     * Print capital cities in the world
     * Sorting: Largest population to smallest
     */
    public void sortCapCitiesPopWorld(){
    }

    /**
     * Print capital cities in a region
     * Sorting: Largest population to smallest
     * @param regionName name of region
     * @param n top N
     */
    public void sortCapCitiesPopRegion(String regionName, int n){
    }

    /**
     * Print capital cities in a continent
     * Sorting: Largest population to smallest
     * @param continentName name of continent
     * @param n top N
     */
    public void sortCapCitiesPopContinent(String continentName, int n){
    }

    /**
     * Print top N capital cities in a region
     * Sorting: Largest population to smallest
     * @param regionName name of region
     * @param n top N
     */
    public void nPopCapCitiesRegion(String regionName, int n){
    }

    /**
     * Print top N capital cities in a continent
     * Sorting: Largest population to smallest
     * @param continentName name of continent
     * @param n top N
     */
    public void nPopCapCitiesContinent(String continentName, int n){
    }

    /**
     * Print top N capital cities in the world
     * Sorting: Largest population to smallest
     * @param n top N
     */
    public void nPopCapCitiesWorld(int n){
    }
}
