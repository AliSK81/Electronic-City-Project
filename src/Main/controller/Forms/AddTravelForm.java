package Main.controller.Forms;

import Main.Main;
import Main.model.Buildings.Terminal;
import Main.model.City;
import Main.model.Entry;
import Main.model.Exceptions.Alerts;
import Main.model.Exceptions.CancelTrip;
import Main.model.Exceptions.InvalidPassenger;
import Main.model.Exceptions.InvalidTrip;
import Main.model.Person;
import Main.model.Trip.Trip;
import Main.model.UI;
import Main.model.Vehicles.Vehicle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddTravelForm extends UI implements Initializable {

    boolean validTrip;

    private TableView<Person> passengersTable;

    @FXML
    private Label errorLBL;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox<City> destCityCBOX;

    @FXML
    private ComboBox<Terminal> originTerCBOX;

    @FXML
    private ComboBox<Terminal> destTerCBOX;

    @FXML
    private ComboBox<Vehicle> vehicleCBOX;

    @FXML
    private ComboBox<Person> driverCBOX;

    @FXML
    private HBox tableHolder;

    @FXML
    private Button doneBTN;

    @FXML
    void cancelAction(ActionEvent event) {
        doneBTN.getScene().getWindow().hide();
    }

    @FXML
    void dataChangedAction(ActionEvent e) {
        try {
            if (e.getSource() == datePicker) {
                Trip.checkTime(datePicker.getValue());
            }
            if (e.getSource() == destCityCBOX) {
                addToCBOX(destTerCBOX, getSelectedItem(destCityCBOX).getTerminals());
                Trip.checkCity(Entry.getCity(), destCityCBOX.getValue());
            }
            if (e.getSource() == originTerCBOX || e.getSource() == destTerCBOX) {

                if (e.getSource() == originTerCBOX) {
                    addToCBOX(driverCBOX, getSelectedItem(originTerCBOX).getDrivers());
                    addToCBOX(vehicleCBOX, getSelectedItem(originTerCBOX).getVehicles());
                }
                Trip.checkTerminal(originTerCBOX.getValue(), destTerCBOX.getValue());
            }
            if (e.getSource() == driverCBOX) {
                Trip.checkDriver(driverCBOX.getValue());
            }
            errorLBL.setText("");

            validTrip = datePicker.getValue() != null && destCityCBOX.getValue() != null && originTerCBOX.getValue() != null &&
                    destTerCBOX.getValue() != null && driverCBOX.getValue() != null && vehicleCBOX.getValue() != null;

            if (validTrip && passengersTable.getSelectionModel().getSelectedItems().size() > 0)
                enable(doneBTN);

        } catch (InvalidTrip ex) {
            errorLBL.setText(ex.getMessage());
            disable(doneBTN);
        } catch (CancelTrip ex) {
            Alerts.showError(ex.getMessage());
//            doneBTN.getScene().getWindow().hide();
        }
    }

    @FXML
    void doneAction(ActionEvent event) {
        try {
            ArrayList<Person> passengers = new ArrayList<>();
            for (Person person : passengersTable.getSelectionModel().getSelectedItems())
                if (!person.isWorking())
                    passengers.add(person);

            Trip.checkCapacity(passengers.size(), vehicleCBOX.getValue().getCapacity());

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            Main.getCityPage().addTrip(Entry.getCity(), originTerCBOX.getValue(), destTerCBOX.getValue(), destCityCBOX.getValue(), passengers, driverCBOX.getValue(), vehicleCBOX.getValue(), dtf.format(datePicker.getValue()));
            doneBTN.getScene().getWindow().hide();

        } catch (RuntimeException ex) {
            Alerts.showError(ex.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        passengersTable = loadTable("PeopleTable.fxml");
        passengersTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        passengersTable.setOnMouseClicked(e -> {
            try {
                Person passenger = passengersTable.getSelectionModel().getSelectedItem();
                Trip.checkPassenger(passenger);

                errorLBL.setText("");
                if (validTrip) enable(doneBTN);

            } catch (InvalidPassenger ex) {
                errorLBL.setText(ex.getMessage());
            }
        });

        errorLBL.setText("");
        errorLBL.setFocusTraversable(true);

        setTable(tableHolder, passengersTable, Entry.getCity().getPeople());
        addToCBOX(originTerCBOX, Entry.getCity().getTerminals());
        addToCBOX(destCityCBOX, Entry.getCountry().getCities());

        disable(doneBTN);
    }
}
