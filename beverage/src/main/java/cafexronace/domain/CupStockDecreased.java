package cafexronace.domain;

import cafexronace.domain.*;
import cafexronace.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class CupStockDecreased extends AbstractEvent {

    private Long id;
    private String stockName;
    private Integer qty;
}
