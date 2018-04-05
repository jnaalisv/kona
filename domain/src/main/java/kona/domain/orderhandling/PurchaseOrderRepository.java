package kona.domain.orderhandling;

import java.util.List;

public interface PurchaseOrderRepository {

    void add(PurchaseOrder purchaseOrder);

    PurchaseOrder get(long id);

    List<PurchaseOrder> getAll();

    void update(PurchaseOrder purchaseOrder);
}
