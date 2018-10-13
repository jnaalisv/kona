package kona.config;

import org.hibernate.SessionFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableConfigurationProperties
@ComponentScan(basePackages = {"kona.persistence"})
public class HibernateConfiguration {

    @Bean
    @ConfigurationProperties("spring.jpa.properties")
    public Properties hibernateProperties() {
        return new Properties();
    }

    @Bean
    public SessionFactory sessionFactory(DataSource dataSource, Properties hibernateProperties) {
        return new LocalSessionFactoryBuilder(dataSource)
                .scanPackages("kona.domain")
                .addProperties(hibernateProperties)
                .buildSessionFactory();
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
}
