package Main.controller.Tables;

import Main.model.City;
import Main.model.Person;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class PeopleTable implements Initializable {

    @FXML
    private TableView<Person> peopleTable;

    @FXML
    private TableColumn<Person, Integer> idCOL;

    @FXML
    private TableColumn<Person, String> nameCOL;

    @FXML
    private TableColumn<Person, String> familyCOL;

    @FXML
    private TableColumn<Person, Integer> birthYearCOL;

    @FXML
    private TableColumn<Person, String> genderCOL;

    @FXML
    private TableColumn<Person, City.Job> jobCOL;

    @FXML
    private TableColumn<Person, Boolean> workingCOL;

    @FXML
    private TableColumn<Person, Boolean> creditorCOL;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idCOL.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCOL.setCellValueFactory(new PropertyValueFactory<>("name"));
        familyCOL.setCellValueFactory(new PropertyValueFactory<>("family"));
        birthYearCOL.setCellValueFactory(new PropertyValueFactory<>("birthYear"));
        genderCOL.setCellValueFactory(new PropertyValueFactory<>("gender"));
        jobCOL.setCellValueFactory(new PropertyValueFactory<>("job"));
        workingCOL.setCellValueFactory(new PropertyValueFactory<>("working"));
        creditorCOL.setCellValueFactory(new PropertyValueFactory<>("creditor"));
    }
}
