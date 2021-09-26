package Main.controller.Forms;

import Main.Main;
import Main.model.City;
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

public class AddCityForm implements Initializable {

    @FXML
    private Label errorLBL;

    @FXML
    private TextField nameTXT;

    @FXML
    private Spinner<Double> moneySPN;

    @FXML
    void clearAction(ActionEvent event) {

    }

    @FXML
    void clearErrorAction(MouseEvent event) {

    }

    @FXML
    void doneAction(ActionEvent event) {
        try {
            Main.getCountryPage().addCity(new City(nameTXT.getText()), moneySPN.getValue());
        } catch (RuntimeException ex) {
            errorLBL.setText(ex.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorLBL.setFocusTraversable(true);
        errorLBL.setText("");

        moneySPN.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(10000, 50000, 10000, 10000));
    }
}
