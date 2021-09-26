package Main.model.Exceptions;

public class LackOfVehicle extends CancelTrip {
    public LackOfVehicle() {
        super("Origin terminal has no vehicles!");
    }
}
