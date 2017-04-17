package kona.web.interfaces.orderhandling;

import kona.model.domain.orderhandling.PurchaseOrder;
import kona.model.domain.orderhandling.OrderLine;
import kona.model.domain.product.ProductCode;

import java.util.List;
import java.util.stream.Collectors;

public class PurchaseOrderAssembler {

    public static PurchaseOrder assembleFrom(PurchaseOrderDTO purchaseOrderDTO) {

        List<OrderLine> orderLines = purchaseOrderDTO
                .orderLines
                .stream()
                .map(orderLineDTO -> new OrderLine(
                        orderLineDTO.id,
                        orderLineDTO.productId,
                        new ProductCode(orderLineDTO.productCode),
                        orderLineDTO.amount
                ))
                .collect(Collectors.toList());

        PurchaseOrder purchaseOrder = new PurchaseOrder(purchaseOrderDTO.id, purchaseOrderDTO.ordererID, orderLines);

        purchaseOrder.setVersion(purchaseOrderDTO.version);
        purchaseOrder.setCreateTime(purchaseOrderDTO.createTime);

        return purchaseOrder;
    }

    public static PurchaseOrderDTO assembleTo(PurchaseOrder purchaseOrder) {

        PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO();
        purchaseOrderDTO.id = purchaseOrder.getId();
        purchaseOrderDTO.ordererID = purchaseOrder.getOrdererID();
        purchaseOrderDTO.version = purchaseOrder.getVersion();
        purchaseOrderDTO.createTime = purchaseOrder.getCreateTime();
        purchaseOrderDTO.orderLines = purchaseOrder
                .getOrderLines()
                .stream()
                .map(orderLine -> new OrderLineDTO(orderLine.getId(), orderLine.getProductId(), orderLine.getAmount(), orderLine.getProductCode().toString()))
                .collect(Collectors.toList());

        return purchaseOrderDTO;
    }
}
