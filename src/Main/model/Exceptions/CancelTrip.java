package Main.model.Exceptions;

public class CancelTrip extends RuntimeException {

    public CancelTrip(String msg) {
        super(msg);
    }

    public CancelTrip(String msg, InvalidTrip cause) {
        super(msg, cause);
    }
}
