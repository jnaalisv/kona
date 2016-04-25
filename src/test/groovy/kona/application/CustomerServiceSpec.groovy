package kona.application

import kona.domain.customer.CustomerRepository
import spock.lang.Specification

class CustomerServiceSpec extends Specification {

    def "loadAll should call getAll" () {

        def customerRepositoryMock = Mock(CustomerRepository)
        def customerService = new CustomerService(customerRepositoryMock);

        when:
        customerService.loadAll()

        then:
        1 * customerRepositoryMock.getAll()
    }
}
