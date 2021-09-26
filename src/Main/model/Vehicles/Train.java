package Main.model.Vehicles;

import Main.model.Costs.ServiceCost;
import Main.model.Entry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Train extends LandVehicle implements Serializable {
    private final ArrayList<TrainService> services = new ArrayList<>();
    private final int wagons;

    public Train(int capacity, String company, double speed, double lpk, int year, int wagons) {
        super(capacity, company, speed, lpk, year);
        this.wagons = wagons;
    }

    @Override
    public void showInfo() {
        System.out.printf("  %-3d | %-7.1f | %-11s | %-8d | %-8.1f | %-4d | %-6.1f | %-6d | %-5d | %s\n", getId(),
                getPrice(), getCompany(), getCapacity(), getSpeed(), getYear(), getLpk(), wagons, 0, services);
    }

    public int getWagons() {
        return wagons;
    }

    public int getNumServices() {
        return services.size();
    }

    public void updateServices(List<TrainService> newServices, double price) {

        for (TrainService service : newServices) {
            if (!services.contains(service)) {
                Entry.getCity().addPayment("Add " + service.name() + " Service", -service.getPrice());
                super.price += service.getPrice();
                services.add(service);
            }
        }

        for (int i = 0; i < services.size(); i++) {
            TrainService service = services.get(i);
            if (!newServices.contains(service)) {
                Entry.getCity().addPayment("Remove " + service.name() + " Service", service.getPrice());
                super.price -= service.getPrice();
                services.remove(service);
                i--;
            }
        }
    }

    public ArrayList<TrainService> getServices() {
        return services;
    }

    public enum TrainService {
        TV(2), Book(1), Game(3), Coffee(1), Food(4), Music(2);

        private final String name;
        private final int stars;
        private final double price;

        TrainService(int stars) {
            this.stars = stars;
            name = this.name();
            price = ServiceCost.valueOf("TrainService").getCost() * getStars();
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