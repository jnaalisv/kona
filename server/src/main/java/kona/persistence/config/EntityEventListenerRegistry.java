package kona.persistence.config;

import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;

import javax.annotation.PostConstruct;

public class EntityEventListenerRegistry {

    private final SessionFactory sessionFactory;

    public EntityEventListenerRegistry(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @PostConstruct
    public void registerListeners(){
        EventListenerRegistry eventListenerRegistry = ((SessionFactoryImplementor) sessionFactory).getServiceRegistry().getService(EventListenerRegistry.class);
        eventListenerRegistry.prependListeners(EventType.PRE_INSERT, EntityListener.class);
        eventListenerRegistry.prependListeners(EventType.PRE_UPDATE, EntityListener.class);
    }

}