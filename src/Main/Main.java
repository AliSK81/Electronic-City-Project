package Main;

import Main.controller.Pages.*;
import Main.model.*;
import Main.model.Exceptions.Alerts;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static Stage stage;
    private static CountryPage countryPage;
    private static CityPage cityPage;
    private static HotelPage hotelPage;
    private static TerminalPage terminalPage;
    private static BankPage bankPage;
    private static BankAccountPage accountPage;
    private static LiveProfitsPage profitsPage;

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Alerts.setConfirmAlertsON(false);
        Alerts.setInfoAlertsON(false);
        Alerts.setWarningAlertsON(false);

        Data.loadData();


        Country country;

        if (Universe.getCountries().size() == 0) {
            Universe.addCountry("IRAN");
            country = Universe.getCountry("IRAN");
            Entry.setCountry(country);

            country.addCity(new City("Capital"), 10000);
            City city = country.getCity("Capital");
            Entry.setCity(city);
        } else {
            country = Universe.getCountries().get(0);
            Entry.setCountry(country);
            Entry.setCity(country.getCities().get(0));
        }

        country.startGameTime();
        country.startBanksProfitability();


        launch(args);
        System.exit(0);

    }

    public static CountryPage getCountryPage() {
        return countryPage;
    }

    public static void setCountryPage(CountryPage countryPage) {
        Main.countryPage = countryPage;
    }

    public static CityPage getCityPage() {
        return cityPage;
    }

    public static void setCityPage(CityPage cityPage) {
        Main.cityPage = cityPage;
    }

    public static HotelPage getHotelPage() {
        return hotelPage;
    }

    public static void setHotelPage(HotelPage hotelPage) {
        Main.hotelPage = hotelPage;
    }

    public static TerminalPage getTerminalPage() {
        return terminalPage;
    }

    public static AirportPage getApPage() {
        return (AirportPage) terminalPage;
    }

    public static BusTerminalPage getBtPage() {
        return (BusTerminalPage) terminalPage;
    }

    public static RailwayStationPage getRsPage() {
        return (RailwayStationPage) terminalPage;
    }

    public static ShippingPortPage getSpPage() {
        return (ShippingPortPage) terminalPage;
    }

    public static void setTerminalPage(TerminalPage terminalPage) {
        Main.terminalPage = terminalPage;
    }

    public static BankPage getBankPage() {
        return bankPage;
    }

    public static void setBankPage(BankPage bankPage) {
        Main.bankPage = bankPage;
    }

    public static BankAccountPage getAccountPage() {
        return accountPage;
    }

    public static void setAccountPage(BankAccountPage accountPage) {
        Main.accountPage = accountPage;
    }

    public static LiveProfitsPage getProfitsPage() {
        return profitsPage;
    }

    public static void setProfitsPage(LiveProfitsPage profitsPage) {
        Main.profitsPage = profitsPage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        setUserAgentStylesheet(STYLESHEET_CASPIAN);

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("view/Pages/CountryPage.fxml"));
        loader.load();

        primaryStage.setScene(new Scene(loader.getRoot()));
        stage = primaryStage;
        stage.setOnCloseRequest(e-> {
            if (Alerts.showConfirm("Exit Program?")) {
                try {
                    Data.saveData();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                System.exit(0);
            }
        });

        primaryStage.show();

    }
}