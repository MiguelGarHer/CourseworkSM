package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CountryTest {

    static Country country;

    @BeforeAll
    static void init() {
        country = new Country("code",
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
    }

    @Test
    void setCitiesTestNull() {
        country.setCities(null);
        assertNull(country.getCities());
    }

    @Test
    void setCitiesTestEmpty() {
        ArrayList<City> emptyCities = new ArrayList<>();
        country.setCities(emptyCities);
        assertEquals(new ArrayList<City>(), country.getCities());
    }

    @Test
    void setCitiesTestNonEmpty() {
        ArrayList<City> cities = new ArrayList<>();
        cities.add(new City(1,
                "name",
                "CCE",
                "countryName",
                "district",
                1));
        cities.add(new City(2,
                "name",
                "CCE",
                "countryName",
                "district",
                2));
        country.setCities(cities);

        assertEquals(1, country.getCities().get(0).getId());
        assertEquals(2, country.getCities().get(1).getId());

    }

    @Test
    void setLanguagesTestNUll() {
        country.setLanguages(null);
        assertNull(country.getLanguages());
    }

    @Test
    void setLanguagesTestEmpty() {
        ArrayList<Language> emptyLanguages = new ArrayList<>();
        country.setLanguages(emptyLanguages);
        assertEquals(new ArrayList<Language>(), country.getLanguages());
    }

    @Test
    void setLanguagesTestNonEmpty() {
        ArrayList<Language> languages = new ArrayList<>();
        languages.add(new Language("language",
                true,
                1.0));
        languages.add(new Language("language",
                true,
                2.0));
        country.setLanguages(languages);

        assertEquals(1.0, country.getLanguages().get(0).getPercentage());
        assertEquals(2.0, country.getLanguages().get(1).getPercentage());
    }

    @Test
    void getCitiesTest() {
        ArrayList<City> cities = new ArrayList<>();
        cities.add(new City(1,
                "name",
                "CCE",
                "countryName",
                "district",
                1));
        cities.add(new City(2,
                "name",
                "CCE",
                "countryName",
                "district",
                2));
        country.setCities(cities);

        assertEquals(cities, country.getCities());

    }

    @Test
    void getLanguagesTest() {
        ArrayList<Language> languages = new ArrayList<>();
        languages.add(new Language("language",
                true,
                1.0));
        languages.add(new Language("language",
                true,
                2.0));
        country.setLanguages(languages);

        assertEquals(languages, country.getLanguages());
    }

    @Test
    void getCodeTest() {
        assertEquals("code", country.getCode());
    }

    @Test
    void getNameTest() {
        assertEquals("name", country.getName());
    }

    @Test
    void getContinentTest() {
        assertEquals("continent", country.getContinent());
    }

    @Test
    void getRegionTest() {
        assertEquals("region", country.getRegion());
    }

    @Test
    void getSurfaceAreaTest() {
        assertEquals(1.0, country.getSurfaceArea());
    }

    @Test
    void getIndepYearTest() {
        assertEquals(1, country.getIndepYear());
    }

    @Test
    void getPopulationTest() {
        assertEquals(1, country.getPopulation());
    }

    @Test
    void getLifeExpectancyTest() {
        assertEquals(1.0, country.getLifeExpectancy());
    }

    @Test
    void getGNPTest() {
        assertEquals(1.0, country.getGNP());
    }

    @Test
    void getGNPOldTest() {
        assertEquals(1.0, country.getGNPOld());
    }

    @Test
    void getLocalNameTest() {
        assertEquals("localName", country.getLocalName());
    }

    @Test
    void getGovernmentFormTest() {
        assertEquals("governmentForm", country.getGovernmentForm());
    }

    @Test
    void getHeadOfStateTest() {
        assertEquals("headOfState", country.getHeadOfState());
    }

    @Test
    void getCapitalTest() {
        assertEquals(1, country.getCapital());
    }

    @Test
    void getCode2Test() {
        assertEquals("code2", country.getCode2());
    }
}


