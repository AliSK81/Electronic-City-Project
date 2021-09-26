package Main.controller.Tables;

import Main.model.Buildings.Hotel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class HotelsTable implements Initializable {

    @FXML
    private TableView<Hotel> hotelsTable;

    @FXML
    private TableColumn<?, ?> idCOL;

    @FXML
    private TableColumn<?, ?> nameCOL;

    @FXML
    private TableColumn<?, ?> addressCOL;

    @FXML
    private TableColumn<?, ?> priceCOL;

    @FXML
    private TableColumn<?, ?> roomsCOL;

    @FXML
    private TableColumn<?, ?> starsCOL;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idCOL.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCOL.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressCOL.setCellValueFactory(new PropertyValueFactory<>("address"));
        priceCOL.setCellValueFactory(new PropertyValueFactory<>("price"));
        roomsCOL.setCellValueFactory(new PropertyValueFactory<>("numRooms"));
        starsCOL.setCellValueFactory(new PropertyValueFactory<>("numServices"));
    }
}
