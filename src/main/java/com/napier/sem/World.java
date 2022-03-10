package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
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
        connect();

        // Read all countries
        getCountries();

        // Vinh: Test: sortCitiesPopContinent()
        sortCitiesPopContinent("Asia");

        System.out.println();

        //Miguel: Test: sortCitiesPopRegion()
        sortCitiesPopRegion("Caribbean");

        // Disconnect from MySQL
        disconnect();
    }

    /**
     * Connect to the MySQL database.
     */
    private void connect()
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(20000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqlException)
            {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqlException.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    private void disconnect()
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
    private void getCountries()
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
     * Retrieve all cities from each country
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
     * Retrieve all languages from each country
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
                boolean isOfficial = false;
                if (resultSet.getString(3).equals("T")) {
                    isOfficial = true;
                }
                Language language = new Language(resultSet.getString(2),isOfficial, resultSet.getDouble(4));
                languages.add(language);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to retrieve city details");
        }
        return languages;

    }

    /**
     *  Prints all country details
     * @param country: Country to be printed
     */
    public void printCountry(Country country) {
        if (country != null) {
            System.out.println(country);
        }
    }

    /**
     *
     * @param countryName
     */
    public void getCountryRep(String countryName){
    }

    /**
     *
     * @param cityName
     */
    public void getCityReport(String cityName){
    }

    /**
     *
     * @param capCityName
     */
    public void getCapCityRep(String capCityName){
    }

    /**
     *
     */
    public void getPopulationRep(){
    }

    /**
     *
     */
    public void sortCountriesPopWorld(){
    }

    /**
     * @param continentName
     */
    public void sortCountriesPopContinent(String continentName){

    }

    /**
     *
     * @param regionName
     */
    public void sortCountriesPopRegion(String regionName){
    }

    /**
     *
     */
    public void nPopCountriesWorld(){
    }

    /**
     *
     * @param continentName
     */
    public void nPopCountriesContinent(String continentName){
    }

    /**
     *
     * @param regionName
     */
    public void nPopCountriesRegion(String regionName){
    }

    /**
     *
     */
    public void sortCitiesPopWorld(){
    }

    /**
     * Print all cities in a continent <br>
     * Condition: All cities in a continent (largest population to smallest)
     * @param continentName name of continent
     */
    public void sortCitiesPopContinent(String continentName){
        // Get all cities in each continent and add to list
        ArrayList<City> continentCities = new ArrayList<>();
        for (Country country : countries) {
            if (country.continent.equals(continentName)) {
                continentCities.addAll(country.cities);
            }
        }

        // Sorting - https://www.baeldung.com/java-8-comparator-comparing
        continentCities.sort(Comparator.comparing(City::getPopulation).reversed());

        // Printing cities in order
        System.out.println("All cities in " + continentName + ", sorted by population");
        for (City city : continentCities) {
            System.out.println(city);
        }
    }

    /**
     *
     * @param regionName
     */
    public void sortCitiesPopRegion(String regionName){

        ArrayList<City> sortCities = new ArrayList<City>();

        for(Country c : countries) {
            if (c.region.equals(regionName)) {
                sortCities.addAll(c.cities);
            }
        }

        sortCities.sort(Comparator.comparingInt(City::getPopulation).reversed());

        System.out.println("All cities in " + regionName + ", sorted by population");
        for(City c: sortCities){
            System.out.println(c.toString());
        }

    }

    /**
     *
     * @param countryName
     */
    public void sortCitiesPopCountry(String countryName){
    }

    /**
     *
     * @param districtName
     */
    public void sortCitiesPopDistrict(String districtName){
    }

    /**
     *
     */
    public void nPopCitiesWorld(){

    }

    /**
     *
     * @param continentName
     */
    public void nPopCitiesContinent(String continentName){
    }

    /**
     *
     * @param regionName
     */
    public void nPopCitiesRegion(String regionName){
    }

    /**
     *
     * @param countryName
     */
    public void nPopCitiesCountry(String countryName){
    }

    /**
     *
     * @param districtName
     */
    public void nPopCitiesDistrict(String districtName){
    }

    /**
     *
     */
    public void sortCapCitiesPopWorld(){
    }

    /**
     *
     * @param regionName
     */
    public void sortCapCitiesPopRegion(String regionName){
    }

    /**
     *
     * @param continentName
     */
    public void sortCapCitiesPopContinent(String continentName){
    }

    /**
     *
     * @param regionName
     */
    public void nPopCapCitiesRegion(String regionName){
    }

    /**
     *
     * @param continentName
     */
    public void nPopCapCitiesContinent(String continentName){
    }

    /**
     *
     */
    public void nPopCapCitiesWorld(){
    }
}
