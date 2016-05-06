package kona.web.interfaces.authentication;

import kona.web.interfaces.AbstractSpringRestMvcTest;
import org.junit.Test;

import static kona.web.authentication.PreAuthTokenFilter.X_AUTH_TOKEN_HEADERNAME;

public class AuthenticationIntegrationTest extends AbstractSpringRestMvcTest {

    @Test
    public void httpGetWithoutAuthTokenShouldReturnUnauthorized(){
        httpGet("/customers")
                .acceptApplicationJson()
                .expect401();
    }

    @Test
    public void httpGetWithInvalidAuthTokenShouldReturnUnauthorized(){
        httpGet("/customers")
                .acceptApplicationJson()
                .header(X_AUTH_TOKEN_HEADERNAME, "invalidAuthenticationToken")
                .expect401();
    }
}
