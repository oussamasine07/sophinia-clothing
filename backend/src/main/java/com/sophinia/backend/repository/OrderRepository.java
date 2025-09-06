package com.sophinia.backend.repository;

import com.sophinia.backend.dto.mappingDTO.OrderWithClientDTO;
import com.sophinia.backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = """
            select
            	orders.id as order_id,
                order_status.status,
                clients.first_name,
                clients.last_name,
                products.name as product_name
            from orders
            	inner join order_status
            on orders.order_statuse_id = order_status.id
                inner join clients
            on orders.client_id = clients.id
            	inner join products
            on orders.product_id = products.id;
        """, nativeQuery = true)
    List<OrderWithClientDTO> getOrdersWithClient ();

    

}
