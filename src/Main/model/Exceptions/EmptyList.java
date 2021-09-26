package Main.model.Exceptions;

public class EmptyList extends RuntimeException {
    public EmptyList() {
        super("No content to show!");
    }
}
