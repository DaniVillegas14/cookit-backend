package ar.edu.unq.cookitbackend.exception;

public class EmailExistException extends Exception {
    public EmailExistException() {
        super("El email ya existe");
    }
}
