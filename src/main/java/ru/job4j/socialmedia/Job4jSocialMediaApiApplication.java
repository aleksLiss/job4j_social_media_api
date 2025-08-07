package ru.job4j.socialmedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class Job4jSocialMediaApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(Job4jSocialMediaApiApplication.class, args);
    }

}
