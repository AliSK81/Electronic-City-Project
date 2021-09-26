package Main.model.Exceptions;

public class LackOfDriver extends CancelTrip {
    public LackOfDriver() {
        super("Origin terminal has no drivers!");
    }
}
