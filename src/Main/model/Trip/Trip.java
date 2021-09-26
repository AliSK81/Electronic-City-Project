package Main.model.Trip;

import Main.model.Buildings.Terminal;
import Main.model.City;
import Main.model.Entry;
import Main.model.Exceptions.*;
import Main.model.Person;
import Main.model.Vehicles.Vehicle;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Trip implements Comparable<Trip>, Serializable {

    private final int id;
    private final String type;
    private final boolean incoming;
    private final Terminal originTER;
    private final City originCity;
    private final Terminal destTER;
    private final City destCity;
    private final Vehicle vehicle;
    private final Person driver;
    private final ArrayList<Person> passengers;
    private final double cost;
    private final String time;

    public Trip(boolean incoming, Terminal originTER, City originCity, Terminal destTER, City destCity, ArrayList<Person> passengers, Person driver, Vehicle vehicle, String time, double cost) {
        this.id = Entry.getCountry().lastTripID++;
        this.incoming = incoming;
        this.type = vehicle.getClass().getSimpleName() + "Trip";
        this.originTER = originTER;
        this.originCity = originCity;
        this.destTER = destTER;
        this.destCity = destCity;
        this.passengers = passengers;
        this.driver = driver;
        this.vehicle = vehicle;
        this.time = time;
        this.cost = cost;
    }

    public static void checkTime(LocalDate time) {
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate now = LocalDate.now();
        LocalDate nextYear = LocalDate.now().plusYears(1);
        if (time.compareTo(now) < 0 || time.compareTo(nextYear) > 0) throw new InvalidTripTime();
    }

    public static void checkCountry() {
        if (Entry.getCountry().getNumOfCities() < 2)
            throw new CancelTrip("The number of cities must be 2 or more to do travel!");
    }

    public static void checkCity(City origin, City dest) {
        if (origin.equals(dest)) throw new InvalidCity();
        if (origin.getNumTerminals() == 0 || dest.getNumTerminals() == 0) throw new LackOfTerminal();
    }

    public static void checkTerminal(Terminal origin, Terminal dest) {
        if (origin != null && dest != null)
            if (!origin.getClass().equals(dest.getClass())) throw new InvalidTerminal();

        if (origin != null) {
            if (origin.getNumVehicles() == 0) throw new LackOfVehicle();
            if (origin.getNumDrivers() == 0) throw new LackOfDriver();
        }
    }

    public static void checkCapacity(int size, int capacity) {
        if (size > capacity) throw new FullCapacity();
        else if (size < capacity / 2) throw new LowCapacity();
    }

    public static void checkDriver(Person driver) {
        if (driver.isCreditor()) throw new InvalidDriver();
    }

    public static void checkPassenger(Person person) {
        if (person.isWorking()) throw new InvalidPassenger("This person cannot travel!");
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public boolean isIncoming() {
        return incoming;
    }

    public double getCost() {
        return cost;
    }


    public void showInfo() {
        System.out.printf(" %-3d | %-18s | %-8s | %-20s | %-19s | %-9d | %-10d | %-10d | %-10s | %.1f $\n",
                id, type, incoming, originCity + ": " + originTER, destCity + ": " + destTER,
                driver.getId(), passengers.size(), vehicle.getId(), time, cost);
    }

    public void showPassengers() {
        if (passengers.isEmpty()) throw new EmptyList();
        System.out.println("------------------------------------------------ Passengers Info -------------------------------------------------");
        System.out.println("  FirstName | LastName   | BirthYear | City\t\t   | Job \t\t| Working | Gender   | Salary  | Creditor | ID");
        for (Person passenger : passengers) passenger.showInfo();
        System.out.println("------------------------------------------------------------------------------------------------------------------");
    }

    @Override
    public int compareTo(Trip trip2) {
        if (!this.time.equals(trip2.time)) {
            return this.time.compareTo(trip2.time);
        } else {
            return Double.compare(this.cost, trip2.cost);
        }
    }

    public Terminal getOriginTER() {
        return originTER;
    }

    public City getOriginCity() {
        return originCity;
    }

    public Terminal getDestTER() {
        return destTER;
    }

    public City getDestCity() {
        return destCity;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getVehicleID() {
        return vehicle.getId();
    }

    public Person getDriver() {
        return driver;
    }

    public int getDriverID() {
        return driver.getId();
    }

    public ArrayList<Person> getPassengers() {
        return passengers;
    }

    public int getNumPassengers() {
        return passengers.size();
    }

    public String getTime() {
        return time;
    }
}

