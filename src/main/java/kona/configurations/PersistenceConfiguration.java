package kona.configurations;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"kona.infrastructure"})
@EnableTransactionManagement
public class PersistenceConfiguration {

    public static final String[] DOMAIN_ENTITIES = {"kona.domain"};

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        HikariConfig dataSourceConfig = new HikariConfig();
        dataSourceConfig.setDriverClassName("org.h2.Driver");
        dataSourceConfig.setJdbcUrl("jdbc:h2:mem:datajpa");
        dataSourceConfig.setUsername("sa");
        dataSourceConfig.setPassword("");

        return new HikariDataSource(dataSourceConfig);
    }

    @Bean
    public Properties hibernateProperties() {
        Properties jpaProperties = new Properties();

        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

        //Specifies the action that is invoked to the database when the Hibernate
        //SessionFactory is created or closed.
        jpaProperties.put("hibernate.hbm2ddl.auto","create");

        //Configures the naming strategy that is used when Hibernate creates
        //new database objects and schema elements
        jpaProperties.put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");

        jpaProperties.put("hibernate.show_sql","false");
        jpaProperties.put("hibernate.format_sql", "true");

        return jpaProperties;
    }
}
