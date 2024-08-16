package com.imatrix.microservices.order.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * Project : OrderService
 * Class : {@link OrderPlacedEvent}
 *
 * @author : Nuwan Samarasinghe
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPlacedEvent
{
    private String orderNumber;
    private String email;
}
