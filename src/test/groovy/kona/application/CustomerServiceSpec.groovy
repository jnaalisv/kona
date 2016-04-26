package kona.application

import kona.domain.customer.CustomerRepository
import spock.lang.Specification

class CustomerServiceSpec extends Specification {

    def customerRepositoryMock = Mock(CustomerRepository)
    def customerService = new CustomerService(customerRepositoryMock);

    def "loadAll should call getAll" () {

        when:
        customerService.loadAll()

        then:
        1 * customerRepositoryMock.getAll()
        0 * _
    }

    def "load should call get with id" () {

        given:
        long customerID = 124l

        when:
        customerService.load(customerID)

        then:
        1 * customerRepositoryMock.get(customerID)
        0 * _
    }
}
