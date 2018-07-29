package app.controller;

import app.exception.BookNotFoundException;
import app.exception.ImpossibleToEditException;
import app.model.Book;
import app.repository.BookRepository;
import com.github.javafaker.Faker;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    @InjectMocks
    private BookController bookController;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private List<Book> expectedBooks;
    @Mock
    private Book expectedBook;
    private Faker faker;
    private String id;

    @Before
    public void setUp() {
        faker = new Faker();
        id = faker.regexify("[a-z]{10}");
    }

    @Test
    public void shouldInvokeFindAll() {
        doReturn(expectedBooks).when(bookRepository).findAll();

        List<Book> books = bookController.getAll();

        verify(bookRepository).findAll();
        assertThat(books, is(expectedBooks));
    }

    @Test
    public void shouldInvokeFindById() {
        doReturn(Optional.of(expectedBook)).when(bookRepository).findById(anyString());

        Book book = bookController.get(id);

        verify(bookRepository).findById(id);
        assertThat(book, is(expectedBook));
    }

    @Test
    public void shouldThrowAnExceptionIfTheBookIsNotFound() {
        exceptionRule.expect(BookNotFoundException.class);
        exceptionRule.expectMessage("Book " + id + " not found");

        doReturn(Optional.empty()).when(bookRepository).findById(anyString());

        bookController.get(id);
    }

    @Test
    public void shouldInvokeSave() {
        doReturn(expectedBook).when(bookRepository).save(any());

        Book bookToSave = mock(Book.class);
        Book book = bookController.save(bookToSave);

        verify(bookRepository).save(bookToSave);
        assertThat(book, is(expectedBook));
    }

    @Test
    public void shouldInvokeSaveWhenEdit() {
        doReturn(Optional.of(expectedBook)).when(bookRepository).findById(anyString());
        doReturn(expectedBook).when(bookRepository).save(any());

        Book bookToSave = mock(Book.class);
        Book book = bookController.edit(id, bookToSave);

        verify(bookRepository).save(bookToSave);
        assertThat(book, is(expectedBook));
    }

    @Test
    public void shouldThrowAnExceptionIfTheBookIsNotFoundWhenEdit() {
        exceptionRule.expect(ImpossibleToEditException.class);

        doReturn(Optional.empty()).when(bookRepository).findById(anyString());

        bookController.edit(id, expectedBook);
    }

    @Test
    public void shouldSetCorrectIdReceiveBook() {
        doReturn(Optional.of(expectedBook)).when(bookRepository).findById(anyString());
        doReturn(expectedBook).when(bookRepository).save(any());

        Book bookToSave = Book.builder()
                .title(faker.book().title())
                .build();

        bookController.edit(id, bookToSave);

        assertThat(bookToSave.getObjectId(), is(id));
    }

}