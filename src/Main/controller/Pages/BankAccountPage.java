package Main.controller.Pages;

import Main.Main;
import Main.model.Accounts.BankAccount;
import Main.model.Accounts.Transaction;
import Main.model.Entry;
import Main.model.Exceptions.Alerts;
import Main.model.UI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class BankAccountPage extends UI implements Initializable {
    
    private TableView<Transaction> transactionsTable;

    @FXML
    private VBox root;

    @FXML
    private Label holderLBL;

    @FXML
    private Label balanceLBL;

    @FXML
    private Label profitTimeLBL;

    @FXML
    private TextField passwordTXT;

    @FXML
    private TextField usernameTXT;

    @FXML
    private Label errorLBL;

    @FXML
    private Button updateInfoBTN;

    @FXML
    private Button resetInfoBTN;

    @FXML
    private Tab transactionsTAB;

    @FXML
    private Label numTransactionsLBL;

    @FXML
    private Spinner<Double> moneySPN;


    @FXML
    private Button addTransactionBTN;

    @FXML
    void backAction(ActionEvent event) {
        Main.getBankPage().resetRoot(root);
    }

    @FXML
    void changeInfoAction(KeyEvent event) {
        enable(resetInfoBTN);
        enable(updateInfoBTN);
        errorLBL.setText("");
    }
    
    @FXML
    void resetInfoAction(ActionEvent event) {
        initInfo();
    }

    @FXML
    void updateInfoAction(ActionEvent event) {
        try {
            BankAccount.checkValidate(usernameTXT.getText(), passwordTXT.getText());

            Entry.getBank().removeUsername(Entry.getAccount().getUsername());

            Entry.getAccount().setUsername(usernameTXT.getText());
            Entry.getAccount().setPassword(passwordTXT.getText());
            Entry.getBank().addUsername(usernameTXT.getText());
            disable(updateInfoBTN);
            disable(resetInfoBTN);
        } catch (RuntimeException ex) {
            errorLBL.setText(ex.getMessage());
        }
    }
    
    @FXML
    void addTransactionAction(ActionEvent event) {
        try {
            if (moneySPN.getValue() < 0) {
                addTransaction(new Transaction(Transaction.Type.Withdraw, moneySPN.getValue()));
            } else if (moneySPN.getValue() > 0) {
                addTransaction(new Transaction(Transaction.Type.Deposit, moneySPN.getValue()));
            }
        } catch (RuntimeException ex) {
            Alerts.showError(ex.getMessage());
        }
    }

    @FXML
    void changeMoneyAction(MouseEvent event) {
        if (moneySPN.getValue() == 0) {
            disable(addTransactionBTN);
        } else {
            enable(addTransactionBTN);

            if (moneySPN.getValue() < 0)
                addTransactionBTN.setText("Withdrawal");
            else
                addTransactionBTN.setText("Deposit");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Main.setAccountPage(this);

        initInfo();
        initTransactions();
    }

    private void initInfo() {
        setLabel(holderLBL, Entry.getAccount().getHolder());
        setLabel(balanceLBL, Entry.getAccount().getBalance());
        setSpinner(moneySPN, -Entry.getAccount().getBalance(), 1000, 0, 25);
        updateProfitTime();

        setText(usernameTXT, Entry.getAccount().getUsername());
        setText(passwordTXT, Entry.getAccount().getPassword());
        setLabel(errorLBL, "");

        disable(updateInfoBTN);
        disable(resetInfoBTN);
    }

    private void initTransactions() {
        transactionsTable = loadTable("TransactionsTable.fxml");
        setTable(transactionsTAB, transactionsTable, Entry.getAccount().getTransactions());
        setLabel(numTransactionsLBL, Entry.getAccount().getNumTransactions());
    }

    private void addTransaction(Transaction tr) {
        Entry.getAccount().addTransaction(tr);
        addToTable(transactionsTable, tr);
        initInfo();
        Main.getBankPage().updateBank();
    }

    public void updateAccount() {
        setLabel(numTransactionsLBL, Entry.getAccount().getNumTransactions());
        setLabel(balanceLBL, Entry.getAccount().getBalance());
        setSpinner(moneySPN, -Entry.getAccount().getBalance(), 1000, 0, 25);
        transactionsTable.getItems().clear();
        transactionsTable.getItems().addAll(Entry.getAccount().getTransactions());
    }

    public void updateProfitTime() {
        if (Entry.getAccount().getBalance() == 0)
            setLabel(profitTimeLBL, "-");
        else
            setLabel(profitTimeLBL, Entry.getAccount().getProfitTime());
    }
}
