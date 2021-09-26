package Main.controller.Forms;

import Main.Main;
import Main.model.Buildings.Terminal;
import Main.model.Entry;
import Main.model.Exceptions.Alerts;
import Main.model.Person;
import Main.model.UI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddWorkerForm extends UI implements Initializable {

    private TableView<Person> driversTable;

    @FXML
    private HBox tableHolder;

    @FXML
    void clearAction(ActionEvent event) {
        driversTable.getSelectionModel().clearSelection();
    }

    @FXML
    void doneAction(ActionEvent event) {
        try {
            List<Person> drivers = getSelectedItems(driversTable);
            Main.getTerminalPage().addWorker(drivers);
            removeFromTable(driversTable, drivers);
        } catch (RuntimeException ex) {
            Alerts.showError(ex.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        driversTable = loadTable("PeopleTable.fxml");
        driversTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        setTable(tableHolder, driversTable, Entry.getCity().getPeople(Terminal.getRequiredJob()));
    }
}
