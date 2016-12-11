import http from '../http'

const customersUrl = 'http://localhost:9999/kona/customers';

export function saveCustomer(customer) {
    if (customer.id > 0) {
        return http.PUT(`${customersUrl}/${customer.id}`, customer);
    } else {
        return http.POST(customersUrl, customer);
    }
}

export function getCustomers(name) {

    const queryParams = {};

    if (name) {
        queryParams.name = name;
    }

    return http.GET(customersUrl, {queryParams});
}

export function deleteCustomer(customerId) {
    return http.DELETE(`${customersUrl}/${customerId}`, customerId)
}

export function getCustomer(customerId) {
    return http.GET(`${customersUrl}/${customerId}`);
}

