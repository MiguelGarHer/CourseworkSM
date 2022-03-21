package com.napier.sem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
public class LanguageTest {
    static Language language;

    @BeforeAll
    static void init() {
        language = new Language("language",
                true,
                1.0);
    }

    @Test
    void getLanguageTest() {
        assertEquals("language", language.getLanguage());
    }

    @Test
    void isOfficialTest() {
        assertTrue(language.isOfficial());
    }

    @Test
    void getPercentageTest() {
        assertEquals(1.0, language.getPercentage());
    }
}