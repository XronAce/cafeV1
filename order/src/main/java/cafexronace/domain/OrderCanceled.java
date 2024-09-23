package cafexronace.domain;

import cafexronace.domain.*;
import cafexronace.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class OrderCanceled extends AbstractEvent {

    private Long id;
    private Long customerId;
    private String beverageName;
    private Integer beverageQty;
    private Date orderDate;
    private String orderStatus;

    public OrderCanceled(Order aggregate) {
        super(aggregate);
    }

    public OrderCanceled() {
        super();
    }
}
//>>> DDD / Domain Event
