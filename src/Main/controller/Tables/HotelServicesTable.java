package Main.controller.Tables;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class HotelServicesTable implements Initializable {

    @FXML
    private TableView<?> servicesTable;

    @FXML
    private TableColumn<?, ?> servicesIdCOL;

    @FXML
    private TableColumn<?, ?> servicesNameCOL;

    @FXML
    private TableColumn<?, ?> servicesStarsCOL;

    @FXML
    private TableColumn<?, ?> servicesPriceCOL;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        servicesIdCOL.setCellValueFactory(new PropertyValueFactory<>("id"));
        servicesNameCOL.setCellValueFactory(new PropertyValueFactory<>("name"));
        servicesStarsCOL.setCellValueFactory(new PropertyValueFactory<>("stars"));
        servicesPriceCOL.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
