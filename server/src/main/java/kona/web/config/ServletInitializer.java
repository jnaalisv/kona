package kona.web.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import kona.infrastructure.config.PersistenceConfiguration;
import kona.model.config.DomainConfiguration;
import kona.web.config.authentication.SpringSecurityConfiguration;
import kona.web.config.authentication.WebSecurityConfig;

public class ServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {
                DomainConfiguration.class,
                PersistenceConfiguration.class,
                SpringSecurityConfiguration.class,
                WebSecurityConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected String getServletName() {
        return "Kona";
    }
}
