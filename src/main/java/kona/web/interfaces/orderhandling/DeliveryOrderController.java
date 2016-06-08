package kona.web.interfaces.orderhandling;

import kona.model.application.OrderHandlingService;
import kona.model.domain.orderhandling.DeliveryOrder;
import kona.web.interfaces.KonaWebResources;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
@RequestMapping(KonaWebResources.DELIVERY_ORDERS)
public class DeliveryOrderController {

    private final OrderHandlingService orderHandlingService;

    @Inject
    public DeliveryOrderController(final OrderHandlingService orderHandlingService) {
        this.orderHandlingService = orderHandlingService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<DeliveryOrderDTO> createANewDeliveryOrder(@RequestBody DeliveryOrderDTO deliveryOrderDTO) {

        DeliveryOrder deliveryOrder = DeliveryOrderAssembler.assembleFrom(deliveryOrderDTO);

        orderHandlingService.saveNew(deliveryOrder);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Location", "/delivery-orders/" + deliveryOrder.getId());
        return new ResponseEntity<>(DeliveryOrderAssembler.assembleTo(deliveryOrder), responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public DeliveryOrderDTO get(@PathVariable long id) {
        return DeliveryOrderAssembler.assembleTo(orderHandlingService.loadBy(id));
    }
}
