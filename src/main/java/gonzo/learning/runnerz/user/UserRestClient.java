package gonzo.learning.runnerz.user;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * this class has the same job as the UserHttpClient but allows for more flexibility by allowing you to write
 * out the methods and handle the request yourself.
 */
@Component
public class UserRestClient {

    private final RestClient restClient;

    public UserRestClient(RestClient.Builder restClient){
        var jdkFactory = new JdkClientHttpRequestFactory();
        this.restClient = restClient.baseUrl("https://jsonplaceholder.typicode.com/")
                .requestFactory(jdkFactory)
                .build();
    }

    public List<User> findAll(){
        return restClient.get()
                .uri("/users")
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});
    }

    public User findById(Integer id){
        return restClient.get()
                .uri("/users/{id}", id)
                .retrieve()
                .body(User.class);
    }

}
