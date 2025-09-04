package com.sophinia.backend.service;


import com.sophinia.backend.dto.validation.AvailabilityValidationDTO;
import com.sophinia.backend.dto.validation.OrderValidationDTO;
import com.sophinia.backend.model.*;
import com.sophinia.backend.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final MeasurementSetRepository measurementSetRepository;
    private final ClientRepository clientRepository;
    private final AvailabilityRepository availabilityRepository;

    private final ClientService clientService;
    private final ProductService productService;
    private final ClothingModelService clothingModelService;
    private final FabricService fabricService;
    private final DecorationService decorationService;
    private final DesignService designService;

    public OrderService (
            final OrderRepository orderRepository,
            final OrderStatusRepository orderStatusRepository,
            final MeasurementSetRepository measurementSetRepository,
            ClientRepository clientRepository,
            final AvailabilityRepository availabilityRepository,

            final ClientService clientService,
            final ProductService productService,
            final ClothingModelService clothingModelService,
            final FabricService fabricService,
            final DecorationService decorationService,
            final DesignService designService
    ) {
        this.orderRepository = orderRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.measurementSetRepository = measurementSetRepository;
        this.clientRepository = clientRepository;
        this.availabilityRepository = availabilityRepository;

        this.clientService = clientService;
        this.productService = productService;
        this.clothingModelService = clothingModelService;
        this.fabricService = fabricService;
        this.decorationService = decorationService;
        this.designService = designService;
    }

    public ResponseEntity<?> makeOrder (OrderValidationDTO orderValidationDTO) {

        // make client login first
        // check if client exists by email
        Client foundClient = clientRepository.findClientByEmail(orderValidationDTO.client().email());

        Client savedCliennt = null;
        if ( foundClient != null) {
            Client newClient = new Client();
            newClient.setFirstName( orderValidationDTO.client().firstName() );
            newClient.setLastName( orderValidationDTO.client().lastName() );
            newClient.setEmail( orderValidationDTO.client().email() );
            newClient.setPhone( orderValidationDTO.client().phone() );

            savedCliennt = clientRepository.save( newClient );
        }


        Client client = foundClient != null ? foundClient : savedCliennt;

        Order order = new Order();

        order.setOrderDate(LocalDate.now());

        Optional<OrderStatus> orderStatus = this.orderStatusRepository.findById(1L);
        order.setOrderStatuse( orderStatus.get() );

        Product product = (Product) productService.getProductById( orderValidationDTO.productId() ).getBody();
        order.setProduct( product );

        Decoration decoration = (Decoration) decorationService.getDecorationById( orderValidationDTO.decorationId() ).getBody();
        order.setDecoration( decoration );

        Fabric fabric = (Fabric) fabricService.getFabricById( orderValidationDTO.fabricId() ).getBody();
        order.setFabric( fabric );

        ClothingModel clothingModel = (ClothingModel) clothingModelService.getClothingModel( orderValidationDTO.clothingModelId() ).getBody();
        order.setClothingModel( clothingModel );

        Design design = (Design) designService.getDesignById( orderValidationDTO.designId() ).getBody();
        order.setDesign( design );

        order.setClient( client );

        Availability availability = new Availability();
        availability.setStartDate( orderValidationDTO.availability().startDate() );
        availability.setEndDate( orderValidationDTO.availability().endDate() );
        availability.setAvailabilityType( orderValidationDTO.availability().availabilityType() );

        Availability savedAvailability = availabilityRepository.save( availability );

        order.setAvialability( savedAvailability );

        // save the order
        Order savedOrder = orderRepository.save( order );

        // create a measurement set and save it
        MeasurementSet measurementSet = new MeasurementSet();
        measurementSet.setProduct( product );
        measurementSet.setOrder( savedOrder );
        measurementSetRepository.save( measurementSet );

        return new ResponseEntity(savedOrder, HttpStatus.OK);

//        return null;
    }

    public ResponseEntity<?> getOrders () {
        List<Order> orders = orderRepository.findAll();

        return new ResponseEntity<>(orders, HttpStatus.OK);

    }

}


























