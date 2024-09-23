package cafexronace.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.Table;

import cafexronace.OrderApplication;
import lombok.Data;

@Entity
@Data
@Table(name = "Order_table")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long customerId;
    private String beverageName;
    private Integer beverageQty;
    private Date orderDate;
    private String orderStatus;

    @PostPersist
    public void onPostPersist() {
        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();
    }

    public static OrderRepository repository() {
        OrderRepository orderRepository = OrderApplication.applicationContext.getBean(
            OrderRepository.class
        );
        return orderRepository;
    }

    public void order() {
        //implement business logic here:

        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();
    }

    public static void cancelOrder(CupOutOfStock cupOutOfStock) {
        repository().findById(cupOutOfStock.getOrderId()).ifPresent(order -> {
            order.setOrderStatus("cancelled");
            repository().save(order);

            OrderCancelled orderCancelled = new OrderCancelled(order);
            orderCancelled.publishAfterCommit();
        });
    }
}
