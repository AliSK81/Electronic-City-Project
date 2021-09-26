package Main.model.Buildings;

import Main.model.Accounts.BankAccount;
import Main.model.Entry;
import Main.model.Exceptions.WrongAmount;
import Main.model.Exceptions.WrongName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Bank implements Serializable {
    private final int id;
    private final ArrayList<BankAccount> bankAccounts = new ArrayList<>();
    private final Set<String> usernames = new HashSet<>();
    private String name;
    private String address;
    private String cityName;
    private double totalMoney;
    private int activeAccounts;

    public Bank(String name, String address) {
        if (!name.matches(".*[a-z].*") && !name.matches(".*[A-Z].*")) throw new WrongName();
        this.name = name;
        this.address = address;
        this.cityName = Entry.getCity().getName();
        this.id = Entry.getCountry().lastBankID++;
    }

    public void checkUsernameExist(String username) {
        if (usernames.contains(username))
            throw new WrongAmount("Username already exist");
    }

    public void addUsername(String username) {
        usernames.add(username);
    }

    public void removeUsername(String username) {
        usernames.remove(username);
    }

    public BankAccount getAccount(String username, String password) {
        for (BankAccount account : bankAccounts)
            if (account.getUsername().equals(username))
                if (account.getPassword().equals(password))
                    return account;

        throw new WrongAmount("Account not found");
    }

    public void showInfo() {
        System.out.printf(" %-3s | %-10s | %-8d | %.1f\n", id, name, activeAccounts, totalMoney);
    }

    public void updateTotalMoney(double amount) {
        totalMoney += amount;
        Entry.getCountry().updateBanksMoney(amount);
    }

    public void addAccount(BankAccount account, double initBalance) {
        bankAccounts.add(account);
        updateTotalMoney(initBalance);
        if (initBalance > 0)
            activeAccounts++;

        account.setBalance(initBalance);
        account.startProfitability();
    }

    public void showAccounts() {
        System.out.println("Holder Id | Balance | Username   | Password");
        for (BankAccount account : bankAccounts) {
            account.showInfo();
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public int getActiveAccounts() {
        return activeAccounts;
    }

    public void setActiveAccounts(int activeAccounts) {
        this.activeAccounts = activeAccounts;
    }

    public List<BankAccount> getAccounts() {
        return bankAccounts;
    }

    public int getNumAccounts() {
        return bankAccounts.size();
    }

    public ArrayList<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    @Override
    public String toString() {
        return "Bank (ID: " + id + ")";
    }

    public String getCityName() {
        return cityName;
    }
}
