package Main.controller.Tables;

import Main.model.Trip.Trip;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class TripsTable implements Initializable {

    @FXML
    private TableView<Trip> tripsTable;

    @FXML
    private TableColumn<?, ?> idCOL;

    @FXML
    private TableColumn<?, ?> typeCOL;

    @FXML
    private TableColumn<?, ?> originTERCOL;

    @FXML
    private TableColumn<?, ?> destCityCOL;

    @FXML
    private TableColumn<?, ?> destTERCOL;

    @FXML
    private TableColumn<?, ?> driverIdCOL;

    @FXML
    private TableColumn<?, ?> vehicleIdCOL;

    @FXML
    private TableColumn<?, ?> numPassengersCOL;

    @FXML
    private TableColumn<?, ?> costCOL;

    @FXML
    private TableColumn<?, ?> timeCOL;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCOL.setCellValueFactory(new PropertyValueFactory<>("id"));
        typeCOL.setCellValueFactory(new PropertyValueFactory<>("type"));
        originTERCOL.setCellValueFactory(new PropertyValueFactory<>("originTER"));
        destCityCOL.setCellValueFactory(new PropertyValueFactory<>("destCity"));
        destTERCOL.setCellValueFactory(new PropertyValueFactory<>("destTER"));
        driverIdCOL.setCellValueFactory(new PropertyValueFactory<>("driver"));
        vehicleIdCOL.setCellValueFactory(new PropertyValueFactory<>("vehicle"));
        numPassengersCOL.setCellValueFactory(new PropertyValueFactory<>("numPassengers"));
        costCOL.setCellValueFactory(new PropertyValueFactory<>("cost"));
        timeCOL.setCellValueFactory(new PropertyValueFactory<>("time"));
    }
}
