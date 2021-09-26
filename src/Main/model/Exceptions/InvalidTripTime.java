package Main.model.Exceptions;

public class InvalidTripTime extends CancelTrip {
    public InvalidTripTime() {
        super("No trip is possible on this date! (now - next one year)");
    }
}
