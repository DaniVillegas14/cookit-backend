package ar.edu.unq.cookitbackend.exception;

public class PasswordIncorrectException extends Exception {
    public PasswordIncorrectException() {
        super("La Contraseña es incorrecta");
    }
}
