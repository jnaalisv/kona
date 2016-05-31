package kona.web.interfaces.orderhandling;

import javax.inject.Inject;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kona.model.domain.orderhandling.DeliveryOrder;
import kona.model.domain.orderhandling.DeliveryOrderService;
import kona.web.interfaces.KonaWebResources;
import kona.web.interfaces.customer.CustomerDTO;

@RestController
@RequestMapping(KonaWebResources.DELIVERY_ORDERS)
public class DeliveryOrderController {

    private final DeliveryOrderService deliveryOrderService;

    @Inject
    public DeliveryOrderController(final DeliveryOrderService deliveryOrderService) {
        this.deliveryOrderService = deliveryOrderService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<DeliveryOrderDTO> createANewDeliveryOrder(@RequestBody DeliveryOrderDTO deliveryOrderDTO) {

        DeliveryOrder deliveryOrder = DeliveryOrderAssembler.assembleFrom(deliveryOrderDTO);

        deliveryOrderService.save(deliveryOrder);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Location", "/delivery-orders/" + deliveryOrder.getId());
        return new ResponseEntity<>(DeliveryOrderAssembler.assembleTo(deliveryOrder), responseHeaders, HttpStatus.CREATED);
    }

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public DeliveryOrderDTO get(@PathVariable long id) {
        return DeliveryOrderAssembler.assembleTo(deliveryOrderService.load(id));
    }
}
