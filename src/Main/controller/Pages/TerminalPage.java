package Main.controller.Pages;

import Main.model.Person;
import Main.model.UI;
import Main.model.Vehicles.Vehicle;

import java.util.List;

public abstract class TerminalPage extends UI {

    abstract public void addVehicle(Vehicle vehicle);

    abstract public void removeVehicle(Vehicle vehicle);

    abstract public void addWorker(List<Person> workers);

    abstract public void removeWorker(Person worker);

}
