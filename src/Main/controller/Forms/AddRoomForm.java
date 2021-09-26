package Main.controller.Forms;

import Main.Main;
import Main.model.Buildings.Room;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddRoomForm implements Initializable {

    @FXML
    private Label errorLBL;

    @FXML
    private TextField bedsTXT;

    @FXML
    private TextField areaTXT;

    @FXML
    void doneAction() {
        try {
            Main.getHotelPage().addRoom(new Room(Integer.parseInt(bedsTXT.getText()), Double.parseDouble(areaTXT.getText())));
        } catch (RuntimeException ex) {
            errorLBL.setText(ex.getMessage());
        }
    }

    @FXML
    void clearAction() {
        bedsTXT.setText("");
        areaTXT.setText("");
        errorLBL.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorLBL.setFocusTraversable(true);
        errorLBL.setText("");
    }
}
