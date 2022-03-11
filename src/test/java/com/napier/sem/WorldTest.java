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
        assertNotEquals("All cities in null, sorted by population", outputStreamCaptor.toString().trim());
    }

    @Test
    void sortCitiesPopCountryTestNull()
    {
        world.sortCitiesPopCountry(null);
        assertNotEquals("All cities in null, sorted by population", outputStreamCaptor.toString().trim());

    }

    @Test
    void sortCitiesPopDistrictTestNull()
    {
        world.sortCitiesPopDistrict(null);
        assertNotEquals("All cities in null, sorted by population", outputStreamCaptor.toString().trim());

    }

    @Test
    void sortCitiesPopContinentTestNull()
    {
        world.sortCitiesPopDistrict(null);
        assertNotEquals("All cities in null, sorted by population", outputStreamCaptor.toString().trim());

    }

}