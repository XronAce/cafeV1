package cafexronace.domain;

import cafexronace.NotificationApplication;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Notification_table")
@Data
//<<< DDD / Aggregate Root
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long customerId;

    private Long orderId;

    private Date notificationDate;

    private String orderStatus;

    public static NotificationRepository repository() {
        NotificationRepository notificationRepository = NotificationApplication.applicationContext.getBean(
            NotificationRepository.class
        );
        return notificationRepository;
    }

    //<<< Clean Arch / Port Method
    public static void notifyCustomer(BeverageMade beverageMade) {
        //implement business logic here:

        /** Example 1:  new item 
        Notification notification = new Notification();
        repository().save(notification);

        CustomerNotified customerNotified = new CustomerNotified(notification);
        customerNotified.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(beverageMade.get???()).ifPresent(notification->{
            
            notification // do something
            repository().save(notification);

            CustomerNotified customerNotified = new CustomerNotified(notification);
            customerNotified.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void notifyCustomer(OrderCancelled orderCancelled) {
        //implement business logic here:

        Notification notification = new Notification();
        notification.setCustomerId(orderCancelled.getCustomerId());
        notification.setOrderId(orderCancelled.getId());
        notification.setNotificationDate(new Date());
        notification.setOrderStatus("cancelled");
        repository().save(notification);

        CustomerNotified customerNotified = new CustomerNotified(notification);
        customerNotified.publishAfterCommit();

        /** Example 2:  finding and process
        
        repository().findById(orderCanceled.get???()).ifPresent(notification->{
            
            notification // do something
            repository().save(notification);

            CustomerNotified customerNotified = new CustomerNotified(notification);
            customerNotified.publishAfterCommit();

         });
        */

    }
}