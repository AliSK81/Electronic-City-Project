package Main.model;

import Main.model.Accounts.BankAccount;
import Main.model.Buildings.*;
import Main.model.Vehicles.Vehicle;

public class Entry {

    private static Country country;
    private static City city;
    private static Hotel hotel;
    private static Terminal terminal;
    private static Vehicle vehicle;
    private static Bank bank;
    private static BankAccount account;

    public static Country getCountry() {
        return country;
    }

    public static void setCountry(Country country) {
        Entry.country = country;
    }

    public static City getCity() {
        return city;
    }

    public static void setCity(City city) {
        Entry.city = city;
    }

    public static Hotel getHotel() {
        return hotel;
    }

    public static void setHotel(Hotel hotel) {
        Entry.hotel = hotel;
    }

    public static Terminal getTerminal() {
        return terminal;
    }

    public static Airport getAP() {
        return (Airport) terminal;
    }

    public static BusTerminal getBT() {
        return (BusTerminal) terminal;
    }

    public static RailwayStation getRS() {
        return (RailwayStation) terminal;
    }

    public static ShippingPort getSP() {
        return (ShippingPort) terminal;
    }

    public static void setTerminal(Terminal terminal) {
        Entry.terminal = terminal;
    }

    public static Vehicle getVehicle() {
        return vehicle;
    }

    public static void setVehicle(Vehicle vehicle) {
        Entry.vehicle = vehicle;
    }

    public static Bank getBank() {
        return bank;
    }

    public static void setBank(Bank bank) {
        Entry.bank = bank;
    }

    public static BankAccount getAccount() {
        return account;
    }

    public static void setAccount(BankAccount account) {
        Entry.account = account;
    }

}
