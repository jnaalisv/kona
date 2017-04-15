import {
    httpGET,
    httpDELETE,
    httpPUT,
    httpPOST
} from './http'

const customersUrl = 'http://localhost:9999/kona/customers';

export function saveCustomer(customer) {
    if (customer.id > 0) {
        return httpPUT(`${customersUrl}/${customer.id}`, customer);
    } else {
        return httpPOST(customersUrl, customer);
    }
}

export function getCustomers(name) {

    const queryParams = {};

    if (name) {
        queryParams.name = name;
    }

    return httpGET(customersUrl, queryParams);
}

export function deleteCustomer(customerId) {
    return httpDELETE(`${customersUrl}/${customerId}`)
}

export function getCustomer(customerId) {
    return httpGET(`${customersUrl}/${customerId}`);
}

