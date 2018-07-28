package app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class BookNotFound extends RuntimeException {
    public BookNotFound(int id) {
        super(String.format("Book %d not found", id));
    }
}
