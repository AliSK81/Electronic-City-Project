package Main.controller.Pages;

import Main.Main;
import Main.model.Buildings.Terminal;
import Main.model.City;
import Main.model.Entry;
import Main.model.Exceptions.Alerts;
import Main.model.Exceptions.WrongAmount;
import Main.model.Person;
import Main.model.Vehicles.Boat;
import Main.model.Vehicles.Ship;
import Main.model.Vehicles.Vehicle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ShippingPortPage extends TerminalPage implements Initializable {

    @FXML
    private VBox root;

    @FXML
    private Label nameLBL;

    @FXML
    private Label idLBL;

    @FXML
    private Label priceLBL;

    @FXML
    private Label areaLBL;

    @FXML
    private Label numTripsLBL;

    @FXML
    private TextField nameTXT;

    @FXML
    private TextField addressTXT;

    @FXML
    private Spinner<Integer> docksSPN;

    @FXML
    private Button updateInfoBTN;

    @FXML
    private Button resetInfoBTN;

    @FXML
    private Tab shipsTAB;

    private TableView<Ship> shipsTable;

    @FXML
    private Label numShipsLBL;

    @FXML
    private Button addShipBTN;

    @FXML
    private Button removeShipBTN;

    @FXML
    private Tab boatsTAB;

    private TableView<Boat> boatsTable;

    @FXML
    private Label numBoatsLBL;

    @FXML
    private Button addBoatBTN;

    @FXML
    private Button removeBoatBTN;

    @FXML
    private Tab driversTAB;

    private TableView<Person> driversTable;

    @FXML
    private Label numDriversLBL;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Main.setTerminalPage(this);

        initInfo();
        initVehicles();
        initDrivers();
    }

    private void initInfo() {
        setLabel(nameLBL, Entry.getSP().getName() + " Shipping Port");
        setLabel(idLBL, Entry.getSP().getId());
        setLabel(priceLBL, Entry.getSP().getPrice());
        setLabel(areaLBL, Entry.getSP().getArea());
        setLabel(numTripsLBL, Entry.getSP().getNumTrips());

        nameTXT.setStyle("");
        setText(nameTXT, Entry.getSP().getName());
        setText(addressTXT, Entry.getSP().getAddress());

        setSpinner(docksSPN, 1, 30, Entry.getSP().getDocks(), 1);

        disable(updateInfoBTN);
        disable(resetInfoBTN);
    }

    private void initDrivers() {
        driversTable = loadTable("PeopleTable.fxml");
        setTable(driversTAB, driversTable, Entry.getSP().getDrivers());
        setLabel(numDriversLBL, Entry.getSP().getNumDrivers());
    }

    private void initVehicles() {
        shipsTable = loadTable("ShipsTable.fxml");
        boatsTable = loadTable("BoatsTable.fxml");
        setTable(shipsTAB, shipsTable, Entry.getSP().getShips());
        setTable(boatsTAB, boatsTable, Entry.getSP().getBoats());
        setLabel(numShipsLBL, Entry.getSP().getNumShips());
        setLabel(numBoatsLBL, Entry.getSP().getNumBoats());
    }

    private void updatePrice() {
        setLabel(priceLBL, Entry.getSP().getPrice());
        Main.getCityPage().updatePayments();
    }

    private void updateVehicles() {
        setLabel(numShipsLBL, Entry.getSP().getNumShips());
        setLabel(numBoatsLBL, Entry.getSP().getNumBoats());
        Main.getCityPage().refreshShippingPorts();
    }

    private void updateDrivers() {
        setLabel(numDriversLBL, Entry.getSP().getNumDrivers());
        Main.getCityPage().refreshShippingPorts();
        Main.getCityPage().refreshPeople();
        Main.getCityPage().updatePeople();
    }


    @FXML
    void addServiceAction(ActionEvent event) {

    }

    @FXML
    void addVehicleAction(ActionEvent event) {
        if (event.getSource() == addShipBTN) {
            showForm("AddShipForm.fxml");
        } else if (event.getSource() == addBoatBTN) {
            showForm("AddBoatForm.fxml");
        }
    }

    @FXML
    void addDriverAction(ActionEvent event) {
        Terminal.setRequiredJob(City.Job.Sailor);
        showForm("AddWorkerForm.fxml");
    }

    @FXML
    void backAction(ActionEvent event) {
        Main.getCityPage().resetRoot(root);
    }

    @FXML
    void changeInfoAction() {
        changeInfo();
    }

    private void changeInfo() {
        if (nameTXT.getText().isEmpty() || nameTXT.getText().startsWith(" ")) {
            nameTXT.setStyle("-fx-background-color: pink");
            disable(updateInfoBTN);
        } else {
            nameTXT.setStyle("");
            enable(updateInfoBTN);
        }
        enable(resetInfoBTN);
    }

    @FXML
    void removeVehicleAction(ActionEvent event) {
        try {
            if (event.getSource() == removeShipBTN) {
                removeVehicle(getSelectedItem(shipsTable));
            } else if (event.getSource() == removeBoatBTN) {
                removeVehicle(getSelectedItem(boatsTable));
            }
        } catch (RuntimeException ex) {
            Alerts.showError(ex.getMessage());
        }
    }

    @FXML
    void removeDriverAction(ActionEvent event) {
        try {
            removeWorker(getSelectedItem(driversTable));
        } catch (RuntimeException ex) {
            Alerts.showError(ex.getMessage());
        }
    }

    @FXML
    void resetInfoAction(ActionEvent event) {
        initInfo();
    }

    @FXML
    void updateInfoAction(ActionEvent event) {
        Entry.getSP().setName(nameTXT.getText());
        Entry.getSP().setAddress(addressTXT.getText());
        Entry.getSP().setDocks(docksSPN.getValue());

        initInfo();
        Main.getCityPage().refreshShippingPorts();
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        Entry.getSP().addVehicle(vehicle);

        if (vehicle instanceof Ship) {
            addToTable(shipsTable, (Ship) vehicle);
        } else if (vehicle instanceof Boat) {
            addToTable(boatsTable, (Boat) vehicle);
        }
        updateVehicles();
        updatePrice();
    }

    @Override
    public void removeVehicle(Vehicle vehicle) {

        if (Alerts.showWarning("Remove " + vehicle + " ?")) {

            if (vehicle instanceof Ship) {
                removeFromTable(shipsTable, (Ship) vehicle);
            } else if (vehicle instanceof Boat) {
                removeFromTable(boatsTable, (Boat) vehicle);
            }

            Entry.getSP().removeVehicle(vehicle);
            updateVehicles();
            updatePrice();
        }
    }

    @Override
    public void addWorker(List<Person> workers) {
        addToTable(driversTable, workers);

        for (Person person : workers)
            Entry.getSP().addDriver(person);

        updateDrivers();
    }

    @Override
    public void removeWorker(Person worker) {
        if (worker.isCreditor())
            throw new WrongAmount("This driver is creditor");

        if (Alerts.showWarning("Remove " + worker + " ?")) {
            removeFromTable(driversTable, worker);
            Entry.getSP().removeDriver(worker);
            updateDrivers();
        }
    }

}
