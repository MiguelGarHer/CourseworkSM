package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


import static org.junit.jupiter.api.Assertions.*;

class WorldTest {
    static World world;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeAll
    static void init() {
        world = new World();
    }

    //Testing for println: https://www.baeldung.com/java-testing-system-out-println
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }


    //
    @Test
    void getResultSetTestNull() {
        assertThrows(NullPointerException.class,
                () -> world.getResultSet(null),
                "NullPointer exception was not thrown");
    }

    @Test
    void getResultSetTestBlank() throws SQLException {
        assertNull(world.getResultSet(" "), "Null exception not thrown");
    }

    @Test
    void getResultSetTestEmpty() throws SQLException {
        assertNull(world.getResultSet(""), "Null exception not thrown");
    }


    @Test
    void createCountryTestNull() {
        assertNull(world.createCountry(null), "Country object is not null");
    }

    @Test
    void resultToCityTestNull() {
        assertNull(world.resultToCity(null, null), "City object is not null");
    }

    @Test
    void getCitiesTestNull() {
        assertTrue(world.getCities(null, null).isEmpty(), "City list is not empty");

    }

    @Test
    void getCitiesTestBlank() {
        assertTrue(world.getCities("  ", "  ").isEmpty(), "City list is not empty");
    }

    @Test
    void getCitiesTestEmpty() {
        assertTrue(world.getCities("", "").isEmpty(), "City list is not empty");
    }

    @Test
    void getLanguagesTestNull() {
        assertTrue(world.getLanguages(null).isEmpty(), "Language list is not empty");
    }

    @Test
    void resultToLanguageTest() {
        assertThrows(NullPointerException.class,
                () -> world.resultToLanguage(null),
                "NullPointerException not thrown");
    }


    //sortCitiesPopRegion tests

    @Test
    void sortCitiesPopRegionTestNull() {
        world.sortCitiesPopRegion(null);
        assertEquals("Null input, no cities",
                outputStreamCaptor.toString().trim(),
                "Null region name was passed");
    }

    @Test
    void sortCitiesPopRegionTestBlank() {
        world.sortCitiesPopRegion("  ");
        assertEquals("Blank input, no cities",
                outputStreamCaptor.toString().trim(),
                "Blank region name was passed");
    }

    @Test
    void sortCitiesPopRegionTestEmpty() {
        world.sortCitiesPopRegion("");
        assertEquals("Empty input, no cities",
                outputStreamCaptor.toString().trim(),
                "Empty region name was passed");
    }

    //sortCitiesPopCountry tests

    @Test
    void sortCitiesPopCountryTestNull() {
        world.sortCitiesPopCountry(null);
        assertEquals("Null input, no cities",
                outputStreamCaptor.toString().trim(),
                "Null countryName was passed");
    }

    @Test
    void sortCitiesPopCountryTestEmpty() {
        world.sortCitiesPopCountry("");
        assertEquals("Empty input, no cities",
                outputStreamCaptor.toString().trim(),
                "Empty countryName was passed");
    }

    @Test
    void sortCitiesPopCountryTestBlank() {
        world.sortCitiesPopCountry("  ");
        assertEquals("Blank input, no cities",
                outputStreamCaptor.toString().trim(),
                "Blank countryName was passed");
    }

    //sortCitiesPopDistrict tests

    @Test
    void sortCitiesPopDistrictTestNull() {
        world.sortCitiesPopDistrict(null);
        assertEquals("Null input, no cities",
                outputStreamCaptor.toString().trim(),
                "Null districtName was passed");
    }

    @Test
    void sortCitiesPopDistrictTestEmpty() {
        world.sortCitiesPopDistrict("");
        assertEquals("Empty input, no cities",
                outputStreamCaptor.toString().trim(),
                "Empty districtName was passed");
    }

    @Test
    void sortCitiesPopDistrictTestBlank() {
        world.sortCitiesPopDistrict("  ");
        assertEquals("Blank input, no cities",
                outputStreamCaptor.toString().trim(),
                "Blank districtName was passed");
    }

    //sortCitiesPopContinent tests

    @Test
    void sortCitiesPopContinentTestNull() {
        world.sortCitiesPopContinent(null);
        assertEquals("Null input, no cities",
                outputStreamCaptor.toString().trim(),
                "Null continentName was passed");
    }

    @Test
    void sortCitiesPopContinentTestBlank() {
        world.sortCitiesPopContinent("  ");
        assertEquals("Blank input, no cities",
                outputStreamCaptor.toString().trim(),
                "Blank continentName was passed");
    }

    @Test
    void sortCitiesPopContinentTestEmpty() {
        world.sortCitiesPopContinent("");
        assertEquals("Empty input, no cities",
                outputStreamCaptor.toString().trim(),
                "Empty continentName was passed");
    }

    //sortCountriesPopCountries tests

    @Test
    void sortCountriesPopCountriesTestNull() {
        world.sortCountriesPopContinent(null);
        assertEquals("Null input, no countries",
                outputStreamCaptor.toString().trim(),
                "Null continentName was passed");
    }

    @Test
    void sortCountriesPopCountriesTestBlank() {
        world.sortCountriesPopContinent("   ");
        assertEquals("Blank input, no countries",
                outputStreamCaptor.toString().trim(),
                "Blank continentName was passed");
    }

    @Test
    void sortCountriesPopCountriesTestEmpty() {
        world.sortCountriesPopContinent("");
        assertEquals("Empty input, no countries",
                outputStreamCaptor.toString().trim(),
                "Empty continentName was passed");
    }

    //nPopCitiesWorld test
    @Test
    void nPopCitiesWorld() {
        world.nPopCitiesWorld(0);
        assertEquals("Invalid number",
                outputStreamCaptor.toString().trim(),
                "n = 0 was passed");
    }

    @Test
    void nPopCitiesWorldNegative() {
        world.nPopCitiesWorld(-5);
        assertEquals("Invalid number",
                outputStreamCaptor.toString().trim(),
                "Negative n was passed");
    }

    @Test
    void nPopCitiesContinentTestNull() {
        world.nPopCitiesContinent(null, 1);
        assertEquals("Null input, no cities",
                outputStreamCaptor.toString().trim(),
                "Null continentName was passed");

    }

    @Test
    void nPopCitiesDistrictTestNull() {
        world.nPopCitiesDistrict(null, 2);
        assertEquals("Null input, no cities",
                outputStreamCaptor.toString().trim(),
                "Null districtName was passed");
    }

    @Test
    void nPopCitiesDistrictTestEmpty() {
        world.nPopCitiesDistrict("", 2);
        assertEquals("Empty input, no cities",
                outputStreamCaptor.toString().trim(),
                "Empty districtName was passed");
    }

    @Test
    void nPopCitiesDistrictTestBlank() {
        world.nPopCitiesDistrict("  ", 2);
        assertEquals("Blank input, no cities",
                outputStreamCaptor.toString().trim(),
                "Blank districtName was passed");
    }

    @Test
    void nPopCitiesDistrictNegative() {
        world.nPopCitiesDistrict("California", -5);
        assertEquals("Invalid number",
                outputStreamCaptor.toString().trim(),
                "Negative n was passed");
    }

    //sortCountriesPopRegion Test(Empty, null and blank)

    @Test
    void sortCountriesPopRegionTestEmpty() {
        world.sortCountriesPopRegion("");
        assertEquals("Empty input, no countries",
                outputStreamCaptor.toString().trim(),
                "Empty regionName was passed");

    }

    @Test
    void sortCountriesPopRegionTestNull() {
        world.sortCountriesPopRegion(null);
        assertEquals("Null input, no countries",
                outputStreamCaptor.toString().trim(),
                "Null regionName was passed");

    }

    @Test
    void sortCountriesPopRegionTestBlank() {
        world.sortCountriesPopRegion("  ");
        assertEquals("Blank input, no countries",
                outputStreamCaptor.toString().trim(),
                "Blank regionName was passed");

    }

    @Test
    void sortCapCitiesPopRegionTestNull() {
        world.sortCapCitiesPopRegion(null);
        assertEquals("REGION NAME IS NULL",
                outputStreamCaptor.toString().trim(),
                "Null regionName was passed");
    }

    @Test
    void sortCapCitiesPopRegionTestEmpty() {
        world.sortCapCitiesPopRegion("This region does not exist");
        assertEquals("No capital cities in this region",
                outputStreamCaptor.toString().trim(),
                "Invalid regionName was passed");
    }

    @Test
    void sortCapCitiesPopContinentTestNull() {
        world.sortCapCitiesPopContinent(null);
        assertEquals("CONTINENT NAME IS NULL",
                outputStreamCaptor.toString().trim(),
                "Null continentName was passed");
    }

    @Test
    void sortCapCitiesPopContinentTestEmpty() {
        world.sortCapCitiesPopContinent("This continent does not exist");
        assertEquals("No capital cities in this continent",
                outputStreamCaptor.toString().trim(),
                "Invalid continentName was passed");
    }

    @Test
    void nPopCitiesContinentTestBlank() {
        world.nPopCitiesContinent("  ", 1);
        assertEquals("Blank input, no cities",
                outputStreamCaptor.toString().trim(),
                "Blank continentName was passed");
    }

    @Test
    void nPopCitiesContinentTestEmpty() {
        world.nPopCitiesContinent("", 1);
        assertEquals("Empty input, no cities",
                outputStreamCaptor.toString().trim(),
                "Empty continentName was passed");

    }

    @Test
    void nPopCitiesContinentTestZero() {
        world.nPopCitiesContinent("Asia", 0);
        assertEquals("Invalid number",
                outputStreamCaptor.toString().trim(),
                "n == 0 was passed");
    }

    @Test
    void nPopCitiesContinentTestNegative() {
        world.nPopCitiesContinent("Asia", -1);
        assertEquals("Invalid number",
                outputStreamCaptor.toString().trim(),
                "Negative n was passed");
    }

    @Test
    void nPopCountriesWorldTestZero() {
        world.nPopCountriesWorld(0);
        assertEquals("Invalid number",
                outputStreamCaptor.toString().trim(),
                "n == 0 was passed");
    }

    @Test
    void nPopCountriesWorldTestNegative() {
        world.nPopCountriesWorld(-1);
        assertEquals("Invalid number",
                outputStreamCaptor.toString().trim(),
                "Negative n was passed");
    }

    @Test
    void nPopCapCitiesRegionTestNull() {
        world.nPopCapCitiesRegion(null, 4);
        assertEquals("Null region name",
                outputStreamCaptor.toString().trim(),
                "Null regionName was passed");
    }

    @Test
    void nPopCapCitiesRegionTestInvalidN() {
        world.nPopCapCitiesRegion("Asia", -1);
        assertEquals("Invalid number",
                outputStreamCaptor.toString().trim(),
                "Invalid (negative) n was passed");
    }

    @Test
    void nPopCapCitiesRegionTestZero() {
        world.nPopCapCitiesRegion("Asia", 0);
        assertEquals("Invalid number",
                outputStreamCaptor.toString().trim(),
                "n == 0 was passed");
    }

    @Test
    void nPopCountriesContinentTestNull() {
        world.nPopCountriesContinent(null, 4);
        assertEquals("Null continent name",
                outputStreamCaptor.toString().trim(),
                "Null continentName was passed");
    }

    @Test
    void nPopCountriesContinentTestBlank() {
        world.nPopCountriesContinent("", 1);
        assertEquals("Blank continent name",
                outputStreamCaptor.toString().trim(),
                "Blank continentName was passed");

    }

    @Test
    void nPopCountriesContinentTestInvalidN() {
        world.nPopCountriesContinent("Asia", -1);
        assertEquals("Invalid number",
                outputStreamCaptor.toString().trim(),
                "Invalid (negative) n was passed");

    }

    @Test
    void nPopCountriesContinentTestZero() {
        world.nPopCountriesContinent("Asia", 0);
        assertEquals("Invalid number",
                outputStreamCaptor.toString().trim(),
                "n == 0 was passed");

    }

    @Test
    void populationReportContinentTestNullContinentName() {
        world.populationReportContinent(null, "filename.md");
        assertEquals("Null input on continent name",
                outputStreamCaptor.toString().trim(),
                "Null continentName was passed");
    }

    @Test
    void populationReportContinentTestBlankContinentName() {
        world.populationReportContinent("  ", "filename.md");
        assertEquals("Blank input on continent name",
                outputStreamCaptor.toString().trim(),
                "Blank continentName was passed");
    }

    @Test
    void populationReportContinentTestNullFileName() {
        world.populationReportContinent("Asia", null);
        assertEquals("Null input on file name",
                outputStreamCaptor.toString().trim(),
                "Null fileName was passed");
    }

    @Test
    void populationReportContinentTestBlankFileName() {
        world.populationReportContinent("Asia", "  ");
        assertEquals("Blank input on file name",
                outputStreamCaptor.toString().trim(),
                "Blank fileName was passed");
    }

    @Test
    void populationReportRegionTestNullRegionName() {
        world.populationReportContinent(null, "filename.md");
        assertEquals("Null input on continent name",
                outputStreamCaptor.toString().trim(),
                "Null continentName was passed");
    }

    @Test
    void populationReportRegionTestBlankRegionName() {
        world.populationReportContinent("  ", "filename.md");
        assertEquals("Blank input on continent name",
                outputStreamCaptor.toString().trim(),
                "Blank continentName was passed");
    }

    @Test
    void populationReportRegionTestNullFileName() {
        world.populationReportContinent("Asia", null);
        assertEquals("Null input on file name",
                outputStreamCaptor.toString().trim(),
                "Null fileName was passed");
    }

    @Test
    void populationReportRegionTestBlankFileName() {
        world.populationReportContinent("Asia", "  ");
        assertEquals("Blank input on file name",
                outputStreamCaptor.toString().trim(),
                "Blank fileName was passed");
    }


    @Test
    void populationReportCountryTestNullCountry() {
        world.populationReportCountry(null, "filename.md");
        assertEquals("Null input on country",
                outputStreamCaptor.toString().trim(),
                "Null Country object was passed");
    }

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

    @Test
    void getAllDistrictPopulationsTestNullCountries() {
        Map<String, Long> districts = world.getAllDistrictPopulations(null);
        assertEquals("Null country list",
                outputStreamCaptor.toString().trim(),
                "Null country list was passed");
    }

    @Test
    void populationCityTestNullCity() {
        world.populationCity(null, "fileName");
        assertEquals("Null input on city",
                outputStreamCaptor.toString().trim(),
                "Null City object was passed");
    }

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


    @Test
    void nPopCountriesRegionTestBlank() {
        world.nPopCountriesRegion("  ", 1);
        assertEquals("Blank input, no region", outputStreamCaptor.toString().trim());
    }

    @Test
    void nPopCountriesRegionTestEmpty() {
        world.nPopCountriesRegion("", 1);
        assertEquals("Empty input, no region", outputStreamCaptor.toString().trim());

    }

    @Test
    void nPopCountriesRegionTestNegative() {
        world.nPopCountriesRegion("Caribbean", -1);
        assertEquals("Invalid number", outputStreamCaptor.toString().trim());
    }

    @Test
    void nPopCountriesRegionTestZero() {
        world.nPopCountriesRegion("Caribbean",0);
        assertEquals("Invalid number", outputStreamCaptor.toString().trim());
    }

    @Test
    void nPopCitiesRegionTestBlank() {
        world.nPopCitiesRegion("  ", 1);
        assertEquals("Blank input, no region", outputStreamCaptor.toString().trim());
    }

    @Test
    void nPopCitiesRegionTestEmpty() {
        world.nPopCitiesRegion("", 1);
        assertEquals("Empty input, no region", outputStreamCaptor.toString().trim());

    }

    @Test
    void nPopCitiesRegionTestNegative() {
        world.nPopCitiesRegion("Caribbean", -1);
        assertEquals("Invalid number", outputStreamCaptor.toString().trim());
    }

    @Test
    void nPopCitiesRegionTestZero() {
        world.nPopCitiesRegion("Caribbean", 0);
        assertEquals("Invalid number", outputStreamCaptor.toString().trim());
    }
}

