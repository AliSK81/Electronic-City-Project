package Main.controller.Tables;

import Main.model.Vehicles.CargoPlane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class CargoPlanesTable implements Initializable {

    @FXML
    private TableView<CargoPlane> cargoPlanesTable;

    @FXML
    private TableColumn<?, ?> idCOL;

    @FXML
    private TableColumn<?, ?> companyCOL;

    @FXML
    private TableColumn<?, ?> capacityCOL;

    @FXML
    private TableColumn<?, ?> priceCOL;

    @FXML
    private TableColumn<?, ?> heightCOL;

    @FXML
    private TableColumn<?, ?> lengthCOL;

    @FXML
    private TableColumn<?, ?> payloadCOL;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCOL.setCellValueFactory(new PropertyValueFactory<>("id"));
        companyCOL.setCellValueFactory(new PropertyValueFactory<>("company"));
        capacityCOL.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        priceCOL.setCellValueFactory(new PropertyValueFactory<>("price"));
        heightCOL.setCellValueFactory(new PropertyValueFactory<>("height"));
        lengthCOL.setCellValueFactory(new PropertyValueFactory<>("length"));
        payloadCOL.setCellValueFactory(new PropertyValueFactory<>("payload"));
    }
}
