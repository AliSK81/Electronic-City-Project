package Main.model.Exceptions;

public class InvalidTrip extends RuntimeException {
    public InvalidTrip(String msg) {
        super(msg);
    }
}

