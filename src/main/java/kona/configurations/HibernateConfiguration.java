package kona.configurations;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@Import(PersistenceConfiguration.class)
public class HibernateConfiguration {

    @Bean
    @Inject
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource, Properties hibernateProperties) {
        final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan(PersistenceConfiguration.DOMAIN_ENTITIES);
        sessionFactory.setHibernateProperties(hibernateProperties);
        return sessionFactory;
    }

    @Bean
    @Inject
    public HibernateTransactionManager transactionManager(final SessionFactory sessionFactory) {
        final HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }
}
