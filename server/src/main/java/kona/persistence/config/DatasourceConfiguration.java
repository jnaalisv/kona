package kona.persistence.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import net.ttddyy.dsproxy.listener.logging.DefaultQueryLogEntryCreator;
import net.ttddyy.dsproxy.listener.logging.SLF4JLogLevel;
import net.ttddyy.dsproxy.listener.logging.SLF4JQueryLoggingListener;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.hibernate.engine.jdbc.internal.FormatStyle;
import org.hibernate.engine.jdbc.internal.Formatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.Properties;

// DIsable for now
//@Configuration
public class DatasourceConfiguration {

    //@Bean(destroyMethod = "close")
    public DataSource actualDataSource() {
        Properties dsProperties = new Properties();
        dsProperties.setProperty("dataSourceClassName", "org.postgresql.ds.PGSimpleDataSource");
        dsProperties.setProperty("dataSource.user", "kona");
        dsProperties.setProperty("dataSource.password", "kona");
        dsProperties.setProperty("dataSource.databaseName", "kona");
        return new HikariDataSource(new HikariConfig(dsProperties));
    }


    private static class PrettyQueryEntryCreator extends DefaultQueryLogEntryCreator {
        private Formatter formatter = FormatStyle.BASIC.getFormatter();

        @Override
        protected String formatQuery(String query) {
            return this.formatter.format(query);
        }
    }

    //@Bean
    //@Primary
    public DataSource dataSource(DataSource actualDataSource) {
        // use pretty formatted query with multiline enabled
        PrettyQueryEntryCreator creator = new PrettyQueryEntryCreator();
        creator.setMultiline(true);

        SLF4JQueryLoggingListener listener = new SLF4JQueryLoggingListener();
        listener.setQueryLogEntryCreator(creator);
        listener.setLogLevel(SLF4JLogLevel.INFO);

        return ProxyDataSourceBuilder
                .create(actualDataSource)
                .name("proxyDS")
                .listener(listener)
                .build();
    }
}
