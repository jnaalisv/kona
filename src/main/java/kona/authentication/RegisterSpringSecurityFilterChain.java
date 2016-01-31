package kona.authentication;

import javax.servlet.Filter;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 * Registers the {@link DelegatingFilterProxy} to use the
 * springSecurityFilterChain before any other registered {@link Filter}
 *
 * @author jnaalisv
 *
 */
public class RegisterSpringSecurityFilterChain extends AbstractSecurityWebApplicationInitializer {

}
