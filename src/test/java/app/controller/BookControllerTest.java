package app.controller;

import app.exception.ImpossibleToEditException;
import app.model.Book;
import app.repository.BookRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class BookControllerTest {

    private BookController bookController;
    private BookRepository bookRepository;
    private final EasyRandom random = new EasyRandom();

    @BeforeEach
    void setUp() {
        bookRepository = mock(BookRepository.class);
        bookController = new BookController(bookRepository);
    }

    @Test
    void shouldInvokeFindAll() {
        List<Book> expectedBooks = emptyList();
        when(bookRepository.findAll()).thenReturn(expectedBooks);

        List<Book> books = bookController.getAll();

        verify(bookRepository).findAll();
        assertThat(books).isEqualTo(expectedBooks);
    }

    @Test
    void shouldInvokeFindById() {
        Book expectedBook = random.nextObject(Book.class);
        when(bookRepository.findById(anyString())).thenReturn(Optional.of(expectedBook));

        Book book = bookController.get(expectedBook.getObjectId());

        verify(bookRepository).findById(expectedBook.getObjectId());
        assertThat(book).isSameAs(expectedBook);
    }

    @Test
    void shouldThrowAnExceptionIfTheBookIsNotFound() {
        String id = random.nextObject(String.class);
        when(bookRepository.findById(anyString())).thenReturn(Optional.empty());

        Throwable throwable = catchThrowable(() -> bookController.get(id));

        assertThat(throwable).hasMessage("Book " + id + " not found");
    }

    @Test
    void shouldInvokeSave() {
        Book expectedBook = random.nextObject(Book.class);
        when(bookRepository.save(any())).thenReturn(expectedBook);
        Book bookToSave = mock(Book.class);

        Book book = bookController.save(bookToSave);

        verify(bookRepository).save(bookToSave);
        assertThat(book).isSameAs(expectedBook);
    }

    @Test
    void shouldInvokeSaveWhenEdit() {
        Book expectedBook = random.nextObject(Book.class);
        when(bookRepository.findById(anyString())).thenReturn(Optional.of(expectedBook));
        when(bookRepository.save(any())).thenReturn(expectedBook);

        Book bookToSave = mock(Book.class);
        Book book = bookController.edit(expectedBook.getObjectId(), bookToSave);

        verify(bookRepository).save(bookToSave);
        assertThat(book).isSameAs(expectedBook);
    }

    @Test
    void shouldThrowAnExceptionIfTheBookIsNotFoundWhenEdit() {
        String id = random.nextObject(String.class);
        Book expectedBook = random.nextObject(Book.class);
        when(bookRepository.findById(anyString())).thenReturn(Optional.empty());

        Throwable throwable = catchThrowable(() -> bookController.edit(id, expectedBook));

        assertThat(throwable).isInstanceOf(ImpossibleToEditException.class);
    }

    @Test
    void shouldSetCorrectIdReceiveBook() {
        String id = random.nextObject(String.class);
        Book expectedBook = random.nextObject(Book.class);
        when(bookRepository.findById(anyString())).thenReturn(Optional.of(expectedBook));
        when(bookRepository.save(any())).thenReturn(expectedBook);

        Book bookToSave = Book.builder()
                .build();

        bookController.edit(id, bookToSave);

        assertThat(bookToSave.getObjectId()).isEqualTo(id);
    }

}