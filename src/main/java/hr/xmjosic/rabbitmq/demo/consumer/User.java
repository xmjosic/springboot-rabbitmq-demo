package hr.xmjosic.rabbitmq.demo.consumer;

import hr.xmjosic.rabbitmq.demo.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class User {

    private static final String queue = "${spring.rabbitmq.demo.queue}";

    @RabbitListener(queues = queue)
    public void consumeMessageFromQueue(OrderStatus orderStatus) {
        System.out.println("Message received from queue : " + orderStatus);
    }

}
