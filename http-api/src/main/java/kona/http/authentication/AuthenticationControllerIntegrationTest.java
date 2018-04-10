package kona.http.authentication;

import kona.web.interfaces.AbstractSpringRestMvcTest;
import org.junit.Test;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@Sql({"classpath:init-database.sql"})
public class AuthenticationControllerIntegrationTest extends AbstractSpringRestMvcTest {

    @Test
    public void validCredentialsShouldAuthenticate() {

        CredentialsDTO credentials = new CredentialsDTO();
        credentials.username = "admin";
        credentials.password = "admin";

        TokenDTO response = httpPost("/authenticate")
                .contentTypeApplicationJson()
                .acceptApplicationJson()
                .content(credentials)
                .expect200()
                .responseBodyAs(TokenDTO.class);

        assertThat(response.token).isNotNull();
    }

    @Test
    public void badCredentialsShouldNotAuthenticate() {

        CredentialsDTO credentials = new CredentialsDTO();
        credentials.username = "admin";
        credentials.password = "notCorrectPassword";

        httpPost("/authenticate")
                .contentTypeApplicationJson()
                .acceptApplicationJson()
                .content(credentials)
                .expect401()
                .responseBody()
                .contains("Bad credentials");
    }
}
