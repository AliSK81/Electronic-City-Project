package Main.model.Exceptions;

public class WrongName extends WrongInput {
    public WrongName() {
        super("Name must include a-z");
    }
}
