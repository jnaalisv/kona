package kona.configurations;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import kona.configurations.authentication.SpringSecurityConfiguration;
import kona.configurations.authentication.WebSecurityConfig;

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

