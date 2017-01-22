package kona.web.interfaces.orderhandling;

import java.util.ArrayList;
import java.util.List;

public class DeliveryOrderDTO {
    public long id;
    public long ordererID;
    public List<OrderLineDTO> orderLines = new ArrayList<>();

    public DeliveryOrderDTO() {}
}
