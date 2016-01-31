package kona.configurations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan(basePackages = {"kona.domain"})
@Configuration
@EnableTransactionManagement
public class ModelConfiguration {

}
