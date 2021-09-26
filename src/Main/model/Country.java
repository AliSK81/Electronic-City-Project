package Main.model;

import Main.Main;
import Main.controller.Pages.CountryPage;
import Main.model.Accounts.BankAccount;
import Main.model.Buildings.Bank;
import Main.model.Exceptions.WrongAmount;
import javafx.application.Platform;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Country implements Serializable {
    private final ArrayList<City> cities = new ArrayList<>();
    int population;
    private String name;
    private double totalMoney;
    private double banksMoney;

    private LocalDateTime gameTime;

    public int lastPersonID;
    public int lastHotelID;
    public int lastRoomID;
    public int lastTerminalID;
    public int lastVehicleID;
    public int lastTripID;
    public int lastPaymentID;
    public int lastBankID;
    public int lastBankAccountID;

    public Country(String name) {
        this.name = name;
        gameTime = LocalDateTime.now();
        lastPersonID = 1000;
    }

    public double getBanksMoney() {
        return banksMoney;
    }

    public void updateBanksMoney(double money) {
        banksMoney += money;
        CountryPage countryPage = Main.getCountryPage();
        if (countryPage != null) {
            Platform.runLater(countryPage::updateBanksMoney);
        }
    }

    public int getPopulation() {
        return population;
    }

    public int getNumCities() {
        return cities.size();
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void checkCityName(String name) {
        for (City city : cities)
            if (city.getName().equalsIgnoreCase(name))
                throw new WrongAmount("This name has already been taken!");
    }

    public void addCity(City city, double initMoney) {
        checkCityName(city.getName());
        totalMoney += initMoney;
        cities.add(city);
        city.setTotalMoney(initMoney);
    }

    public City getCity(String name) {
        for (City city : cities) {
            if (city.getName().equalsIgnoreCase(name)) return city;
        }
        throw new WrongAmount("City not found!");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfCities() {
        return cities.size();
    }

    public void addPayment(double cost) {
        totalMoney += cost;
    }

    public List<City> getCities() {
        return cities;
    }

    public void startBanksProfitability() {
        for (City city : cities)
            for (Bank bank : city.getBanks())
                for (BankAccount account : bank.getAccounts())
                    account.startProfitability();

    }

    public void startGameTime() {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(709);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                gameTime = gameTime.plusDays(1).plusHours(1).plusMinutes(1).plusSeconds(1);
            }

        });
        thread.start();
    }

    public LocalDateTime getGameTime() {
        return gameTime;
    }

    @Override
    public String toString() {
        return name + " Country";
    }

}
