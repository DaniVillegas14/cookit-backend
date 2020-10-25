package ar.edu.unq.cookitbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class EmailExistException extends RuntimeException {
    public EmailExistException() {
        super("El email ya existe");
    }
}
