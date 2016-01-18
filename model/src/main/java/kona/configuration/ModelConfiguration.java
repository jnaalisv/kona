package kona.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan(basePackages = {"kona.model" })
@Configuration
@EnableTransactionManagement
public class ModelConfiguration {

}
