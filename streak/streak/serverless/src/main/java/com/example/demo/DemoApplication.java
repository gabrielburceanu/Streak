package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.context.FunctionRegistration;
import org.springframework.cloud.function.context.FunctionType;
import org.springframework.cloud.function.context.FunctionalSpringApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.GenericApplicationContext;

import java.util.function.Function;

//@NativeHint(types = {
//		@TypeHint(typeNames = "org.springframework.context.support.GenericApplicationContext")
//})
@SpringBootApplication
public class DemoApplication {// implements ApplicationContextInitializer<GenericApplicationContext> {
// docker run -p 8080:8080 serverless:0.0.1-SNAPSHOT
	public static void main(String[] args) {
		System.out.println("########Version 1.0.0 5:37");
		//FunctionalSpringApplication.run(DemoApplication.class, args);
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public Function<String, String> lambda() {
		return s -> "Hello " + s + ", and welcome to Spring Cloud Function!!!";
	}

//	@Override
//	public void initialize(GenericApplicationContext applicationContext) {
//		applicationContext.registerBean("lambda", FunctionRegistration.class,
//				() -> new FunctionRegistration<>(lambda())
//						.type(FunctionType.from(String.class).to(String.class)));
//
//	}
}
