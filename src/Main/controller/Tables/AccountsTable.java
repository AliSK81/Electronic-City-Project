package Main.controller.Tables;

import Main.model.Accounts.BankAccount;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountsTable implements Initializable {

    @FXML
    private TableView<BankAccount> accountsTable;

    @FXML
    private TableColumn<?, ?> idCOL;

    @FXML
    private TableColumn<?, ?> balanceCOL;

    @FXML
    private TableColumn<?, ?> holderCOL;

    @FXML
    private TableColumn<?, ?> numTransactionsCOL;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCOL.setCellValueFactory(new PropertyValueFactory<>("id"));
        balanceCOL.setCellValueFactory(new PropertyValueFactory<>("balance"));
        holderCOL.setCellValueFactory(new PropertyValueFactory<>("holder"));
        numTransactionsCOL.setCellValueFactory(new PropertyValueFactory<>("numTransactions"));
    }
}
