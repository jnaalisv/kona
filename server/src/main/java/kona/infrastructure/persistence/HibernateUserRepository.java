package kona.infrastructure.persistence;

import kona.model.domain.user.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.Optional;

@Repository
public class HibernateUserRepository {

    private final SessionFactory sessionFactory;

    @Inject
    public HibernateUserRepository(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public Optional<User> loadUserByUsername(String username) {
        return getCurrentSession()
                .createQuery("select u from User u where u.username = :username", User.class)
                .setParameter("username", username)
                .uniqueResultOptional();
    }
}
