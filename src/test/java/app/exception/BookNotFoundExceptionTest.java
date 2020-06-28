package app.exception;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BookNotFoundExceptionTest {

    @Test
    void shouldGetRightMessage() {
        EasyRandom random = new EasyRandom();
        String id = random.nextObject(String.class);

        BookNotFoundException bookNotFoundException = new BookNotFoundException(id);

        assertThat(bookNotFoundException.getMessage()).isEqualTo("Book " + id + " not found");
    }

}