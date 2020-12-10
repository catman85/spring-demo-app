package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Map;

@SpringBootApplication
@EnableJpaAuditing
public class DemoApplication {

	public static void main(String[] args) {
//		Map<String,String> env = System.getenv();
//		String user = env.get("JAVA_HOME");
//		System.out.println(user);
		SpringApplication.run(DemoApplication.class, args);
	}

	@Configuration
	@Profile("local")
	@ComponentScan(lazyInit = true)
	static class LocalConfig {
	}

}
