package cafexronace.domain;

import cafexronace.domain.*;
import cafexronace.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class OrderCanceled extends AbstractEvent {

    private Long id;
    private Long customerId;
    private String beverageName;
    private Integer beverageQty;
    private Date orderDate;
    private String orderStatus;
}
