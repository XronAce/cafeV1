package cafexronace.domain;

import cafexronace.InventoryApplication;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Inventory_table")
@Data
//<<< DDD / Aggregate Root
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    private String stockName;

    private Integer qty;

    public static InventoryRepository repository() {
        InventoryRepository inventoryRepository = InventoryApplication.applicationContext.getBean(
            InventoryRepository.class
        );
        return inventoryRepository;
    }

    //<<< Clean Arch / Port Method
    public static void decreaseCupStock(OrderPlaced orderPlaced) {
        //implement business logic here:

        /** Example 1:  new item 
        Inventory inventory = new Inventory();
        repository().save(inventory);

        CupStockDecreased cupStockDecreased = new CupStockDecreased(inventory);
        cupStockDecreased.publishAfterCommit();
        CupOutOfStock cupOutOfStock = new CupOutOfStock(inventory);
        cupOutOfStock.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(inventory->{
            
            inventory // do something
            repository().save(inventory);

            CupStockDecreased cupStockDecreased = new CupStockDecreased(inventory);
            cupStockDecreased.publishAfterCommit();
            CupOutOfStock cupOutOfStock = new CupOutOfStock(inventory);
            cupOutOfStock.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
