package com.napier.sem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class LanguageTest {
    static Language language;

    @BeforeAll
    static void init() {
        language = new Language("language",
                true,
                1.0);
    }

    @Test
    void getLanguageTest() {
        assertEquals("language",
                language.getLanguage(),
                "Language is not equal to language");
    }

    @Test
    void isOfficialTest() {
        assertTrue(language.isOfficial(), "IsOfficial is not equal to True");
    }

    @Test
    void getPercentageTest() {
        assertEquals(1.0,
                language.getPercentage(),
                "Percentage is not equal to 1.0");
    }
}
