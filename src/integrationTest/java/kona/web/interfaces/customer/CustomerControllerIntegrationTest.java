package kona.web.interfaces.customer;

import static kona.web.authentication.PreAuthTokenFilter.X_AUTH_TOKEN_HEADERNAME;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import kona.web.interfaces.AbstractSpringRestMvcTest;
import kona.web.interfaces.KonaWebResources;

public class CustomerControllerIntegrationTest extends AbstractSpringRestMvcTest {

    @Test
    public void shouldPostACustomer() {

        CustomerDTO aNewCustomer = new CustomerDTO();
        aNewCustomer.name = "Mr. Murdoch";

        CustomerDTO aPostedCustomer = httpPost("/customers")
                .contentTypeApplicationJson()
                .content(aNewCustomer)
                .header(X_AUTH_TOKEN_HEADERNAME, someUserAuthToken)
                .expect201()
                .responseBodyAs(CustomerDTO.class);

        assertThat(aPostedCustomer.id).isNotNull();
    }

    @Test
    public void shouldPostAndGetCustomers() {

        CustomerDTO aNewCustomer = new CustomerDTO();
        aNewCustomer.name = "Mr. Murdoch";

        httpPost(KonaWebResources.CUSTOMERS)
                .contentTypeApplicationJson()
                .content(aNewCustomer)
                .header(X_AUTH_TOKEN_HEADERNAME, someUserAuthToken)
                .expect201();


        aNewCustomer.name = "Mr. Smith";

        httpPost(KonaWebResources.CUSTOMERS)
                .contentTypeApplicationJson()
                .content(aNewCustomer)
                .header(X_AUTH_TOKEN_HEADERNAME, someUserAuthToken)
                .expect201()
                .responseBodyAs(CustomerDTO.class);

        List<CustomerDTO> postedCustomers = httpGet(KonaWebResources.CUSTOMERS)
                .acceptApplicationJson()
                .header(X_AUTH_TOKEN_HEADERNAME, someUserAuthToken)
                .expect200()
                .responseBodyAsListOf(CustomerDTO.class);

        assertThat(postedCustomers.size()).isEqualTo(2);

        assertThat(postedCustomers.stream().map(customer -> customer.name).collect(Collectors.toList())).contains("Mr. Murdoch");
        assertThat(postedCustomers.stream().map(customer -> customer.name).collect(Collectors.toList())).contains("Mr. Smith");

        CustomerDTO postedCustomer = httpGet(KonaWebResources.CUSTOMERS + "/{id}", postedCustomers.get(0).id)
                .acceptApplicationJson()
                .header(X_AUTH_TOKEN_HEADERNAME, someUserAuthToken)
                .expect200()
                .responseBodyAs(CustomerDTO.class);

        assertThat(postedCustomer.id).isEqualTo(postedCustomers.get(0).id);
    }
}
