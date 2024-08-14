package com.imatrix.microservices.order.repository;

import com.imatrix.microservices.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 * Project : OrderService
 * Class : {@link OrderRepository}
 *
 * @author : iMatrixLabs
 */
public interface OrderRepository extends JpaRepository<Order,Long>
{
}
