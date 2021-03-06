package com.napier.sem;

import java.sql.*;
import java.util.*;

/**
 * Facade/Interface class modelling a world
 */
public class World {

    private Connection con;                    // Connection to MySQL database
    private final List<Country> countries;     // ArrayList containing all the data of the countries

    /**
     * Constructor for World
     * <br>
     * Initiates database communication and reading of data
     */
    public World(){
        countries = new ArrayList<>();
        con = null;
    }

    /**
     * Connect to the MySQL database.
     * @param location Path to database
     * @param delay Delay between each connection attempt
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
            country.setCities(getCities(resultSet.getString(1),resultSet.getString(2)));

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
    public List<City> getCities(String countryCode, String countryName)
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
    public List<Language> getLanguages(String countryCode) {
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
            boolean isOfficial = "T".equals(resultSet.getString(3));
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
     * Prints population report of all available continents
     * Generates a markdown report
     */
    public void populationReportAllContinents() {
        HashSet<String> continentNames = new HashSet<>();
        for (Country country : countries) {
            continentNames.add(country.getContinent());
        }

        String fileName = "populationReportAllContinents";
        for (String continent : continentNames) {
            populationReportContinent(continent, fileName);
        }
    }

    /**
     * Print and generate population report of a continent
     * @param continentName specified continent
     * @param fileName name of markdown file
     */
    public void populationReportContinent(String continentName, String fileName){
        // Null, empty and blank parameter check
        if (continentName == null) {
            System.out.println("Null input on continent name");
            return;
        } else if (continentName.isBlank()) {
            System.out.println("Blank input on continent name");
            return;
        }

        if (fileName == null) {
            System.out.println("Null input on file name");
            return;
        } else if (fileName.isBlank()) {
            System.out.println("Blank input on file name");
            return;
        }

        long totalPopulation = 0;
        long cityPopulation = 0;
        long countrySidePopulation;

        for (Country country : countries) {
            if (country.getContinent().equals(continentName)) {
                totalPopulation += country.getPopulation();
                for (City city : country.getCities()) {
                    cityPopulation += city.getPopulation();
                }
            }
        }

        countrySidePopulation = totalPopulation - cityPopulation;

        int cityPopulationPercentage = (int) Math.round(((double) cityPopulation  / totalPopulation) * 100);
        int countrySidePopulationPercentage = (int) Math.round(((double) countrySidePopulation / totalPopulation) * 100);

        //Print
        System.out.println("Population report for " + continentName);
        System.out.println("Total population: " + totalPopulation);
        System.out.println("Population living in cities: " + cityPopulation + "(" + cityPopulationPercentage + "%)");
        System.out.println("Population not living in cities: " + countrySidePopulation + "(" + countrySidePopulationPercentage + "%)");

        //Markdown
        MarkdownWriter.populationReportToMarkdown(
                continentName,
                totalPopulation,
                cityPopulation,
                cityPopulationPercentage,
                countrySidePopulation,
                countrySidePopulationPercentage,
                fileName
        );
    }


    /**
     * Prints and generates population report of all regions
     */
    public void populationReportAllRegions() {
        HashSet<String> regionNames = new HashSet<>();
        for (Country country : countries) {
            regionNames.add(country.getRegion());
        }

        String fileName = "populationReportAllRegions";
        for (String regionName : regionNames) {
            populationReportRegion(regionName, fileName);
        }
    }

    /**
     * Prints and generates population report of a region
     * @param regionName specified region
     * @param fileName name of markdown file
     */
    public void populationReportRegion(String regionName, String fileName) {
        // Null, empty and blank parameter check
        if (regionName == null) {
            System.out.println("Null input on continent name");
            return;
        } else if (regionName.isBlank()) {
            System.out.println("Blank input on continent name");
            return;
        }

        if (fileName == null) {
            System.out.println("Null input on file name");
            return;
        } else if (fileName.isBlank()) {
            System.out.println("Blank input on file name");
            return;
        }

        long totalPopulation = 0;
        long cityPopulation = 0;
        long countrySidePopulation;

        for (Country country : countries) {
            if (country.getRegion().equals(regionName)) {
                totalPopulation += country.getPopulation();
                for (City city : country.getCities()) {
                    cityPopulation += city.getPopulation();
                }
            }
        }

        countrySidePopulation = totalPopulation - cityPopulation;

        int cityPopulationPercentage = (int) Math.round(((double) cityPopulation  / totalPopulation) * 100);
        int countrySidePopulationPercentage = (int) Math.round(((double) countrySidePopulation / totalPopulation) * 100);

        //Print
        System.out.println("Population report for " + regionName);
        System.out.println("Total population: " + totalPopulation);
        System.out.println("Population living in cities: " + cityPopulation + "(" + cityPopulationPercentage + "%)");
        System.out.println("Population not living in cities: " + countrySidePopulation + "(" + countrySidePopulationPercentage + "%)");

        //Markdown
        MarkdownWriter.populationReportToMarkdown(
                regionName,
                totalPopulation,
                cityPopulation,
                cityPopulationPercentage,
                countrySidePopulation,
                countrySidePopulationPercentage,
                fileName
        );
    }

    /**
     * Prints and generates population report of all countries
     */
    public void populationReportAllCountries() {
        String fileName = "populationReportAllCountries";
        for (Country country : countries ) {
            populationReportCountry(country, fileName);
        }
    }

    /**
     * Prints and generates population report of a country
     * @param country specified country
     * @param fileName name of markdown file
     */
    public void populationReportCountry(Country country, String fileName) {
        // Null, empty and blank parameter check
        if (country == null) {
            System.out.println("Null input on country");
            return;
        }

        if (fileName == null) {
            System.out.println("Null input on file name");
            return;
        } else if (fileName.isBlank()) {
            System.out.println("Blank input on file name");
            return;
        }

        long totalPopulation = 0;
        long cityPopulation = 0;
        long countrySidePopulation;

        totalPopulation += country.getPopulation();
        for (City city : country.getCities()) {
            cityPopulation += city.getPopulation();
        }

        countrySidePopulation = totalPopulation - cityPopulation;

        int cityPopulationPercentage = (int) Math.round(((double) cityPopulation  / totalPopulation) * 100);
        int countrySidePopulationPercentage = (int) Math.round(((double) countrySidePopulation / totalPopulation) * 100);

        //Print
        System.out.println("Population report for " + country.getName());
        System.out.println("Total population: " + totalPopulation);
        System.out.println("Population living in cities: " + cityPopulation + "(" + cityPopulationPercentage + "%)");
        System.out.println("Population not living in cities: " + countrySidePopulation + "(" + countrySidePopulationPercentage + "%)");

        //Markdown
        MarkdownWriter.populationReportToMarkdown(
                country.getName(),
                totalPopulation,
                cityPopulation,
                cityPopulationPercentage,
                countrySidePopulation,
                countrySidePopulationPercentage,
                fileName
        );
    }

    /**
     * Prints and writes population of the world
     */
    public void populationWorld() {
        System.out.println("Population of the world: " + getWorldPopulation());

        String fileName = "populationWorld";
        MarkdownWriter.populationToMarkdown("World", getWorldPopulation(), fileName);
    }

    /**
     * Calculates and returns the world population
     * @return current world population
     */
    public long getWorldPopulation() {
        long population = 0;
        for (Country country : countries) {
            population += country.getPopulation();
        }
        return population;
    }

    /**
     * Prints and writes populations of all districts
     */
    public void populationAllDistricts() {
        String fileName = "populationAllDistricts";
        HashMap<String, Long> districts = (HashMap<String, Long>) getAllDistrictPopulations(countries);

        for (String district : districts.keySet()) {
            MarkdownWriter.populationToMarkdown(district, districts.get(district), fileName);
        }
    }

    /**
     * Calculates and returns the total population of a district
     * @param countryList ArrayList of Country objects
     * @return HashMap consisting of country names (K) and populations (V)
     */
    public Map<String, Long> getAllDistrictPopulations(List<Country> countryList) {
        if (countryList == null) {
            System.out.println("Null country list");
            return null;
        }
        HashMap<String, Long> districts = new HashMap<>();
        for (Country country : countryList) {
            for (City city : country.getCities()) {
                if (districts.containsKey(city.getDistrict())) {
                    districts.put(city.getDistrict(), districts.get(city.getDistrict()) + city.getPopulation());
                } else {
                    districts.put(city.getDistrict(), (long) city.getPopulation());
                }
            }
        }
        return districts;
    }

    /**
     * Prints and writes populations of all cities
     */
    public void populationAllCities() {
        String fileName = "populationAllCities";
        for (Country country : countries) {
            for (City city : country.getCities()) {
                populationCity(city, fileName);
            }
        }
    }

    /**
     * Prints and writes population of a city
     * @param city specified city
     * @param fileName file
     */
    public void populationCity(City city, String fileName) {
        // Null, empty and blank parameter check
        if (city == null) {
            System.out.println("Null input on city");
            return;
        }

        if (fileName == null) {
            System.out.println("Null input on file name");
            return;
        } else if (fileName.isBlank()) {
            System.out.println("Blank input on file name");
            return;
        }

        //Print
        System.out.println("Population report for " + city.getName());
        System.out.println("Total population: " + city.getPopulation());

        //Markdown
        MarkdownWriter.populationToMarkdown(
                city.getName(),
                city.getPopulation(),
                fileName
        );
    }

    /**
     * Generates and writes language report of all languages
     */
    public void languageReportAllLanguages() {
        HashMap<String, Long> languages = new HashMap<>();
        for (Country country : countries) {
            for (Language language : country.getLanguages()) {
                long langPopulation = Math.round((double) country.getPopulation() / 100 * language.getPercentage());
                if (!languages.containsKey(language.getLanguage())) {
                    languages.put(language.getLanguage(), 0L);
                }
                languages.put(language.getLanguage(), languages.get(language.getLanguage()) + langPopulation);

            }
        }

        ArrayList<Map.Entry<String, Long>> sortList = new ArrayList<>(languages.entrySet());

        //Sort all the languages from largest speaking population to smallest
        sortList.sort(Map.Entry.comparingByValue());
        Collections.reverse(sortList);

        //Markdown
        String fileName = "languageReportAllLanguages";
        for (Map.Entry<String,Long> entry : sortList) {
            MarkdownWriter.languageToMarkdown(
                    entry.getKey(),
                    entry.getValue(),
                    (double) Math.round((double) entry.getValue() / getWorldPopulation() * 100000) / 1000,
                    fileName);
        }
    }

    /**
     * Converts language HashMap to ArrayList
     * @param languageMap HashMap consisting of language names and their populations
     * @return ArrayList of Map.Entry
     */
    public List<Map.Entry<String, Long>> languageHashMapToArrayList(Map<String,Long> languageMap) {
        ArrayList<Map.Entry<String, Long>> sortList = new ArrayList<>(languageMap.entrySet());
        sortList.sort(Map.Entry.comparingByValue());
        Collections.reverse(sortList);

        return sortList;
    }

    /**
     * Generates and writes language report of selected languages
     * @param languageList ArrayList of selected languages
     */
    public void languageReportSelectedLanguages(List<String> languageList) {
        String fileName = "languageReportSelectedLanguages";

        HashMap<String, Long> selectedLanguages = new HashMap<>();
        for (String languageName : languageList) {
            selectedLanguages.put(languageName, 0L);
        }

        for (Country country : countries) {
            for (Language language : country.getLanguages()) {
                if (selectedLanguages.containsKey(language.getLanguage())) {
                    long langPopulation = Math.round((double) country.getPopulation() / 100 * language.getPercentage());
                    if (selectedLanguages.get(language.getLanguage()) == 0L) {
                        selectedLanguages.put(language.getLanguage(), langPopulation);
                    } else {
                        selectedLanguages.put(
                                language.getLanguage(),
                                selectedLanguages.get(language.getLanguage()) + langPopulation);
                    }
                }
            }
        }

        //Sort all the languages from largest speaking population to smallest - https://www.baeldung.com/java-8-comparator-comparing
        ArrayList<Map.Entry<String,Long>> sortedLanguages = (ArrayList<Map.Entry<String, Long>>) languageHashMapToArrayList(selectedLanguages);

        for (Map.Entry<String,Long> entry : sortedLanguages) {
            MarkdownWriter.languageToMarkdown(
                    entry.getKey(),
                    entry.getValue(),
                    (double) Math.round((double) entry.getValue() / getWorldPopulation() * 100000) / 1000,
                    fileName);
        }
    }

    /**
     * Print all countries in the world
     * Sorting: Largest population to smallest
     */
    public void sortCountriesPopWorld(){

        //Arraylist of countries that stores all the countries in the world
        ArrayList<Country> sortCountries = new ArrayList<>(this.countries);

        //Sort all the countries from larges population to smallest - https://www.baeldung.com/java-8-comparator-comparing
        sortCountries.sort(Comparator.comparing(Country::getPopulation).reversed());

        System.out.println("All the countries in the world organized by population");

        //Print out all the countries
        for(Country c : sortCountries){
            System.out.println(c);
        }

        //Write markdown file
        String fileName = "sortCountriesPopWorld";
        MarkdownWriter.countryListToMarkdown(sortCountries, fileName);
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
        } else if (regionName.isBlank()){
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

        //Sort temporary list - https://www.baeldung.com/java-8-comparator-comparing
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

        //Sort all countries by population - https://www.baeldung.com/java-8-comparator-comparing
        nWorldCountries.sort(Comparator.comparing(Country::getPopulation).reversed());

        //Print
        System.out.println("Top " + n + " countries in the world, sorted by population");
        for (int i = 0; i < n; i++) {
            System.out.println(nWorldCountries.get(i));
        }

        //Write to markdown file
        String fileName = "nPopCountriesWorldTop" + n;
        MarkdownWriter.countryListToMarkdown(nWorldCountries, n, fileName);
    }

    /**
     * Print top N countries in a continent
     * Sorting: Largest population to smallest
     * @param continentName name of continent
     * @param n top N
     */
    public void nPopCountriesContinent(String continentName, int n) {
        if (continentName == null) {
            System.out.println("Null continent name");
            return;
        } else if (continentName.isBlank()) {
            System.out.println("Blank continent name");
            return;
        }

        if (n <= 0){
            System.out.println("Invalid number");
            return;
        }

        ArrayList<Country> continentCountries = new ArrayList<>();
        for (Country country : countries) {
            if (country.getContinent().equals(continentName)) {
                continentCountries.add(country);
            }

        }

        //Sort all countries by population - https://www.baeldung.com/java-8-comparator-comparing
        continentCountries.sort(Comparator.comparing(Country::getPopulation).reversed());

        //Print
        System.out.println("Top " + n + " countries in " + continentName + ", sorted by population");
        for (int i = 0; i < n; i++) {
            System.out.println(continentCountries.get(i));
        }

        //Write to markdown file
        String fileName = "nPopCountriesContinent" + continentName + "Top" + n;
        MarkdownWriter.countryListToMarkdown(continentCountries, n, fileName);
    }

    /**
     * Print top N countries in a region
     * Sorting: Largest population to smallest
     * @param regionName name of region
     * @param n top N
     */
    public void nPopCountriesRegion(String regionName, int n){
        // Null, empty and blank parameter check
        if (regionName == null) {
            System.out.println("Null input, no region");
            return;
        } else if (regionName.isBlank()) {
            System.out.println("Blank input, no region");
            return;
        }

        // Non negative and zero parameter check
        if (n < 1) {
            System.out.println("Invalid number");
            return;
        }

        //Get all countries in specified region and add to list
        ArrayList<Country> regionCountries = new ArrayList<>();
        for (Country country : countries) {
            if (country.getRegion().equals(regionName)) {
                regionCountries.add(country);
            }
        }

        // Sorting - https://www.baeldung.com/java-8-comparator-comparing
        regionCountries.sort(Comparator.comparing(Country::getPopulation).reversed());

        //Print list until nth element
        System.out.println("Top " + n + " countries in " + regionName + ", sorted by population");
        for (int i = 0; i < n; i++) {
            System.out.println(regionCountries.get(i));
        }

        //Write to markdown file
        String fileName = "nPopCountriesRegion" + regionName + "Top" + n;
        MarkdownWriter.countryListToMarkdown(regionCountries, n, fileName);
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

        // Sorting - https://www.baeldung.com/java-8-comparator-comparing
        allCities.sort(Comparator.comparing(City::getPopulation).reversed());

        //Print
        System.out.println("All cities in the world, sorted by population");
        for (City city : allCities) {
            System.out.println(city);
        }

        //Markdown file
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

        String fileName = "sortCitiesPopContinent" + continentName;
        MarkdownWriter.cityListToMarkdown(continentCities, fileName);
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

        //Markdown
        String fileName = "sortCitiesPopRegion" + regionName;
        MarkdownWriter.cityListToMarkdown(sortCities, fileName);
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

        //Markdown file
        String fileName = "sortCitiesPopCountry" + countryName;
        MarkdownWriter.cityListToMarkdown(countryCities, fileName);
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

        //Markdown files
        String fileName = "sortCitiesPopDistrict" + districtName;
        MarkdownWriter.cityListToMarkdown(districtCities, fileName);
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

        //Markdown
        String fileName = "nPopCitiesWorldTop" + n;
        MarkdownWriter.cityListToMarkdown(worldCities, n, fileName);
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

        //Markdown
        String fileName = "nPopCitiesContinent" + continentName + "Top" + n;
        MarkdownWriter.cityListToMarkdown(continentCities, n, fileName);
    }

    /**
     * Print top N cities in a region
     * Sorting: Largest population to smallest
     * @param regionName name of region
     * @param n top N
     */
    public void nPopCitiesRegion(String regionName, int n){
        // Null, empty and blank parameter check
        if (regionName == null) {
            System.out.println("Null input, no region");
            return;
        } else if (regionName.isBlank()) {
            System.out.println("Blank input, no region");
            return;
        }

        // Non negative and zero parameter check
        if (n < 1) {
            System.out.println("Invalid number");
            return;
        }

        //Get all cities in specified region and add to list
        ArrayList<City> regionCities = new ArrayList<>();
        for (Country country : countries) {
            if (country.getRegion().equals(regionName)) {
                regionCities.addAll(country.getCities());
            }
        }

        //Sort all cities by population
        regionCities.sort(Comparator.comparing(City::getPopulation).reversed());

        //Print list until nth element
        System.out.println("Top " + n + " cities in " + regionName + ", sorted by population");
        for (int i = 0; i < n; i++) {
            System.out.println(regionCities.get(i));
        }

        //Markdown
        String fileName = "nPopCitiesRegion" + regionName + "Top" + n;
        MarkdownWriter.cityListToMarkdown(regionCities, n, fileName);
    }

    /**
     * Print top N cities in a country
     * Sorting: Largest population to smallest
     * @param countryName name of country
     * @param n top N
     */
    public void nPopCitiesCountry(String countryName, int n){
        //Check n is not an invalid number
        if(n<=0){
            System.out.println("Invalid number");
            return;
        }

        //Check Country input is not invalid
        if (countryName == null) {
            System.out.println("Null input, no such city.");
            return;
        } else if (countryName.isEmpty()) {
            System.out.println("Empty input.");
            return;
        } else if (countryName.isBlank()) {
            System.out.println("Blank input.");
            return;
        }

        //Get all cities in the country
        ArrayList<City> nCountryCity = new ArrayList<>();
        for (Country country : countries) {
            if (country.getName().equals(countryName)) {
                nCountryCity.addAll(country.getCities());
            }
        }

        //Sort all cities by population
        nCountryCity.sort(Comparator.comparingInt(City::getPopulation).reversed());

        //Print it
        System.out.println("Top " + n + " populated cities in " + countryName + ".");
        for (int i = 0; i < n; i++) {
            System.out.println(nCountryCity.get(i));
        }

        //Markdown
        String fileName = "nPopCitiesCountry" + countryName + "Top" + n;
        MarkdownWriter.cityListToMarkdown(nCountryCity, n, fileName);
    }

    /**
     * Print top N cities in a district
     * @param districtName name of district
     * @param n top N
     */
    public void nPopCitiesDistrict(String districtName, int n){
        if(n<1){
            System.out.println("Invalid number");
            return;
        }

        // Null, empty and blank parameter check
        if (districtName == null) {
            System.out.println("Null input, no cities");
            return;
        } else if (districtName.isBlank()) {
            System.out.println("Blank input, no cities");
            return;
        }

        //ArrayList to store all the cities in that district
        ArrayList<City> sortCities = new ArrayList<>();

        //Search all the cities whose district is "districtName"
        for(Country c : countries){
            for(City city : c.getCities()){
                if(city.getDistrict().equals(districtName)){
                    //Add the city to the arraylist
                    sortCities.add(city);
                }
            }
        }

        //Sort all the cities from the largest population to smallest
        sortCities.sort(Comparator.comparing(City::getPopulation).reversed());

        //Print out all the cities sorted
        System.out.println("All the cities in the district " + districtName + "sorted from largest population to smallest.");
        for (City c : sortCities){
            System.out.println(c.getName());
        }

        //Markdown
        String fileName = "nPopCitiesDistrict" + districtName + "Top" + n;
        MarkdownWriter.cityListToMarkdown(sortCities, n, fileName);
    }

    /**
     * Print capital cities in the world
     * Sorting: Largest population to smallest
     */
    public void sortCapCitiesPopWorld(){
        ArrayList<City> sortCapCities = new ArrayList<>();

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

        // Markdown
        String fileName = "sortCapCitiesPopWorld";
        MarkdownWriter.cityListToMarkdown(sortCapCities, fileName);
    }
    
    /**
     * Print capital cities in a region
     * Sorting: Largest population to smallest
     * @param regionName name of region
     */
    public void sortCapCitiesPopRegion(String regionName){
        ArrayList<City> sortCapCities = new ArrayList<>();

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

                //Markdown
                String fileName = "sortCapCitiesPopRegion" + regionName;
                MarkdownWriter.cityListToMarkdown(sortCapCities, fileName);
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

                System.out.println("All capital cities in " + continentName + ", sorted by population");
                for (City c : sortCapCities) {
                    System.out.println(c.toString());
                }

                //Markdown
                String fileName = "sortCapCitiesPopContinent" + continentName;
                MarkdownWriter.cityListToMarkdown(sortCapCities, fileName);
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
        // Parameter check for regionName
        if (regionName == null) {
            System.out.println("Null region name");
            return;
        } else if (regionName.isBlank()) {
            System.out.println("Blank input, no capital cities");
            return;
        }

        // Parameter check for n
        if (n <= 0) {
            System.out.println("Invalid number");
            return;
        }
        ArrayList<City> regionCapCities = new ArrayList<>();

        // Get all cap cities in the region
        for (Country country : countries) {
            if (country.getRegion().equals(regionName)) {
                for (City city: country.getCities()) {
                    if (city.getId() == country.getCapital()) {
                        regionCapCities.add(city);
                    }
                }
            }
        }

        // Sort
        regionCapCities.sort(Comparator.comparingInt(City::getPopulation).reversed());

        // Print
        System.out.println("Top " + n + " populated capital cities in" + regionName);
        for (int i = 0; i < n; i++) {
            System.out.println(regionCapCities.get(i));
        }

        // Markdown
        String fileName = "nPopCapCitiesRegion" + regionName + "Top" + n;
        MarkdownWriter.cityListToMarkdown(regionCapCities, n, fileName);
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

                //Markdown
                String fileName = "nPopCapCitiesContinent" + continentName + "Top" + n;
                MarkdownWriter.cityListToMarkdown(sortCapCities,n, fileName);
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
    public void nPopCapCitiesWorld(int n) {
        ///Check n is not an invalid number
        if (n <= 0) {
            System.out.println("Invalid number");
            return;
        }

        //Get all Capital cities in the world
        ArrayList<City> nWorldCapCities = new ArrayList<>();

        for (Country country : countries) {
            for (City city : country.getCities()) {
                if (city.getId() == country.getCapital()) {
                    nWorldCapCities.add(city);
                }
            }
        }

        //Sort capital cities
        nWorldCapCities.sort(Comparator.comparingInt(City::getPopulation).reversed());

        //Print
        System.out.println("Top " + n + " capital cities in the world, sorted by population");
        for (int i = 0; i < n; i++) {
            System.out.println(nWorldCapCities.get(i));
        }

        //Markdown
        String fileName = "nPopCapCitiesWorld" + "Top" + n;
        MarkdownWriter.cityListToMarkdown(nWorldCapCities, n, fileName);
    }
}
