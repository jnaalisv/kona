package kona.web.interfaces.authentication;

import kona.web.interfaces.AbstractSpringRestMvcTest;
import org.junit.Test;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@Sql({"classpath:init-database.sql", "classpath:users.sql"})
public class AuthenticationControllerIntegrationTest extends AbstractSpringRestMvcTest {

    @Test
    public void validCredentialsShouldAuthenticate() {

        CredentialsDTO credentials = new CredentialsDTO();
        credentials.username = "admin";
        credentials.password = "admin";

        String response = httpPost("/authenticate")
                .contentTypeApplicationJson()
                .acceptTextPlain()
                .content(credentials)
                .expect200()
                .responseBody();

        assertThat(response).isNotNull();
    }

    @Test
    public void badCredentialsShouldNotAuthenticate() {

        CredentialsDTO credentials = new CredentialsDTO();
        credentials.username = "admin";
        credentials.password = "notCorrectPassword";

        httpPost("/authenticate")
                .contentTypeApplicationJson()
                .acceptTextPlain()
                .content(credentials)
                .expect401()
                .responseBody()
                .contains("Bad credentials");
    }
}
