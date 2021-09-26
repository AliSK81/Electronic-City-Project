package Main.controller.Tables;

import Main.model.Costs.Payment;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class PaymentsTable implements Initializable {

    @FXML
    private TableView<Payment> paymentsTable;

    @FXML
    private TableColumn<?, ?> idCOL;

    @FXML
    private TableColumn<?, ?> serviceCOL;

    @FXML
    private TableColumn<?, ?> incomeCOL;

    @FXML
    private TableColumn<?, ?> costCOL;

    @FXML
    private TableColumn<?, ?> timeCOL;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCOL.setCellValueFactory(new PropertyValueFactory<>("id"));
        serviceCOL.setCellValueFactory(new PropertyValueFactory<>("service"));
        incomeCOL.setCellValueFactory(new PropertyValueFactory<>("income"));
        costCOL.setCellValueFactory(new PropertyValueFactory<>("cost"));
        timeCOL.setCellValueFactory(new PropertyValueFactory<>("time"));
    }
}
