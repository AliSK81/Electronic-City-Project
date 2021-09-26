package Main.model.Vehicles;

import java.io.Serializable;

public class Ship extends MarineVehicle implements Serializable {
    private final String captain;

    public Ship(int capacity, String company, Fuel fuel, double depth, String captain) {
        super(capacity, company, fuel, depth);
        this.captain = captain;
    }

    @Override
    public void showInfo() {
        System.out.printf("  %-3d | %-7.1f | %-11s | %-8d | %-8s | %-8.1f | %s\n",
                getId(), getPrice(), getCompany(), getCapacity(), getFuel(), getDepth(), captain);
    }

    public String getCaptain() {
        return captain;
    }
}


