package com.imatrix.microservices.order.service;

import com.imatrix.microservices.order.dto.OrderRequest;
import com.imatrix.microservices.order.model.Order;
import com.imatrix.microservices.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : ProductService
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

    public void placeOrder( OrderRequest orderRequest )
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
    }
}
