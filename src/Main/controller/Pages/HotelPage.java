package Main.controller.Pages;

import Main.Main;
import Main.model.Buildings.Hotel.HotelService;
import Main.model.Buildings.Room;
import Main.model.Entry;
import Main.model.Exceptions.Alerts;
import Main.model.UI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HotelPage extends UI implements Initializable {

    @FXML
    private VBox root;

    @FXML
    private Label nameLBL;

    @FXML
    private Label idLBL;

    @FXML
    private Label priceLBL;

    @FXML
    private TextField nameTXT;

    @FXML
    private TextField addressTXT;

    @FXML
    private Button updateInfoBTN;

    @FXML
    private Button resetInfoBTN;

    @FXML
    private Tab roomsTAB;

    private TableView<Room> roomsTable;

    @FXML
    private Label numRoomsLBL;

    @FXML
    private Tab servicesTAB;

    private TableView<HotelService> servicesTable;

    @FXML
    private Label numServicesLBL;

    @FXML
    void addRoomAction(ActionEvent event) {
        showForm("AddRoomForm.fxml");
    }

    @FXML
    void addServiceAction(ActionEvent event) {
        showForm("AddHotelServiceForm.fxml");
    }

    @FXML
    void removeRoomAction(ActionEvent event) {
        try {
            removeRoom(getSelectedItem(roomsTable));
        } catch (RuntimeException ex) {
            Alerts.showError(ex.getMessage());
        }
    }

    @FXML
    void removeServiceAction(ActionEvent event) {
        try {
            removeService(getSelectedItem(servicesTable));
        } catch (RuntimeException ex) {
            Alerts.showError(ex.getMessage());
        }
    }

    @FXML
    void backAction(ActionEvent event) {
        Main.getCityPage().resetRoot(root);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.setHotelPage(this);

        initInfo();
        initRooms();
        initServices();

    }

    private void initInfo() {
        setLabel(nameLBL, Entry.getHotel().getName() + " Hotel");
        setLabel(idLBL, Entry.getHotel().getId());
        setLabel(priceLBL, Entry.getHotel().getPrice());

        setText(nameTXT, Entry.getHotel().getName());
        setText(addressTXT, Entry.getHotel().getAddress());

        disable(updateInfoBTN);
        disable(resetInfoBTN);
    }


    private void initRooms() {
        roomsTable = loadTable("RoomsTable.fxml");
        setTable(roomsTAB, roomsTable, Entry.getHotel().getRooms());
        setLabel(numRoomsLBL, Entry.getHotel().getNumRooms());
    }

    private void initServices() {
        servicesTable = loadTable("HotelServicesTable.fxml");
        setTable(servicesTAB, servicesTable, Entry.getHotel().getServices());
        setLabel(numServicesLBL, Entry.getHotel().getNumServices());
    }

    private void updatePrice() {
        setLabel(priceLBL, Entry.getHotel().getPrice());
        Main.getCityPage().updatePayments();
    }

    private void updateServices() {
        setLabel(numServicesLBL, Entry.getHotel().getNumServices());
        Main.getCityPage().refreshHotels();
    }

    private void updateRooms() {
        setLabel(numRoomsLBL, Entry.getHotel().getNumRooms());
        Main.getCityPage().refreshHotels();
    }

    public void addRoom(Room room) {
        Entry.getHotel().addRoom(room);
        addToTable(roomsTable, room);
        updateRooms();
        updatePrice();
    }

    public void addServices(List<HotelService> services, double price) {
        Entry.getHotel().addServices(services, price);
        addToTable(servicesTable, services);
        updateServices();
        updatePrice();
    }

    private void removeRoom(Room room) {
        if (Alerts.showWarning("Remove " + room + " ?")) {
            removeFromTable(roomsTable, room);
            Entry.getHotel().removeRoom(room);
            updateRooms();
            updatePrice();
        }
    }


    private void removeService(HotelService service) {
        if (Alerts.showWarning("Remove " + service + " ?")) {
            removeFromTable(servicesTable, service);
            Entry.getHotel().removeService(service);
            updateServices();
            updatePrice();
        }
    }

    @FXML
    void changeInfoAction() {
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
    void resetInfoAction(ActionEvent event) {
        initInfo();
    }

    @FXML
    void updateInfoAction(ActionEvent event) {
        Entry.getHotel().setName(nameTXT.getText());
        Entry.getHotel().setAddress(addressTXT.getText());

        initInfo();
        Main.getCityPage().refreshHotels();
    }

}
