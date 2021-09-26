package Main.model.Buildings;

import Main.model.Vehicles.AirVehicle;
import Main.model.Vehicles.CargoPlane;
import Main.model.Vehicles.PassengerPlane;
import Main.model.Vehicles.Vehicle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Airport extends Terminal implements Serializable {

    final ArrayList<AirVehicle> airVehicles = new ArrayList<>();
    private int runways;
    private boolean international;
    private int numPPlanes;
    private int numCPlanes;

    public Airport(String name, String address, double area, int runways, boolean international) {
        super(name, address, area);
        this.runways = runways;
        this.international = international;
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        super.addVehicle(vehicle);
        airVehicles.add((AirVehicle) vehicle);
        if (vehicle instanceof PassengerPlane) numPPlanes++;
        else if (vehicle instanceof CargoPlane) numCPlanes++;
    }

    @Override
    public void removeVehicle(Vehicle vehicle) {
        super.removeVehicle(vehicle);
        airVehicles.remove((AirVehicle) vehicle);
        if (vehicle instanceof PassengerPlane) numPPlanes--;
        else if (vehicle instanceof CargoPlane) numCPlanes--;
    }

    public List<PassengerPlane> getPassengerPlanes() {
        ArrayList<PassengerPlane> PPlanes = new ArrayList<>();
        airVehicles.stream().filter(AV -> AV instanceof PassengerPlane).forEach(PP -> PPlanes.add((PassengerPlane) PP));
        return PPlanes;
    }

    public List<CargoPlane> getCargoPlanes() {
        ArrayList<CargoPlane> CPlanes = new ArrayList<>();
        airVehicles.stream().filter(AV -> AV instanceof CargoPlane).forEach(CP -> CPlanes.add((CargoPlane) CP));
        return CPlanes;
    }

    @Override
    public List<PassengerPlane> getVehicles() {
        return getPassengerPlanes();
    }

    public int getRunways() {
        return runways;
    }

    public void setRunways(int runways) {
        this.runways = runways;
    }

    public boolean isInternational() {
        return international;
    }

    public void setInternational(boolean international) {
        this.international = international;
    }

    public int getNumPPlanes() {
        return numPPlanes;
    }

    public int getNumCPlanes() {
        return numCPlanes;
    }

}
