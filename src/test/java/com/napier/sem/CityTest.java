package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Test Class: Class testing City class
 */
class CityTest {
    static City city;   // City object

    /**
     * Initializing method, executed before each test
     */
    @BeforeAll
    static void init() {
        city = new City(1,
                "name",
                "CCE",
                "countryName",
                "district",
                1);
    }

    /**
     * Unit Test: Testing getPopulation
     */
    @Test
    void getPopulationTest() {
        assertEquals(1,
                city.getPopulation(),
                "Population is not equal to 1");
    }

    /**
     * Unit Test: Testing getId
     */
    @Test
    void getIdTest() {
        assertEquals(1,
                city.getId(),
                "Id is not equal to 1");
    }

    /**
     * Unit Test: Testing getName
     */
    @Test
    void getNameTest() {
        assertEquals("name",
                city.getName(),
                "Name is not equal to name");
    }

    /**
     * Unit Test: Testing getCountryName
     */
    @Test
    void getCountryNameTest() {
        assertEquals("countryName",
                city.getCountryName(),
                "CountryName is not equal to countryName");
    }

    /**
     * Unit Test: Testing getCountryCode
     */
    @Test
    void getCountryCodeTest() {
        assertEquals("CCE",
                city.getCountryCode(),
                "CountryCode is not equal to CCE");
    }

    /**
     * Unit Test: Testing getDistrict
     */
    @Test
    void getDistrictTest() {
        assertEquals("district",
                city.getDistrict(),
                "District is not equal to district");
    }
}