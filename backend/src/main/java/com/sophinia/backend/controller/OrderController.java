package com.sophinia.backend.controller;


import com.sophinia.backend.dto.validation.MeasurementsValuesDTO;
import com.sophinia.backend.dto.validation.OrderValidationDTO;
import com.sophinia.backend.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> getOrders () {
        return orderService.getOrders();
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<?> orderDetails (@PathVariable Long id) {
        return orderService.getOrderDetails( id );
    }

    @PostMapping("/place-order")
    public ResponseEntity<?> placeAnOrder ( @Valid @RequestBody OrderValidationDTO orderValidationDTO ) {
        return orderService.makeOrder( orderValidationDTO );
    }

    @GetMapping("get-orders-with-clients")
    public ResponseEntity<?> getOrdersWithCients () {
        return orderService.getOrdersWithClients();
    }

    @PostMapping("/set-measures")
    public ResponseEntity<?> takeMeasures ( @Valid @RequestBody MeasurementsValuesDTO measurementsValuesDTO ) {
        return orderService.setMeasures( measurementsValuesDTO );
    }

}
