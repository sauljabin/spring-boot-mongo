package app.controller;

import app.exception.BookNotFound;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @GetMapping("/books")
    String getAllBooks() {
        return "Hello from get";
    }

    @GetMapping("/books/{id}")
    String getBook(@PathVariable int id) {
        throw new BookNotFound(id);
    }

}