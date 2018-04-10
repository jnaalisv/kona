package kona.application.services;

import kona.domain.orderhandling.PurchaseOrder;
import kona.domain.orderhandling.PurchaseOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;

    public PurchaseOrderService(final PurchaseOrderRepository purchaseOrderRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    @Transactional
    public void saveNew(PurchaseOrder purchaseOrder) {
        this.purchaseOrderRepository.add(purchaseOrder);
    }

    @Transactional(readOnly = true)    public PurchaseOrder loadBy(long id) {
        return purchaseOrderRepository.get(id);
    }

    @Transactional(readOnly = true)
    public List<PurchaseOrder> getAll() {
        return purchaseOrderRepository.getAll();
    }

    @Transactional
    public void update(PurchaseOrder purchaseOrder) {
        purchaseOrderRepository.update(purchaseOrder);
    }
}
