package Main.controller.Forms;

import Main.Main;
import Main.model.Vehicles.MarineVehicle;
import Main.model.Vehicles.Ship;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AddShipForm implements Initializable {

    @FXML
    private Label errorLBL;

    @FXML
    private TextField companyTXT;

    @FXML
    private TextField captainTXT;

    @FXML
    private Spinner<Integer> capacitySPN;

    @FXML
    private Spinner<Double> depthSPN;

    @FXML
    private ChoiceBox<MarineVehicle.Fuel> fuelCBOX;

    @FXML
    void clearAction(ActionEvent event) {
        errorLBL.setText("");
        companyTXT.setText("");
        captainTXT.setText("");
        capacitySPN.getEditor().setText("10");
        depthSPN.getEditor().setText("10");
        fuelCBOX.getSelectionModel().select(0);
    }

    @FXML
    void clearErrorAction(MouseEvent event) {
        errorLBL.setText("");
    }

    @FXML
    void doneAction(ActionEvent event) {
        try {
            Main.getSpPage().addVehicle(new Ship(capacitySPN.getValue(), companyTXT.getText(), fuelCBOX.getValue(), depthSPN.getValue(), captainTXT.getText()));
        } catch (RuntimeException ex) {
            errorLBL.setText(ex.getMessage());
        }
    }

    @FXML
    void limitTextField(KeyEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorLBL.setFocusTraversable(true);
        errorLBL.setText("");

        capacitySPN.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 30, 20, 1));
        depthSPN.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(500, 1000, 750, 12.5));
        fuelCBOX.getItems().addAll(MarineVehicle.Fuel.values());
        fuelCBOX.getSelectionModel().select(0);
    }

}
