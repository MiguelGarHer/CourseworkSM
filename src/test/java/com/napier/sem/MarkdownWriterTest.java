package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Test Class: Class testing MarkdownWriter class
 */
class MarkdownWriterTest {
    private static List<Country> countries; // List of Country objects

    private static List<City> cities;   // List of City objects

    // Stream capturing console print
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    /**
     * Initializing method, executed before all tests
     */
    @BeforeAll
    static void init() {
        countries = new ArrayList<>();
        countries.add(new Country(
                "code",
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
                "code2"));

        cities = new ArrayList<>();
        cities.add(new City(1,
                "name",
                "CCE",
                "countryName",
                "district",
                1));
    }

    /**
     * Prepare method, executed before each test
     */
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    /**
     * Unit Test: countryListToMarkdown - blank parameter test (fileName)
     */
    @Test
    void countryListToMarkdownTestBlankFileName() {
        MarkdownWriter.countryListToMarkdown(countries, "   ");
        assertEquals("Blank filename",
                outputStreamCaptor.toString().trim(),
                "Empty countryList was passed");
    }

    /**
     * Unit Test: countryListToMarkdown - null parameter test (countries)
     */
    @Test
    void countryListToMarkdownTestNullList() {
        MarkdownWriter.countryListToMarkdown(null, "fileName");
        assertEquals("Null country list",
                outputStreamCaptor.toString().trim(),
                "Null countryList was passed");
    }

    /**
     * Unit Test: countryListToMarkdown - null parameter test (fileName)
     */
    @Test
    void countryListToMarkdownTestNullFileName() {
        MarkdownWriter.countryListToMarkdown(countries, null);
        assertEquals("Null filename",
                outputStreamCaptor.toString().trim(),
                "Null file name was passed");
    }

    /**
     * Unit Test: countryListToMarkdown - invalid number parameter test (n)
     */
    @Test
    void countryListToMarkdownTestInvalidNumber() {
        MarkdownWriter.countryListToMarkdown(countries,-1, "fileName");
        assertEquals("Invalid number",
                outputStreamCaptor.toString().trim(),
                "Invalid n number was passed");
    }

    /**
     * Unit Test: countryListToMarkdown - number == 0 parameter test (n)
     */
    @Test
    void countryListToMarkdownTestInvalidNumberZero() {
        MarkdownWriter.countryListToMarkdown(countries,0, "fileName");
        assertEquals("Invalid number",
                outputStreamCaptor.toString().trim(),
                "n = 0 was passed");
    }

    /**
     * Unit Test: languageToMarkdown - null parameter test (language)
     */
    @Test
    void languageToMarkdownTestNullLanguage() {
        MarkdownWriter.languageToMarkdown(
                null,
                1,
                5.0,
                "fileName");
        assertEquals("Null input on language",
                outputStreamCaptor.toString().trim(),
                "Null language was passed");
    }

    /**
     * Unit Test: languageToMarkdown - null parameter test (fileName)
     */
    @Test
    void languageToMarkdownTestNullFileName() {
        MarkdownWriter.languageToMarkdown(
                "language",
                1,
                5.0,
                null);
        assertEquals("Null filename",
                outputStreamCaptor.toString().trim(),
                "Null filename was passed");
    }

    /**
     * Unit Test: languageToMarkdown - blank parameter test (fileName)
     */
    @Test
    void languageToMarkdownTestBlankFileName() {
        MarkdownWriter.languageToMarkdown(
                "language",
                1,
                5.0,
                "");
        assertEquals("Blank filename",
                outputStreamCaptor.toString().trim(),
                "Blank filename was passed");
    }

    /**
     * Unit Test: cityListToMarkdown - blank parameter test (fileName)
     */
    @Test
    void cityListToMarkdownTestBlankFileName() {
        MarkdownWriter.cityListToMarkdown(cities, "   ");
        assertEquals("Blank filename",
                outputStreamCaptor.toString().trim(),
                "Blank filename was passed");
    }

    /**
     * Unit Test: cityListToMarkdown - null parameter test (cities)
     */
    @Test
    void cityListToMarkdownTestNullList() {
        MarkdownWriter.cityListToMarkdown(null, "fileName");
        assertEquals("Null city list",
                outputStreamCaptor.toString().trim(),
                "Null list was passed");
    }

    /**
     * Unit Test: cityListToMarkdown - null parameter test (fileName)
     */
    @Test
    void cityListToMarkdownTestNullFileName() {
        MarkdownWriter.cityListToMarkdown(cities, null);
        assertEquals("Null filename",
                outputStreamCaptor.toString().trim(),
                "Null filename was passed");
    }

    /**
     * Unit Test: cityListToMarkdown - invalid number parameter test (n)
     */
    @Test
    void cityListToMarkdownTestInvalidNumber() {
        MarkdownWriter.cityListToMarkdown(cities,-1, "fileName");
        assertEquals("Invalid number",
                outputStreamCaptor.toString().trim(),
                "Invalid n number was passed");
    }

    /**
     * Unit Test: cityListToMarkdown - number == 0 parameter test (n)
     */
    @Test
    void cityListToMarkdownTestInvalidNumberZero() {
        MarkdownWriter.cityListToMarkdown(cities,0, "fileName");
        assertEquals("Invalid number",
                outputStreamCaptor.toString().trim(),
                "n = 0 was passed");
    }

    /**
     * Unit Test: populationToMarkdown - null parameter test (name)
     */
    @Test
    void populationToMarkdownTestNullName() {
        MarkdownWriter.populationToMarkdown(null, 1, "fileName");
        assertEquals("Null input on name",
                outputStreamCaptor.toString().trim(),
                "Null name input was passed");
    }

    /**
     * Unit Test: populationToMarkdown - blank parameter test (name)
     */
    @Test
    void populationToMarkdownTestBlankName() {
        MarkdownWriter.populationToMarkdown("", 1, "fileName");
        assertEquals("",
                outputStreamCaptor.toString().trim(),
                "Empty name input was NOT passed");
    }

    /**
     * Unit Test: populationToMarkdown - null parameter test (fileName)
     */
    @Test
    void populationToMarkdownTestNullFileName() {
        MarkdownWriter.populationToMarkdown("name", 1, null);
        assertEquals("Null filename",
                outputStreamCaptor.toString().trim(),
                "Null filename was passed");
    }

    /**
     * Unit Test: populationToMarkdown - blank parameter test (fileName)
     */
    @Test
    void populationToMarkdownTestBlankFileName() {
        MarkdownWriter.populationToMarkdown("name", 1, "");
        assertEquals("Blank filename",
                outputStreamCaptor.toString().trim(),
                "Blank filename was passed");
    }

    /**
     * Unit Test: populationReportToMarkdown - null parameter test (name)
     */
    @Test
    void populationReportToMarkdownTestNullName() {
        MarkdownWriter.populationReportToMarkdown(
                null,
                1,
                1,
                1,
                1,
                1,
                "fileName"
        );
        assertEquals("Null input on name",
                outputStreamCaptor.toString().trim(),
                "Null name input was passed");
    }

    /**
     * Unit Test: populationReportToMarkdown - blank parameter test (name)
     */
    @Test
    void populationReportToMarkdownTestBlankName() {
        MarkdownWriter.populationReportToMarkdown(
                "",
                1,
                1,
                1,
                1,
                1,
                "fileName"
        );
        assertEquals("Blank name",
                outputStreamCaptor.toString().trim(),
                "Blank name input was passed");
    }

    /**
     * Unit Test: populationReportToMarkdown - null parameter test (fileName)
     */
    @Test
    void populationReportToMarkdownTestNullFileName() {
        MarkdownWriter.populationReportToMarkdown(
                "name",
                1,
                1,
                1,
                1,
                1,
                null
        );
        assertEquals("Null filename",
                outputStreamCaptor.toString().trim(),
                "Null filename was passed");
    }

    /**
     * Unit Test: populationReportToMarkdown - empty parameter test (fileName)
     */
    @Test
    void populationReportToMarkdownTestEmptyFileName() {
        MarkdownWriter.populationReportToMarkdown(
                "name",
                1,
                1,
                1,
                1,
                1,
                ""
        );
        assertEquals("Blank filename",
                outputStreamCaptor.toString().trim(),
                "Blank filename was passed");
    }
}
