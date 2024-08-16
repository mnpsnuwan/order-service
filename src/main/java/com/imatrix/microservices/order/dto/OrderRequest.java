package com.imatrix.microservices.order.dto;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * Project : OrderService
 * Class : {@link OrderRequest}
 *
 * @author : iMatrixLabs
 */
public record OrderRequest(Long id, String orderNumber, String skuCode, BigDecimal price, Integer quantity, UserDetails userDetails)
{
    public record UserDetails(String email, String firstName, String lastName){}
}
