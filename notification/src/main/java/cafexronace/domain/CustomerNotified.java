package cafexronace.domain;

import cafexronace.domain.*;
import cafexronace.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class CustomerNotified extends AbstractEvent {

    private Long id;
    private Long customerId;
    private Long orderId;
    private Date notificationDate;
    private String orderStatus;

    public CustomerNotified(Notification aggregate) {
        super(aggregate);
    }

    public CustomerNotified() {
        super();
    }
}
//>>> DDD / Domain Event
