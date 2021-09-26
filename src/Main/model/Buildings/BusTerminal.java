package Main.model.Buildings;

import Main.model.Vehicles.Bus;
import Main.model.Vehicles.Vehicle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BusTerminal extends Terminal implements Serializable {

    final ArrayList<Bus> buses = new ArrayList<>();

    public BusTerminal(String name, String address, double area) {
        super(name, address, area);
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        super.addVehicle(vehicle);
        buses.add((Bus) vehicle);
    }

    @Override
    public void removeVehicle(Vehicle vehicle) {
        super.removeVehicle(vehicle);
        buses.remove((Bus) vehicle);
    }

    public List<Bus> getBuses() {
        return buses;
    }

    @Override
    public List<Bus> getVehicles() {
        return buses;
    }

}
