package com.napier.sem;

/**
 * Language class modelling a language
 */
public class Language {
    private final String language;  // Language name
    private final boolean isOfficial; // Language Status
    private final double percentage;  // Percentage of population

    /**
     * Constructor
     * @param language language name
     * @param isOfficial language status
     * @param percentage percentage of population
     */
    public Language(String language, boolean isOfficial, double percentage) {
        this.language = language;
        this.isOfficial = isOfficial;
        this.percentage = percentage;
    }

    /**
     * Getter for language attribute
     * @return language name
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Getter for language status
     * @return language status
     */
    public boolean isOfficial() {
        return isOfficial;
    }

    /**
     * Getter for population percentage
     * @return population percentage
     */
    public double getPercentage() {
        return percentage;
    }

    /**
     * ToString method
     * @return all class attributes
     */
    @Override
    public String toString() {
        return "Language{" +
                "language='" + language + '\'' +
                ", isOfficial=" + isOfficial +
                ", percentage=" + percentage +
                '}';
    }

}
