package Main.model.Vehicles;

import java.io.Serializable;

public abstract class MarineVehicle extends Vehicle implements Serializable {
    private final Fuel fuel;
    private final double depth;

    public MarineVehicle(int capacity, String company, Fuel fuel, double depth) {
        super(capacity, company);
        this.fuel = fuel;
        this.depth = depth;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public double getDepth() {
        return depth;
    }

    public enum Fuel {
        MGO, MDO, IFO, MFO, HFO
    }

}

