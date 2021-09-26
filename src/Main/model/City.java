package Main.model;

import Main.model.Buildings.*;
import Main.model.Costs.Payment;
import Main.model.Exceptions.WrongAmount;
import Main.model.Exceptions.WrongName;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class City implements Serializable {

    private final String name;
    private final ArrayList<Person> people = new ArrayList<>();
    private final ArrayList<Hotel> hotels = new ArrayList<>();
    private final ArrayList<Terminal> terminals = new ArrayList<>();
    private final ArrayList<Payment> payments = new ArrayList<>();
    private final ArrayList<Bank> banks = new ArrayList<>();
    public int numWorkers;
    public int numCreditors;
    private double totalMoney;
    private int numAirports;
    private int numBusTerminals;
    private int numRailways;
    private int numShippingPorts;

    public City(String name) {
        if (!name.matches(".*[a-z].*") && !name.matches(".*[A-Z].*")) throw new WrongName();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // ---------------------- Getters Start --------------------

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getPopulation() {
        return people.size();
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    public ArrayList<Person> getPeople(Job job) {
        ArrayList<Person> drivers = new ArrayList<>();
        people.stream().filter(person -> person.getJob().equals(job) && !person.isWorking()).forEach(drivers::add);
        return drivers;
    }

    public ArrayList<Hotel> getHotels() {
        return hotels;
    }

    public ArrayList<Terminal> getTerminals() {
        return terminals;
    }

    public ArrayList<Payment> getPayments(String type) {
        if (type.equals("all")) return payments;

        ArrayList<Payment> incomes = new ArrayList<>();
        ArrayList<Payment> outputs = new ArrayList<>();

        for (Payment payment : payments)
            if (payment.isIncome()) incomes.add(payment);
            else outputs.add(payment);

        if (type.equals("incomes")) return incomes;
        else return outputs;
    }

    public ArrayList<Bank> getBanks() {
        return banks;
    }

    public int getNumHotels() {
        return hotels.size();
    }


    public int getNumAirports() {
        return numAirports;
    }

    public int getNumBusTerminals() {
        return numBusTerminals;
    }

    public int getNumRailways() {
        return numRailways;
    }

    public int getNumShippingPorts() {
        return numShippingPorts;
    }

    // ---------------------- Getters End --------------------


    // ----------- Banks -------------

    public void addBank(Bank bank) {
        banks.add(bank);
    }


    // ---------------------------


    // ------- Start Hotels ------------
    public void addHotel(Hotel hotel) {
        addPayment("Add Hotel", -hotel.getPrice());
        hotels.add(hotel);
    }

    public void removeHotel(Hotel hotel) {
        addPayment("Remove Hotel", hotel.getPrice());
        hotels.remove(hotel);
    }

    // ------------- End Hotels ----------------------


    // -------------- Start People --------------------

    public Person generatePerson() {
        String[] boyNames = {"Jacob", "Michael", "Joshua", "Matthew", "Daniel", "Andrew", "Ethan", "Joseph", "William", "Anthony", "David", "Nicholas", "Ryan", "Tyler", "James", "John", "Jonathan", "Noah", "Brandon", "Dylan", "Samuel", "Zachary", "Nathan", "Logan", "Justin", "Gabriel", "Jose"};
        String[] girlNames = {"Emily", "Madison", "Emma", "Olivia", "Hannah", "Abigail", "Isabella", "Samantha", "Ashley", "Sarah", "Sophia", "Alyssa", "Grace", "Ava", "Taylor", "Brianna", "Natalie", "Kayla", "Jessica", "Anna", "Victoria", "Mia", "Sydney", "Jasmine", "Julia", "Morgan", "Destiny", "Ella", "Kaitlyn", "Megan"};
        String[] lastNames = {"ALLEN", "YOUNG", "KING", "WRIGHT", "LOPEZ", "HILL", "SCOTT", "GREEN", "ADAMS", "BAKER", "NELSON", "CARTER", "MITCHELL", "PEREZ", "ROBERTS", "TURNER", "PHILLIPS", "CAMPBELL", "PARKER", "EVANS", "EDWARDS", "COLLINS", "STEWART", "SANCHEZ", "MORRIS", "ROGERS", "REED", "COOK", "MORGAN", "BELL", "MURPHY", "BAILEY", "RIVERA", "COOPER", "COX", "HOWARD", "WARD"};
        Job[] jobs = Job.values();

        String name, gender;
        if (Math.random() < 0.5) {
            name = boyNames[(int) (Math.random() * boyNames.length)];
            gender = "Male";
        } else {
            name = girlNames[(int) (Math.random() * girlNames.length)];
            gender = "Female";
        }
        int family = (int) (Math.random() * lastNames.length);
        int year = (int) (Math.random() * (2002 - 1960) + 1960);
        int job = (int) (Math.random() * jobs.length);

        return new Person(name, lastNames[family], this.name, year, jobs[job], gender);
    }

    public void addPerson(Person person) {
        people.add(person);
        Entry.getCountry().population++;
    }

    public void removePerson(Person person) {
        people.remove(person);
        Entry.getCountry().population--;
    }

    // -------------- End People --------------------


    //---------------- Start Terminals ---------------

    public void addTerminal(Terminal TER) {
        addPayment("Add " + TER.getClass().getSimpleName(), -TER.getPrice());
        terminals.add(TER);
        if (TER instanceof Airport) numAirports++;
        else if (TER instanceof BusTerminal) numBusTerminals++;
        else if (TER instanceof RailwayStation) numRailways++;
        else if (TER instanceof ShippingPort) numShippingPorts++;
    }

    public void removeTerminal(Terminal TER) {
        addPayment("Remove " + TER.getClass().getSimpleName(), TER.getPrice());
        terminals.remove(TER);
        if (TER instanceof Airport) numAirports--;
        else if (TER instanceof BusTerminal) numBusTerminals--;
        else if (TER instanceof RailwayStation) numRailways--;
        else if (TER instanceof ShippingPort) numShippingPorts--;
    }

    public List<Airport> getAirports() {
        ArrayList<Airport> airports = new ArrayList<>();
        terminals.stream().filter(TER -> TER instanceof Airport).forEach(AP -> airports.add((Airport) AP));
        return airports;
    }

    public List<RailwayStation> getRailways() {
        ArrayList<RailwayStation> railways = new ArrayList<>();
        terminals.stream().filter(TER -> TER instanceof RailwayStation).forEach(RW -> railways.add((RailwayStation) RW));
        return railways;
    }

    public List<BusTerminal> getBusTerminals() {
        ArrayList<BusTerminal> busTerminals = new ArrayList<>();
        terminals.stream().filter(TER -> TER instanceof BusTerminal).forEach(BT -> busTerminals.add((BusTerminal) BT));
        return busTerminals;
    }

    public List<ShippingPort> getShippingPorts() {
        ArrayList<ShippingPort> shippingPorts = new ArrayList<>();
        terminals.stream().filter(TER -> TER instanceof ShippingPort).forEach(SP -> shippingPorts.add((ShippingPort) SP));
        return shippingPorts;
    }

    //---------------- End Terminals ---------------


    public void checkPayment(double cost) {
        if (cost < -1000)
            throw new WrongAmount("The maximum allowed payment must be observed! (0-1000$)");
        if (totalMoney + cost < 0)
            throw new WrongAmount("No Enough Money; Total Money: " + totalMoney + " $, Required :" + -cost + " $\n");
    }

    public void addPayment(String service, double cost) {
        checkPayment(cost);

        totalMoney += cost;
        Entry.getCountry().addPayment(cost);
        boolean income = !(cost < 0);
        Payment payment = new Payment(service, cost, income);
        payments.add(payment);
    }

    public double computeWorkersSalary() {
        double total_salary = 0;
        for (Person person : people) if (person.isCreditor()) total_salary += person.getSalary();
        return total_salary;
    }

    public void payWorkersSalary() {
        this.addPayment("WorkersSalary", -computeWorkersSalary());
        for (Person person : people) {
            person.setCreditor(false);
            numCreditors--;
        }
    }

    @Override
    public String toString() {
        return name + " City";
    }

    public void transferPerson(City city2, Person person) {
        this.people.remove(person);
        city2.people.add(person);
        person.setCity(city2.getName());
    }

    public int getNumWorkers() {
        return numWorkers;
    }

    public int getNumCreditors() {
        return numCreditors;
    }

    public int getNumTerminals() {
        return terminals.size();
    }

    public int getNumBanks() {
        return banks.size();
    }

    public enum Job {
        Sailor, Pilot, BusDriver, LocoDriver, Crew
    }

}