package kona.persistence.config;

import kona.domain.address.AddressRepository;
import kona.domain.customer.CustomerRepository;
import kona.domain.orderhandling.PurchaseOrderRepository;
import kona.domain.product.ProductRepository;
import kona.domain.user.UserRepository;
import kona.persistence.hibernate.*;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(SpringHibernateConfiguration.class)
public class HibernateRepositoriesConfig {

    @Bean
    public AddressRepository addressRepository(final SessionFactory sessionFactory) {
        return new HibernateAddressRepository(sessionFactory);
    }

    @Bean
    public CustomerRepository customerRepository(final SessionFactory sessionFactory) {
        return new HibernateCustomerRepository(sessionFactory);
    }

    @Bean
    public ProductRepository productRepository(final SessionFactory sessionFactory) {
        return new HibernateProductRepository(sessionFactory);
    }

    @Bean
    public PurchaseOrderRepository purchaseOrderRepository(final SessionFactory sessionFactory) {
        return new HibernatePurchaseOrderRepository(sessionFactory);
    }

    @Bean
    public UserRepository userRepository(final SessionFactory sessionFactory) {
        return new HibernateUserRepository(sessionFactory);
    }
}
