package app.model;

import com.github.javafaker.Faker;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BookTest {

    private Faker faker;

    @Before
    public void setUp() {
        faker = new Faker();
    }

    @Test
    public void shouldGetRestLink() {
        String id = faker.regexify("[a-z]{10}");

        Book book = Book.builder()
                .objectId(id)
                .build();

        assertThat(book.getLink(), is("/books/" + id));
    }

}