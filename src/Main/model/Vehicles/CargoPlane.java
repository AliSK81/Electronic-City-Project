package Main.model.Vehicles;

import java.io.Serializable;

public class CargoPlane extends AirVehicle implements Serializable {
    private final double payload;

    public CargoPlane(int capacity, String company, double height, double length, double payload) {
        super(capacity, company, height, length);
        this.payload = payload;
    }

    @Override
    public void showInfo() {
        System.out.printf("  %-3d | %-7.1f | %-11s | %-8d | %-11.1f | %-8.1f | %.1f\n",
                getId(), getPrice(), getCompany(), getCapacity(), getHeight(), getLength(), payload);
    }

    public double getPayload() {
        return payload;
    }
}
