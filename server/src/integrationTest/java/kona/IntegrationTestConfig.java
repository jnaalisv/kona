package kona;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class IntegrationTestConfig {

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        Properties props = new Properties();
        props.setProperty("dataSourceClassName", "org.postgresql.ds.PGSimpleDataSource");
        props.setProperty("dataSource.user", "kona_test");
        props.setProperty("dataSource.password", "kona_test");
        props.setProperty("dataSource.databaseName", "kona_test");
        HikariConfig dataSourceConfig = new HikariConfig(props);
        return new HikariDataSource(dataSourceConfig);
    }
}