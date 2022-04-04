package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MarkdownWriterTest {
    private static ArrayList<Country> countries;

    private static ArrayList<City> cities;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


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


    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void countryListToMarkdownTestEmptyFileName() {
        MarkdownWriter.countryListToMarkdown(countries, "");
    }

    @Test
    void countryListToMarkdownTestBlankFileName() {
        MarkdownWriter.countryListToMarkdown(countries, "   ");
        assertEquals("Blank filename", outputStreamCaptor.toString().trim());
    }

    @Test
    void countryListToMarkdownTestNullList() {
        MarkdownWriter.countryListToMarkdown(null, "fileName");
        assertEquals("Null country list", outputStreamCaptor.toString().trim());
    }

    @Test
    void countryListToMarkdownTestNullFileName() {
        MarkdownWriter.countryListToMarkdown(countries, null);
        assertEquals("Null filename", outputStreamCaptor.toString().trim());
    }

    @Test
    void countryListToMarkdownTestInvalidNumber() {
        MarkdownWriter.countryListToMarkdown(countries,-1, "fileName");
        assertEquals("Invalid number", outputStreamCaptor.toString().trim());
    }

    @Test
    void countryListToMarkdownTestInvalidNumberZero() {
        MarkdownWriter.countryListToMarkdown(countries,0, "fileName");
        assertEquals("Invalid number", outputStreamCaptor.toString().trim());
    }

    @Test
    void cityListToMarkdownTestEmptyFileName() {
        MarkdownWriter.cityListToMarkdown(cities, "");
    }

    @Test
    void cityListToMarkdownTestBlankFileName() {
        MarkdownWriter.cityListToMarkdown(cities, "   ");
        assertEquals("Blank filename", outputStreamCaptor.toString().trim());
    }

    @Test
    void cityListToMarkdownTestNullList() {
        MarkdownWriter.cityListToMarkdown(null, "fileName");
        assertEquals("Null city list", outputStreamCaptor.toString().trim());
    }

    @Test
    void cityListToMarkdownTestNullFileName() {
        MarkdownWriter.cityListToMarkdown(cities, null);
        assertEquals("Null filename", outputStreamCaptor.toString().trim());
    }

    @Test
    void cityListToMarkdownTestInvalidNumber() {
        MarkdownWriter.cityListToMarkdown(cities,-1, "fileName");
        assertEquals("Invalid number", outputStreamCaptor.toString().trim());
    }

    @Test
    void cityListToMarkdownTestInvalidNumberZero() {
        MarkdownWriter.cityListToMarkdown(cities,0, "fileName");
        assertEquals("Invalid number", outputStreamCaptor.toString().trim());
    }
}
