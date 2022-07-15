package com.vgb.prules.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductRulesApplication implements ApplicationRunner {

	@Autowired
	DemoApplication demoApplication;
	public static void main(String[] args) {
		SpringApplication.run(ProductRulesApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.err.println("foooo");
		demoApplication.init();
		demoApplication.run();
	}
}
