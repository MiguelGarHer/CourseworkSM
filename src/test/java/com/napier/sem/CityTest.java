package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class CityTest {
    static City city;

    @BeforeAll
    static void init() {
        city = new City(1,
                "name",
                "CCE",
                "district",
                1);
    }

    @Test
    void getPopulationTest() {
        assertEquals(1,city.getPopulation());
    }

    @Test
    void getIdTest() {
        assertEquals(1,city.getId());
    }

    @Test
    void getNameTest() {
        assertEquals("name", city.getName());
    }

    @Test
    void getCountryCodeTest() {
        assertEquals("CCE", city.getCountryCode());
    }

    @Test
    void getDistrictTest() {
        assertEquals("district", city.getDistrict());
    }
}