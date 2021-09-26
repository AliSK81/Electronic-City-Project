package Main.controller.Tables;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class RoomsTable implements Initializable {

    @FXML
    private TableView<?> roomsTable;

    @FXML
    private TableColumn<?, ?> roomsIdCOL;

    @FXML
    private TableColumn<?, ?> roomsBedsCOL;

    @FXML
    private TableColumn<?, ?> roomsAreaCOL;

    @FXML
    private TableColumn<?, ?> roomsPriceCOL;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roomsIdCOL.setCellValueFactory(new PropertyValueFactory<>("id"));
        roomsBedsCOL.setCellValueFactory(new PropertyValueFactory<>("beds"));
        roomsAreaCOL.setCellValueFactory(new PropertyValueFactory<>("area"));
        roomsPriceCOL.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
