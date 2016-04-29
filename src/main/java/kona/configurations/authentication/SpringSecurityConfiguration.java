package kona.configurations.authentication;

import java.util.Arrays;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.token.KeyBasedPersistenceTokenService;
import org.springframework.security.core.token.SecureRandomFactoryBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

import kona.authentication.PreAuthUserDetailsService;
import kona.web.address.AddressController;
import kona.web.authentication.AuthenticationController;

@ComponentScan("kona.authentication")
@Configuration
public class SpringSecurityConfiguration {

    /**
     * Configure authentication provider for Spring Security to use BCrypt
     * password encoding and {@link UserDetailsService} implementation.
     *
     * @param userDetailsService implementation that can load a user from the
     *            database
     * @return configured DaoAuthenticationProvider instance
     */

    @Inject
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(UserDetailsService userDetailsService, BCryptPasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    /**
     * Used by {@link AuthenticationController} to authenticate authentication requests.
     * @param daoAuthProvider
     * @param preAuthProvider
     * @return
     */
    @Inject
    @Bean
    public AuthenticationManager authenticationManager(PreAuthenticatedAuthenticationProvider preAuthProvider, DaoAuthenticationProvider daoAuthProvider) {
        return new ProviderManager(Arrays.asList(preAuthProvider, daoAuthProvider));
    }

    /**
     * Used by {@link AddressController}
     * to allocate authentication tokens.
     * @return
     * @throws Exception
     */
    @Bean
    public KeyBasedPersistenceTokenService keyBasedPersistenceTokenService() throws Exception {
        KeyBasedPersistenceTokenService keyBasedPersistenceTokenService = new KeyBasedPersistenceTokenService();
        keyBasedPersistenceTokenService.setSecureRandom(new SecureRandomFactoryBean().getObject());
        keyBasedPersistenceTokenService.setServerInteger(981463275);
        keyBasedPersistenceTokenService.setServerSecret("98sd98fyasdjfh23082n");
        return keyBasedPersistenceTokenService;
    }

    /**
     * Provider that links AuthenticationManager and {@link PreAuthUserDetailsService}
     * @param preAuthUserDetailsService
     * @return
     */
    @Inject
    @Bean
    public PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider(PreAuthUserDetailsService preAuthUserDetailsService) {
        PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider = new PreAuthenticatedAuthenticationProvider();
        preAuthenticatedAuthenticationProvider.setPreAuthenticatedUserDetailsService(preAuthUserDetailsService);
        return preAuthenticatedAuthenticationProvider;
    }

}
