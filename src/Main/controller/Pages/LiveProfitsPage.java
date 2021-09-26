package Main.controller.Pages;

import Main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class LiveProfitsPage implements Initializable {

    @FXML
    private VBox root;

    @FXML
    private TextArea textArea;

    @FXML
    private Button okBTN;

    @FXML
    void clearAction(ActionEvent event) {
        textArea.clear();
    }

    @FXML
    void okAction(ActionEvent event) {
        Main.setProfitsPage(null);
        root.getScene().getWindow().hide();
    }

    public void appendData(String data) {
        textArea.appendText(data);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Main.setProfitsPage(this);
    }
}
