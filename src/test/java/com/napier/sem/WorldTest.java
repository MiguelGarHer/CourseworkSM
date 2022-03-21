package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class WorldTest
{
    static World world;
    private final PrintStream standardOut = System.out;
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

    @Test
    void sortCitiesPopRegionTestNull()
    {
        world.sortCitiesPopRegion(null);
        assertEquals("No cities", outputStreamCaptor.toString().trim());
    }

    @Test
    void sortCitiesPopCountryTestNull()
    {
        world.sortCitiesPopCountry(null);
        assertEquals("No cities", outputStreamCaptor.toString().trim());

    }

    @Test
    void sortCitiesPopDistrictTestNull()
    {
        world.sortCitiesPopDistrict(null);
        assertEquals("No cities", outputStreamCaptor.toString().trim());

    }

    @Test
    void sortCitiesPopContinentTestNull()
    {
        world.sortCitiesPopContinent(null);
        assertEquals("No cities", outputStreamCaptor.toString().trim());
    }

    @Test
    void sortCountriesPopCountriesTestNull()
    {
        world.sortCountriesPopContinent(null);
        assertEquals("No countries", outputStreamCaptor.toString().trim());

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

}