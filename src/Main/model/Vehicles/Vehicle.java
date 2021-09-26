package Main.model.Vehicles;

import Main.model.Costs.ServiceCost;
import Main.model.Entry;

import java.io.Serializable;

public abstract class Vehicle implements Serializable {
    private final int capacity;
    double price;
    private int id;
    private String company;

    public Vehicle(int capacity, String company) {
        this.id = Entry.getCountry().lastVehicleID++;
        this.company = company;
        this.capacity = capacity;
        this.price = ServiceCost.valueOf(this.getClass().getSimpleName()).getCost() * capacity;
    }

    abstract public void showInfo();

    public int getId() {
        return id;
    }


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getPrice() {
        return price;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(ID: " + id + ")";
    }


}
