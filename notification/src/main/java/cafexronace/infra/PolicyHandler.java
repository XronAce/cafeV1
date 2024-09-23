package cafexronace.infra;

import cafexronace.config.kafka.KafkaProcessor;
import cafexronace.domain.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    NotificationRepository notificationRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='BeverageMade'"
    )
    public void wheneverBeverageMade_NotifyCustomer(
        @Payload BeverageMade beverageMade
    ) {
        BeverageMade event = beverageMade;
        System.out.println(
            "\n\n##### listener NotifyCustomer : " + beverageMade + "\n\n"
        );

        // Sample Logic //
        Notification.notifyCustomer(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
