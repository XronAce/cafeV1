package cafexronace.domain;

import cafexronace.domain.*;
import cafexronace.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class CupStockDecreased extends AbstractEvent {

    private Long id;
    private Long orderId;
    private Long customerId;
    private String beverageName;
    private String stockName;
    private Integer qty;
    private Integer beverageQty;
}
