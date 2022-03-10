package com.napier.sem;

public class Language {
    private final String language;
    private final boolean isOfficial;
    private final double percentage;

    public Language(String language, boolean isOfficial, double percentage) {
        this.language = language;
        this.isOfficial = isOfficial;
        this.percentage = percentage;
    }

    public String getLanguage() {
        return language;
    }

    public boolean isOfficial() {
        return isOfficial;
    }

    public double getPercentage() {
        return percentage;
    }

    @Override
    public String toString() {
        return "Language{" +
                "language='" + language + '\'' +
                ", isOfficial=" + isOfficial +
                ", percentage=" + percentage +
                '}';
    }
}
