package app.exception;

import com.github.javafaker.Faker;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BookNotFoundExceptionTest {

    private Faker faker;

    @Before
    public void setUp() {
        faker = new Faker();
    }

    @Test
    public void shouldGetRightMessage() {
        String id = faker.regexify("[a-z]{10}");
        BookNotFoundException bookNotFoundException = new BookNotFoundException(id);
        assertThat(bookNotFoundException.getMessage(), is("Book " + id + " not found"));
    }

}