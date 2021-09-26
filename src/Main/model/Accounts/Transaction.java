package Main.model.Accounts;

import Main.model.Entry;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction implements Serializable {
    private final int id;
    private final Type type;
    private final double cost;
    private final String time;

    public Transaction(Type type, double cost) {
        this.id = Entry.getCountry().lastBankAccountID++;
        this.type = type;
        this.cost = cost;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = Entry.getCountry().getGameTime();
        this.time = dtf.format(now);
    }

    public void showInfo() {
        System.out.printf(" %-3d | %-11s | %-5.1f $ | %s\n", id, type, cost, time);
    }

    public double getCost() {
        return cost;
    }

    public int getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public String getTime() {
        return time;
    }

    public enum Type {
        Deposit, Withdraw, Profit
    }
}


