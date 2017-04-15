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

        DeliveryOrder deliveryOrder = new DeliveryOrder(deliveryOrderDTO.id, deliveryOrderDTO.ordererID, orderLines);

        deliveryOrder.setVersion(deliveryOrderDTO.version);
        deliveryOrder.setCreateTime(deliveryOrderDTO.createTime);

        return deliveryOrder;
    }

    public static DeliveryOrderDTO assembleTo(DeliveryOrder deliveryOrder) {

        DeliveryOrderDTO deliveryOrderDTO = new DeliveryOrderDTO();
        deliveryOrderDTO.id = deliveryOrder.getId();
        deliveryOrderDTO.ordererID = deliveryOrder.getOrdererID();
        deliveryOrderDTO.version = deliveryOrder.getVersion();
        deliveryOrderDTO.createTime = deliveryOrder.getCreateTime();
        deliveryOrderDTO.orderLines = deliveryOrder
                .getOrderLines()
                .stream()
                .map(orderLine -> new OrderLineDTO(orderLine.getId(), orderLine.getProductCode().toString(), orderLine.getAmount()))
                .collect(Collectors.toList());

        return deliveryOrderDTO;
    }
}
