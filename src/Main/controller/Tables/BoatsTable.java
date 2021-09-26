package Main.controller.Tables;

import Main.model.Vehicles.Boat;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class BoatsTable implements Initializable {

    @FXML
    private TableView<Boat> boatsTable;

    @FXML
    private TableColumn<?, ?> idCOL;

    @FXML
    private TableColumn<?, ?> companyCOL;

    @FXML
    private TableColumn<?, ?> capacityCOL;

    @FXML
    private TableColumn<?, ?> priceCOL;

    @FXML
    private TableColumn<?, ?> fuelCOL;

    @FXML
    private TableColumn<?, ?> depthCOL;

    @FXML
    private TableColumn<?, ?> usageCOL;

    @FXML
    private TableColumn<?, ?> engineCOL;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCOL.setCellValueFactory(new PropertyValueFactory<>("id"));
        companyCOL.setCellValueFactory(new PropertyValueFactory<>("company"));
        capacityCOL.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        priceCOL.setCellValueFactory(new PropertyValueFactory<>("price"));
        fuelCOL.setCellValueFactory(new PropertyValueFactory<>("fuel"));
        depthCOL.setCellValueFactory(new PropertyValueFactory<>("depth"));
        usageCOL.setCellValueFactory(new PropertyValueFactory<>("usage"));
        engineCOL.setCellValueFactory(new PropertyValueFactory<>("engine"));
    }
}
