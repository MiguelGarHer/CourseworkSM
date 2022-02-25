package com.napier.sem;

public class Language {
    public String language;
    public boolean isOfficial;
    public double percentage;

    public Language(String language, boolean isOfficial, double percentage) {
        this.language = language;
        this.isOfficial = isOfficial;
        this.percentage = percentage;
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
