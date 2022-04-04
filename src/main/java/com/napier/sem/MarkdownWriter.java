package com.napier.sem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MarkdownWriter {
    public static void countryListToMarkdown(ArrayList<Country> countries, String fileName) {
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
        } else if (fileName.isEmpty()) {
            System.out.println("Empty filename");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("| Code | Name | Continent | Region | Population | Capital |\r\n");
        sb.append("| --- | --- | --- | --- | --- | --- |\r\n");

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
            sb.append("| " +
                    country.getCode() + " | " +
                    country.getName() + " | " +
                    country.getContinent() + " | " +
                    country.getRegion() + " | " +
                    country.getPopulation() + " | " +
                    capitalCityName +
                    " |" +"\r\n");
        }
        try {
            new File("./reports/").mkdir();
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("./reports/" + fileName + ".md")));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void countryListToMarkdown(ArrayList<Country> countries, int n, String fileName) {
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
        } else if (fileName.isEmpty()) {
            System.out.println("Empty filename");
            return;
        }

        if (n <= 0) {
            System.out.println("Invalid number");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("| Code | Name | Continent | Region | Population | Capital |\r\n");
        sb.append("| --- | --- | --- | --- | --- | --- |\r\n");

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
            sb.append("| " +
                    country.getCode() + " | " +
                    country.getName() + " | " +
                    country.getContinent() + " | " +
                    country.getRegion() + " | " +
                    country.getPopulation() + " | " +
                    capitalCityName +
                    " |" +"\r\n");
        }
        try {
            new File("./reports/").mkdir();
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("./reports/" + fileName + ".md")));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void countryPopulationToMarkdown() {

    }

    public static void cityListToMarkdown(ArrayList<City> cities, String fileName) {
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
        sb.append("| Name | Country | District | Population |\r\n");
        sb.append("| --- | --- | --- | --- |\r\n");

        for (City city : cities) {
            sb.append("| " +
                    city.getName() + " | " +
                    city.getCountryName() + " | " +
                    city.getDistrict() + " | " +
                    city.getPopulation() + " | " +
                    " |" +"\r\n");
        }
        try {
            new File("./reports/").mkdir();
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("./reports/" + fileName + ".md")));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void cityListToMarkdown(ArrayList<City> cities, int n, String fileName) {
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

        if (n <= 0) {
            System.out.println("Invalid number");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("| Name | Country | District | Population |\r\n");
        sb.append("| --- | --- | --- | --- |\r\n");

        for (int i = 0; i < n; i++) {
            City city = cities.get(i);
            sb.append("| " +
                    city.getName() + " | " +
                    city.getCountryName() + " | " +
                    city.getDistrict() + " | " +
                    city.getPopulation() + " | " +
                    " |" +"\r\n");
        }
        try {
            new File("./reports/").mkdir();
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("./reports/" + fileName + ".md")));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

