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

    public static void stringBuilderToMarkdown(String[] columnNames, StringBuilder records, String reportType, String fileName) {

        if (records == null) {
            System.out.println("Null stringbuilder");
            return;
        } else if (records.length() == 0) {
            System.out.println("Empty stringbuilder");
            return;
        }

        if (reportType == null) {
            System.out.println("Null report type");
            return;
        } else if (reportType.isBlank()) {
            System.out.println("Blank report type");
            return;
        }

        if (fileName == null) {
            System.out.println("Null filename");
            return;
        } else if (fileName.isBlank()) {
            System.out.println("Blank filename");
            return;
        }

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

    public static boolean createDirectory(String path) {   // https://stackoverflow.com/a/36217180
        if (path == null) {
            System.out.println("Null path");
            return false;
        } else if (path.isBlank()) {
            System.out.println("Blank path");
            return false;
        }

        File directory = new File(path);
        boolean directoryCreated = directory.exists();
        if (!directoryCreated) {
            directoryCreated = directory.mkdir();
        }

        return directoryCreated;
    }
}

