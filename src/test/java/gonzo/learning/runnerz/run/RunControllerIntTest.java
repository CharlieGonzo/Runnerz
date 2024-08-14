package gonzo.learning.runnerz.run;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RunControllerIntTest {

    @LocalServerPort
    int randomServerPort;

    RestClient client;

    @BeforeEach
    void setUp(){
        client = RestClient.create("http://localhost:" + randomServerPort);
    }

    @Test
    void shouldFindAllRuns(){
        List<Runs> runs = client.get()
                .uri("/api/runs")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });

            
        assertEquals(10,runs.size(),"failed to retrieve correct list of runs");
    }
}
