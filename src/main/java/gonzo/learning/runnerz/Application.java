package gonzo.learning.runnerz;

import gonzo.learning.runnerz.run.RunRepository;
import gonzo.learning.runnerz.user.User;
import gonzo.learning.runnerz.user.UserHttpClient;
import gonzo.learning.runnerz.user.UserRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.awt.desktop.UserSessionEvent;
import java.sql.SQLOutput;
import java.util.List;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}


	//creating http client for class UserHttpClient and putting it in the spring context
	@Bean
	UserHttpClient userHttpClient(){
		RestClient restClient = RestClient.create("https://jsonpalceholder.typicode.com/");
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
		return factory.createClient(UserHttpClient.class);
	}


//	@Bean
//	CommandLineRunner runner(UserHttpClient client) {
//		return args -> {
//			List<User> users = client.findAll();
//			System.out.println(users);
//		};
//	}



}
