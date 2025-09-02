package com.sophinia.backend.controller;


import com.sophinia.backend.dto.validation.OrderValidationDTO;
import com.sophinia.backend.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController (
            final OrderService orderService
    ) {
        this.orderService = orderService;
    }

    @PostMapping("/place-order")
    public ResponseEntity<?> placeAnOrder ( @Valid @RequestBody OrderValidationDTO orderValidationDTO ) {
        return orderService.makeOrder( orderValidationDTO );
    }

}
