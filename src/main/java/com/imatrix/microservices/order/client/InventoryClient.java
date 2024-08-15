package com.imatrix.microservices.order.client;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

/**
 * Created by IntelliJ IDEA.
 * Project : OrderService
 * Class : {@link InventoryClient}
 *
 * @author : iMatrixLabs
 */

public interface InventoryClient
{
    @GetExchange("api/inventory")
    boolean isInStock( @RequestParam String skuCode, @RequestParam Integer quantity );
}
