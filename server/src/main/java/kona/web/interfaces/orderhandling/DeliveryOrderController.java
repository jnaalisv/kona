package kona.web.interfaces.orderhandling;

import kona.model.application.OrderHandlingService;
import kona.model.domain.orderhandling.DeliveryOrder;
import kona.web.interfaces.KonaWebResources;
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
@RequestMapping(KonaWebResources.DELIVERY_ORDERS)
public class DeliveryOrderController {

    private final OrderHandlingService orderHandlingService;

    public DeliveryOrderController(final OrderHandlingService orderHandlingService) {
        this.orderHandlingService = orderHandlingService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<DeliveryOrderDTO> getOrders() {
        return orderHandlingService
                .getAll()
                .stream()
                .map(DeliveryOrderAssembler::assembleTo)
                .collect(Collectors.toList());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<DeliveryOrderDTO> createANewDeliveryOrder(@RequestBody DeliveryOrderDTO deliveryOrderDTO) {

        DeliveryOrder deliveryOrder = DeliveryOrderAssembler.assembleFrom(deliveryOrderDTO);

        orderHandlingService.saveNew(deliveryOrder);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Location", "/delivery-orders/" + deliveryOrder.getId());
        return new ResponseEntity<>(DeliveryOrderAssembler.assembleTo(deliveryOrder), responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{deliveryOrderId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public DeliveryOrderDTO updateOrder(@PathVariable long deliveryOrderId, @RequestBody DeliveryOrderDTO deliveryOrderDTO) {
        DeliveryOrder deliveryOrder = DeliveryOrderAssembler.assembleFrom(deliveryOrderDTO);

        orderHandlingService.update(deliveryOrder);

        return DeliveryOrderAssembler.assembleTo(deliveryOrder);
    }

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public DeliveryOrderDTO get(@PathVariable long id) {
        return DeliveryOrderAssembler.assembleTo(orderHandlingService.loadBy(id));
    }
}
