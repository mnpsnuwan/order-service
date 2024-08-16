package com.imatrix.microservices.order.service;

import com.imatrix.microservices.order.client.InventoryClient;
import com.imatrix.microservices.order.dto.OrderRequest;
import com.imatrix.microservices.order.event.OrderPlacedEvent;
import com.imatrix.microservices.order.model.Order;
import com.imatrix.microservices.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : OrderService
 * Class : {@link OrderService}
 *
 * @author : iMatrixLabs
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService
{
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public void placeOrder( OrderRequest orderRequest )
    {
        // When we call the inventory service, once it disconnected Connection refuse error will occur. We need to resolve it by mocking the response then it can to minimize the cost.
        // 1. Using Mockito
        // 2. Using Wiremock
        var isProductInStock = inventoryClient.isInStock( orderRequest.skuCode(), orderRequest.quantity() );

        if( isProductInStock )
        {
            // Map OrderRequest to Order object
            Order order = new Order();
            order.setOrderNumber( UUID.randomUUID().toString() );
            order.setSkuCode( orderRequest.skuCode() );
            order.setPrice( orderRequest.price() );
            order.setQuantity( orderRequest.quantity() );

            // Save order to OrderRepository
            orderRepository.save( order );
            log.info( "Order placed successfully" );
            
            // Send the message to Kafka topic
            OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent(order.getOrderNumber(), orderRequest.userDetails().email());
            log.info( "Start - Sending OrderPlacedEvent {} to Kafka topic order-placed", orderPlacedEvent );
            kafkaTemplate.send("order-placed", orderPlacedEvent);
            log.info( "End - Sending OrderPlacedEvent {} to Kafka topic order-placed", orderPlacedEvent );
        }
        else
        {
            throw new RuntimeException( "Product with skuCode " + orderRequest.skuCode() + " is not in stock" );
        }
    }
}
