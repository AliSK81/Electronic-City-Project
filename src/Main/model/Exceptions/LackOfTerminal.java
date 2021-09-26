package Main.model.Exceptions;

public class LackOfTerminal extends CancelTrip {
    public LackOfTerminal() {
        super("No terminals found at origin or destination cities!");
    }
}
