package Main.controller.Pages;

import Main.Main;
import Main.model.Buildings.Terminal;
import Main.model.City.Job;
import Main.model.Entry;
import Main.model.Exceptions.Alerts;
import Main.model.Exceptions.WrongAmount;
import Main.model.Person;
import Main.model.Vehicles.CargoPlane;
import Main.model.Vehicles.PassengerPlane;
import Main.model.Vehicles.Vehicle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AirportPage extends TerminalPage implements Initializable {

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
    private Spinner<Integer> runwaysSPN;

    @FXML
    private RadioButton yesRBTN;

    @FXML
    private RadioButton noRBTN;

    @FXML
    private ToggleGroup internationalGroup;

    @FXML
    private Button updateInfoBTN;

    @FXML
    private Button resetInfoBTN;

    @FXML
    private Tab passengerPlanesTAB;

    private TableView<PassengerPlane> passengerPlanesTable;

    @FXML
    private Label numPPlanesLBL;

    @FXML
    private ScrollPane crewsHolder;

    private TableView<Person> crewsTable;

    @FXML
    private Button crewsBTN;

    @FXML
    private Button addPPlaneBTN;

    @FXML
    private Button removePPlaneBTN;

    @FXML
    private Tab cargoPlanesTAB;

    private TableView<CargoPlane> cargoPlanesTable;

    @FXML
    private Label numCPlanesLBL;

    @FXML
    private Button addCPlaneBTN;

    @FXML
    private Button removeCPlaneBTN;

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
        initCrews();
    }

    private void initInfo() {
        setLabel(nameLBL, Entry.getAP().getName() + " Airport");
        setLabel(idLBL, Entry.getAP().getId());
        setLabel(priceLBL, Entry.getAP().getPrice());
        setLabel(areaLBL, Entry.getAP().getArea());
        setLabel(numTripsLBL, Entry.getAP().getNumTrips());

        nameTXT.setStyle("");
        setText(nameTXT, Entry.getAP().getName());
        setText(addressTXT, Entry.getAP().getAddress());

        setSpinner(runwaysSPN, 1, 30, Entry.getAP().getRunways(), 1);

        if (Entry.getAP().isInternational())
            select(yesRBTN);
        else
            select(noRBTN);

        disable(updateInfoBTN);
        disable(resetInfoBTN);
    }

    private void initDrivers() {
        driversTable = loadTable("PeopleTable.fxml");
        setTable(driversTAB, driversTable, Entry.getAP().getDrivers());
        setLabel(numDriversLBL, Entry.getAP().getNumDrivers());
    }

    private void initCrews() {
        crewsTable = loadTable("PeopleTable.fxml");
        crewsHolder.setContent(crewsTable);
        crewsBTN.setVisible(false);
        crewsTable.setOnMouseClicked(e -> {
            crewsBTN.setText("Remove Crew");
            crewsBTN.setStyle("-fx-background-color: linear-gradient(to top , #800013, #ff3c66);" +
                    " -fx-font-weight: bold;" +
                    " -fx-text-fill: white;" +
                    " -fx-font-size: 13;");
            crewsBTN.setOnAction(event -> removeCrewAction());
        });
    }

    private void initVehicles() {
        passengerPlanesTable = loadTable("PassengerPlanesTable.fxml");
        passengerPlanesTable.setOnMouseClicked(e -> {
            try {
                crewsTable.getItems().clear();
                crewsTable.getItems().addAll(getSelectedItem(passengerPlanesTable).getCrews());
                crewsBTN.setText("Add Crew");
                crewsBTN.setStyle("-fx-background-color: linear-gradient(to top , #002f26, #11b297);" +
                        " -fx-text-base-color: white;" +
                        " -fx-font-weight: bold;" +
                        " -fx-font-size: 13;");
                crewsBTN.setVisible(true);
                crewsBTN.setOnAction(event -> addCrewAction());

            } catch (RuntimeException ignored) {
            }
        });

        cargoPlanesTable = loadTable("CargoPlanesTable.fxml");
        setTable(passengerPlanesTAB, passengerPlanesTable, Entry.getAP().getPassengerPlanes());
        setTable(cargoPlanesTAB, cargoPlanesTable, Entry.getAP().getCargoPlanes());
        setLabel(numPPlanesLBL, Entry.getAP().getNumPPlanes());
        setLabel(numCPlanesLBL, Entry.getAP().getNumCPlanes());
    }

    private void updatePrice() {
        setLabel(priceLBL, Entry.getAP().getPrice());
        Main.getCityPage().updatePayments();
    }

    private void updateVehicles() {
        setLabel(numPPlanesLBL, Entry.getAP().getNumPPlanes());
        setLabel(numCPlanesLBL, Entry.getAP().getNumCPlanes());
        Main.getCityPage().refreshAirports();
    }

    private void updateDrivers() {
        setLabel(numDriversLBL, Entry.getAP().getNumDrivers());
        Main.getCityPage().refreshAirports();
        Main.getCityPage().refreshPeople();
        Main.getCityPage().updatePeople();
    }

    private void updateCrews() {
        passengerPlanesTable.refresh();
        Main.getCityPage().refreshPeople();
        Main.getCityPage().updatePeople();
    }

    @FXML
    void addVehicleAction(ActionEvent event) {
        if (event.getSource() == addPPlaneBTN) {
            showForm("AddPassengerPlaneForm.fxml");
        } else if (event.getSource() == addCPlaneBTN) {
            showForm("AddCargoPlaneForm.fxml");
        }
    }

    @FXML
    void addDriverAction(ActionEvent event) {
        Terminal.setRequiredJob(Job.Pilot);
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

    private void addCrewAction() {
        Terminal.setRequiredJob(Job.Crew);
        showForm("AddWorkerForm.fxml");
    }

    private void removeCrewAction() {
        try {
            removeWorker(getSelectedItem(crewsTable));
        } catch (RuntimeException ex) {
            Alerts.showError(ex.getMessage());
        }
    }

    @FXML
    void removeVehicleAction(ActionEvent event) {
        try {
            if (event.getSource() == removePPlaneBTN) {
                removeVehicle(getSelectedItem(passengerPlanesTable));
            } else if (event.getSource() == removeCPlaneBTN) {
                removeVehicle(getSelectedItem(cargoPlanesTable));
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
        Entry.getAP().setName(nameTXT.getText());
        Entry.getAP().setAddress(addressTXT.getText());
        Entry.getAP().setRunways(runwaysSPN.getValue());
        Entry.getAP().setInternational(yesRBTN.isSelected());

        initInfo();
        Main.getCityPage().refreshAirports();
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        Entry.getAP().addVehicle(vehicle);

        if (vehicle instanceof PassengerPlane) {
            addToTable(passengerPlanesTable, (PassengerPlane) vehicle);
        } else if (vehicle instanceof CargoPlane) {
            addToTable(cargoPlanesTable, (CargoPlane) vehicle);
        }
        updateVehicles();
        updatePrice();
    }

    @Override
    public void removeVehicle(Vehicle vehicle) {

        if (vehicle instanceof PassengerPlane)
            if (((PassengerPlane) vehicle).getNumCrews() > 0)
                throw new WrongAmount("This vehicle has crews");

        if (Alerts.showWarning("Remove " + vehicle + " ?")) {

            if (vehicle instanceof PassengerPlane) {
                removeFromTable(passengerPlanesTable, (PassengerPlane) vehicle);
            } else if (vehicle instanceof CargoPlane) {
                removeFromTable(cargoPlanesTable, (CargoPlane) vehicle);
            }

            Entry.getAP().removeVehicle(vehicle);
            updateVehicles();
            updatePrice();
        }
    }

    @Override
    public void addWorker(List<Person> workers) {

        if (Terminal.getRequiredJob() == Job.Pilot) {
            addToTable(driversTable, workers);
            for (Person person : workers)
                Entry.getAP().addDriver(person);
            updateDrivers();
        } else if (Terminal.getRequiredJob() == Job.Crew) {
            addToTable(crewsTable, workers);
            for (Person person : workers)
                getSelectedItem(passengerPlanesTable).addCrew(person);
            updateCrews();
        }
    }

    @Override
    public void removeWorker(Person worker) {
        if (worker.isCreditor())
            throw new WrongAmount("This worker is creditor");

        if (Alerts.showWarning("Remove " + worker + " ?")) {

            if (worker.getJob() == Job.Pilot) {
                removeFromTable(driversTable, worker);
                Entry.getAP().removeDriver(worker);
                updateDrivers();
            } else if (worker.getJob() == Job.Crew) {
                removeFromTable(crewsTable, worker);
                getSelectedItem(passengerPlanesTable).removeCrew(worker);
                updateCrews();
            }
        }
    }
}
