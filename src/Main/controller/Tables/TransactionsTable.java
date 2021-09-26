package Main.controller.Tables;

import Main.model.Accounts.Transaction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionsTable implements Initializable {


    @FXML
    private TableView<Transaction> transactionsTable;

    @FXML
    private TableColumn<?, ?> idCOL;

    @FXML
    private TableColumn<?, ?> typeCOL;

    @FXML
    private TableColumn<?, ?> costCOL;

    @FXML
    private TableColumn<?, ?> timeCOL;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCOL.setCellValueFactory(new PropertyValueFactory<>("id"));
        typeCOL.setCellValueFactory(new PropertyValueFactory<>("type"));
        costCOL.setCellValueFactory(new PropertyValueFactory<>("cost"));
        timeCOL.setCellValueFactory(new PropertyValueFactory<>("time"));
    }
}
