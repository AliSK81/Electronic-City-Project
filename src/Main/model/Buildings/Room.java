package Main.model.Buildings;

import Main.model.Costs.ServiceCost;
import Main.model.Entry;
import Main.model.Exceptions.WrongAmount;

import java.io.Serializable;

public class Room implements Serializable {

    private final int id;
    private final int beds;
    private final double area;
    private final double price;

    public Room(int beds, double area) {
        if (beds < 1 || beds > 6) throw new WrongAmount("Invalid number of beds! (1-6)");
        if (area < 20 || area > 200) throw new WrongAmount("Invalid input area! (20-200)");

        this.id = Entry.getCountry().lastRoomID++;
        this.beds = beds;
        this.area = area;
        this.price = ServiceCost.Room.getCost() * beds;
    }

    public int getId() {
        return id;
    }

    public int getBeds() {
        return beds;
    }

    public double getArea() {
        return area;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Room (ID: " + id + ")";
    }
}
