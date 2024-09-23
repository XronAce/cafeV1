package cafexronace.domain;

import cafexronace.domain.*;
import cafexronace.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class CupOutOfStock extends AbstractEvent {

    private Long id;
    private Long orderId;
    private String beverageName;
    private Long customerId;
    private String stockName;
    private Integer qty;
    private Integer beverageQty;

    public CupOutOfStock(Inventory aggregate) {
        super(aggregate);
    }

    public CupOutOfStock() {
        super();
    }
}
//>>> DDD / Domain Event
