package Main.model.Vehicles;

import java.io.Serializable;

public class Bus extends LandVehicle implements Serializable {
    private final int line;
    private final boolean vip;

    public Bus(int capacity, String company, double speed, double lpk, int year, int line, boolean vip) {
        super(capacity, company, speed, lpk, year);
        this.line = line;
        this.vip = vip;
    }

    @Override
    public void showInfo() {
        System.out.printf("  %-3d | %-7.1f | %-11s | %-8d | %-8.1f | %-4d | %-6.1f | %-5d | %s\n",
                getId(), getPrice(), getCompany(), getCapacity(), getSpeed(), getYear(), getLpk(), line, vip);
    }

    public int getLine() {
        return line;
    }

    public boolean isVip() {
        return vip;
    }
}
