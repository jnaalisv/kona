import { httpGET, httpDELETE, httpPOST, httpPUT } from './http'

const ordersUrl = 'http://localhost:9999/kona/delivery-orders';

export function saveOrder(order) {
    if (order.id > 0) {
        return httpPUT(`${ordersUrl}/${order.id}`, order);
    } else {
        return httpPOST(ordersUrl, order);
    }
}

export function getOrders(name) {

    const queryParams = {};

    if (name) {
        queryParams.name = name;
    }

    return httpGET(ordersUrl, queryParams);
}

export function deleteOrder(orderId) {
    return httpDELETE(`${ordersUrl}/${orderId}`)
}

export function getOrder(orderId) {
    return httpGET(`${ordersUrl}/${orderId}`);
}
