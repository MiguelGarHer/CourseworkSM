package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Test Class: Class testing World class
 */
class WorldTest {
    static World world; // World object

    // Stream collecting console output
    // Testing for println: https://www.baeldung.com/java-testing-system-out-println
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    /**
     * Initializing method, executed before all tests
     */
    @BeforeAll
    static void init() {
        world = new World();
    }

    /**
     * Preparation method, executed before each test
     */
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    /**
     * Unit Test: getResultSet - null parameter test (sqlQueryString)
     */
    @Test
    void getResultSetTestNull() {
        assertThrows(NullPointerException.class,
                () -> world.getResultSet(null),
                "NullPointer exception was not thrown");
    }

    /**
     * Unit Test: getResultSet - blank parameter test (sqlQueryString)
     */
    @Test
    void getResultSetTestBlank() throws SQLException {
        assertNull(world.getResultSet(" "), "Null exception not thrown");
    }

    /**
     * Unit Test: getResultSet - empty parameter test (sqlQueryString)
     */
    @Test
    void getResultSetTestEmpty() throws SQLException {
        assertNull(world.getResultSet(""), "Null exception not thrown");
    }

    /**
     * Unit Test: createCountry - null parameter test (resultSet)
     */
    @Test
    void createCountryTestNull() {
        assertNull(world.createCountry(null), "Country object is not null");
    }

    /**
     * Unit Test: resultToCity - blank parameter test (resultSet, countryName)
     */
    @Test
    void resultToCityTestNull() {
        assertNull(world.resultToCity(null, null), "City object is not null");
    }

    /**
     * Unit Test: getCities - null parameter test (countryCode, countryName)
     */
    @Test
    void getCitiesTestNull() {
        assertTrue(world.getCities(null, null).isEmpty(), "City list is not empty");

    }

    /**
     * Unit Test: getCities - blank parameter test (countryCode, countryName)
     */
    @Test
    void getCitiesTestBlank() {
        assertTrue(world.getCities("  ", "  ").isEmpty(), "City list is not empty");
    }

    /**
     * Unit Test: getCities - empty parameter test (countryCode, countryName)
     */
    @Test
    void getCitiesTestEmpty() {
        assertTrue(world.getCities("", "").isEmpty(), "City list is not empty");
    }

    /**
     * Unit Test: getLanguages - null parameter test (countryCode)
     */
    @Test
    void getLanguagesTestNull() {
        assertTrue(world.getLanguages(null).isEmpty(), "Language list is not empty");
    }

    /**
     * Unit Test: resultToLanguage - null parameter test (resultSet)
     */
    @Test
    void resultToLanguageTest() {
        assertThrows(NullPointerException.class,
                () -> world.resultToLanguage(null),
                "NullPointerException not thrown");
    }

    /**
     * Unit Test: sortCitiesPopRegion - null parameter test (regionName)
     */
    @Test
    void sortCitiesPopRegionTestNull() {
        world.sortCitiesPopRegion(null);
        assertEquals("Null input, no cities",
                outputStreamCaptor.toString().trim(),
                "Null region name was passed");
    }

    /**
     * Unit Test: sortCitiesPopRegion - empty/blank parameter test (regionName)
     */
    @Test
    void sortCitiesPopRegionTestBlank() {
        world.sortCitiesPopRegion("  ");
        assertEquals("Blank input, no cities",
                outputStreamCaptor.toString().trim(),
                "Blank region name was passed");
    }

    /**
     * Unit Test: sortCitiesPopCountry - null parameter test (countryName)
     */
    @Test
    void sortCitiesPopCountryTestNull() {
        world.sortCitiesPopCountry(null);
        assertEquals("Null input, no cities",
                outputStreamCaptor.toString().trim(),
                "Null countryName was passed");
    }

    /**
     * Unit Test: sortCitiesPopCountry - blank/empty parameter test (countryName)
     */
    @Test
    void sortCitiesPopCountryTestBlank() {
        world.sortCitiesPopCountry("  ");
        assertEquals("Blank input, no cities",
                outputStreamCaptor.toString().trim(),
                "Blank countryName was passed");
    }

    /**
     * Unit Test: sortCitiesPopDistrict - null parameter test (districtName)
     */
    @Test
    void sortCitiesPopDistrictTestNull() {
        world.sortCitiesPopDistrict(null);
        assertEquals("Null input, no cities",
                outputStreamCaptor.toString().trim(),
                "Null districtName was passed");
    }

    /**
     * Unit Test: sortCitiesPopDistrict - blank/empty parameter test (districtName)
     */
    @Test
    void sortCitiesPopDistrictTestBlank() {
        world.sortCitiesPopDistrict("  ");
        assertEquals("Blank input, no cities",
                outputStreamCaptor.toString().trim(),
                "Blank districtName was passed");
    }

    /**
     * Unit Test: sortCitiesPopContinent - null parameter test (continentName)
     */
    @Test
    void sortCitiesPopContinentTestNull() {
        world.sortCitiesPopContinent(null);
        assertEquals("Null input, no cities",
                outputStreamCaptor.toString().trim(),
                "Null continentName was passed");
    }

    /**
     * Unit Test: sortCitiesPopContinent - blank/empty parameter test (continentName)
     */
    @Test
    void sortCitiesPopContinentTestBlank() {
        world.sortCitiesPopContinent("  ");
        assertEquals("Blank input, no cities",
                outputStreamCaptor.toString().trim(),
                "Blank continentName was passed");
    }

    /**
     * Unit Test: sortCountriesPopContinent - null parameter test (continentName)
     */
    @Test
    void sortCountriesPopContinentTestNull() {
        world.sortCountriesPopContinent(null);
        assertEquals("Null input, no countries",
                outputStreamCaptor.toString().trim(),
                "Null continentName was passed");
    }

    /**
     * Unit Test: sortCountriesPopContinent - blank/empty parameter test (continentName)
     */
    @Test
    void sortCountriesPopContinentTestBlank() {
        world.sortCountriesPopContinent("   ");
        assertEquals("Blank input, no countries",
                outputStreamCaptor.toString().trim(),
                "Blank continentName was passed");
    }

    /**
     * Unit Test: nPopCitiesWorld - invalid parameter test (n == 0)
     */
    @Test
    void nPopCitiesWorldZero() {
        world.nPopCitiesWorld(0);
        assertEquals("Invalid number",
                outputStreamCaptor.toString().trim(),
                "n = 0 was passed");
    }

    /**
     * Unit Test: nPopCitiesWorld - invalid parameter test (n < 0)
     */
    @Test
    void nPopCitiesWorldNegative() {
        world.nPopCitiesWorld(-5);
        assertEquals("Invalid number",
                outputStreamCaptor.toString().trim(),
                "Negative n was passed");
    }

    /**
     * Unit Test: nPopCitiesContinent - null parameter test (continentName)
     */
    @Test
    void nPopCitiesContinentTestNull() {
        world.nPopCitiesContinent(null, 1);
        assertEquals("Null input, no cities",
                outputStreamCaptor.toString().trim(),
                "Null continentName was passed");
    }

    /**
     * Unit Test: nPopCitiesContinent - blank/empty parameter test (continentName)
     */
    @Test
    void nPopCitiesContinentTestBlank() {
        world.nPopCitiesContinent("", 1);
        assertEquals("Blank input, no cities",
                outputStreamCaptor.toString().trim(),
                "Blank continentName was passed");
    }

    /**
     * Unit Test: nPopCitiesDistrict - null parameter test (continentName)
     */
    @Test
    void nPopCitiesDistrictTestNull() {
        world.nPopCitiesDistrict(null, 2);
        assertEquals("Null input, no cities",
                outputStreamCaptor.toString().trim(),
                "Null districtName was passed");
    }

    /**
     * Unit Test: nPopCitiesDistrict - blank/empty parameter test (continentName)
     */
    @Test
    void nPopCitiesDistrictTestBlank() {
        world.nPopCitiesDistrict("  ", 2);
        assertEquals("Blank input, no cities",
                outputStreamCaptor.toString().trim(),
                "Blank districtName was passed");
    }

    /**
     * Unit Test: nPopCitiesDistrict - invalid parameter test (n <= 0)
     */
    @Test
    void nPopCitiesDistrictInvalidNumber() {
        world.nPopCitiesDistrict("California", -5);
        assertEquals("Invalid number",
                outputStreamCaptor.toString().trim(),
                "Negative n was passed");
    }

    /**
     * Unit Test: sortCountriesPopRegion - null parameter test (regionName)
     */
    @Test
    void sortCountriesPopRegionTestNull() {
        world.sortCountriesPopRegion(null);
        assertEquals("Null input, no countries",
                outputStreamCaptor.toString().trim(),
                "Null regionName was passed");

    }

    /**
     * Unit Test: sortCountriesPopRegion - blank/empty parameter test (regionName)
     */
    @Test
    void sortCountriesPopRegionTestBlank() {
        world.sortCountriesPopRegion("  ");
        assertEquals("Blank input, no countries",
                outputStreamCaptor.toString().trim(),
                "Blank regionName was passed");

    }

    /**
     * Unit Test: sortCapCitiesPopRegion - null parameter test (regionName)
     */
    @Test
    void sortCapCitiesPopRegionTestNull() {
        world.sortCapCitiesPopRegion(null);
        assertEquals("REGION NAME IS NULL",
                outputStreamCaptor.toString().trim(),
                "Null regionName was passed");
    }

    /**
     * Unit Test: sortCapCitiesPopRegion - invalid parameter test (regionName not found)
     */
    @Test
    void sortCapCitiesPopRegionTestEmpty() {
        world.sortCapCitiesPopRegion("This region does not exist");
        assertEquals("No capital cities in this region",
                outputStreamCaptor.toString().trim(),
                "Invalid regionName was passed");
    }

    /**
     * Unit Test: sortCapCitiesPopContinent - null parameter test (continentName)
     */
    @Test
    void sortCapCitiesPopContinentTestNull() {
        world.sortCapCitiesPopContinent(null);
        assertEquals("CONTINENT NAME IS NULL",
                outputStreamCaptor.toString().trim(),
                "Null continentName was passed");
    }

    /**
     * Unit Test: sortCapCitiesPopContinent - invalid parameter test (continentName not found)
     */
    @Test
    void sortCapCitiesPopContinentTestEmpty() {
        world.sortCapCitiesPopContinent("This continent does not exist");
        assertEquals("No capital cities in this continent",
                outputStreamCaptor.toString().trim(),
                "Invalid continentName was passed");
    }

    /**
     * Unit Test: nPopCitiesContinent - invalid parameter test (n == 0)
     */
    @Test
    void nPopCitiesContinentTestZero() {
        world.nPopCitiesContinent("Asia", 0);
        assertEquals("Invalid number",
                outputStreamCaptor.toString().trim(),
                "n == 0 was passed");
    }

    /**
     * Unit Test: nPopCitiesContinent - invalid parameter test (n < 0)
     */
    @Test
    void nPopCitiesContinentTestNegative() {
        world.nPopCitiesContinent("Asia", -1);
        assertEquals("Invalid number",
                outputStreamCaptor.toString().trim(),
                "Negative n was passed");
    }

    /**
     * Unit Test: nPopCountriesWorld - invalid parameter test (n == 0)
     */
    @Test
    void nPopCountriesWorldTestZero() {
        world.nPopCountriesWorld(0);
        assertEquals("Invalid number",
                outputStreamCaptor.toString().trim(),
                "n == 0 was passed");
    }

    /**
     * Unit Test: nPopCountriesWorld - invalid parameter test (n < 0)
     */
    @Test
    void nPopCountriesWorldTestNegative() {
        world.nPopCountriesWorld(-1);
        assertEquals("Invalid number",
                outputStreamCaptor.toString().trim(),
                "Negative n was passed");
    }

    /**
     * Unit Test: nPopCapCitiesRegion - null parameter test (regionName)
     */
    @Test
    void nPopCapCitiesRegionTestNull() {
        world.nPopCapCitiesRegion(null, 4);
        assertEquals("Null region name",
                outputStreamCaptor.toString().trim(),
                "Null regionName was passed");
    }

    /**
     * Unit Test: nPopCapCitiesRegion - blank/empty parameter test (regionName)
     */
    @Test
    void nPopCapCitiesRegionTestEmpty() {
        world.nPopCapCitiesRegion("", 4);
        assertEquals("Blank input, no capital cities",
                outputStreamCaptor.toString().trim(),
                "Blank/empty regionName was passed");
    }

    /**
     * Unit Test: nPopCapCitiesRegion - invalid parameter test (n < 0)
     */
    @Test
    void nPopCapCitiesRegionTestInvalidN() {
        world.nPopCapCitiesRegion("Asia", -1);
        assertEquals("Invalid number",
                outputStreamCaptor.toString().trim(),
                "Invalid (negative) n was passed");
    }

    /**
     * Unit Test: nPopCapCitiesRegion - invalid parameter test (n == 0)
     */
    @Test
    void nPopCapCitiesRegionTestZero() {
        world.nPopCapCitiesRegion("Asia", 0);
        assertEquals("Invalid number",
                outputStreamCaptor.toString().trim(),
                "n == 0 was passed");
    }

    /**
     * Unit Test: nPopCountriesContinent - null parameter test (continentName)
     */
    @Test
    void nPopCountriesContinentTestNull() {
        world.nPopCountriesContinent(null, 4);
        assertEquals("Null continent name",
                outputStreamCaptor.toString().trim(),
                "Null continentName was passed");
    }

    /**
     * Unit Test: nPopCountriesContinent - blank/empty parameter test (continentName)
     */
    @Test
    void nPopCountriesContinentTestBlank() {
        world.nPopCountriesContinent("", 1);
        assertEquals("Blank continent name",
                outputStreamCaptor.toString().trim(),
                "Blank continentName was passed");
    }

    /**
     * Unit Test: nPopCountriesContinent - invalid parameter test (n < 0)
     */
    @Test
    void nPopCountriesContinentTestInvalidN() {
        world.nPopCountriesContinent("Asia", -1);
        assertEquals("Invalid number",
                outputStreamCaptor.toString().trim(),
                "Invalid (negative) n was passed");

    }

    /**
     * Unit Test: nPopCountriesContinent - invalid parameter test (n == 0)
     */
    @Test
    void nPopCountriesContinentTestZero() {
        world.nPopCountriesContinent("Asia", 0);
        assertEquals("Invalid number",
                outputStreamCaptor.toString().trim(),
                "n == 0 was passed");

    }

    /**
     * Unit Test: populationReportContinent - null parameter test (continentName)
     */
    @Test
    void populationReportContinentTestNullContinentName() {
        world.populationReportContinent(null, "filename.md");
        assertEquals("Null input on continent name",
                outputStreamCaptor.toString().trim(),
                "Null continentName was passed");
    }

    /**
     * Unit Test: populationReportContinent - blank/empty parameter test (continentName)
     */
    @Test
    void populationReportContinentTestBlankContinentName() {
        world.populationReportContinent("  ", "filename.md");
        assertEquals("Blank input on continent name",
                outputStreamCaptor.toString().trim(),
                "Blank continentName was passed");
    }

    /**
     * Unit Test: populationReportContinent - null parameter test (fileName)
     */
    @Test
    void populationReportContinentTestNullFileName() {
        world.populationReportContinent("Asia", null);
        assertEquals("Null input on file name",
                outputStreamCaptor.toString().trim(),
                "Null fileName was passed");
    }

    /**
     * Unit Test: populationReportContinent - blank/empty parameter test (fileName)
     */
    @Test
    void populationReportContinentTestBlankFileName() {
        world.populationReportContinent("Asia", "  ");
        assertEquals("Blank input on file name",
                outputStreamCaptor.toString().trim(),
                "Blank fileName was passed");
    }

    /**
     * Unit Test: populationReportRegion - null parameter test (regionName)
     */
    @Test
    void populationReportRegionTestNullRegionName() {
        world.populationReportRegion(null, "filename.md");
        assertEquals("Null input on continent name",
                outputStreamCaptor.toString().trim(),
                "Null continentName was passed");
    }

    /**
     * Unit Test: populationReportRegion - blank/empty parameter test (regionName)
     */
    @Test
    void populationReportRegionTestBlankRegionName() {
        world.populationReportRegion("  ", "filename.md");
        assertEquals("Blank input on continent name",
                outputStreamCaptor.toString().trim(),
                "Blank continentName was passed");
    }

    /**
     * Unit Test: populationReportRegion - null parameter test (fileName)
     */
    @Test
    void populationReportRegionTestNullFileName() {
        world.populationReportRegion("Asia", null);
        assertEquals("Null input on file name",
                outputStreamCaptor.toString().trim(),
                "Null fileName was passed");
    }

    /**
     * Unit Test: populationReportRegion - blank/empty parameter test (fileName)
     */
    @Test
    void populationReportRegionTestBlankFileName() {
        world.populationReportRegion("Asia", "  ");
        assertEquals("Blank input on file name",
                outputStreamCaptor.toString().trim(),
                "Blank fileName was passed");
    }

    /**
     * Unit Test: populationReportCountry - null parameter test (country)
     */
    @Test
    void populationReportCountryTestNullCountry() {
        world.populationReportCountry(null, "filename.md");
        assertEquals("Null input on country",
                outputStreamCaptor.toString().trim(),
                "Null Country object was passed");
    }

    /**
     * Unit Test: populationReportCountry - null parameter test (fileName)
     */
    @Test
    void populationReportCountryTestNullFileName() {
        Country country = new Country("code",
                "name",
                "continent",
                "region",
                1.0,
                1,
                1,
                1.0,
                1.0,
                1.0,
                "localName",
                "governmentForm",
                "headOfState",
                1,
                "code2");

        world.populationReportCountry(country, null);
        assertEquals("Null input on file name",
                outputStreamCaptor.toString().trim(),
                "Null fileName was passed");
    }

    /**
     * Unit Test: populationReportCountry - blank/empty parameter test (fileName)
     */
    @Test
    void populationReportCountryTestBlankFileName() {
        Country country = new Country("code",
                "name",
                "continent",
                "region",
                1.0,
                1,
                1,
                1.0,
                1.0,
                1.0,
                "localName",
                "governmentForm",
                "headOfState",
                1,
                "code2");
        world.populationReportCountry(country, "  ");
        assertEquals("Blank input on file name",
                outputStreamCaptor.toString().trim(),
                "Blank fileName was passed");
    }

    /**
     * Unit Test: getAllDistrictPopulations - null parameter test (countryList)
     */
    @Test
    void getAllDistrictPopulationsTestNullCountries() {
        world.getAllDistrictPopulations(null);
        assertEquals("Null country list",
                outputStreamCaptor.toString().trim(),
                "Null country list was passed");
    }

    /**
     * Unit Test: populationCity - null parameter test (city)
     */
    @Test
    void populationCityTestNullCity() {
        world.populationCity(null, "fileName");
        assertEquals("Null input on city",
                outputStreamCaptor.toString().trim(),
                "Null City object was passed");
    }

    /**
     * Unit Test: populationCity - null parameter test (fileName)
     */
    @Test
    void populationCityTestNullFileName() {
        City city = new City(1,
                "name",
                "CCE",
                "countryName",
                "district",
                1);
        world.populationCity(city, null);
        assertEquals("Null input on file name",
                outputStreamCaptor.toString().trim(),
                "Null fileName was passed");

    }

    /**
     * Unit Test: populationCity - blank/empty parameter test (fileName)
     */
    @Test
    void populationCityTestBlankFileName() {
        City city = new City(1,
            "name",
            "CCE",
            "countryName",
            "district",
            1);

        world.populationCity(city, "");
        assertEquals("Blank input on file name",
                outputStreamCaptor.toString().trim(),
                "Blank filename was passed");

    }
    /**
     * Unit Test: nPopCountriesRegion - null parameter test (regionName)
     */
    @Test
    void nPopCountriesRegionTestNull() {
        world.nPopCountriesRegion(null, 1);
        assertEquals("Null input, no region",
                outputStreamCaptor.toString().trim(),
                "Null region name was passed");
    }

    /**
     * Unit Test: nPopCountriesRegion - blank parameter test (regionName)
     */
    @Test
    void nPopCountriesRegionTestBlank() {
        world.nPopCountriesRegion("  ", 1);
        assertEquals("Blank input, no region",
                outputStreamCaptor.toString().trim(),
                "Blank region name was passed");
    }

    /**
     * Unit Test: nPopCountriesRegion - invalid parameter test (n < 0)
     */
    @Test
    void nPopCountriesRegionTestNegative() {
        world.nPopCountriesRegion("Caribbean", -1);
        assertEquals("Invalid number",
                outputStreamCaptor.toString().trim(),
                "Negative n was passed");
    }

    /**
     * Unit Test: nPopCountriesRegion - invalid parameter test (n == 0)
     */
    @Test
    void nPopCountriesRegionTestZero() {
        world.nPopCountriesRegion("Caribbean",0);
        assertEquals("Invalid number",
                outputStreamCaptor.toString().trim(),
                "n == 0 was passed");
    }

    /**
     * Unit Test: nPopCitiesRegion - null parameter test (regionName)
     */
    @Test
    void nPopCitiesRegionTestNull() {
        world.nPopCitiesRegion(null, 1);
        assertEquals("Null input, no region",
                outputStreamCaptor.toString().trim(),
                "Null region name was passed");
    }

    /**
     * Unit Test: nPopCitiesRegion - blank/empty parameter test (regionName)
     */
    @Test
    void nPopCitiesRegionTestBlank() {
        world.nPopCitiesRegion("  ", 1);
        assertEquals("Blank input, no region",
                outputStreamCaptor.toString().trim(),
                "Blank region name was passed");
    }

    /**
     * Unit Test: nPopCitiesRegion - invalid parameter test (n < 0)
     */
    @Test
    void nPopCitiesRegionTestNegative() {
        world.nPopCitiesRegion("Caribbean", -1);
        assertEquals("Invalid number",
                outputStreamCaptor.toString().trim(),
                "Negative n was passed");
    }

    /**
     * Unit Test: nPopCitiesRegion - invalid parameter test (n == 0)
     */
    @Test
    void nPopCitiesRegionTestZero() {
        world.nPopCitiesRegion("Caribbean", 0);
        assertEquals("Invalid number",
                outputStreamCaptor.toString().trim(),
                "n == 0 was passed");
    }
}

