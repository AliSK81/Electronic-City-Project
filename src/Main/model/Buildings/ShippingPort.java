package Main.model.Buildings;

import Main.model.Vehicles.Boat;
import Main.model.Vehicles.MarineVehicle;
import Main.model.Vehicles.Ship;
import Main.model.Vehicles.Vehicle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShippingPort extends Terminal implements Serializable {

    final ArrayList<MarineVehicle> marineVehicles = new ArrayList<>();
    private int docks;
    private int numShips;
    private int numBoats;

    public ShippingPort(String name, String address, double area, int docks) {
        super(name, address, area);
        this.docks = docks;
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        super.addVehicle(vehicle);
        marineVehicles.add((MarineVehicle) vehicle);
        if (vehicle instanceof Ship) numShips++;
        else if (vehicle instanceof Boat) numBoats++;
    }

    @Override
    public void removeVehicle(Vehicle vehicle) {
        super.removeVehicle(vehicle);
        marineVehicles.remove((MarineVehicle) vehicle);
        if (vehicle instanceof Ship) numShips--;
        else if (vehicle instanceof Boat) numBoats--;
    }

    public List<Ship> getShips() {
        ArrayList<Ship> ships = new ArrayList<>();
        marineVehicles.stream().filter(MV -> MV instanceof Ship).forEach(SH -> ships.add((Ship) SH));
        return ships;
    }

    public List<Boat> getBoats() {
        ArrayList<Boat> boats = new ArrayList<>();
        marineVehicles.stream().filter(MV -> MV instanceof Boat).forEach(BO -> boats.add((Boat) BO));
        return boats;
    }

    @Override
    public List<Ship> getVehicles() {
        return getShips();
    }

    public int getDocks() {
        return docks;
    }

    public void setDocks(int docks) {
        this.docks = docks;
    }

    public int getNumShips() {
        return numShips;
    }

    public int getNumBoats() {
        return numBoats;
    }
}
