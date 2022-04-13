package com.napier.sem;

import java.util.List;

/**
 * Country class modelling a country
 */
public class Country {
	private List<City> cities;				// List of cities
	private List<Language> languages; 		// List of languages

	private final String code;				// Country code
	private final String name; 				// Country name
	private final String continent; 		// Country continent
	private final String region; 			// Country region
	private final double surfaceArea;		// Surface area
	private final int indepYear;			// Year of independence
	private final int population;			// Population
	private final double lifeExpectancy;	// Life expectancy
	private final double GNP;				// Current GNP
	private final double GNPOld;			// Old GNP
	private final String localName; 		// Local country name
	private final String governmentForm; 	// Government form
	private final String headOfState;		// Head of State
	private final int capital;				// Capital city id
	private final String code2;				// Secondary country code

	/**
	 * Constructor
	 * @param code country code
	 * @param name country name
	 * @param continent country continent
	 * @param region country region
	 * @param surfaceArea surface area
	 * @param indepYear year of independence
	 * @param population population
	 * @param lifeExpectancy life expectancy
	 * @param GNP current GNP
	 * @param GNPOld old GNP
	 * @param localName local country name
	 * @param governmentForm government form
	 * @param headOfState head of state
	 * @param capital capital city id
	 * @param code2 secondary Country code
	 */
	public Country(String code, String name, String continent, String region, double surfaceArea, int indepYear, int population, double lifeExpectancy, double GNP, double GNPOld, String localName, String governmentForm, String headOfState, int capital, String code2) {
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

	/**
	 * Setter for cities attribute
	 * @param cities list of cities
	 */
	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	/**
	 * Setter for languages attribute
	 * @param languages list of languages
	 */
	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}

	/**
	 * Getter for cities attribute
	 * @return list of cities
	 */
	public List<City> getCities() {
		return cities;
	}

	/**
	 * Getter for languages attribute
	 * @return list of languages
	 */
	public List<Language> getLanguages() {
		return languages;
	}

	/**
	 * Getter for code attribute
	 * @return country code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Getter for name attribute
	 * @return country name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for continent attribute
	 * @return country continent name
	 */
	public String getContinent() {
		return continent;
	}

	/**
	 * Getter for region attribute
	 * @return country region name
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * Getter for surface area attribute
	 * @return country surface area
	 */
	public double getSurfaceArea() {
		return surfaceArea;
	}

	/**
	 * Getter for indepYear attribute
	 * @return country year of independence
	 */
	public int getIndepYear() {
		return indepYear;
	}

	/**
	 * Getter for population attribute
	 * @return country population
	 */
	public int getPopulation() {
		return population;
	}

	/**
	 * Getter for life expectancy attribute
	 * @return country life expectancy
	 */
	public double getLifeExpectancy() {
		return lifeExpectancy;
	}

	/**
	 * Getter for GNP attribute
	 * @return current country GNP
	 */
	public double getGNP() {
		return GNP;
	}

	/**
	 * Getter for GNP old attribute
	 * @return old country GNP
	 */
	public double getGNPOld() {
		return GNPOld;
	}

	/**
	 * Getter for local name attribute
	 * @return local country name
	 */
	public String getLocalName() {
		return localName;
	}

	/**
	 * Getter for government form attribute
	 * @return country government form
	 */
	public String getGovernmentForm() {
		return governmentForm;
	}

	/**
	 * Getter for head of state attribute
	 * @return country head of state
	 */
	public String getHeadOfState() {
		return headOfState;
	}

	/**
	 * Getter for capital city id attribute
	 * @return capital city id
	 */
	public int getCapital() {
		return capital;
	}

	/**
	 * Getter for secondary country code attribute
	 * @return secondary country code
	 */
	public String getCode2() {
		return code2;
	}

	/**
	 * ToString method
	 * @return all class attributes
	 */
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