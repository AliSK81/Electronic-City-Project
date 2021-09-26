package Main.controller.Tables;

import Main.model.Vehicles.Train;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class TrainsTable implements Initializable {

    @FXML
    private TableView<Train> trainsTable;

    @FXML
    private TableColumn<?, ?> idCOL;

    @FXML
    private TableColumn<?, ?> companyCOL;

    @FXML
    private TableColumn<?, ?> capacityCOL;

    @FXML
    private TableColumn<?, ?> priceCOL;

    @FXML
    private TableColumn<?, ?> speedCOL;

    @FXML
    private TableColumn<?, ?> lpkCOL;

    @FXML
    private TableColumn<?, ?> yearCOL;

    @FXML
    private TableColumn<?, ?> wagonsCOL;

    @FXML
    private TableColumn<?, ?> starsCOL;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCOL.setCellValueFactory(new PropertyValueFactory<>("id"));
        companyCOL.setCellValueFactory(new PropertyValueFactory<>("company"));
        capacityCOL.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        priceCOL.setCellValueFactory(new PropertyValueFactory<>("price"));
        speedCOL.setCellValueFactory(new PropertyValueFactory<>("speed"));
        lpkCOL.setCellValueFactory(new PropertyValueFactory<>("lpk"));
        yearCOL.setCellValueFactory(new PropertyValueFactory<>("year"));
        wagonsCOL.setCellValueFactory(new PropertyValueFactory<>("wagons"));
        starsCOL.setCellValueFactory(new PropertyValueFactory<>("numServices"));
    }
}
