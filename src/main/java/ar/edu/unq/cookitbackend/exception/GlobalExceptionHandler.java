package ar.edu.unq.cookitbackend.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class})
    @Nullable
    public final ResponseEntity<ApiError> handleException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();

        if (ex instanceof NotFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            NotFoundException notFoundException = (NotFoundException) ex;

            return handleException(notFoundException, headers, status, request);
        } else if (ex instanceof EmailExistException) {
            HttpStatus status = HttpStatus.CONFLICT;
            EmailExistException emailExistException = (EmailExistException) ex;

            return handleException(emailExistException, headers, status, request);
        }  else if (ex instanceof LoginException) {
            HttpStatus status = HttpStatus.CONFLICT;
            LoginException loginException = (LoginException) ex;

            return handleException(loginException, headers, status, request);
        } else if (ex instanceof PasswordIncorrectException) {
            HttpStatus status = HttpStatus.CONFLICT;
            PasswordIncorrectException passwordIncorrectException = (PasswordIncorrectException) ex;

            return handleException(passwordIncorrectException, headers, status, request);
        } if (ex instanceof CreateDocumentationException) {
            HttpStatus status = HttpStatus.CONFLICT;
            CreateDocumentationException createDocumentationException = (CreateDocumentationException) ex;

            return handleException(createDocumentationException, headers, status, request);
        } else {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(ex, null, headers, status, request);
        }
    }

    private ResponseEntity<ApiError> handleException(
            Exception ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMessage = ex.getMessage();
        return handleExceptionInternal(ex, new ApiError(errorMessage), headers, status, request);
    }

    private ResponseEntity<ApiError> handleExceptionInternal(Exception ex, ApiError body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<>(body, headers, status);
    }
}
