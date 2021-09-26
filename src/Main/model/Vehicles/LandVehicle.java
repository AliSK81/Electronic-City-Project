package Main.model.Vehicles;

import java.io.Serializable;

public abstract class LandVehicle extends Vehicle implements Serializable {
    private final double speed;
    private final double lpk;
    private final int year;

    public LandVehicle(int capacity, String company, double speed, double lpk, int year) {
        super(capacity, company);
        this.speed = speed;
        this.lpk = lpk;
        this.year = year;
    }

    public double getSpeed() {
        return speed;
    }

    public double getLpk() {
        return lpk;
    }

    public int getYear() {
        return year;
    }

}



