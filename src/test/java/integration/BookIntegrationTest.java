package integration;

import app.config.SpringBootMongoDBApplication;
import app.model.Book;
import com.github.javafaker.Faker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootMongoDBApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;
    private Faker faker;
    private Book bookRequest;
    private String title;
    private String description;

    @Before
    public void setUp() {
        faker = new Faker();
        title = faker.book().title();
        description = faker.lorem().sentence();
        bookRequest = Book.builder()
                .title(title)
                .description(description)
                .build();
    }

    @Test
    public void shouldAddNewBook() {
        ResponseEntity<Book> response = testRestTemplate.postForEntity(resourceUrl(), bookRequest, Book.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().getTitle(), is(title));
        assertThat(response.getBody().getDescription(), is(description));
        assertThat(response.getBody().getObjectId(), is(notNullValue()));
    }

    private String resourceUrl() {
        return String.format("http://localhost:%d/books", port);
    }

}
