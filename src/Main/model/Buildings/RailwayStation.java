package Main.model.Buildings;

import Main.model.Vehicles.Train;
import Main.model.Vehicles.Vehicle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RailwayStation extends Terminal implements Serializable {

    final ArrayList<Train> trains = new ArrayList<>();
    private int inputs;
    private int outputs;

    public RailwayStation(String name, String address, double area, int inputs, int outputs) {
        super(name, address, area);
        this.inputs = inputs;
        this.outputs = outputs;
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        super.addVehicle(vehicle);
        trains.add((Train) vehicle);
    }

    @Override
    public void removeVehicle(Vehicle vehicle) {
        super.removeVehicle(vehicle);
        trains.remove((Train) vehicle);
    }

    public List<Train> getTrains() {
        return trains;
    }

    @Override
    public List<Train> getVehicles() {
        return trains;
    }

    public int getInputs() {
        return inputs;
    }

    public void setInputs(int inputs) {
        this.inputs = inputs;
    }

    public int getOutputs() {
        return outputs;
    }

    public void setOutputs(int outputs) {
        this.outputs = outputs;
    }
}
