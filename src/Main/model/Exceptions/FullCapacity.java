package Main.model.Exceptions;

public class FullCapacity extends InvalidTrip {
    public FullCapacity() {
        super("Vehicle capacity is full!");
    }
}
