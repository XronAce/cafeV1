package cafexronace.domain;

import cafexronace.domain.*;
import cafexronace.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class CupStockDecreased extends AbstractEvent {

    private Long id;
    private Long orderId;
    private String stockName;
    private Integer qty;

    public CupStockDecreased(Inventory aggregate) {
        super(aggregate);
    }

    public CupStockDecreased() {
        super();
    }
}
//>>> DDD / Domain Event
