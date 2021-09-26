package Main.model.Costs;

import Main.model.Entry;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Payment implements Serializable {
    private final int id;
    private final String service;
    private final double cost;
    private final boolean income;
    private final String time;

    public Payment(String service, double cost, boolean income) {
        this.id = Entry.getCountry().lastPaymentID++;
        this.service = service;
        this.cost = cost;
        this.income = income;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        this.time = dtf.format(now);
    }

    public void showInfo() {
        String type = (income) ? "Income" : "Output";
        System.out.printf(" %-3d | %-18s | %-11s | %-5.1f $ | %s\n", id, service, type, cost, time);
    }

    public int getId() {
        return id;
    }

    public String getService() {
        return service;
    }

    public double getCost() {
        return cost;
    }

    public boolean isIncome() {
        return income;
    }

    public String getTime() {
        return time;
    }
}