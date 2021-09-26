package Main.model.Vehicles;

import java.io.Serializable;

public class Boat extends MarineVehicle implements Serializable {
    private final boolean engine;
    private final Usage usage;

    public Boat(int capacity, String company, Fuel fuel, double depth, boolean engine, Usage usage) {
        super(capacity, company, fuel, depth);
        this.engine = engine;
        this.usage = usage;
    }

    @Override
    public void showInfo() {
        System.out.printf("  %-3d | %-7.1f | %-12s | %-8d | %-11s | %-8.1f | %-6s | %s\n",
                getId(), getPrice(), getCompany(), getCapacity(), getFuel(), getDepth(), engine, usage);
    }

    public boolean isEngine() {
        return engine;
    }

    public Usage getUsage() {
        return usage;
    }


    public enum Usage {
        Fishing, LifeBoat, TowTruck, Sports
    }

}
