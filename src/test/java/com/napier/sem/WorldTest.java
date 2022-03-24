package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;


import static org.junit.jupiter.api.Assertions.*;

public class WorldTest
{
    static World world;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeAll
    static void init()
    {
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
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            world.getResultSet(null);
        });
    }

    @Test
    void getResultSetTestBlank() throws SQLException {
        assertNull(world.getResultSet(" "));
    }

    @Test
    void getResultSetTestEmpty() throws SQLException {
        assertNull(world.getResultSet(""));
    }


    @Test
    void createCountryTestNull() {
        assertNull(world.createCountry(null));
    }

    @Test
    void resultToCityTestNull() {
        assertNull(world.resultToCity(null));
    }

    @Test
    void getCitiesTestNull() {
        assertTrue(world.getCities(null).isEmpty());

    }

    @Test
    void getCitiesTestBlank() {
        assertTrue(world.getCities("  ").isEmpty());
    }

    @Test
    void getCitiesTestEmpty() {
        assertTrue(world.getCities("").isEmpty());
    }

    @Test
    void getLanguagesTestNull() {
        assertTrue(world.getLanguages(null).isEmpty());
    }

    @Test
    void resultToLanguageTest() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            world.resultToLanguage(null);
        });
    }


    //sortCitiesPopRegion tests

    @Test
    void sortCitiesPopRegionTestNull()
    {
        world.sortCitiesPopRegion(null);
        assertEquals("Null input, no cities", outputStreamCaptor.toString().trim());
    }

    @Test
    void sortCitiesPopRegionTestBlank()
    {
        world.sortCitiesPopRegion("  ");
        System.out.println("Blank input, no cities");
    }

    @Test
    void sortCitiesPopRegionTestEmpty()
    {
        world.sortCitiesPopRegion("");
        System.out.println("Empty input, no cities");
    }

    //sortCitiesPopCountry tests

    @Test
    void sortCitiesPopCountryTestNull()
    {
        world.sortCitiesPopCountry(null);
        assertEquals("Null input, no cities", outputStreamCaptor.toString().trim());
    }

    @Test
    void sortCitiesPopCountryTestEmpty()
    {
        world.sortCitiesPopCountry("");
        assertEquals("Empty input, no cities", outputStreamCaptor.toString().trim());
    }

    @Test
    void sortCitiesPopCountryTestBlank()
    {
        world.sortCitiesPopCountry("  ");
        assertEquals("Blank input, no cities", outputStreamCaptor.toString().trim());
    }

    //sortCitiesPopDistrict tests

    @Test
    void sortCitiesPopDistrictTestNull()
    {
        world.sortCitiesPopDistrict(null);
        assertEquals("Null input, no cities", outputStreamCaptor.toString().trim());
    }
    @Test
    void sortCitiesPopDistrictTestEmpty()
    {
        world.sortCitiesPopDistrict("");
        assertEquals("Empty input, no cities", outputStreamCaptor.toString().trim());
    }
    @Test
    void sortCitiesPopDistrictTestBlank()
    {
        world.sortCitiesPopDistrict("  ");
        assertEquals("Blank input, no cities", outputStreamCaptor.toString().trim());
    }

    //sortCitiesPopContinent tests

    @Test
    void sortCitiesPopContinentTestNull()
    {
        world.sortCitiesPopContinent(null);
        assertEquals("Null input, no cities", outputStreamCaptor.toString().trim());
    }

    @Test
    void sortCitiesPopContinentTestBlank()
    {
        world.sortCitiesPopContinent("  ");
        assertEquals("Blank input, no cities", outputStreamCaptor.toString().trim());
    }

    @Test
    void sortCitiesPopContinentTestEmpty()
    {
        world.sortCitiesPopContinent("");
        assertEquals("Empty input, no cities", outputStreamCaptor.toString().trim());
    }

    //sortCountriesPopCountries tests

    @Test
    void sortCountriesPopCountriesTestNull()
    {
        world.sortCountriesPopContinent(null);
        assertEquals("Null input, no countries", outputStreamCaptor.toString().trim());
    }

    @Test
    void sortCountriesPopCountriesTestBlank()
    {
        world.sortCountriesPopContinent("   ");
        assertEquals("Blank input, no countries", outputStreamCaptor.toString().trim());
    }

    @Test
    void sortCountriesPopCountriesTestEmpty()
    {
        world.sortCountriesPopContinent("");
        assertEquals("Empty input, no countries", outputStreamCaptor.toString().trim());
    }
    //nPopCitiesWorld test
    @Test
    void nPopCitiesWorld()
    {
        world.nPopCitiesWorld(0);
        assertEquals("Invalid number", outputStreamCaptor.toString().trim());
    }
    @Test
    void nPopCitiesWorldNegative()
    {
        world.nPopCitiesWorld(-5);
        assertEquals("Invalid number", outputStreamCaptor.toString().trim());
    }

    @Test
    void nPopCitiesContinentTestNull() {
        world.nPopCitiesContinent(null, 1);
        assertEquals("Null input, no cities", outputStreamCaptor.toString().trim());

    }
//sortCountriesPopRegion Test(Empty, null and blank)

    @Test
    void sortCountriesPopRegionTestEmpty()
    {
        world.sortCountriesPopRegion("");
        assertEquals("Empty input, no countries", outputStreamCaptor.toString().trim());

    }

    @Test
    void sortCountriesPopRegionTestNull()
    {
        world.sortCountriesPopRegion(null);
        assertEquals("Null input, no countries", outputStreamCaptor.toString().trim());

    }

    @Test
    void sortCountriesPopRegionTestBlank()
    {
        world.sortCountriesPopRegion("  ");
        assertEquals("Blank input, no countries", outputStreamCaptor.toString().trim());

    }

    @Test
    void sortCapCitiesPopRegionTestNull(){
        world.sortCapCitiesPopRegion(null);
        assertEquals("REGION NAME IS NULL", outputStreamCaptor.toString().trim());
    }

    @Test
    void sortCapCitiesPopRegionTestEmpty(){
        world.sortCapCitiesPopRegion("This region does not exist");
        assertEquals("No capital cities in this region", outputStreamCaptor.toString().trim());
    }

    @Test
    void sortCapCitiesPopContinentTestNull(){
        world.sortCapCitiesPopContinent(null);
        assertEquals("CONTINENT NAME IS NULL", outputStreamCaptor.toString().trim());
    }

    @Test
    void sortCapCitiesPopContinentTestEmpty(){
        world.sortCapCitiesPopContinent("This continent does not exist");
        assertEquals("No capital cities in this continent", outputStreamCaptor.toString().trim());
    }

    @Test
    void nPopCitiesContinentTestBlank() {
        world.nPopCitiesContinent("  ", 1);
        assertEquals("Blank input, no cities", outputStreamCaptor.toString().trim());
    }

    @Test
    void nPopCitiesContinentTestEmpty() {
        world.nPopCitiesContinent("", 1);
        assertEquals("Empty input, no cities", outputStreamCaptor.toString().trim());

    }

    @Test
    void nPopCitiesContinentTestZeroOrNegative() {
        world.nPopCitiesContinent("Asia", -1);
        assertEquals("Invalid number", outputStreamCaptor.toString().trim());
    }

    @Test
    void nPopCountriesWorldTestNegative() {
        world.nPopCountriesWorld(-1);
        assertEquals("Invalid number", outputStreamCaptor.toString().trim());
    }

    @Test
    void nPopCountriesWorldTestZero() {
        world.nPopCountriesWorld(0);
        assertEquals("Invalid number", outputStreamCaptor.toString().trim());
    }
}

