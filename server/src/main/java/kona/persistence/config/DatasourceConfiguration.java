package kona.persistence.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DatasourceConfiguration {

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        Properties dsProperties = new Properties();
        dsProperties.setProperty("dataSourceClassName", "org.postgresql.ds.PGSimpleDataSource");
        dsProperties.setProperty("dataSource.user", "kona");
        dsProperties.setProperty("dataSource.password", "kona");
        dsProperties.setProperty("dataSource.databaseName", "kona");
        return new HikariDataSource(new HikariConfig(dsProperties));
    }
}
