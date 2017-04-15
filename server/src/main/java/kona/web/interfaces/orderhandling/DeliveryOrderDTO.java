package kona.web.interfaces.orderhandling;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DeliveryOrderDTO {
    public long id;
    public LocalDateTime createTime;
    public long version;
    public long ordererID;
    public List<OrderLineDTO> orderLines = new ArrayList<>();

    public DeliveryOrderDTO() {}
}
