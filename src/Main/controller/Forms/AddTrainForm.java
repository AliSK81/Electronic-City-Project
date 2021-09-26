package Main.controller.Forms;

import Main.Main;
import Main.model.Vehicles.Train;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AddTrainForm implements Initializable {

    @FXML
    private Label errorLBL;

    @FXML
    private TextField companyTXT;

    @FXML
    private Spinner<Integer> capacitySPN;

    @FXML
    private Spinner<Double> speedSPN;

    @FXML
    private Spinner<Double> lpkSPN;

    @FXML
    private ChoiceBox<Integer> yearCBOX;

    @FXML
    private ChoiceBox<Integer> wagonsCBOX;

    @FXML
    void clearAction(ActionEvent event) {
        errorLBL.setText("");
        companyTXT.setText("");
        capacitySPN.getEditor().setText("10");
        speedSPN.getEditor().setText("10");
        lpkSPN.getEditor().setText("10");
        wagonsCBOX.getSelectionModel().select(0);
    }

    @FXML
    void clearErrorAction(MouseEvent event) {
        errorLBL.setText("");
    }

    @FXML
    void doneAction(ActionEvent event) {
        try {
            Main.getRsPage().addVehicle(new Train(capacitySPN.getValue(), companyTXT.getText(), speedSPN.getValue(), lpkSPN.getValue(), yearCBOX.getValue(), wagonsCBOX.getValue()));
        } catch (RuntimeException ex) {
            errorLBL.setText(ex.getMessage());
        }
    }

    @FXML
    void limitTextField(KeyEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorLBL.setFocusTraversable(true);
        errorLBL.setText("");

        capacitySPN.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 30, 20, 1));
        speedSPN.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(500, 1000, 750, 12.5));
        lpkSPN.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(1000, 2000, 1500, 12.5));
        lpkSPN.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(1000, 2000, 1500, 12.5));
        yearCBOX.getItems().addAll(2004, 2005, 2006, 2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016);
        yearCBOX.getSelectionModel().select(0);
        wagonsCBOX.getItems().addAll(5, 10, 15, 20, 25, 30);
        wagonsCBOX.getSelectionModel().select(0);
    }
}
