package springproject.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The custom exception.
 *
 * @author Evgeniy Presnov
 */
public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException() {}

    public BookNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
