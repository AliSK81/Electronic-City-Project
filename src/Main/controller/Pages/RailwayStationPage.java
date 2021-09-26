package Main.controller.Pages;

import Main.Main;
import Main.model.Buildings.Terminal;
import Main.model.City;
import Main.model.Entry;
import Main.model.Exceptions.Alerts;
import Main.model.Exceptions.WrongAmount;
import Main.model.Person;
import Main.model.Vehicles.Train;
import Main.model.Vehicles.Vehicle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RailwayStationPage extends TerminalPage implements Initializable {

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
    private Spinner<Integer> inputsSPN;

    @FXML
    private Spinner<Integer> outputsSPN;

    @FXML
    private Button updateInfoBTN;

    @FXML
    private Button resetInfoBTN;

    @FXML
    private Tab trainsTAB;

    private TableView<Train> trainsTable;

    @FXML
    private Label numTrainsLBL;

    @FXML
    private Button addTrainBTN;

    @FXML
    private Button removeTrainBTN;

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
        setLabel(nameLBL, Entry.getRS().getName() + " Railway Station");
        setLabel(idLBL, Entry.getRS().getId());
        setLabel(priceLBL, Entry.getRS().getPrice());
        setLabel(areaLBL, Entry.getRS().getArea());
        setLabel(numTripsLBL, Entry.getRS().getNumTrips());

        nameTXT.setStyle("");
        setText(nameTXT, Entry.getRS().getName());
        setText(addressTXT, Entry.getRS().getAddress());

        setSpinner(inputsSPN, 1, 30, Entry.getRS().getInputs(), 1);

        setSpinner(outputsSPN, 1, 30, Entry.getRS().getOutputs(), 1);

        disable(updateInfoBTN);
        disable(resetInfoBTN);
    }

    private void initDrivers() {
        driversTable = loadTable("PeopleTable.fxml");
        setTable(driversTAB, driversTable, Entry.getRS().getDrivers());
        setLabel(numDriversLBL, Entry.getRS().getNumDrivers());
    }

    private void initVehicles() {
        trainsTable = loadTable("TrainsTable.fxml");
        setTable(trainsTAB, trainsTable, Entry.getRS().getTrains());
        setLabel(numTrainsLBL, Entry.getRS().getNumVehicles());
    }

    private void updatePrice() {
        setLabel(priceLBL, Entry.getRS().getPrice());
        Main.getCityPage().updatePayments();
        Main.getCityPage().refreshPeople();
        Main.getCityPage().updatePeople();
    }

    private void updateVehicles() {
        setLabel(numTrainsLBL, Entry.getRS().getNumVehicles());
        Main.getCityPage().refreshRailways();
    }

    private void updateDrivers() {
        setLabel(numDriversLBL, Entry.getRS().getNumDrivers());
        Main.getCityPage().refreshRailways();
        Main.getCityPage().refreshPeople();
    }


    @FXML
    void enterServicesAction(ActionEvent event) {
        try {
            Entry.setVehicle(getSelectedItem(trainsTable));
            showForm("AddTrainServiceForm.fxml");
        } catch (RuntimeException ex) {
            Alerts.showError(ex.getMessage());
        }
    }

    @FXML
    void addVehicleAction(ActionEvent event) {
        if (event.getSource() == addTrainBTN) {
            showForm("AddTrainForm.fxml");
        }
    }

    @FXML
    void addDriverAction(ActionEvent event) {
        Terminal.setRequiredJob(City.Job.LocoDriver);
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
            if (event.getSource() == removeTrainBTN)
                removeVehicle(getSelectedItem(trainsTable));
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
        Entry.getRS().setName(nameTXT.getText());
        Entry.getRS().setAddress(addressTXT.getText());
        Entry.getRS().setInputs(inputsSPN.getValue());
        Entry.getRS().setOutputs(outputsSPN.getValue());

        initInfo();
        Main.getCityPage().refreshRailways();
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        Entry.getRS().addVehicle(vehicle);
        addToTable(trainsTable, (Train) vehicle);

        updateVehicles();
        updatePrice();
    }

    @Override
    public void removeVehicle(Vehicle vehicle) {

        if (Alerts.showWarning("Remove " + vehicle + " ?")) {
            removeFromTable(trainsTable, (Train) vehicle);
            Entry.getRS().removeVehicle(vehicle);

            updateVehicles();
            updatePrice();
        }
    }

    @Override
    public void addWorker(List<Person> workers) {
        addToTable(driversTable, workers);

        for (Person person : workers)
            Entry.getRS().addDriver(person);

        updateDrivers();
    }

    @Override
    public void removeWorker(Person worker) {
        if (worker.isCreditor())
            throw new WrongAmount("This driver is creditor");

        if (Alerts.showWarning("Remove " + worker + " ?")) {
            removeFromTable(driversTable, worker);
            Entry.getRS().removeDriver(worker);
            updateDrivers();
        }
    }

    public void addServices(List<Train.TrainService> services, double price) {
        getSelectedItem(trainsTable).updateServices(services, price);
        trainsTable.refresh();
        updatePrice();
    }

}
