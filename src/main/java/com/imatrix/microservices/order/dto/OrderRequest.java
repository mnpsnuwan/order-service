package com.imatrix.microservices.order.dto;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * Project : SpotHub
 * Class : {@link OrderRequest}
 *
 * @author : iMatrixLabs
 */
public record OrderRequest(Long id, String orderNumber, String skuCode, BigDecimal price, Integer quantity)
{
}
