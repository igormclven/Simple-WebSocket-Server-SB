package igor.mclven.WebsocktetDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WebsocktetDemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(WebsocktetDemoApplication.class, args);

		String appName = context.getEnvironment().getProperty("spring.application.name", "WebsocktetDemo");
		System.out.println(appName + " is running!");
	}

}
