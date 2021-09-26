package Main.controller.Forms;

import Main.Main;
import Main.model.Buildings.BusTerminal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AddBusTerminalForm implements Initializable {

    @FXML
    private Label errorLBL;

    @FXML
    private TextField nameTXT;

    @FXML
    private TextField addressTXT;

    @FXML
    private Spinner<Double> areaSNP;

    @FXML
    void clearAction(ActionEvent event) {
        errorLBL.setText("");
        nameTXT.setText("");
        addressTXT.setText("");
        areaSNP.getEditor().setText("25");
    }

    @FXML
    void clearErrorAction(MouseEvent event) {
        errorLBL.setText("");
    }

    @FXML
    void doneAction(ActionEvent event) {
        try {
            Main.getCityPage().addTerminal(new BusTerminal(nameTXT.getText(), addressTXT.getText(), areaSNP.getValue()));
        } catch (RuntimeException ex) {
            errorLBL.setText(ex.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorLBL.setText("");
        errorLBL.setFocusTraversable(true);
        areaSNP.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(10, 50, 25, 2.5));
    }

}
