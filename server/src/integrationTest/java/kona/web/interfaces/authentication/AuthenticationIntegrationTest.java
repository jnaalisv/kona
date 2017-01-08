package kona.web.interfaces.authentication;

import kona.web.interfaces.AbstractSpringRestMvcTest;
import org.junit.Test;
import org.springframework.http.HttpHeaders;

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
                .header(HttpHeaders.AUTHORIZATION, "invalidAuthenticationToken")
                .expect401();
    }

    @Test
    public void httpGetWithValidAuthTokenShouldReturnHttpOK(){
        httpGet("/customers")
                .acceptApplicationJson()
                .header(HttpHeaders.AUTHORIZATION, adminAuthToken)
                .expect200();
    }
}
