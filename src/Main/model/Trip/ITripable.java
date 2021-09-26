package Main.model.Trip;

import Main.model.Buildings.Terminal;
import Main.model.City;
import Main.model.Person;
import Main.model.Vehicles.Vehicle;

import java.io.IOException;
import java.util.ArrayList;

public interface ITripable {

    void addTrip(City originCity, Terminal destTER, City destCity, ArrayList<Person> passengers, Person driver, Vehicle vehicle, String date) throws IOException;

    void sortTrips();

    double getTripCost(int passengers, Vehicle vehicle);

    void showTripsHistory(String typeOfTrip);
}
