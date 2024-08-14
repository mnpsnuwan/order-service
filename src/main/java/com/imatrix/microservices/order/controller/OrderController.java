package com.imatrix.microservices.order.controller;

import com.imatrix.microservices.order.dto.OrderRequest;
import com.imatrix.microservices.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * Project : ProductService
 * Class : {@link OrderController}
 *
 * @author : iMatrixLabs
 */

@RestController
@RequestMapping( "/api/order" )
@RequiredArgsConstructor
public class OrderController
{
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus( HttpStatus.CREATED )
    public String placeOrder( @RequestBody OrderRequest orderRequest )
    {
        orderService.placeOrder( orderRequest );
        return "Order Placed Successfully";
    }
}
