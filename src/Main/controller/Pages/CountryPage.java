package Main.controller.Pages;

import Main.Main;
import Main.model.City;
import Main.model.Data;
import Main.model.Entry;
import Main.model.Exceptions.Alerts;
import Main.model.UI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CountryPage extends UI implements Initializable {

    private TableView<City> citiesTable;

    @FXML
    private VBox root;

    @FXML
    private Label nameLBL;

    @FXML
    private Label populationLBL;

    @FXML
    private Label totalMoneyLBL;

    @FXML
    private Label banksMoneyLBL;

    @FXML
    private HBox tableHolder;

    @FXML
    void addCityAction(ActionEvent event) {
        showForm("AddCityForm.fxml");
    }

    @FXML
    void backAction(ActionEvent event) {

    }

    @FXML
    void enterCityAction(ActionEvent event) {
        try {
            Entry.setCity(getSelectedItem(citiesTable));
            enterCity();
        } catch (RuntimeException ex) {
            Alerts.showError(ex.getMessage());
        }
    }


    public void enterCity() {
        root.getScene().getWindow().hide();
        Stage stage = showPage("CityPage.fxml");
        stage.setOnCloseRequest(e -> showPage("CountryPage.fxml"));
    }

    public void backToCountry(Pane root) {
        root.getScene().getWindow().hide();
        showPage("CountryPage.fxml");
    }

    @FXML
    void exitAction(ActionEvent event) {
        if (Alerts.showConfirm("Exit Program?")) {
            try {
                Data.saveData();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            System.exit(0);
        }
    }

    @FXML
    void liveProfitsAction(ActionEvent event) {
        showPage("LiveProfitsPage.fxml").setResizable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Main.setCountryPage(this);

        setLabel(populationLBL, Entry.getCountry().getPopulation());
        setLabel(nameLBL, Entry.getCountry().getName());
        setLabel(totalMoneyLBL, Entry.getCountry().getTotalMoney());
        setLabel(banksMoneyLBL, Entry.getCountry().getBanksMoney());

        citiesTable = loadTable("CitiesTable.fxml");

        setTable(tableHolder, citiesTable, Entry.getCountry().getCities());
    }

    public void addCity(City city, double initMoney) {
        Entry.getCountry().addCity(city, initMoney);
        addToTable(citiesTable, city);
        updateTotalMoney();
    }

    public void updateTotalMoney() {
        setLabel(totalMoneyLBL, Entry.getCountry().getTotalMoney());
    }

    public void updateBanksMoney() {
        setLabel(banksMoneyLBL, Entry.getCountry().getBanksMoney());
    }
}
