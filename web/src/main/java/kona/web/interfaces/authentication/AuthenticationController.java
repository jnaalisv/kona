package kona.web.interfaces.authentication;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.token.KeyBasedPersistenceTokenService;
import org.springframework.security.core.token.Token;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("authenticate")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final KeyBasedPersistenceTokenService keyBasedPersistenceTokenService;

    @Inject
    public AuthenticationController(
            final AuthenticationManager authenticationManager,
            final KeyBasedPersistenceTokenService keyBasedPersistenceTokenService) {
        this.authenticationManager = authenticationManager;
        this.keyBasedPersistenceTokenService = keyBasedPersistenceTokenService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String authenticate(@RequestBody CredentialsDTO credentials) throws Exception {
        UsernamePasswordAuthenticationToken authenticationRequest = new UsernamePasswordAuthenticationToken(credentials.username, credentials.password);

        authenticationManager.authenticate(authenticationRequest);

        Token token = keyBasedPersistenceTokenService.allocateToken(credentials.username);
        return token.getKey();
    }
}
