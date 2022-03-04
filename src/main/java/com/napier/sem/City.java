package com.napier.sem;

public class City {
    public int id;
    public String name;
    public String countryCode;
    public String district;
    public int population;

    /**
     * Constructor for City
     * @param id Unique Id
     * @param name Name 
     * @param countryCode Country code
     * @param district District name
     * @param population Population
     */
    public City(int id, String name, String countryCode, String district, int population) {
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
        this.district = district;
        this.population = population;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", district='" + district + '\'' +
                ", population=" + population +
                '}';
    }
}
