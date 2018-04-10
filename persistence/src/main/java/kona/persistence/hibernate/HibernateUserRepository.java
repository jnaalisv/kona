package kona.persistence.hibernate;

import kona.domain.user.User;
import kona.domain.user.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class HibernateUserRepository implements UserRepository {

    private final SessionFactory sessionFactory;

    public HibernateUserRepository(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return getCurrentSession()
                .createQuery("select u from User u where u.username = :username", User.class)
                .setParameter("username", username)
                .uniqueResultOptional();
    }
}
