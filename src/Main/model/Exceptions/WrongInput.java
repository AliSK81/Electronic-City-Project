package Main.model.Exceptions;

import java.util.InputMismatchException;

public class WrongInput extends InputMismatchException {
    public WrongInput(String msg) {
        super(msg);
    }
}

