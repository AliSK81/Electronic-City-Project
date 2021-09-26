package Main.model;

import Main.model.Exceptions.WrongAmount;

import java.util.ArrayList;

public class Universe {

    private final static ArrayList<Country> countries = new ArrayList<>();

    public static void addCountry(String name) {
        Country country = new Country(name);
        countries.add(country);
    }

    public static void addCountry(Country country) {
        countries.add(country);
    }

    public static Country getCountry(String name) {
        for (Country country : countries)
            if (country.getName().equalsIgnoreCase(name))
                return country;

        throw new WrongAmount("Country not found!");
    }

    public static ArrayList<Country> getCountries() {
        return countries;
    }
}
