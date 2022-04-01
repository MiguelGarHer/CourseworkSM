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
            } catch (SQLException sqlException) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqlException.getMessage());
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
     * Retrieves result set from SQL query
     * @param sqlQueryString MySQL query
     * @return ResultSet with all records
     * @throws SQLException Whenever a query fails
     */
    public ResultSet getResultSet(String sqlQueryString) throws SQLException {
        if (sqlQueryString.isBlank() || sqlQueryString.isEmpty()) {
            return null;
        }
        // Create an SQL statement using connection
        Statement statement = con.createStatement();
        // Execute SQL statement
        return statement.executeQuery(sqlQueryString);
    }

    /**
     * Creates a Country from result set
     * @param resultSet ResultSet from SQL query
     * @return Country
     */
    public Country createCountry(ResultSet resultSet)
    {
        try {
            Country country = new Country(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5),
                    resultSet.getInt(6),
                    resultSet.getInt(7),
                    resultSet.getDouble(8),
                    resultSet.getDouble(9),
                    resultSet.getDouble(10),
                    resultSet.getString(11),
                    resultSet.getString(12),
                    resultSet.getString(13),
                    resultSet.getInt(14),
                    resultSet.getString(15));

            // Get cities method
            country.setCities(getCities(resultSet.getString(1),resultSet.getString(1)));

            // Get languages method
            country.setLanguages(getLanguages(resultSet.getString(1)));

            return country;
        } catch (SQLException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Converts ResultSet to City object
     * @param resultSet ResultSet from SQL query
     * @return City
     */
    public City resultToCity(ResultSet resultSet, String countryName) {
        try {
            City city;
            city = new City(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    countryName,
                    resultSet.getString(4),
                    resultSet.getInt(5));
            return city;
        } catch (SQLException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    /**
     * Retrieves all cities of a country
     * @param countryCode country code
     * @return ArrayList of City
     */
    public ArrayList<City> getCities(String countryCode, String countryName)
    {
        ArrayList<City> cities = new ArrayList<>();
        try {
            String sqlQuery = "SELECT * FROM city WHERE countrycode = '" + countryCode + "';";
            ResultSet resultSet = getResultSet(sqlQuery);
            while(resultSet.next()) {
                cities.add(resultToCity(resultSet, countryName));
            }
            return cities;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException ignored) {

        }
        return cities;
    }


    /**
     * Retrieves all countries in the world
     */
    public void getCountries()
    {
        try {
            String sqlQuery = "SELECT * FROM country;";
            ResultSet resultSet = getResultSet(sqlQuery);
            // For each row returned, add new Country to list
            while (resultSet.next()) {
                Country country = createCountry(resultSet);
                countries.add(country);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Read all languages in a country
     * @param countryCode country code
     * @return list of Language objects
     */
    public ArrayList<Language> getLanguages(String countryCode) {
        ArrayList<Language> languages = new ArrayList<>();
        try {
            String sqlQuery = "SELECT * FROM countrylanguage WHERE countrycode = '" + countryCode + "';";
            ResultSet resultSet = getResultSet(sqlQuery);
            while (resultSet.next())
            {
                Language language = resultToLanguage(resultSet);
                languages.add(language);
            }
            return languages;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException ignored) {
        }
        return languages;
    }

    /**
     * Converts ResultSet to Language object
     * @param resultSet ResultSet from SQL query
     * @return Language object
     */
    public Language resultToLanguage(ResultSet resultSet) {
        try {
            boolean isOfficial = resultSet.getString(3).equals("T");
            Language language;
            language = new Language(
                    resultSet.getString(2),
                    isOfficial,
                    resultSet.getDouble(4)
            );
            return language;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
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
        // Null, empty and blank parameter check
        if (continentName == null) {
            System.out.println("Null input, no countries");
            return;
        } else if (continentName.isEmpty()) {
            System.out.println("Empty input, no countries");
            return;
        } else if (continentName.isBlank()) {
            System.out.println("Blank input, no countries");
            return;
        }

        // Get all cities in each continent and add to temporary list
        ArrayList<Country> continentCountries = new ArrayList<>();
        for (Country country : countries) {
            if (country.getContinent().equals(continentName)) {
                continentCountries.add(country);
            }
        }


        // Sort temporary list - https://www.baeldung.com/java-8-comparator-comparing
        continentCountries.sort(Comparator.comparing(Country::getPopulation).reversed());

        // Print sorted list
        System.out.println("All countries in " + continentName + ", sorted by population");
        for (Country country : continentCountries) {
            System.out.println(country);
        }

        //Write markdown file
        String fileName = "sortCountriesPopContinent" + continentName;
        MarkdownWriter.countryListToMarkdown(continentCountries, fileName);
    }

    /**
     * Print all countries in a region
     * Sorting: Largest population to smallest
     * @param regionName name of region
     */
    public void sortCountriesPopRegion(String regionName){
        //Empty, null and blank check
        if(regionName == null){
            System.out.println("Null input, no countries");
            return;
        }if(regionName.isEmpty()){
            System.out.println("Empty input, no countries");
            return;
        } else if(regionName.isBlank()){
            System.out.println("Blank input, no countries");
            return;
        }
        //Get all cities in each continent and add to temporary list
        ArrayList<Country> regionCountries = new ArrayList<>();
        for (Country country : countries){
            if(country.getRegion().equals(regionName)){
                regionCountries.add(country);
            }
        }

        //Sort temporary list
        regionCountries.sort(Comparator.comparing(Country::getPopulation).reversed());
        //Print sorted list
        System.out.println("All countries in " + regionName + ", sorted by population");
        for (Country country : regionCountries) {
            System.out.println(country);
        }

        //Write markdown file
        String fileName = "sortCountriesPopRegion" + regionName;
        MarkdownWriter.countryListToMarkdown(regionCountries, fileName);
    }

    /**
     * Print top N countries in the world
     * Sorting: Largest population to smallest
     * @param n top N
     */
    public void nPopCountriesWorld(int n){
        ///Check n is not an invalid number
        if(n<=0){
            System.out.println("Invalid number");
            return;
        }

        ArrayList<Country> nWorldCountries = new ArrayList<>();
        for (Country country: countries) {
            if (country != null)  {
                nWorldCountries.add(country);
            }
        }

        //Sort all countries by population
        nWorldCountries.sort(Comparator.comparing(Country::getPopulation).reversed());

        //Print
        System.out.println("Top " + n + " countries in the world, sorted by population");
        for (int i = 0; i < n; i++) {
            System.out.println(nWorldCountries.get(i));
        }

        //Write to markdown file
        String fileName = "nPopCountriesWorld" + n;
        MarkdownWriter.countryListToMarkdown(nWorldCountries, n, fileName);
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

        MarkdownWriter.cityListToMarkdown(allCities, "sortCitiesPopWorld");
    }

    /**
     * Print all cities in a continent <br>
     * Sorting: Largest population to smallest
     * @param continentName name of continent
     */
    public void sortCitiesPopContinent(String continentName){
        // Null, empty and blank parameter check
        if (continentName == null) {
            System.out.println("Null input, no cities");
            return;
        } else if (continentName.isEmpty()) {
            System.out.println("Empty input, no cities");
            return;
        } else if (continentName.isBlank()) {
            System.out.println("Blank input, no cities");
            return;
        }

        // Get all cities in each continent and add to temporary list
        ArrayList<City> continentCities = new ArrayList<>();
        for (Country country : countries) {
            if (country.getContinent().equals(continentName)) {
                continentCities.addAll(country.getCities());
            }
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
        // Null, empty and blank parameter check
        if (regionName == null) {
            System.out.println("Null input, no cities");
            return;
        } else if (regionName.isEmpty()) {
            System.out.println("Empty input, no cities");
            return;
        } else if (regionName.isBlank()) {
            System.out.println("Blank input, no cities");
            return;
        }

        ArrayList<City> sortCities = new ArrayList<>();

        for(Country c : countries) {
            if (c.getRegion().equals(regionName)) {
                sortCities.addAll(c.getCities());
            }
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
        // Null, empty and blank parameter check
        if (countryName == null) {
            System.out.println("Null input, no cities");
            return;
        } else if (countryName.isEmpty()) {
            System.out.println("Empty input, no cities");
            return;
        } else if (countryName.isBlank()) {
            System.out.println("Blank input, no cities");
            return;
        }

        //Get all cities in every country and add to list
        ArrayList<City> countryCities = new ArrayList<>();
        for (Country country : countries) {
            if (country.getName().equals(countryName)) {
                countryCities.addAll(country.getCities());
            }
        }            System.out.println("No cities");

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
        // Null, empty and blank parameter check
        if (districtName == null) {
            System.out.println("Null input, no cities");
            return;
        } else if (districtName.isEmpty()) {
            System.out.println("Empty input, no cities");
            return;
        } else if (districtName.isBlank()) {
            System.out.println("Blank input, no cities");
            return;
        }

        //Get all cities in every country and add to list
        ArrayList<City> districtCities = new ArrayList<>();
        for (Country country : countries) {
            for (City city : country.getCities()) {
                if (city.getDistrict().equals(districtName)) {
                    districtCities.add(city);
                }
            }
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

        //Check n is not an invalid number (negative or zero)
        if(n<1){
            System.out.println("Invalid number");
            return;
        }
        ArrayList<City> worldCities = new ArrayList<>();
        for (Country country: countries){
            worldCities.addAll(country.getCities());
        }
        //Sort all cities by population
        worldCities.sort(Comparator.comparing(City::getPopulation).reversed());

        //Print top N list
        System.out.println("Top " + n + " cities in the world, sorted by population");
        for (int i = 0; i < n; i++) {
            System.out.println(worldCities.get(i));
        }
    }


    /**
     * Print top N cities in a continent
     * Sorting: Largest population to smallest
     * @param continentName name of continent
     * @param n top N
     */
    public void nPopCitiesContinent(String continentName, int n) {
        // Null, empty and blank parameter check
        if (continentName == null) {
            System.out.println("Null input, no cities");
            return;
        } else if (continentName.isEmpty()) {
            System.out.println("Empty input, no cities");
            return;
        } else if (continentName.isBlank()) {
            System.out.println("Blank input, no cities");
            return;
        }

        // Non negative and zero parameter check
        if (n < 1) {
            System.out.println("Invalid number");
            return;
        }

        //Get all cities in specified continent and add to list
        ArrayList<City> continentCities = new ArrayList<>();
        for (Country country : countries) {
            if (country.getContinent().equals(continentName)) {
                continentCities.addAll(country.getCities());
            }
        }

        //Sort all cities by population
        continentCities.sort(Comparator.comparing(City::getPopulation).reversed());

        //Print list until nth element
        System.out.println("Top " + n + " cities in " + continentName + ", sorted by population");
        for (int i = 0; i < n; i++) {
            System.out.println(continentCities.get(i));
        }
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

        ArrayList<City> sortCapCities = new ArrayList<City>();

            for (Country country : countries) {
                for (City city : country.getCities()) {
                    if (city.getId() == country.getCapital()) {
                            sortCapCities.add(city);
                    }
                }
            }

        //Sort capital cities
        sortCapCities.sort(Comparator.comparingInt(City::getPopulation).reversed());

        //Print
        System.out.println("All capital cities in the World, sorted by population");
        for (City c : sortCapCities) {
            System.out.println(c.toString());
        }
    }
    
    /**
     * Print capital cities in a region
     * Sorting: Largest population to smallest
     * @param regionName name of region
     */
    public void sortCapCitiesPopRegion(String regionName){

        ArrayList<City> sortCapCities = new ArrayList<City>();

        //NULL checker
        if(regionName != null) {

            for (Country c : countries) {
                if (c.getRegion().equals(regionName)) {
                    for (City city : c.getCities()) {
                        if (city.getId() == c.getCapital()) {
                            sortCapCities.add(city);
                        }
                    }
                }
            }

            //EMPTY checker
            if(!sortCapCities.isEmpty()) {

                sortCapCities.sort(Comparator.comparingInt(City::getPopulation).reversed());

                System.out.println("All capital cities in " + regionName + ", sorted by population");
                for (City c : sortCapCities) {
                    System.out.println(c.toString());
                }
            }
            else {
                System.out.println("No capital cities in this region");
            }
        }
        else {
            System.out.println("REGION NAME IS NULL");
        }
    }

    /**
     * Print capital cities in a continent
     * Sorting: Largest population to smallest
     * @param continentName name of continent
     */
    public void sortCapCitiesPopContinent(String continentName){

        ArrayList<City> sortCapCities = new ArrayList<City>();

        //NULL checker
        if(continentName != null) {

            for (Country c : countries) {
                if (c.getContinent().equals(continentName)) {
                    for (City city : c.getCities()) {
                        if (city.getId() == c.getCapital()) {
                            sortCapCities.add(city);
                        }
                    }
                }
            }

            //EMPTY checker
            if(!sortCapCities.isEmpty()) {

                sortCapCities.sort(Comparator.comparingInt(City::getPopulation).reversed());

                System.out.println("All capital cities in " + continentName + ", sorted by population");
                for (City c : sortCapCities) {
                    System.out.println(c.toString());
                }
            }
            else {
                System.out.println("No capital cities in this continent");
            }
        }
        else {
            System.out.println("CONTINENT NAME IS NULL");
        }
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

        ArrayList<City> sortCapCities = new ArrayList<>();

        //NULL checker
        if(continentName != null) {
            for (Country c : countries) {
                if (c.getContinent().equals(continentName)) {
                    for (City city : c.getCities()) {
                        if (city.getId() == c.getCapital()) {
                            sortCapCities.add(city);
                        }
                    }
                }
            }

            //EMPTY checker
            if(!sortCapCities.isEmpty()) {

                sortCapCities.sort(Comparator.comparingInt(City::getPopulation).reversed());

                System.out.println("The " + n + " capital cities in " + continentName + ", sorted by population");
                for (int i = 0; i < n; i++) {
                    System.out.println(sortCapCities.get(i));
                }
            }
            else {
                System.out.println("No capital cities in this continent");
            }
        }
        else {
            System.out.println("CONTINENT NAME IS NULL");
        }
    }

    /**
     * Print top N capital cities in the world
     * Sorting: Largest population to smallest
     * @param n top N
     */
    public void nPopCapCitiesWorld(int n){
    }
}
