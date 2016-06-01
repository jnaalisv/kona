package kona.web.authentication;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.token.KeyBasedPersistenceTokenService;
import org.springframework.security.core.token.Token;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class PreAuthUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {


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
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken preAuthenticatedAuthentication) throws UsernameNotFoundException {

        // PreAuthTokenFilter populates the preAuthenticatedAuthentication
        String tokenKey = preAuthenticatedAuthentication.getCredentials().toString();

        Token verifiedToken = tryToVerify(tokenKey);

        // when generating the token, username is presented as extended information
        return userDetailsService.loadUserByUsername(verifiedToken.getExtendedInformation());
    }

    private Token tryToVerify(String tokenKey) {
        try {
            return keyBasedPersistenceTokenService.verifyToken(tokenKey);
        } catch (Exception e) {
            throw new BadCredentialsException("Token verification failed");
        }
    }
}
