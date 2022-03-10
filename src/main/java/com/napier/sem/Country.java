package com.napier.sem;

import java.util.ArrayList;

public class Country {
	private final ArrayList<City> cities;
	private final ArrayList<Language> languages;

	private final String code;
	private final String name;
	private final String continent;
	private final String region;
	private final double surfaceArea;
	private final int indepYear;
	private final int population;
	private final double lifeExpectancy;
	private final double GNP;
	private final double GNPOld;
	private final String localName;
	private final String governmentForm;
	private final String headOfState;
	private final int capital;
	private final String code2;

	public Country(ArrayList<City> cities, ArrayList<Language> languages, String code, String name, String continent, String region, double surfaceArea, int indepYear, int population, double lifeExpectancy, double GNP, double GNPOld, String localName, String governmentForm, String headOfState, int capital, String code2) {
		this.cities = cities;
		this.languages = languages;
		this.code = code;
		this.name = name;
		this.continent = continent;
		this.region = region;
		this.surfaceArea = surfaceArea;
		this.indepYear = indepYear;
		this.population = population;
		this.lifeExpectancy = lifeExpectancy;
		this.GNP = GNP;
		this.GNPOld = GNPOld;
		this.localName = localName;
		this.governmentForm = governmentForm;
		this.headOfState = headOfState;
		this.capital = capital;
		this.code2 = code2;
	}

	public ArrayList<City> getCities() {
		return cities;
	}

	public ArrayList<Language> getLanguages() {
		return languages;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getContinent() {
		return continent;
	}

	public String getRegion() {
		return region;
	}

	public double getSurfaceArea() {
		return surfaceArea;
	}

	public int getIndepYear() {
		return indepYear;
	}

	public int getPopulation() {
		return population;
	}

	public double getLifeExpectancy() {
		return lifeExpectancy;
	}

	public double getGNP() {
		return GNP;
	}

	public double getGNPOld() {
		return GNPOld;
	}

	public String getLocalName() {
		return localName;
	}

	public String getGovernmentForm() {
		return governmentForm;
	}

	public String getHeadOfState() {
		return headOfState;
	}

	public int getCapital() {
		return capital;
	}

	public String getCode2() {
		return code2;
	}

	@Override
	public String toString() {
		return "Country{" +
				"cities=" + cities +
				", languages=" + languages +
				", code='" + code + '\'' +
				", name='" + name + '\'' +
				", continent='" + continent + '\'' +
				", region='" + region + '\'' +
				", surfaceArea=" + surfaceArea +
				", indepYear=" + indepYear +
				", population=" + population +
				", lifeExpectancy=" + lifeExpectancy +
				", GNP=" + GNP +
				", GNPOld=" + GNPOld +
				", localName='" + localName + '\'' +
				", governmentForm='" + governmentForm + '\'' +
				", headOfState='" + headOfState + '\'' +
				", capital=" + capital +
				", code2='" + code2 + '\'' +
				'}';
	}
}