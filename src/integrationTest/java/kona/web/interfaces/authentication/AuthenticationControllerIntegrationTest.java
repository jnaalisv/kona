package kona.web.interfaces.authentication;

import kona.web.interfaces.AbstractSpringRestMvcTest;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthenticationControllerIntegrationTest extends AbstractSpringRestMvcTest {

    @Test
    public void shouldAuthenticate() {

        CredentialsDTO credentials = new CredentialsDTO();
        credentials.username = "jnaalisv";
        credentials.password = "jnaalisv";

        String response = httpPost("/authenticate")
                .contentTypeApplicationJson()
                .acceptTextPlain()
                .content(credentials)
                .expect200()
                .responseBody();

        assertThat(response).isNotNull();
    }
}
