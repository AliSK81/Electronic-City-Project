package Main.controller.Pages;

import Main.Main;
import Main.model.Buildings.*;
import Main.model.City;
import Main.model.Costs.Payment;
import Main.model.Entry;
import Main.model.Exceptions.Alerts;
import Main.model.Exceptions.WrongAmount;
import Main.model.Person;
import Main.model.Trip.Trip;
import Main.model.UI;
import Main.model.Vehicles.Vehicle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CityPage extends UI implements Initializable {

    private TableView<Person> peopleTable;
    private TableView<Hotel> hotelsTable;
    private TableView<Airport> airportsTable;
    private TableView<BusTerminal> busTerminalsTable;
    private TableView<RailwayStation> railwaysTable;
    private TableView<ShippingPort> shippingPortsTable;
    private TableView<Bank> banksTable;
    private TableView<Trip> tripsTable;
    private TableView<Payment> paymentsTable;

    @FXML
    private VBox root;

    @FXML
    private Label nameLBL;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab peopleTAB;

    @FXML
    private Label populationLBL;

    @FXML
    private Label numWorkersLBL;

    @FXML
    private Label numCreditorsLBL;

    @FXML
    private CheckBox addRandomPersonCBOX;

    @FXML
    private Tab hotelsTAB;

    @FXML
    private Label numHotelsLBL;

    @FXML
    private Tab terminalsTAB;

    @FXML
    private Tab airportsTAB;

    @FXML
    private Label numAirportsLBL;

    @FXML
    private Tab busTerminalTAB;

    @FXML
    private Label numBusTerminalsLBL;

    @FXML
    private Tab railwaysTAB;

    @FXML
    private Label numRailwaysLBL;

    @FXML
    private Tab shippingPortsTAB;

    @FXML
    private Label numShippingPortsLBL;

    @FXML
    private Tab tripsTAB;

    @FXML
    private Label numTripsLBL;

    @FXML
    private ComboBox<Terminal> terminalsCBOX;

    @FXML
    private RadioButton allTripsRBTN;

    @FXML
    private ToggleGroup tripsFilterGroup;

    @FXML
    private RadioButton incomingTripsRBTN;

    @FXML
    private RadioButton outgoingTripsRBTN;

    @FXML
    private Tab paymentsTAB;

    @FXML
    private Label totalMoneyLBL;

    @FXML
    private RadioButton allPaymentsRBTN;

    @FXML
    private ToggleGroup tripsFilterGroup1;

    @FXML
    private RadioButton incomesRBTN;

    @FXML
    private RadioButton outputsRBTN;

    @FXML
    private Tab banksTAB;

    @FXML
    private Label numBanksLBL;

    @FXML
    void addAirportAction(ActionEvent event) {
        showForm("AddAirportForm.fxml");
    }

    @FXML
    void addBusTerminalAction(ActionEvent event) {
        showForm("AddBusTerminalForm.fxml");
    }

    @FXML
    void addRailwayAction(ActionEvent event) {
        showForm("AddRailwayStationForm.fxml");
    }

    @FXML
    void addShippingPortAction(ActionEvent event) {
        showForm("AddShippingPortForm.fxml");
    }

    @FXML
    void backAction(ActionEvent event) {
        Main.getCountryPage().backToCountry(root);
    }

    @FXML
    void addPersonAction(ActionEvent event) {
        if (addRandomPersonCBOX.isSelected())
            addPerson(Entry.getCity().generatePerson());
        else
            showForm("AddPersonForm.fxml");
    }

    @FXML
    void removePersonAction(ActionEvent event) {
        try {
            removePerson(getSelectedItem(peopleTable));
        } catch (RuntimeException ex) {
            Alerts.showError(ex.getMessage());
        }
    }

    @FXML
    void enterHotelAction(ActionEvent event) {
        try {
            Entry.setHotel(getSelectedItem(hotelsTable));
            setRoot(loadPage("HotelPage.fxml"));
        } catch (RuntimeException ex) {
            Alerts.showError(ex.getMessage());
        }
    }

    @FXML
    void addHotelAction(ActionEvent event) {
        showForm("AddHotelForm.fxml");
    }

    @FXML
    void removeHotelAction(ActionEvent event) {
        try {
            removeHotel(getSelectedItem(hotelsTable));
        } catch (RuntimeException ex) {
            Alerts.showError(ex.getMessage());
        }
    }

    @FXML
    void enterAirportAction(ActionEvent event) {
        try {
            Entry.setTerminal(getSelectedItem(airportsTable));
            setRoot(loadPage("AirportPage.fxml"));
        } catch (RuntimeException ex) {
            Alerts.showError(ex.getMessage());
        }
    }

    @FXML
    void enterBusTerminalAction(ActionEvent event) {
        try {
            Entry.setTerminal(getSelectedItem(busTerminalsTable));
            setRoot(loadPage("BusTerminalPage.fxml"));
        } catch (RuntimeException ex) {
            Alerts.showError(ex.getMessage());
        }
    }

    @FXML
    void enterRailwayAction(ActionEvent event) {
        try {
            Entry.setTerminal(getSelectedItem(railwaysTable));
            setRoot(loadPage("RailwayStationPage.fxml"));
        } catch (RuntimeException ex) {
            Alerts.showError(ex.getMessage());
        }
    }

    @FXML
    void enterShippingPortAction(ActionEvent event) {
        try {
            Entry.setTerminal(getSelectedItem(shippingPortsTable));
            setRoot(loadPage("ShippingPortPage.fxml"));
        } catch (RuntimeException ex) {
            Alerts.showError(ex.getMessage());
        }
    }

    @FXML
    void removeAirportAction(ActionEvent event) {
        try {
            removeTerminal(getSelectedItem(airportsTable));
        } catch (RuntimeException ex) {
            Alerts.showError(ex.getMessage());
        }
    }

    @FXML
    void removeBusTerminalAction(ActionEvent event) {
        try {
            removeTerminal(getSelectedItem(busTerminalsTable));
        } catch (RuntimeException ex) {
            Alerts.showError(ex.getMessage());
        }
    }

    @FXML
    void removeRailwayAction(ActionEvent event) {
        try {
            removeTerminal(getSelectedItem(railwaysTable));
        } catch (RuntimeException ex) {
            Alerts.showError(ex.getMessage());
        }
    }

    @FXML
    void removeShippingPortAction(ActionEvent event) {
        try {
            removeTerminal(getSelectedItem(shippingPortsTable));
        } catch (RuntimeException ex) {
            Alerts.showError(ex.getMessage());
        }
    }

    @FXML
    void enterBankAction(ActionEvent event) {
        try {
            Entry.setBank(getSelectedItem(banksTable));
            setRoot(loadPage("BankPage.fxml"));
        } catch (RuntimeException ex) {
            Alerts.showError(ex.getMessage());
        }
    }

    @FXML
    void addBankAction(ActionEvent event) {
        showForm("AddBankForm.fxml");
    }

    @FXML
    void addTripAction(ActionEvent event) {
        try {
            Trip.checkCountry();
            showForm("AddTravelForm.fxml");
        } catch (RuntimeException ex) {
            Alerts.showError(ex.getMessage());
        }
    }

    @FXML
    void selectTripsTerminalAction(ActionEvent event) {
        Terminal TER =  getSelectedItem(terminalsCBOX);
        if (TER != null) {
            setTable(tripsTable,TER.getTrips("all"));
            select(allTripsRBTN);
        }
    }

    @FXML
    void paymentTypeAction(ActionEvent event) {
        if (event.getSource() == allPaymentsRBTN)
            setTable(paymentsTable, Entry.getCity().getPayments("all"));
        else if (event.getSource() == incomesRBTN)
            setTable(paymentsTable, Entry.getCity().getPayments("incomes"));
        else if (event.getSource() == outputsRBTN)
            setTable(paymentsTable, Entry.getCity().getPayments("outputs"));
    }

    @FXML
    void tripTypeAction(ActionEvent event) {
        Terminal TER = terminalsCBOX.getValue();

        if(TER != null) {
            if (event.getSource() == allTripsRBTN)
                setTable(tripsTable, TER.getTrips("all"));
            else if (event.getSource() == incomingTripsRBTN)
                setTable(tripsTable, TER.getTrips("incoming"));
            else if (event.getSource() == outgoingTripsRBTN)
                setTable(tripsTable, TER.getTrips("outgoing"));
        }
    }

    @FXML
    void paySalariesAction(ActionEvent event) {

        double money = Entry.getCity().computeWorkersSalary();
        if (money == 0)
            Alerts.showInfo("no creditors exist in city");

        else if (Alerts.showConfirm("Pay " + money + " to creditors?")) {
            Entry.getCity().payWorkersSalary();
        }
    }

    public void setRoot(Pane newRoot) {
        root.getScene().setRoot(newRoot);
    }

    public void resetRoot(Pane oldRoot) {
        oldRoot.getScene().setRoot(root);
    }

    private void initCity() {
        setLabel(nameLBL, Entry.getCity().getName() + " City");
        setLabel(totalMoneyLBL, Entry.getCity().getTotalMoney());
    }

    private void initPeople() {
        peopleTable = loadTable("PeopleTable.fxml");
        setTable(peopleTAB, peopleTable, Entry.getCity().getPeople());
        updatePeople();
    }

    private void initHotels() {
        hotelsTable = loadTable("HotelsTable.fxml");
        setTable(hotelsTAB, hotelsTable, Entry.getCity().getHotels());
        updateHotels();
    }

    private void initTerminals() {
        airportsTable = loadTable("AirportsTable.fxml");
        setTable(airportsTAB, airportsTable, Entry.getCity().getAirports());

        busTerminalsTable = loadTable("BusTerminalsTable.fxml");
        setTable(busTerminalTAB, busTerminalsTable, Entry.getCity().getBusTerminals());

        railwaysTable = loadTable("RailwayStationsTable.fxml");
        setTable(railwaysTAB, railwaysTable, Entry.getCity().getRailways());

        shippingPortsTable = loadTable("ShippingPortsTable.fxml");
        setTable(shippingPortsTAB, shippingPortsTable, Entry.getCity().getShippingPorts());

        updateTerminals();
    }

    private void initBanks() {
        banksTable = loadTable("BanksTable.fxml");
        setTable(banksTAB, banksTable, Entry.getCity().getBanks());
        setLabel(numBanksLBL, Entry.getCity().getNumBanks());
    }

    private void initTrips() {
        tripsTable = loadTable("TripsTable.fxml");

        setTable(tripsTAB, tripsTable, null);
        addToCBOX(terminalsCBOX, Entry.getCity().getTerminals());
        setLabel(numTripsLBL, 0);
    }

    private void initPayments() {
        paymentsTable = loadTable("PaymentsTable.fxml");
        setTable(paymentsTAB, paymentsTable, Entry.getCity().getPayments("all"));
    }

    public void updatePeople() {
        setLabel(populationLBL, Entry.getCity().getPopulation());
        setLabel(numWorkersLBL, Entry.getCity().getNumWorkers());
        setLabel(numCreditorsLBL, Entry.getCity().getNumCreditors());
    }

    public void updateHotels() {
        setLabel(numHotelsLBL, Entry.getCity().getNumHotels());
    }

    public void updateTerminals() {
        setLabel(numAirportsLBL, Entry.getCity().getNumAirports());
        setLabel(numBusTerminalsLBL, Entry.getCity().getNumBusTerminals());
        setLabel(numShippingPortsLBL, Entry.getCity().getNumShippingPorts());
        setLabel(numRailwaysLBL, Entry.getCity().getNumRailways());
        addToCBOX(terminalsCBOX, Entry.getCity().getTerminals());
    }

    public void addPerson(Person person) {
        Entry.getCity().addPerson(person);
        addToTable(peopleTable, person);
        updatePeople();
    }

    private void removePerson(Person person) {
        if (person.isWorking())
            throw new WrongAmount("This person is working!");

        if (person.isCreditor())
            throw new WrongAmount("This Person is creditor!");

        if (Alerts.showWarning("Remove " + person + " ?")) {
            removeFromTable(peopleTable, person);
            Entry.getCity().removePerson(person);
            updatePeople();
        }
    }

    public void addHotel(Hotel hotel) {
        Entry.getCity().addHotel(hotel);
        addToTable(hotelsTable, hotel);
        updateHotels();
        updatePayments();
    }

    private void removeHotel(Hotel hotel) {
        if (Alerts.showWarning("Remove " + hotel + " ?")) {
            removeFromTable(hotelsTable, hotel);
            Entry.getCity().removeHotel(hotel);
            updateHotels();
            updatePayments();
        }
    }

    public void addTerminal(Terminal TER) {
        Entry.getCity().addTerminal(TER);

        if (TER instanceof Airport) {
            addToTable(airportsTable, (Airport) TER);
        } else if (TER instanceof BusTerminal) {
            addToTable(busTerminalsTable, (BusTerminal) TER);
        } else if (TER instanceof RailwayStation) {
            addToTable(railwaysTable, (RailwayStation) TER);
        } else if (TER instanceof ShippingPort) {
            addToTable(shippingPortsTable, (ShippingPort) TER);
        }
        updateTerminals();
        updatePayments();
    }

    public void removeTerminal(Terminal TER) {

        if (TER.getNumVehicles() > 0)
            throw new WrongAmount("This Terminal has vehicles");

        if (TER.getNumDrivers() > 0)
            throw new WrongAmount("This terminal has workers");

        if (Alerts.showWarning("Remove " + TER + " ?")) {

            if (TER instanceof Airport) {
                removeFromTable(airportsTable, (Airport) TER);
            } else if (TER instanceof BusTerminal) {
                removeFromTable(busTerminalsTable, (BusTerminal) TER);
            } else if (TER instanceof RailwayStation) {
                removeFromTable(railwaysTable, (RailwayStation) TER);
            } else if (TER instanceof ShippingPort) {
                removeFromTable(shippingPortsTable, (ShippingPort) TER);
            }

            Entry.getCity().removeTerminal(TER);
            updateTerminals();
            updatePayments();
        }
    }

    public void addTrip(City originCity, Terminal originTER, Terminal destTER, City destCity, ArrayList<Person> passengers, Person driver, Vehicle vehicle, String date) {
        originTER.addTrip(originCity, destTER, destCity, passengers, driver, vehicle, date);
        addToTable(tripsTable, originTER.getLastTrip());
        updatePayments();
        setTable(peopleTable, Entry.getCity().getPeople());
        updatePeople();
        updateTerminals();
        refreshAirports();
        refreshShippingPorts();
        refreshBusTermianls();
        refreshRailways();
    }

    public void addBank(Bank bank) {
        Entry.getCity().addBank(bank);
        addToTable(banksTable, bank);
        setLabel(numBanksLBL, Entry.getCity().getNumBanks());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Main.setCityPage(this);

        initCity();
        initPeople();
        initHotels();
        initTerminals();
        initTrips();
        initPayments();
        initBanks();

    }

    public void refreshPeople() {
        peopleTable.refresh();
    }

    public void refreshHotels() {
        hotelsTable.refresh();
    }

    public void refreshAirports() {
        airportsTable.refresh();
    }

    public void refreshBusTermianls() {
        busTerminalsTable.refresh();
    }

    public void refreshRailways() {
        railwaysTable.refresh();
    }

    public void refreshShippingPorts() {
        shippingPortsTable.refresh();
    }

    public void refreshBanks() {
        banksTable.refresh();
    }

    public void updatePayments() {
        setTable(paymentsTable, Entry.getCity().getPayments("all"));
        select(allPaymentsRBTN);
        setLabel(totalMoneyLBL, Entry.getCity().getTotalMoney());
    }
}
