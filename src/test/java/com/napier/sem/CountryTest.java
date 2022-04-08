package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CountryTest {

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
        assertNull(country.getCities(), "Cities is not null");
    }

    @Test
    void setCitiesTestEmpty() {
        ArrayList<City> emptyCities = new ArrayList<>();
        country.setCities(emptyCities);
        assertEquals(new ArrayList<City>(),
                country.getCities(),
                "Cities is not empty");
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

        assertEquals(1,
                country.getCities().get(0).getId(),
                "City Id is not equal to 1");
    }

    @Test
    void setLanguagesTestNull() {
        country.setLanguages(null);
        assertNull(country.getLanguages(), "Languages is not null");
    }

    @Test
    void setLanguagesTestEmpty() {
        ArrayList<Language> emptyLanguages = new ArrayList<>();
        country.setLanguages(emptyLanguages);
        assertEquals(new ArrayList<Language>(),
                country.getLanguages(),
                "Languages is not empty");
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

        assertEquals(1.0,
                country.getLanguages().get(0).getPercentage(),
                "Language percentage is not equal to 1.0");
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

        assertEquals(cities, country.getCities(), "Cities list is not equal to getCities");

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

        assertEquals(languages, country.getLanguages(), "Language list is not equal to languages");
    }

    @Test
    void getCodeTest() {
        assertEquals("code", country.getCode(), "Code is not equal to code");
    }

    @Test
    void getNameTest() {
        assertEquals("name", country.getName(), "Name is not equal to name");
    }

    @Test
    void getContinentTest() {
        assertEquals("continent", country.getContinent(), "Continent name is not equal to continent");
    }

    @Test
    void getRegionTest() {
        assertEquals("region", country.getRegion(), "Region name is not equal to region");
    }

    @Test
    void getSurfaceAreaTest() {
        assertEquals(1.0, country.getSurfaceArea(), "Surface area is not equal to 1.0");
    }

    @Test
    void getIndepYearTest() {
        assertEquals(1, country.getIndepYear(), "Independence year is not equal to 1");
    }

    @Test
    void getPopulationTest() {
        assertEquals(1, country.getPopulation(),"Population is not equal to 1");
    }

    @Test
    void getLifeExpectancyTest() {
        assertEquals(1.0, country.getLifeExpectancy(), "Life expectancy is not equal to 1.0");
    }

    @Test
    void getGNPTest() {
        assertEquals(1.0, country.getGNP(), "GNP is not equal to 1.0");
    }

    @Test
    void getGNPOldTest() {
        assertEquals(1.0, country.getGNPOld(), "GNPOld is not equal to 1.0");
    }

    @Test
    void getLocalNameTest() {
        assertEquals("localName", country.getLocalName(), "Local name is not equal to localName");
    }

    @Test
    void getGovernmentFormTest() {
        assertEquals("governmentForm", country.getGovernmentForm(), "GovernmentForm is not equal to governmentForm");
    }

    @Test
    void getHeadOfStateTest() {
        assertEquals("headOfState", country.getHeadOfState(), "HeadOfState is not equal to headOfState");
    }

    @Test
    void getCapitalTest() {
        assertEquals(1, country.getCapital(), "Capital id is not equal to 1");
    }

    @Test
    void getCode2Test() {
        assertEquals("code2", country.getCode2(), "Code2 is not equal to code2");
    }
}


