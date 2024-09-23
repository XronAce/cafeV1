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
    Repository Repository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='CupOutOfStock'"
    )
    public void wheneverCupOutOfStock_CancelOrder(
        @Payload CupOutOfStock cupOutOfStock
    ) {
        CupOutOfStock event = cupOutOfStock;
        System.out.println(
            "\n\n##### listener CancelOrder : " + cupOutOfStock + "\n\n"
        );

        // Sample Logic //
        Order.cancelOrder(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
