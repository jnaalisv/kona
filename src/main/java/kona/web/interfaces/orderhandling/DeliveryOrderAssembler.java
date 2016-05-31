package kona.web.interfaces.orderhandling;

import java.util.List;
import java.util.stream.Collectors;

import kona.model.domain.orderhandling.DeliveryOrder;
import kona.model.domain.orderhandling.OrderLine;

public class DeliveryOrderAssembler {

    public static DeliveryOrder assembleFrom(DeliveryOrderDTO deliveryOrderDTO) {

        List<OrderLine> orderLines = deliveryOrderDTO
                .orderLines
                .stream()
                .map(orderLineDTO -> new OrderLine(
                        orderLineDTO.id,
                        orderLineDTO.productID,
                        orderLineDTO.amount,
                        null
                ))
                .collect(Collectors.toList());

        return new DeliveryOrder(deliveryOrderDTO.id, deliveryOrderDTO.payerID, orderLines);
    }

    public static DeliveryOrderDTO assembleTo(DeliveryOrder deliveryOrder) {

        DeliveryOrderDTO deliveryOrderDTO = new DeliveryOrderDTO();
        deliveryOrderDTO.id = deliveryOrder.getId();
        deliveryOrderDTO.payerID = deliveryOrder.getPayerID();
        deliveryOrderDTO.orderLines = deliveryOrder
                .getOrderLines()
                .stream()
                .map(orderLine -> new OrderLineDTO(orderLine.getId(), orderLine.getProductID(), orderLine.getAmount()))
                .collect(Collectors.toList());

        return deliveryOrderDTO;
    }
}
