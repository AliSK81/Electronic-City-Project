package Main.model.Exceptions;

public class LowCapacity extends CancelTrip {
    public LowCapacity() {
        super("This number of passengers is not enough to do a trip! (half - full capacity of vehicle)");
    }
}
