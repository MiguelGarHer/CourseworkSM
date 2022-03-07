package com.napier.sem;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Country {
	public ArrayList<City> cities;
	public ArrayList<Language> languages;

	public String code;
	public String name;
	public String continent;
	public String region;
	public double surfaceArea;
	public int indepYear;
	public int population;
	public double lifeExpectancy;
	public double GNP;
	public double GNPOld;
	public String localName;
	public String governmentForm;
	public String headOfState;
	public int capital;
	public String code2;

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