package kona.web.interfaces.orderhandling;

import kona.model.domain.orderhandling.DeliveryOrder;
import kona.model.domain.orderhandling.OrderLine;
import kona.model.domain.product.ProductCode;

import java.util.List;
import java.util.stream.Collectors;

public class DeliveryOrderAssembler {

    public static DeliveryOrder assembleFrom(DeliveryOrderDTO deliveryOrderDTO) {

        List<OrderLine> orderLines = deliveryOrderDTO
                .orderLines
                .stream()
                .map(orderLineDTO -> new OrderLine(
                        orderLineDTO.id,
                        new ProductCode(orderLineDTO.productCode),
                        orderLineDTO.amount
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
                .map(orderLine -> new OrderLineDTO(orderLine.getId(), orderLine.getProductCode().toString(), orderLine.getAmount()))
                .collect(Collectors.toList());

        return deliveryOrderDTO;
    }
}
