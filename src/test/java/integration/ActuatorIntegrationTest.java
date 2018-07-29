package integration;

import app.config.SpringBootMongoDBApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootMongoDBApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActuatorIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void shouldReturn200WhenSendingRequestToInfoEndpoint() {
        ResponseEntity<Map> entity = testRestTemplate.getForEntity("http://localhost:" + port + "/actuator/info", Map.class);

        assertThat(entity.getStatusCode(), is(HttpStatus.OK));
    }

}
