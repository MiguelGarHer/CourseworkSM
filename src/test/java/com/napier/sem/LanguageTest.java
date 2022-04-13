package com.napier.sem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Test Class: Class testing Language class
 */
class LanguageTest {
    static Language language;   // Language object

    /**
     * Initializing method, executed before each test
     */
    @BeforeAll
    static void init() {
        language = new Language("language",
                true,
                1.0);
    }

    /**
     * Unit Test: Testing getLanguage
     */
    @Test
    void getLanguageTest() {
        assertEquals("language",
                language.getLanguage(),
                "Language is not equal to language");
    }

    /**
     * Unit Test: Testing isOfficial
     */
    @Test
    void isOfficialTest() {
        assertTrue(language.isOfficial(), "IsOfficial is not equal to True");
    }

    /**
     * Unit Test: Testing getPercentage
     */
    @Test
    void getPercentageTest() {
        assertEquals(1.0,
                language.getPercentage(),
                "Percentage is not equal to 1.0");
    }
}
