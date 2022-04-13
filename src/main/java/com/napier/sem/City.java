package com.napier.sem;

/**
 * City class modelling a city
 */
public class City {
    private final int id;               // City id
    private final String name;          // City name
    private final String countryCode;   // Country code
    private final String countryName;   // Country name
    private final String district;      // District name
    private final int population;       // Population

    /**
     * Constructor for City
     * @param id Unique Id
     * @param name Name
     * @param countryCode Country code
     * @param countryName
     * @param district District name
     * @param population Population
     */
    public City(int id, String name, String countryCode, String countryName, String district, int population) {
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.district = district;
        this.population = population;
    }

    /**
     * Getter for population attribute
     * @return city population
     */
    public int getPopulation(){
        return  this.population;
    }

    /**
     * Getter for city id attribute
     * @return city id
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for city name attribute
     * @return city name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for country code attribute
     * @return country code
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Getter for country name attribute
     * @return country name
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Getter for city district attribute
     * @return city district
     */
    public String getDistrict() {
        return district;
    }

    /**
     * ToString method
     * @return all class attributes
     */
    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", countryName='" + countryName + '\'' +
                ", district='" + district + '\'' +
                ", population=" + population +
                '}';
    }
}
