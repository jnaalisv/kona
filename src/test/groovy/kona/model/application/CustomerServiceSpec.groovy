package kona.model.application

import kona.model.domain.customer.Customer
import kona.model.domain.customer.CustomerRepository
import spock.lang.Specification

class CustomerServiceSpec extends Specification {

    def customerRepositoryMock = Mock(CustomerRepository)
    def customerService = new CustomerService(customerRepositoryMock);
    def customer = new Customer()

    def "save should call add" () {

        when:
        customerService.save(customer)

        then:
        1 * customerRepositoryMock.add(customer)
        0 * _
    }

    def "find by null should call getAll" () {

        when:
        customerService.findBy(null)

        then:
        1 * customerRepositoryMock.getAll()
        0 * _
    }

    def "find by name should call findByName" () {

        when:
        customerService.findBy("Spooky")

        then:
        1 * customerRepositoryMock.findByName("Spooky")
        0 * _
    }
}
