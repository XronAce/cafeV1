package cafexronace.domain;

import cafexronace.BeverageApplication;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Beverage_table")
@Data
//<<< DDD / Aggregate Root
public class Beverage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long customerId;

    private Long orderId;

    private String beverageName;

    private Integer beverageQty;

    public static BeverageRepository repository() {
        BeverageRepository beverageRepository = BeverageApplication.applicationContext.getBean(
            BeverageRepository.class
        );
        return beverageRepository;
    }

    //<<< Clean Arch / Port Method
    public static void makeBeverage(CupStockDecreased cupStockDecreased) {
        //implement business logic here:

        Beverage beverage = new Beverage();
        beverage.setCustomerId(cupStockDecreased.getCustomerId());
        beverage.setOrderId(cupStockDecreased.getOrderId());
        beverage.setBeverageName(cupStockDecreased.getBeverageName());
        beverage.setBeverageQty(cupStockDecreased.getBeverageQty());
        repository().save(beverage);

        BeverageMade beverageMade = new BeverageMade(beverage);
        beverageMade.publishAfterCommit();

        /** Example 2:  finding and process
        
        repository().findById(cupStockDecreased.get???()).ifPresent(beverage->{
            
            beverage // do something
            repository().save(beverage);

            BeverageMade beverageMade = new BeverageMade(beverage);
            beverageMade.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
