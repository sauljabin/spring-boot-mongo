package app.model;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BookTest {

    @Test
    void shouldGetRestLink() {
        EasyRandom random = new EasyRandom();

        Book book = random.nextObject(Book.class);

        assertThat(book.getLink()).isEqualTo("/books/" + book.getObjectId());
    }

}