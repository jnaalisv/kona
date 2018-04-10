package kona.http.orderhandling;

import kona.model.application.PurchaseOrderService;
import kona.domain.orderhandling.PurchaseOrder;
import kona.http.KonaWebResources;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(KonaWebResources.PURCHASE_ORDERS)
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(final PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<PurchaseOrderDTO> getOrders() {
        return purchaseOrderService
                .getAll()
                .stream()
                .map(PurchaseOrderAssembler::assembleTo)
                .collect(Collectors.toList());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PurchaseOrderDTO> create(@RequestBody PurchaseOrderDTO purchaseOrderDTO) {

        PurchaseOrder purchaseOrder = PurchaseOrderAssembler.assembleFrom(purchaseOrderDTO);

        purchaseOrderService.saveNew(purchaseOrder);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Location", "/purchase-orders/" + purchaseOrder.getId());
        return new ResponseEntity<>(PurchaseOrderAssembler.assembleTo(purchaseOrder), responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{purchaseOrderId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PurchaseOrderDTO update(@PathVariable long purchaseOrderId, @RequestBody PurchaseOrderDTO purchaseOrderDTO) {
        PurchaseOrder purchaseOrder = PurchaseOrderAssembler.assembleFrom(purchaseOrderDTO);

        purchaseOrderService.update(purchaseOrder);

        return PurchaseOrderAssembler.assembleTo(purchaseOrder);
    }

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PurchaseOrderDTO get(@PathVariable long id) {
        return PurchaseOrderAssembler.assembleTo(purchaseOrderService.loadBy(id));
    }
}
