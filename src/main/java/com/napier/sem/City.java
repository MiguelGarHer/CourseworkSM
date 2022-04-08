package com.napier.sem;

public class City {
    private final int id;
    private final String name;
    private final String countryCode;



    private final String countryName;
    private final String district;
    private final int population;

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

    public int getPopulation(){
        return  this.population;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getDistrict() {
        return district;
    }

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
