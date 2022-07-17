package com.vgb.prules.demo;

import com.vgb.prules.demo.util.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring boot application
 */
@SpringBootApplication
public class ProductRulesApplication implements ApplicationRunner {

    @Autowired
    Demo demo;

    public static void main(String[] args) {
        SpringApplication.run(ProductRulesApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        demo.init();
        demo.run();
    }
}
