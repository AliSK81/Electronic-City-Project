package Main.model.Vehicles;

import Main.model.Entry;
import Main.model.Person;

import java.io.Serializable;
import java.util.ArrayList;

public class PassengerPlane extends AirVehicle implements Serializable {
    private final ArrayList<Person> crews = new ArrayList<>();
    private final int seats;

    public PassengerPlane(int capacity, String company, double height, double length, int seats) {
        super(capacity, company, height, length);
        this.seats = seats;
    }

    @Override
    public void showInfo() {

    }

    public void addCrew(Person person) {
        crews.add(person);
        person.setWorking(true);
        Entry.getCity().numWorkers++;
    }

    public void removeCrew(Person person) {
        crews.remove(person);
        person.setWorking(false);
        Entry.getCity().numWorkers--;
    }

    public ArrayList<Person> getCrews() {
        return crews;
    }

    public int getSeats() {
        return seats;
    }

    public int getNumCrews() {
        return crews.size();
    }
}
