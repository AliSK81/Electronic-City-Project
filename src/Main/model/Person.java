package Main.model;

import Main.model.City.Job;
import Main.model.Costs.ServiceCost;

import java.io.Serializable;

public class Person implements Serializable {

    private final String name;
    private final String family;
    private final int birthYear;
    private final Job job;
    private final String gender;
    private final double salary;
    private final int id;
    private String city;
    private boolean working;
    private boolean creditor;

    public Person(String name, String family, String city, int birthYear, Job job, String gender) {
        this.name = name;
        this.family = family;
        this.city = city;
        this.birthYear = birthYear;
        this.job = job;
        this.gender = gender;
        this.salary = ServiceCost.valueOf(job.toString()).getCost();
        this.id = Entry.getCountry().lastPersonID++;
    }

    public String getName() {
        return name;
    }

    public String getFamily() {
        return family;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public Job getJob() {
        return job;
    }

    public String getGender() {
        return gender;
    }

    public double getSalary() {
        return salary;
    }

    public boolean isWorking() {
        return working;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }

    public boolean isCreditor() {
        return creditor;
    }

    public void setCreditor(boolean creditor) {
        this.creditor = creditor;
    }

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void showInfo() {
        System.out.printf("  %-10s| %-10s | %-9d | %-11s | %-10s | %-7s | %-8s | %-7.1f | %-8s | %d\n",
                name, family, birthYear, city, job, working, gender, salary, creditor, id);
    }

    @Override
    public String toString() {
        return String.format("%s %s (ID: %d)", name, family, id);
    }
}
