package integration;

import app.config.SpringBootMongoDBApplication;
import app.model.Book;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = SpringBootMongoDBApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void shouldAddNewBook() {
        EasyRandom random = new EasyRandom();
        Book bookRequest = random.nextObject(Book.class);

        ResponseEntity<Book> response = testRestTemplate.postForEntity(resourceUrl(), bookRequest, Book.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getTitle()).isEqualTo(bookRequest.getTitle());
        assertThat(response.getBody().getDescription()).isEqualTo(bookRequest.getDescription());
        assertThat(response.getBody().getObjectId()).isNotNull();
    }

    private String resourceUrl() {
        return String.format("http://localhost:%d/books", port);
    }

}
