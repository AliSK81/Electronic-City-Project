package Main.controller.Forms;

import Main.Main;
import Main.model.Accounts.BankAccount;
import Main.model.Entry;
import Main.model.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AddAccountForm implements Initializable {

    @FXML
    private Label errorLBL;

    @FXML
    private ComboBox<Person> holderCBOX;

    @FXML
    private TextField usernameTXT;

    @FXML
    private TextField passwordTXT;

    @FXML
    private Spinner<Double> initBalanceSPN;

    @FXML
    void clearAction(ActionEvent event) {
        holderCBOX.getSelectionModel().clearSelection();
        usernameTXT.setText("");
        passwordTXT.setText("");
        initBalanceSPN.getEditor().setText("0");
        errorLBL.setText("");
    }

    @FXML
    void clearErrorAction(MouseEvent event) {
        errorLBL.setText("");
    }

    @FXML
    void doneAction(ActionEvent event) {
        try {
            Person holder = holderCBOX.getValue();
            if (holder == null)
                errorLBL.setText("Select Account Holder");
            else
                Main.getBankPage().addAccount(new BankAccount(holder, usernameTXT.getText(), passwordTXT.getText()), initBalanceSPN.getValue());
        } catch (RuntimeException ex) {
            errorLBL.setText(ex.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorLBL.setText("");
        errorLBL.setFocusTraversable(true);
        holderCBOX.getItems().addAll(Entry.getCity().getPeople());
        initBalanceSPN.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 1000, 0, 62.5));
    }

}
