package de.robinschuerer.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import de.robinschuerer.norcom.searchservice.SearchService;

@SpringBootApplication
@ComponentScan(basePackageClasses = {SearchService.class, Application.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}