package Main.controller.Forms;

import Main.Main;
import Main.model.Accounts.BankAccount;
import Main.model.Entry;
import Main.model.UI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class EnterAccountForm implements Initializable {

    @FXML
    private VBox root;

    @FXML
    private Label errorLBL;

    @FXML
    private TextField usernameTXT;

    @FXML
    private TextField passwordTXT;

    @FXML
    void clearAction(ActionEvent event) {
        usernameTXT.setText("");
        passwordTXT.setText("");
        errorLBL.setText("");
    }

    @FXML
    void clearErrorAction(MouseEvent event) {
        errorLBL.setText("");
    }

    @FXML
    void doneAction(ActionEvent event) {
        try {
            BankAccount account = Entry.getBank().getAccount(usernameTXT.getText(), passwordTXT.getText());
            Entry.setAccount(account);

            root.getScene().getWindow().hide();
            Main.getBankPage().setRoot(UI.loadPage("BankAccountPage.fxml"));

        } catch (RuntimeException ex) {
            errorLBL.setText(ex.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorLBL.setText("");
        errorLBL.setFocusTraversable(true);
    }
}
