package Main.model.Vehicles;

import java.io.Serializable;

public abstract class AirVehicle extends Vehicle implements Serializable {
    private final double height;
    private final double length;

    public AirVehicle(int capacity, String company, double height, double length) {
        super(capacity, company);
        this.height = height;
        this.length = length;
    }

    public double getHeight() {
        return height;
    }

    public double getLength() {
        return length;
    }

    abstract public void showInfo();
}