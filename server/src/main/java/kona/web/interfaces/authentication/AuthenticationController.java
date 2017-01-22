package kona.web.interfaces.authentication;

import kona.web.config.authentication.SpringSecurityConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.token.KeyBasedPersistenceTokenService;
import org.springframework.security.core.token.Token;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);

    private final AuthenticationManager authenticationManager;
    private final KeyBasedPersistenceTokenService keyBasedPersistenceTokenService;

    public AuthenticationController(
            final AuthenticationManager authenticationManager,
            final KeyBasedPersistenceTokenService keyBasedPersistenceTokenService) {
        this.authenticationManager = authenticationManager;
        this.keyBasedPersistenceTokenService = keyBasedPersistenceTokenService;
    }

    /**
     * Authenticates credentials using Spring Security's
     * {@link AuthenticationManager} configured in
     * {@link SpringSecurityConfiguration}.
     *
     * @param credentials
     * @return A token that should be included in HTTP requests that require
     *         authentication
     * @throws org.springframework.security.core.AuthenticationException
     */
    @PostMapping(path = "authenticate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TokenDTO authenticate(@RequestBody CredentialsDTO credentials) throws Exception {
        UsernamePasswordAuthenticationToken authenticationRequest = new UsernamePasswordAuthenticationToken(credentials.username, credentials.password);

        authenticationManager.authenticate(authenticationRequest);

        Token token = keyBasedPersistenceTokenService.allocateToken(credentials.username);
        return TokenDTO.from(token.getKey());
    }
}
