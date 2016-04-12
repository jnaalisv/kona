package kona.configurations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = {"kona.domain", "kona.application"})
@Configuration
public class DomainConfiguration {

}
