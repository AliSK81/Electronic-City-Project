package Main.controller.Pages;

import Main.Main;
import Main.model.Accounts.BankAccount;
import Main.model.Entry;
import Main.model.UI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class BankPage extends UI implements Initializable {

    private TableView<BankAccount> accountsTable;

    @FXML
    private VBox root;

    @FXML
    private Label nameLBL;

    @FXML
    private Label idLBL;

    @FXML
    private Label activeAccountsLBL;

    @FXML
    private TextField nameTXT;

    @FXML
    private TextField addressTXT;

    @FXML
    private Label totalMoneyLBL;

    @FXML
    private Button updateInfoBTN;

    @FXML
    private Button resetInfoBTN;

    @FXML
    private Tab accountsTAB;

    @FXML
    private Label numAccountsLBL;

    @FXML
    void addAccountAction(ActionEvent event) {
        showForm("AddAccountForm.fxml");
    }

    @FXML
    void backAction(ActionEvent event) {
        Main.getCityPage().resetRoot(root);
    }

    @FXML
    void changeInfoAction(KeyEvent event) {
        if (nameTXT.getText().isEmpty() || nameTXT.getText().startsWith(" ")) {
            nameTXT.setStyle("-fx-background-color: pink");
            disable(updateInfoBTN);
        } else {
            nameTXT.setStyle("");
            enable(updateInfoBTN);
        }
        enable(resetInfoBTN);
    }

    @FXML
    void enterAccountAction(ActionEvent event) {
        showForm("EnterAccountForm.fxml");
    }

    @FXML
    void resetInfoAction(ActionEvent event) {
        initInfo();
    }

    @FXML
    void updateInfoAction(ActionEvent event) {
        Entry.getBank().setName(nameTXT.getText());
        Entry.getBank().setAddress(addressTXT.getText());

        initInfo();
        Main.getCityPage().refreshBanks();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Main.setBankPage(this);

        initInfo();
        initAccounts();
    }

    private void initInfo() {
        setLabel(nameLBL, Entry.getBank().getName() + " Bank");
        setLabel(idLBL, Entry.getBank().getId());
        setLabel(activeAccountsLBL, Entry.getBank().getActiveAccounts());
        setLabel(totalMoneyLBL, Entry.getBank().getTotalMoney());

        setText(nameTXT, Entry.getBank().getName());
        setText(addressTXT, Entry.getBank().getAddress());

        disable(updateInfoBTN);
        disable(resetInfoBTN);
    }

    private void initAccounts() {
        accountsTable = loadTable("AccountsTable.fxml");
        accountsTable.setOnMouseClicked(e -> {
            BankAccount account = getSelectedItem(accountsTable);
            System.out.println("username: " + account.getUsername() + " pass: " + account.getPassword());
        });

        setTable(accountsTAB, accountsTable, Entry.getBank().getAccounts());
        setLabel(numAccountsLBL, Entry.getBank().getNumAccounts());
    }

    public void addAccount(BankAccount account, double initBalance) {
        Entry.getBank().addAccount(account, initBalance);
        addToTable(accountsTable, account);
        updateBank();
    }

    public void updateBank() {
        setLabel(numAccountsLBL, Entry.getBank().getNumAccounts());
        setLabel(activeAccountsLBL, Entry.getBank().getActiveAccounts());
        setLabel(totalMoneyLBL, Entry.getBank().getTotalMoney());
        Main.getCityPage().refreshBanks();
        accountsTable.refresh();
    }

    public void setRoot(Pane newRoot) {
        root.getScene().setRoot(newRoot);
    }

    public void resetRoot(Pane oldRoot) {
        oldRoot.getScene().setRoot(root);
    }
}
