package kona.model.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = {"kona.model.domain", "kona.model.application"})
@Configuration
public class DomainConfiguration {

}
