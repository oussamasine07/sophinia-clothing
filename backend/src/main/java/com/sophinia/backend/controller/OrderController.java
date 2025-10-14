package com.sophinia.backend.controller;


import com.sophinia.backend.dto.response.OrderWithClientDTO;
import com.sophinia.backend.dto.request.MeasurementsValuesDTO;
import com.sophinia.backend.dto.request.OrderValidationDTO;
import com.sophinia.backend.model.Order;
import com.sophinia.backend.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController (
            final OrderService orderService
    ) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrders () {
        return orderService.getOrders();
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Map<String, Object>> orderDetails (@PathVariable Long id) {
        return orderService.getOrderDetails( id );
    }

    @PostMapping("/place-order")
    public ResponseEntity<Order> placeAnOrder (@Valid @RequestBody OrderValidationDTO orderValidationDTO ) {
        return orderService.makeOrder( orderValidationDTO );
    }

    @GetMapping("get-orders-with-clients")
    public ResponseEntity<List<OrderWithClientDTO>> getOrdersWithCients () {
        return orderService.getOrdersWithClients();
    }

    @PostMapping("/set-measures")
    public ResponseEntity<Map<String, Object>> takeMeasures ( @Valid @RequestBody MeasurementsValuesDTO measurementsValuesDTO ) {
        return orderService.setMeasures( measurementsValuesDTO );
    }

}
