package kona.configurations.authentication;

import java.util.Arrays;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.token.KeyBasedPersistenceTokenService;
import org.springframework.security.core.token.SecureRandomFactoryBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import kona.web.addresses.AddressController;
import kona.web.authentication.AuthenticationController;

@Configuration
public class SpringSecurityConfiguration {

    /**
     * Use BCrypt password encoding with default strength of 10 log rounds.
     *
     * @return BCryptPasswordEncoder instance
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

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
    public AuthenticationManager authenticationManager(DaoAuthenticationProvider daoAuthenticationProvider) {
        return new ProviderManager(Arrays.asList(daoAuthenticationProvider));
    }

    /**
     * Used by {@link AddressController}
     * to allocate authentication tokens.
     * @return
     * @throws Exception
     */
    @Inject
    @Bean
    public KeyBasedPersistenceTokenService keyBasedPersistenceTokenService() throws Exception {
        KeyBasedPersistenceTokenService keyBasedPersistenceTokenService = new KeyBasedPersistenceTokenService();
        keyBasedPersistenceTokenService.setSecureRandom(new SecureRandomFactoryBean().getObject());
        keyBasedPersistenceTokenService.setServerInteger(981463275);
        keyBasedPersistenceTokenService.setServerSecret("98sd98fyasdjfh23082n");
        return keyBasedPersistenceTokenService;
    }

}
