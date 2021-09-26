package Main.controller.Tables;

import Main.model.Buildings.Bank;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class BanksTable implements Initializable {

    @FXML
    private TableView<Bank> banksTable;

    @FXML
    private TableColumn<?, ?> idCOL;

    @FXML
    private TableColumn<?, ?> nameCOL;

    @FXML
    private TableColumn<?, ?> addressCOL;

    @FXML
    private TableColumn<?, ?> numAccountsCOL;

    @FXML
    private TableColumn<?, ?> activeAccountsCOL;

    @FXML
    private TableColumn<?, ?> totalMoneyCOL;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCOL.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCOL.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressCOL.setCellValueFactory(new PropertyValueFactory<>("address"));
        numAccountsCOL.setCellValueFactory(new PropertyValueFactory<>("numAccounts"));
        activeAccountsCOL.setCellValueFactory(new PropertyValueFactory<>("activeAccounts"));
        totalMoneyCOL.setCellValueFactory(new PropertyValueFactory<>("totalMoney"));
    }
}
