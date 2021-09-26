package Main.model.Exceptions;

public class InvalidPassenger extends InvalidTrip {
    public InvalidPassenger() {
        super("This person cannot travel!");
    }

    public InvalidPassenger(String msg) {
        super(msg);
    }
}
