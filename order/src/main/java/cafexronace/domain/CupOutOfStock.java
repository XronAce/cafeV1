package cafexronace.domain;

import cafexronace.domain.*;
import cafexronace.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class CupOutOfStock extends AbstractEvent {

    private Long id;
    private Long orderId;
    private String stockName;
    private Integer qty;
}
