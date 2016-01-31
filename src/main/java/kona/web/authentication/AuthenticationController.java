package kona.web.authentication;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.token.KeyBasedPersistenceTokenService;
import org.springframework.security.core.token.Token;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kona.configurations.authentication.SpringSecurityConfiguration;


@RestController
@RequestMapping("authenticate")
public class AuthenticationController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);

    private final AuthenticationManager authenticationManager;
    private final KeyBasedPersistenceTokenService keyBasedPersistenceTokenService;

    @Inject
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
     * @param credentialsDTO
     * @return A token that should be included in HTTP requests that require
     *         authentication
     * @throws org.springframework.security.core.AuthenticationException
     */
    @RequestMapping(method = RequestMethod.POST)
    public String authenticate(@RequestBody CredentialsDTO credentialsDTO) throws Exception {
        UsernamePasswordAuthenticationToken authenticationRequest = new UsernamePasswordAuthenticationToken(credentialsDTO.username, credentialsDTO.password);

        authenticationManager.authenticate(authenticationRequest);

        Token token = keyBasedPersistenceTokenService.allocateToken(credentialsDTO.username);
        return token.getKey();
    }
}
