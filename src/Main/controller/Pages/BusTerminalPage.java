package Main.controller.Pages;

import Main.Main;
import Main.model.Buildings.Terminal;
import Main.model.City;
import Main.model.Entry;
import Main.model.Exceptions.Alerts;
import Main.model.Exceptions.WrongAmount;
import Main.model.Person;
import Main.model.Vehicles.Bus;
import Main.model.Vehicles.Vehicle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BusTerminalPage extends TerminalPage implements Initializable {

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
    private Button updateInfoBTN;

    @FXML
    private Button resetInfoBTN;

    @FXML
    private Tab busesTAB;

    private TableView<Bus> busesTable;

    @FXML
    private Label numBusesLBL;

    @FXML
    private Button addBusBTN;

    @FXML
    private Button removeBusBTN;

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
        setLabel(nameLBL, Entry.getBT().getName() + " Airport");
        setLabel(idLBL, Entry.getBT().getId());
        setLabel(priceLBL, Entry.getBT().getPrice());
        setLabel(areaLBL, Entry.getBT().getArea());
        setLabel(numTripsLBL, Entry.getBT().getNumTrips());

        nameTXT.setStyle("");
        setText(nameTXT, Entry.getBT().getName());
        setText(addressTXT, Entry.getBT().getAddress());

        disable(updateInfoBTN);
        disable(resetInfoBTN);
    }

    private void initDrivers() {
        driversTable = loadTable("PeopleTable.fxml");
        setTable(driversTAB, driversTable, Entry.getBT().getDrivers());
        setLabel(numDriversLBL, Entry.getBT().getNumDrivers());
    }

    private void initVehicles() {
        busesTable = loadTable("BusesTable.fxml");
        setTable(busesTAB, busesTable, Entry.getBT().getBuses());
        setLabel(numBusesLBL, Entry.getBT().getNumVehicles());
    }

    private void updatePrice() {
        setLabel(priceLBL, Entry.getBT().getPrice());
        Main.getCityPage().updatePayments();
    }

    private void updateVehicles() {
        setLabel(numBusesLBL, Entry.getBT().getNumVehicles());
        Main.getCityPage().refreshBusTermianls();
    }

    private void updateDrivers() {
        setLabel(numDriversLBL, Entry.getBT().getNumDrivers());
        Main.getCityPage().refreshBusTermianls();
        Main.getCityPage().refreshPeople();
        Main.getCityPage().updatePeople();
    }

    @FXML
    void addVehicleAction(ActionEvent event) {
        if (event.getSource() == addBusBTN)
            showForm("AddBusForm.fxml");
    }

    @FXML
    void addDriverAction(ActionEvent event) {
        Terminal.setRequiredJob(City.Job.BusDriver);
        showForm("AddWorkerForm.fxml");
    }

    @FXML
    void backAction(ActionEvent event) {
        Main.getCityPage().resetRoot(root);
    }

    @FXML
    void changeInfoAction(KeyEvent event) {
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
    void removeDriverAction(ActionEvent event) {
        try {
            removeWorker(getSelectedItem(driversTable));
        } catch (RuntimeException ex) {
            Alerts.showError(ex.getMessage());
        }
    }

    @FXML
    void removeVehicleAction(ActionEvent event) {
        try {
            if (event.getSource() == removeBusBTN)
                removeVehicle(getSelectedItem(busesTable));
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
        Entry.getBT().setName(nameTXT.getText());
        Entry.getBT().setAddress(addressTXT.getText());

        initInfo();
        Main.getCityPage().refreshBusTermianls();
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        Entry.getBT().addVehicle(vehicle);
        addToTable(busesTable, (Bus) vehicle);

        updateVehicles();
        updatePrice();
    }

    @Override
    public void removeVehicle(Vehicle vehicle) {
        if (Alerts.showWarning("Remove " + vehicle + " ?")) {
            removeFromTable(busesTable, (Bus) vehicle);
            Entry.getBT().removeVehicle(vehicle);

            updateVehicles();
            updatePrice();
        }
    }

    @Override
    public void addWorker(List<Person> workers) {
        addToTable(driversTable, workers);

        for (Person person : workers)
            Entry.getBT().addDriver(person);

        updateDrivers();
    }

    @Override
    public void removeWorker(Person worker) {
        if (worker.isCreditor())
            throw new WrongAmount("This driver is creditor");

        if (Alerts.showWarning("Remove " + worker + " ?")) {
            removeFromTable(driversTable, worker);
            Entry.getBT().removeDriver(worker);
            updateDrivers();
        }
    }

}
