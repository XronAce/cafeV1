package cafexronace.domain;

import cafexronace.domain.*;
import cafexronace.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class BeverageMade extends AbstractEvent {

    private Long id;
    private Long customerId;
    private Long orderId;
    private String beverageName;
    private Integer beverageQty;
}
