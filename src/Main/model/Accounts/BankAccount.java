package Main.model.Accounts;

import Main.Main;
import Main.controller.Pages.BankAccountPage;
import Main.controller.Pages.BankPage;
import Main.controller.Pages.CityPage;
import Main.controller.Pages.LiveProfitsPage;
import Main.model.Buildings.Bank;
import Main.model.Entry;
import Main.model.Exceptions.WrongAmount;
import Main.model.Person;
import javafx.application.Platform;

import java.io.Serializable;
import java.util.ArrayList;

public class BankAccount implements Serializable {
    private final ArrayList<Transaction> transactions = new ArrayList<>();

    private final Person holder;
    private final Bank bank;
    private final double profitAmount = 0.18;
    private String username;
    private String password;
    private double balance;
    private int profitTime;
    private final int id;

    public BankAccount(Person holder, String username, String password) {
        checkValidate(username, password);
        id = Entry.getCountry().lastBankAccountID++;
        this.bank = Entry.getBank();
        this.holder = holder;
        this.username = username;
        this.password = password;
        this.profitTime = 60;
    }

    public static void checkValidate(String username, String password) {
        if (username.isEmpty() || username.contains(" "))
            throw new WrongAmount("Invalid username");

        Entry.getBank().checkUsernameExist(username);

        if (password.isEmpty() || password.contains(" "))
            throw new WrongAmount("Invalid password");

        if (!password.matches(".*[a-z].*") &&
                !password.matches(".*[A-Z].*") &&
                !password.matches(".*[0-9].*"))
            throw new WrongAmount("Weak password");

        // valid
        Entry.getBank().addUsername(username);
    }

    public void showInfo() {
        System.out.printf("%-9d | %-5.1f | %-10s | %-10s | %d\n", holder.getId(), balance, username, password, profitTime);
    }

    public Person getHolder() {
        return holder;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getProfitTime() {
        return profitTime;
    }

    public Bank getBank() {
        return bank;
    }

    public double getProfitAmount() {
        return profitAmount;
    }

    public int getNumTransactions() {
        return transactions.size();
    }

    public int getId() {
        return id;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction tr) {

        transactions.add(tr);

        if (balance == 0 && tr.getCost() > 0)
            bank.setActiveAccounts(bank.getActiveAccounts() + 1);

        balance += tr.getCost();

        if (balance == 0 && tr.getCost() < 0)
            bank.setActiveAccounts(bank.getActiveAccounts() - 1);

        bank.updateTotalMoney(tr.getCost());

        if (tr.getType() == Transaction.Type.Deposit) {
            synchronized (this) {
                notifyAll();
            }
        }

        if (tr.getType() == Transaction.Type.Profit) {
            showLiveProfit(tr);
        }
    }

    public void showLiveProfit(Transaction tr) {
        double lastBalance = balance - tr.getCost();

        CityPage cityPage = Main.getCityPage();
        BankPage bankPage = Main.getBankPage();
        BankAccountPage accountPage = Main.getAccountPage();
        LiveProfitsPage profitsPage = Main.getProfitsPage();

        Platform.runLater(() -> {
            if (cityPage != null)
                cityPage.refreshBanks();
            if (bankPage != null)
                bankPage.updateBank();
            if (accountPage != null)
                accountPage.updateAccount();
            if (profitsPage != null)
                profitsPage.appendData(String.format("%-45s\t%-20s\t%-20s\t%15.1f$\t%10.0f%%\t%15.1f$\t%s\n", holder, bank.getCityName(),
                        bank.getName(), lastBalance, (profitAmount * 100), balance, tr.getTime()));
        });
    }

    public void startProfitability() {
        new Thread(() -> {
            try {
                while (true) {

                    while (profitTime > 0) {

                        synchronized (this) {
                            while (balance == 0) {
                                wait();
                                profitTime = 60;
                            }
                        }

                        Thread.sleep(1000);
                        profitTime--;

                        BankAccountPage accountPage = Main.getAccountPage();
                        Platform.runLater(() -> {
                            if (accountPage != null)
                                accountPage.updateProfitTime();
                        });
                    }

                    addTransaction(new Transaction(Transaction.Type.Profit, balance * profitAmount));
                    profitTime = 60;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
