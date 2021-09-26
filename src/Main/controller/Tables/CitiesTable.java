package Main.controller.Tables;

import Main.model.City;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class CitiesTable implements Initializable {

    @FXML
    private TableView<City> citiesTable;

    @FXML
    private TableColumn<?, ?> nameCOL;

    @FXML
    private TableColumn<?, ?> populationCOL;

    @FXML
    private TableColumn<?, ?> totalMoneyCOL;

    @FXML
    private TableColumn<?, ?> numTerminalsCOL;

    @FXML
    private TableColumn<?, ?> numHotelsCOL;

    @FXML
    private TableColumn<?, ?> numBanksCOL;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameCOL.setCellValueFactory(new PropertyValueFactory<>("name"));
        populationCOL.setCellValueFactory(new PropertyValueFactory<>("population"));
        totalMoneyCOL.setCellValueFactory(new PropertyValueFactory<>("totalMoney"));
        numTerminalsCOL.setCellValueFactory(new PropertyValueFactory<>("numTerminals"));
        numHotelsCOL.setCellValueFactory(new PropertyValueFactory<>("numHotels"));
        numBanksCOL.setCellValueFactory(new PropertyValueFactory<>("numBanks"));
    }
}
