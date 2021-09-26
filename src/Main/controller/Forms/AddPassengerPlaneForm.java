package Main.controller.Forms;

import Main.Main;
import Main.model.UI;
import Main.model.Vehicles.PassengerPlane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AddPassengerPlaneForm extends UI implements Initializable {

    @FXML
    private Label errorLBL;

    @FXML
    private TextField companyTXT;

    @FXML
    private Spinner<Integer> capacitySPN;

    @FXML
    private Spinner<Double> heightSPN;

    @FXML
    private Spinner<Double> lengthSPN;

    @FXML
    private ChoiceBox<Integer> seatsCBOX;

    @FXML
    void clearAction(ActionEvent event) {
        errorLBL.setText("");
        companyTXT.setText("");
        capacitySPN.getEditor().setText("10");
        heightSPN.getEditor().setText("10");
        lengthSPN.getEditor().setText("10");
        seatsCBOX.getSelectionModel().select(0);
    }

    @FXML
    void clearErrorAction(MouseEvent event) {
        errorLBL.setText("");
    }

    @FXML
    void doneAction(ActionEvent event) {
        try {
            Main.getApPage().addVehicle(new PassengerPlane(capacitySPN.getValue(), companyTXT.getText(), heightSPN.getValue(), lengthSPN.getValue(), seatsCBOX.getValue()));
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
        heightSPN.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(500, 1000, 750, 12.5));
        lengthSPN.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(1000, 2000, 1500, 12.5));
        seatsCBOX.getItems().addAll(1, 2, 3, 4, 5, 6);
        seatsCBOX.getSelectionModel().select(0);
    }
}
