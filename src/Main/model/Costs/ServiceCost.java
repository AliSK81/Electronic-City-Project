package Main.model.Costs;

public enum ServiceCost {
    // Buildings
    Hotel(60, " -"), Room(6, "Bed"),
    Airport(10, "km²"), BusTerminal(11, "km²"),
    RailwayStation(12, "km²"), ShippingPort(13, "km²"),
    // Vehicles
    Bus(5, "capacity"), Train(6, "capacity"), Boat(7, "capacity"),
    Ship(8, "capacity"), PassengerPlane(9, "capacity"), CargoPlane(10, "capacity"),
    // Jobs
    Sailor(70, "trip"), Pilot(80, "trip"), BusDriver(55, "trip"),
    LocoDriver(65, "trip"), Crew(50, "trip"),
    // Services
    HotelService(10, "star"), TrainService(9, "star"),
    // Trips
    BusTrip(50, "passenger"), TrainTrip(55, "passenger"),
    PassengerPlaneTrip(62, "passenger"),
    ShipTrip(66, "passenger");

    private final double cost;
    private final String fee;

    ServiceCost(double cost, String fee) {
        this.cost = cost;
        this.fee = fee;
    }

    public double getCost() {
        return cost;
    }
}