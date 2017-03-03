package kona;

import kona.model.config.DomainConfiguration;
import kona.persistence.config.HibernateConfiguration;
import kona.web.config.WebConfiguration;
import kona.web.config.authentication.SpringSecurityConfiguration;
import kona.web.config.authentication.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootConfiguration
@Import({
        DomainConfiguration.class,
        HibernateConfiguration.class,
        SpringSecurityConfiguration.class,
        WebSecurityConfig.class,
        WebConfiguration.class
})
public class KonaApplication {

    @Bean
    public TomcatEmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory() {
        return new TomcatEmbeddedServletContainerFactory();
    }

    public static void main(String[] args) {
        SpringApplication.run(KonaApplication.class, args);
    }
}
