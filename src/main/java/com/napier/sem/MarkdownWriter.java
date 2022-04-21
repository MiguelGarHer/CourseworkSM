package com.napier.sem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Helper class handling Markdown writing/reading
 */
public class MarkdownWriter {

    /**
     * Prepare a list of countries to be written into Markdown format
     * @param countries list of Country objects
     * @param fileName name of Markdown file
     */
    public static void countryListToMarkdown(List<Country> countries, String fileName) {
        if (countries == null){
            System.out.println("Null country list");
            return;
        }

        if (fileName == null) {
            System.out.println("Null filename");
            return;
        } else if (fileName.isBlank()) {
            System.out.println("Blank filename");
            return;
        }

        StringBuilder sb = new StringBuilder();
        String[] columnNames = new String[2];
        columnNames[0] = "| Code | Name | Continent | Region | Population | Capital |\r\n";
        columnNames[1] = "| --- | --- | --- | --- | --- | --- |\r\n";

        for (Country country : countries) {
            String capitalCityName = null;
            for (City city : country.getCities()) {
                if (city.getId() == country.getCapital()) {
                    capitalCityName = city.getName();
                }
            }
            if (capitalCityName == null) {
                capitalCityName = "";
            }
            sb.append("| ");
            sb.append(country.getCode());
            sb.append(" | ");
            sb.append(country.getName());
            sb.append(" | ");
            sb.append(country.getContinent());
            sb.append(" | ");
            sb.append(country.getRegion());
            sb.append(" | ");
            sb.append(country.getPopulation());
            sb.append(" | ");
            sb.append(capitalCityName);
            sb.append(" |");
            sb.append("\r\n");
        }

        // Call writing method
        stringBuilderToMarkdown(columnNames, sb, "", fileName);
    }

    /**
     * Prepare a number of countries to be written into Markdown format
     * @param countries list of Country objects
     * @param n number of countries to be written
     * @param fileName name of Markdown file
     */
    public static void countryListToMarkdown(List<Country> countries, int n, String fileName) {
        if (countries == null){
            System.out.println("Null country list");
            return;
        }

        if (fileName == null) {
            System.out.println("Null filename");
            return;
        } else if (fileName.isBlank()) {
            System.out.println("Blank filename");
            return;
        }

        if (n <= 0) {
            System.out.println("Invalid number");
            return;
        }

        StringBuilder sb = new StringBuilder();
        String[] columnNames = new String[2];

        columnNames[0] = "| Code | Name | Continent | Region | Population | Capital |\r\n";
        columnNames[1] = "| --- | --- | --- | --- | --- | --- |\r\n";

        for (int i = 0; i < n; i++) {
            String capitalCityName = null;
            Country country = countries.get(i);
            for (City city : country.getCities()) {
                if (city.getId() == country.getCapital()) {
                    capitalCityName = city.getName();
                }
            }
            if (capitalCityName == null) {
                capitalCityName = "";
            }
            sb.append("| ");
            sb.append(country.getCode());
            sb.append(" | ");
            sb.append(country.getName());
            sb.append(" | ");
            sb.append(country.getContinent());
            sb.append(" | ");
            sb.append(country.getRegion());
            sb.append(" | ");
            sb.append(country.getPopulation());
            sb.append(" | ");
            sb.append(capitalCityName);
            sb.append(" |");
            sb.append("\r\n");
        }

        stringBuilderToMarkdown(columnNames, sb, "country", fileName);

    }

    /**
     * Prepare a population information into Markdown format
     * @param name name of information (country, world etc)
     * @param population population of object
     * @param fileName name of Markdown file
     */
    public static void populationToMarkdown(
            String name,
            long population,
            String fileName) {

        String columnName = name;
        if (name == null) {
            System.out.println("Null input on name");
            return;
        } else if (name.isEmpty()) {
            columnName = "-";
        }

        if (fileName == null) {
            System.out.println("Null filename");
            return;
        } else if (fileName.isBlank()) {
            System.out.println("Blank filename");
            return;
        }

        StringBuilder sb = new StringBuilder();
        String[] columnNames = new String[2];
        columnNames[0] = "| Name | Population |\r\n";
        columnNames[1] = "| --- | --- |\r\n";

        sb.append("| ");
        sb.append(columnName);
        sb.append(" | ");
        sb.append(population);
        sb.append(" | ");
        sb.append("\r\n");

        stringBuilderToMarkdown(columnNames, sb, "population", fileName);

    }

    /**
     * Prepare a population report to be written into Markdown format
     * @param name name of area (world, continent etc).
     * @param totalPopulation total population of area
     * @param cityPopulation population living in cities
     * @param cityPopulationPercentage city population percentage
     * @param countrySidePopulation population not living in cities
     * @param countrySidePopulationPercentage non-city population percentage
     * @param fileName name of Markdown file
     */
    public static void populationReportToMarkdown(
            String name,
            long totalPopulation,
            long cityPopulation,
            int cityPopulationPercentage,
            long countrySidePopulation,
            int countrySidePopulationPercentage,
            String fileName) {

        if (name == null) {
            System.out.println("Null input on name");
            return;
        } else if (name.isBlank()) {
            System.out.println("Blank name");
        }

        if (fileName == null) {
            System.out.println("Null filename");
            return;
        } else if (fileName.isBlank()) {
            System.out.println("Blank filename");
            return;
        }

        StringBuilder sb = new StringBuilder();
        String[] columnNames = new String[2];
        columnNames[0] = "| Name | Population living in cities | Population not living in cities | Total population |\r\n";
        columnNames[1] = "| --- | --- | --- | --- |\r\n";

        sb.append("| ");
        sb.append(name);
        sb.append(" | ");
        sb.append(cityPopulation);
        sb.append(" (");
        sb.append(cityPopulationPercentage);
        sb.append("%) | ");
        sb.append(countrySidePopulation);
        sb.append(" (");
        sb.append(countrySidePopulationPercentage);
        sb.append("%) | ");
        sb.append(totalPopulation);
        sb.append(" (");
        sb.append(cityPopulationPercentage + countrySidePopulationPercentage);
        sb.append("%) |");
        sb.append("\r\n");

        stringBuilderToMarkdown(columnNames, sb, "population", fileName);

    }

    /**
     * Prepare a Language to be written into Markdown format
     * @param language language name
     * @param population speaking population
     * @param percentage percentage of overall population
     * @param fileName name of Markdown
     */
    public static void languageToMarkdown(
            String language,
            long population,
            double percentage,
            String fileName) {

        String languageName = language;

        if (language == null) {
            System.out.println("Null input on language");
            return;
        } else if (language.isEmpty()) {
            languageName = "-";
        }

        if (fileName == null) {
            System.out.println("Null filename");
            return;
        } else if (fileName.isBlank()) {
            System.out.println("Blank filename");
            return;
        }

        StringBuilder sb = new StringBuilder();
        String[] columnNames = new String[2];
        columnNames[0] = "| Language | Speaking population |\r\n";
        columnNames[1] = "| --- | --- |\r\n";

        sb.append("| ");
        sb.append(languageName);
        sb.append(" | ");
        sb.append(population);
        sb.append(" (");
        sb.append(percentage);
        sb.append("%) | ");
        sb.append("\r\n");

        stringBuilderToMarkdown(columnNames, sb, "language", fileName);

    }

    /**
     * Prepare a list of cities to be written into Markdown format
     * @param cities list of City objects
     * @param fileName name of Markdown file
     */
    public static void cityListToMarkdown(List<City> cities, String fileName) {
        if (cities == null){
            System.out.println("Null city list");
            return;
        }

        if (fileName == null) {
            System.out.println("Null filename");
            return;
        } else if (fileName.isBlank()) {
            System.out.println("Blank filename");
            return;
        } else if (fileName.isEmpty()) {
            System.out.println("Empty filename");
            return;
        }

        StringBuilder sb = new StringBuilder();
        String[] columnNames = new String[2];
        columnNames[0] = "| Name | Country | District | Population |\r\n";
        columnNames[1] = "| --- | --- | --- | --- |\r\n";

        for (City city : cities) {
            sb.append("| ");
            sb.append(city.getName());
            sb.append(" | ");
            sb.append(city.getCountryName());
            sb.append(" | ");
            sb.append(city.getDistrict());
            sb.append(" | ");
            sb.append(city.getPopulation());
            sb.append(" | ");
            sb.append(" |");
            sb.append("\r\n");
        }

        stringBuilderToMarkdown(columnNames, sb, "city", fileName);

    }

    /**
     * Prepare a number of cities to be written into Markdown format
     * @param cities list of City objects
     * @param n number of cities to be written
     * @param fileName name of Markdown file
     */
    public static void cityListToMarkdown(List<City> cities, int n, String fileName) {
        if (cities == null){
            System.out.println("Null city list");
            return;
        }

        if (fileName == null) {
            System.out.println("Null filename");
            return;
        } else if (fileName.isBlank()) {
            System.out.println("Blank filename");
            return;
        }

        if (n <= 0) {
            System.out.println("Invalid number");
            return;
        }

        StringBuilder sb = new StringBuilder();
        String[] columnNames = new String[2];
        columnNames[0] = "| Name | Country | District | Population |\r\n";
        columnNames[1] = "| --- | --- | --- | --- |\r\n";

        for (int i = 0; i < n; i++) {
            City city = cities.get(i);
            sb.append("| ");
            sb.append(city.getName());
            sb.append(" | ");
            sb.append(city.getCountryName());
            sb.append(" | ");
            sb.append(city.getDistrict());
            sb.append(" | ");
            sb.append(city.getPopulation());
            sb.append(" | ");
            sb.append(" |");
            sb.append("\r\n");
        }
        stringBuilderToMarkdown(columnNames, sb, "city", fileName);
    }

    /**
     * Write a Markdown table
     * @param columnNames name of columns
     * @param records all data (records)
     * @param reportType type of report
     * @param fileName name of Markdown file
     */
    public static void stringBuilderToMarkdown(String[] columnNames, StringBuilder records, String reportType, String fileName) {
        // Parameter check for records
        if (records == null) {
            System.out.println("Null string builder");
            return;
        } else if (records.length() == 0) {
            System.out.println("Empty string builder");
            return;
        }

        // Parameter check for report type
        if (reportType == null) {
            System.out.println("Null report type");
            return;
        } else if (reportType.isBlank()) {
            System.out.println("Blank report type");
            return;
        }

        // Parameter check for file name
        if (fileName == null) {
            System.out.println("Null filename");
            return;
        } else if (fileName.isBlank()) {
            System.out.println("Blank filename");
            return;
        }

        // Parameter check for column names
        if (columnNames == null) {
            System.out.println("Null column name");
            return;
        } else if (columnNames.length != 2) {
            System.out.println("Invalid column name (missing row?)");
            return;
        }

        String baseDirectoryPath = "./reports/";

        if (createDirectory(baseDirectoryPath)) {
            String reportDirectoryPath;

            switch (reportType) {
                case "country":
                    reportDirectoryPath = baseDirectoryPath + "country/";
                    break;
                case "city":
                    reportDirectoryPath = baseDirectoryPath + "city/";
                    break;
                case "population":
                    reportDirectoryPath = baseDirectoryPath + "population/";
                    break;
                case "language":
                    reportDirectoryPath = baseDirectoryPath + "language/";
                    break;
                default:
                    reportDirectoryPath = baseDirectoryPath;
                    break;
            }

            if (createDirectory(reportDirectoryPath)) {
                try {
                    FileWriter fw;

                    String fullFilePath = reportDirectoryPath + fileName + ".md";
                    File file = new File(fullFilePath);

                    if (file.exists()) {
                        fw = new FileWriter(file, true);
                    } else {
                        fw = new FileWriter(file);
                        records.insert(0, columnNames[1]);
                        records.insert(0, columnNames[0]);
                    }

                    BufferedWriter writer = new BufferedWriter(fw);
                    writer.write(records.toString());
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Failed to create directory");
            }
        } else {
            System.out.println("Failed to create base directory");
        }
    }

    /**
     * Create a directory if it doesn't exist already
     * @param path path to new directory
     * @return boolean - True if successful, otherwise False
     */
    public static boolean createDirectory(String path) {
        // Parameter check for path
        if (path == null) {
            System.out.println("Null path");
            return false;
        } else if (path.isBlank()) {
            System.out.println("Blank path");
            return false;
        }

        File directory = new File(path);
        boolean directoryCreated = directory.exists();  // Refer to: https://stackoverflow.com/a/36217180
        if (!directoryCreated) {
            directoryCreated = directory.mkdir();
        }

        return directoryCreated;
    }
}

