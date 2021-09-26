package Main.controller.Forms;

import Main.Main;
import Main.model.Buildings.Bank;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddBankForm implements Initializable {

    @FXML
    private Label errorLBL;

    @FXML
    private TextField nameTXT;

    @FXML
    private TextField addressTXT;

    @FXML
    void clearAction(ActionEvent event) {
        nameTXT.setText("");
        addressTXT.setText("");
        errorLBL.setText("");
    }

    @FXML
    void doneAction() {
        try {
            Main.getCityPage().addBank(new Bank(nameTXT.getText(), addressTXT.getText()));
        } catch (RuntimeException ex) {
            errorLBL.setText(ex.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorLBL.setFocusTraversable(true);
        errorLBL.setText("");
    }

}
