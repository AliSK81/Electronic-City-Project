package Main.model.Buildings;

import Main.model.Costs.ServiceCost;
import Main.model.Entry;
import Main.model.Exceptions.WrongName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Hotel implements Serializable {
    private final int id;
    private final ArrayList<Room> rooms = new ArrayList<>();
    private final ArrayList<HotelService> services = new ArrayList<>();
    private String name;
    private String address;
    private double price;

    public Hotel(String name, String address) {
        if (!name.matches(".*[a-z].*") && !name.matches(".*[A-Z].*")) throw new WrongName();
        this.id = Entry.getCountry().lastHotelID++;
        this.name = name;
        this.address = address;
        this.price = ServiceCost.Hotel.getCost();
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumRooms() {
        return rooms.size();
    }

    public int getNumServices() {
        return services.size();
    }

    public void addRoom(Room room) {
        rooms.add(room);
        price += room.getPrice();
        Entry.getCity().addPayment("Add Room", -room.getPrice());
    }

    public void removeRoom(Room room) {
        rooms.remove(room);
        price -= room.getPrice();
        Entry.getCity().addPayment("Remove Room", room.getPrice());
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void addServices(List<HotelService> services, double price) {
        for (HotelService service : services) {
            Entry.getCity().addPayment("Add " + service.name(), -service.getPrice());
        }
        this.price += price;
        this.services.addAll(services);
    }

    public void removeService(HotelService service) {
        Entry.getCity().addPayment("Remove " + service.name(), service.getPrice());
        services.remove(service);
        this.price -= service.getPrice();
    }

    public ArrayList<HotelService> getServices() {
        return services;
    }

    @Override
    public String toString() {
        return name + " Hotel (ID: " + id + ")";
    }

    public enum HotelService {
        Restaurant(5), SwimmingPool(4), GameNet(4), ShoppingCenter(5),
        SportsHall(3), CarParking(2), Library(1), Cinema(3);

        private final int id;
        private final String name;
        private final int stars;
        private final double price;

        HotelService(int stars) {
            id = this.ordinal();
            this.stars = stars;
            name = this.name();
            price = ServiceCost.valueOf("HotelService").getCost() * getStars();
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getStars() {
            return stars;
        }

        public double getPrice() {
            return price;
        }

    }
}


