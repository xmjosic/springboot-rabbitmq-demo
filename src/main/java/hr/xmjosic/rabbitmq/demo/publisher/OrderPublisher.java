package hr.xmjosic.rabbitmq.demo.publisher;

import hr.xmjosic.rabbitmq.demo.config.MessagingConfig;
import hr.xmjosic.rabbitmq.demo.dto.Order;
import hr.xmjosic.rabbitmq.demo.dto.OrderStatus;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/order")
@AllArgsConstructor
public class OrderPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final MessagingConfig messagingConfig;

    @PostMapping("/{storeName}")
    public String orderSomething(@RequestBody Order order, @PathVariable String storeName) {
        order.setOrderId(UUID.randomUUID().toString());
        OrderStatus orderStatus = new OrderStatus(order, "PROCESS", "Order placed successfully in " + storeName);
        rabbitTemplate.convertAndSend(messagingConfig.getExchange(), messagingConfig.getRoutingKey(), orderStatus);
        return "Success!";
    }

}