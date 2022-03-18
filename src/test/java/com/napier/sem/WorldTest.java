package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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

}