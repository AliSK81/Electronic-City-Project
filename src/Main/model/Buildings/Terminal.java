package Main.model.Buildings;

import Main.model.City;
import Main.model.City.Job;
import Main.model.Costs.ServiceCost;
import Main.model.Entry;
import Main.model.Exceptions.EmptyList;
import Main.model.Exceptions.WrongName;
import Main.model.Person;
import Main.model.Trip.ITripable;
import Main.model.Trip.Trip;
import Main.model.Vehicles.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

abstract public class Terminal implements ITripable, Serializable {

    transient private static Job requiredJob;
    private final int id;
    private final double area;
    int numVehicles;
    ArrayList<Person> drivers = new ArrayList<>();
    ArrayList<Trip> trips = new ArrayList<>();
    private String name;
    private String address;
    private double price;

    public Terminal(String name, String address, double area) {
        if (!name.matches(".*[a-z].*") && !name.matches(".*[A-Z].*")) throw new WrongName();
        this.id = Entry.getCountry().lastTerminalID++;
        this.name = name;
        this.address = address;
        this.area = area;
        this.price = ServiceCost.valueOf(this.getClass().getSimpleName()).getCost() * area;
    }

    public static Job getRequiredJob() {
        return requiredJob;
    }

    public static void setRequiredJob(Job job) {
        requiredJob = job;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getArea() {
        return area;
    }

    public double getPrice() {
        return price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumVehicles() {
        return numVehicles;
    }

    public int getNumDrivers() {
        return drivers.size();
    }

    public int getNumTrips() {
        return trips.size();
    }

    public void addDriver(Person person) {
        drivers.add(person);
        person.setWorking(true);
        Entry.getCity().numWorkers++;
    }

    // TODO transfer crew

    public void removeDriver(Person person) {
        drivers.remove(person);
        person.setWorking(false);
        Entry.getCity().numWorkers--;
    }

    private void transferDriver(Terminal destTER, City destCity, Person driver) {
        this.drivers.remove(driver);
        destTER.drivers.add(driver);
        Entry.getCity().transferPerson(destCity, driver);
        driver.setCreditor(true);
        destCity.numCreditors++;
    }

    private void transferVehicle(Terminal destTER, City destCity, Vehicle vehicle) {

        if (this instanceof Airport) {
            ((Airport) this).airVehicles.remove((AirVehicle) vehicle);
            ((Airport) destTER).airVehicles.add((AirVehicle) vehicle);
            if (vehicle instanceof PassengerPlane) { // transfer crews
                for (Person crew : ((PassengerPlane) vehicle).getCrews()) {
                    Entry.getCity().transferPerson(destCity, crew);
                }
            }
        }
        if (this instanceof BusTerminal) {
            ((BusTerminal) this).buses.remove((Bus) vehicle);
            ((BusTerminal) destTER).buses.add((Bus) vehicle);
        }
        if (this instanceof RailwayStation) {
            ((RailwayStation) this).trains.remove((Train) vehicle);
            ((RailwayStation) destTER).trains.add((Train) vehicle);
        }
        if (this instanceof ShippingPort) {
            ((ShippingPort) this).marineVehicles.remove((MarineVehicle) vehicle);
            ((ShippingPort) destTER).marineVehicles.add((MarineVehicle) vehicle);
        }
    }

    private void transferPassengers(City destCity, ArrayList<Person> passengers) {
        for (Person passanger : passengers) {
            Entry.getCity().transferPerson(destCity, passanger);
        }
    }

    @Override
    public void addTrip(City originCity, Terminal destTER, City destCity, ArrayList<Person> passengers, Person driver, Vehicle vehicle, String date) {
        Trip outgoing_trip = new Trip(false, this, originCity, destTER, destCity, passengers, driver, vehicle, date, getTripCost(passengers.size(), vehicle));
        Trip incoming_trip = new Trip(true, this, originCity, destTER, destCity, passengers, driver, vehicle, date, outgoing_trip.getCost());
        this.trips.add(outgoing_trip);
        destTER.trips.add(incoming_trip);

        transferDriver(destTER, destCity, driver);
        transferVehicle(destTER, destCity, vehicle);
        transferPassengers(destCity, passengers);

        outgoing_trip.showPassengers();
        Entry.getCity().addPayment(incoming_trip.getType(), incoming_trip.getCost());
    }

    public Trip getLastTrip() {
        return trips.get(trips.size() - 1);
    }

    @Override
    public void sortTrips() {
        Collections.sort(trips);
    }

    @Override
    public double getTripCost(int passengers, Vehicle vehicle) {
        return ServiceCost.valueOf(vehicle.getClass().getSimpleName() + "Trip").getCost() * passengers;
    }

    @Override
    public void showTripsHistory(String typeOfTrip) {
        int total = 0, incoming = 0, outgoing = 0;
        for (Trip trip : trips) total += (trip.isIncoming()) ? ++incoming : ++outgoing;
        if (total == 0) throw new EmptyList();
        if (typeOfTrip.equals("Incoming") && incoming == 0) throw new EmptyList();
        if (typeOfTrip.equals("Outgoing") && outgoing == 0) throw new EmptyList();
        sortTrips();
        System.out.println("---------------------------------------------------------------- Trips History ----------------------------------------------------------------");
        System.out.println(" ID  | Type\t\t\t\t  | Incoming | Origin\t\t\t\t| Dest\t\t\t\t  | Driver ID | Passengers | Vehicle ID | Date\t\t | Cost");
        boolean show = true;
        for (Trip trip : trips) {
            if (typeOfTrip.equals("Incoming")) show = trip.isIncoming();
            else if (typeOfTrip.equals("Outgoing")) show = !trip.isIncoming();
            if (show) trip.showInfo();
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public void addVehicle(Vehicle vehicle) {
        Entry.getCity().addPayment("Add " + vehicle.getClass().getSimpleName(), -vehicle.getPrice());
        numVehicles++;
    }

    public void removeVehicle(Vehicle vehicle) {
        Entry.getCity().addPayment("Remove " + vehicle.getClass().getSimpleName(), vehicle.getPrice());
        numVehicles--;
    }

    public ArrayList<Person> getDrivers() {
        return drivers;
    }

    public ArrayList<Trip> getTrips(String type) {
        if (type.equals("all")) return trips;

        ArrayList<Trip> incoming = new ArrayList<>();
        ArrayList<Trip> outgoing = new ArrayList<>();

        for (Trip trip : trips)
            if (trip.isIncoming()) incoming.add(trip);
            else outgoing.add(trip);

        if (type.equals("incoming")) return incoming;
        else return outgoing;
    }

    @Override
    public String toString() {
        return name + " " + this.getClass().getSimpleName() + "(ID: " + id + ")";
    }

    abstract public <T extends Vehicle> List<T> getVehicles();
}


