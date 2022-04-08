package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CityTest {
    static City city;

    @BeforeAll
    static void init() {
        city = new City(1,
                "name",
                "CCE",
                "countryName",
                "district",
                1);
    }

    @Test
    void getPopulationTest() {
        assertEquals(1,
                city.getPopulation(),
                "Population is not equal to 1");
    }

    @Test
    void getIdTest() {
        assertEquals(1,
                city.getId(),
                "Id is not equal to 1");
    }

    @Test
    void getNameTest() {
        assertEquals("name",
                city.getName(),
                "Name is not equal to name");
    }

    @Test
    void getCountryNameTest() {
        assertEquals("countryName",
                city.getCountryName(),
                "CountryName is not equal to countryName");
    }

    @Test
    void getCountryCodeTest() {
        assertEquals("CCE",
                city.getCountryCode(),
                "CountryCode is not equal to CCE");
    }

    @Test
    void getDistrictTest() {
        assertEquals("district",
                city.getDistrict(),
                "District is not equal to district");
    }
}