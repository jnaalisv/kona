package kona.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@EnableAutoConfiguration
public class KonaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KonaApplication.class, args);
    }
}
