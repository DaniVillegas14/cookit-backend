package ar.edu.unq.cookitbackend.exception;

public class LoginException extends Exception {

    public LoginException() {
        super("Los datos ingresados no coinciden");
    }
}
