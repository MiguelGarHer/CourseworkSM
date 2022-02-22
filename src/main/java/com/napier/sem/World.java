package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class World {

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /// ArrayList containing all the data of the countries
    private ArrayList<Country> countries = new ArrayList<>();

    /**
     *
     */
    public World(){
        // Connect to MySQL
        connect();

        // Read all countries
        getCountries();

        // Test read country
        displayCountry(countries.get(0));

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
                Thread.sleep(15000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
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
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT *" +
                    "FROM country;";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                Country country = new Country(rset.getString(1),rset.getString(2),rset.getString(3),rset.getString(4),rset.getDouble(5),rset.getInt(6),rset.getInt(7),rset.getDouble(8),rset.getDouble(9),rset.getDouble(10),rset.getString(11),rset.getString(12),rset.getString(13),rset.getInt(14),rset.getString(15));
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
     *
     * @param country
     */
    public void displayCountry(Country country) {
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
     *
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
     *
     * @param continentName
     */
    public void sortCitiesPopContinent(String continentName){
    }

    /**
     *
     * @param regionName
     */
    public void sortCitiesPopRegion(String regionName){
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
