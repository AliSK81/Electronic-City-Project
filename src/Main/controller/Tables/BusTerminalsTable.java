package Main.controller.Tables;

import Main.model.Buildings.BusTerminal;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class BusTerminalsTable implements Initializable {

    @FXML
    private TableView<BusTerminal> busTerminalsTable;

    @FXML
    private TableColumn<?, ?> idCOL;

    @FXML
    private TableColumn<?, ?> nameCOL;

    @FXML
    private TableColumn<?, ?> areaCOL;

    @FXML
    private TableColumn<?, ?> priceCOL;

    @FXML
    private TableColumn<?, ?> vehiclesCOL;

    @FXML
    private TableColumn<?, ?> driversCOL;

    @FXML
    private TableColumn<?, ?> tripsCOL;

    @FXML
    private TableColumn<?, ?> addressCOL;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCOL.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCOL.setCellValueFactory(new PropertyValueFactory<>("name"));
        areaCOL.setCellValueFactory(new PropertyValueFactory<>("area"));
        priceCOL.setCellValueFactory(new PropertyValueFactory<>("price"));
        vehiclesCOL.setCellValueFactory(new PropertyValueFactory<>("numVehicles"));
        driversCOL.setCellValueFactory(new PropertyValueFactory<>("numDrivers"));
        tripsCOL.setCellValueFactory(new PropertyValueFactory<>("numTrips"));
        addressCOL.setCellValueFactory(new PropertyValueFactory<>("address"));
    }
}
