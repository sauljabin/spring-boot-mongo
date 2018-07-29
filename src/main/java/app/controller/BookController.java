package app.controller;

import app.exception.BookNotFoundException;
import app.exception.ImpossibleToEditException;
import app.model.Book;
import app.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    @ResponseBody
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    @ResponseBody
    public Book get(@PathVariable String id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @DeleteMapping("/books/{id}")
    @ResponseBody
    public void delete(@PathVariable String id) {
        bookRepository.deleteById(id);
    }

    @PutMapping("/books/{id}")
    @ResponseBody
    public Book edit(@PathVariable String id, @RequestBody Book book) {
        bookRepository.findById(id)
                .orElseThrow(ImpossibleToEditException::new);

        book.setObjectId(id);
        return bookRepository.save(book);
    }

    @PostMapping("/books")
    @ResponseBody
    public Book save(@RequestBody Book book) {
        return bookRepository.save(book);
    }

}