package kona.persistence.config;

import kona.model.domain.AbstractEntity;
import kona.persistence.AuthenticatedUsernameProvider;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntityListener implements PreInsertEventListener, PreUpdateEventListener {
    private static final Logger logger = LoggerFactory.getLogger(EntityListener.class);

    private final AuthenticatedUsernameProvider authenticatedUsernameProvider;

    public EntityListener(final AuthenticatedUsernameProvider authenticatedUsernameProvider) {
        this.authenticatedUsernameProvider = authenticatedUsernameProvider;
    }

    @Override
    public boolean onPreInsert(PreInsertEvent event) {
        logger.info("onPreInsert ");

        Object entity = event.getEntity();

        if (entity instanceof AbstractEntity){
            AbstractEntity abstractEntity = (AbstractEntity) entity;
            abstractEntity.setCreatedBy(authenticatedUsernameProvider.getUsername());
        }

        return false;
    }

    @Override
    public boolean onPreUpdate(PreUpdateEvent event) {
        logger.info("onPreUpdate ");
        Object entity = event.getEntity();

        if (entity instanceof AbstractEntity){
            AbstractEntity abstractEntity = (AbstractEntity) entity;
            abstractEntity.setCreatedBy(authenticatedUsernameProvider.getUsername());
        }

        return false;
    }
}