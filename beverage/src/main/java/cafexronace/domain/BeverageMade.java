package cafexronace.domain;

import cafexronace.domain.*;
import cafexronace.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class BeverageMade extends AbstractEvent {

    private Long id;
    private Long customerId;
    private Long orderId;
    private String beverageName;
    private Integer beverageQty;

    public BeverageMade(Beverage aggregate) {
        super(aggregate);
    }

    public BeverageMade() {
        super();
    }
}
//>>> DDD / Domain Event
