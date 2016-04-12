package kona.authentication;

import javax.inject.Inject;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.KeyBasedPersistenceTokenService;
import org.springframework.security.core.token.Token;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Verifies given authentication token and loads UserDetails
 */
@Component
public class PreAuthUserDetailsService implements AuthenticationUserDetailsService {

    private final UserDetailsService userDetailsService;
    private final KeyBasedPersistenceTokenService keyBasedPersistenceTokenService;

    @Inject
    public PreAuthUserDetailsService(
            final UserDetailsService userDetailsService,
            final KeyBasedPersistenceTokenService keyBasedPersistenceTokenService) {
        this.userDetailsService = userDetailsService;
        this.keyBasedPersistenceTokenService = keyBasedPersistenceTokenService;
    }

    @Override
    public UserDetails loadUserDetails(Authentication authentication) throws UsernameNotFoundException {

        String principal = authentication.getPrincipal().toString();
        Token verifiedToken = tryToVerifyKey(principal);

        return userDetailsService.loadUserByUsername(verifiedToken.getExtendedInformation());
    }

    private Token tryToVerifyKey(String key) {
        try {
            return keyBasedPersistenceTokenService.verifyToken(key);
        } catch (Exception e) {
            throw new BadCredentialsException("Token verification failed");
        }
    }
}