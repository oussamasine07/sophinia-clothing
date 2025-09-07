package com.sophinia.backend.repository;

import com.sophinia.backend.dto.mappingDTO.OrderDetailsDTO;
import com.sophinia.backend.dto.mappingDTO.OrderWithClientDTO;
import com.sophinia.backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

//    @Query(
//            value = """
//                    select
//                        orders.id as order_id,
//                        products.name as product_name,
//                        products.image as product_image,
//                        products.description as product_description,
//                        clothing_models.name as clothing_model_name,
//                        clothing_models.image as clothing_model_image,
//                        decorations.name as decoration_name,
//                        decorations.image as decoration_image,
//                        designs.name as design_name,
//                        designs.image as design_image,
//                        clients.first_name,
//                        clients.last_name,
//                        clients.email,
//                        clients.phone,
//                        clients.address,
//                        clients.city,
//                        clients.postal_code
//                    from orders
//                        inner join products
//                    on orders.product_id = products.id
//                        inner join clothing_models\s
//                    on orders.clothing_model_id = clothing_models.id
//                        inner join decorations
//                    on orders.decoration_id = decorations.id
//                        inner join designs
//                    on orders.design_id = designs.id\s
//                        inner join clients
//                    on orders.client_id = clients.id
//                    where orders.id = :id;
//                        """,
//            nativeQuery = true
//    )
//    List<Object[]> getOrderDetails(@Param("id") Long id );

        @Query(
            value = """
                    select
                        orders.id as order_id,
                          products.name as product_name,
                          products.image as product_image,
                          products.description as product_description,
                        measurement_fields.id as field_id,
                          measurement_fields.name as field_name,
                          clothing_models.name as clothing_model_name,
                          clothing_models.image as clothing_model_image,
                          decorations.name as decoration_name,
                          decorations.image as decoration_image,
                          designs.name as design_name,
                          designs.image as design_image,
                          clients.first_name,
                          clients.last_name,
                          clients.email,
                          clients.phone,
                          clients.address,
                          clients.city,
                          clients.postal_code,
                        measuremet_sets.id as measurement_set
                      from orders
                        inner join products
                      on orders.product_id = products.id
                        inner join product_measurement_fields
                      on product_measurement_fields.product_id = products.id
                        inner join measurement_fields
                      on measurement_fields.id = product_measurement_fields.measurement_field_id
                        inner join measuremet_sets\s
                      on measuremet_sets.order_id = orders.id
                        inner join clothing_models\s
                      on orders.clothing_model_id = clothing_models.id
                        inner join decorations
                      on orders.decoration_id = decorations.id
                        inner join designs
                      on orders.design_id = designs.id\s
                        inner join clients
                      on orders.client_id = clients.id
                      where orders.id = :id;
                        """,
            nativeQuery = true
    )
    List<Object[]> getOrderDetails(@Param("id") Long id );

    @Query(
            value = """
                        select
                            measurement_fields.name as measurement_field_name,
                            measurement_values.value as measure_value
                        from measurement_values
                            inner join measuremet_sets
                        on measuremet_sets.id = measurement_values.measurement_set_id
                            inner join measurement_fields
                        on measurement_fields.id = measurement_values.measurement_field_id
                        where measurement_values.measurement_set_id = :id;
                    """,
            nativeQuery = true
    )
    List<Object[]> getMeaserementValues(@Param("id") Long id);

}
