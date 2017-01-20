import http from '../http'

const ordersUrl = 'http://localhost:9999/kona/delivery-orders';

export function saveOrder(order) {
    if (order.id > 0) {
        return http.PUT(`${ordersUrl}/${order.id}`, order);
    } else {
        return http.POST(ordersUrl, order);
    }
}

export function getOrders(name) {

    const queryParams = {};

    if (name) {
        queryParams.name = name;
    }

    return http.GET(ordersUrl, queryParams);
}

export function deleteOrder(orderId) {
    return http.DELETE(`${ordersUrl}/${orderId}`)
}

export function getOrder(orderId) {
    return http.GET(`${ordersUrl}/${orderId}`);
}
