package kona.persistence.config;

import kona.persistence.AuthenticatedUsernameProvider;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;

import javax.annotation.PostConstruct;

public class EntityEventListenerRegistry {

    private final SessionFactory sessionFactory;
    private final AuthenticatedUsernameProvider authenticatedUsernameProvider;

    public EntityEventListenerRegistry(final SessionFactory sessionFactory, final AuthenticatedUsernameProvider authenticatedUsernameProvider) {
        this.sessionFactory = sessionFactory;
        this.authenticatedUsernameProvider = authenticatedUsernameProvider;
    }

    @PostConstruct
    public void registerListeners(){
        final EventListenerRegistry eventListenerRegistry = ((SessionFactoryImplementor) sessionFactory).getServiceRegistry().getService(EventListenerRegistry.class);
        final EntityListener entityListener = new EntityListener(authenticatedUsernameProvider);

        eventListenerRegistry.appendListeners(EventType.PRE_INSERT, entityListener);
        eventListenerRegistry.appendListeners(EventType.PRE_UPDATE, entityListener);
    }

}