package com.sophinia.backend.service;


import com.sophinia.backend.dto.mappingDTO.*;
import com.sophinia.backend.dto.validation.MeasurementsValuesDTO;
import com.sophinia.backend.dto.validation.OrderValidationDTO;
import com.sophinia.backend.exception.NotFoundException;
import com.sophinia.backend.model.*;
import com.sophinia.backend.repository.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final MeasurementSetRepository measurementSetRepository;
    private final ClientRepository clientRepository;
    private final AvailabilityRepository availabilityRepository;
    private final MeasurementFieldRepository measurementFieldRepository;
    private final MeasurementValueRepository measurementValueRepository;

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
            final ClientRepository clientRepository,
            final AvailabilityRepository availabilityRepository,
            final MeasurementFieldRepository measurementFieldRepository,
            final MeasurementValueRepository measurementValueRepository,

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
        this.measurementValueRepository = measurementValueRepository;

        this.clientService = clientService;
        this.productService = productService;
        this.clothingModelService = clothingModelService;
        this.fabricService = fabricService;
        this.decorationService = decorationService;
        this.designService = designService;
        this.measurementFieldRepository = measurementFieldRepository;
    }

    public ResponseEntity<Order> makeOrder (OrderValidationDTO orderValidationDTO) {

        // make client login first
        // check if client exists by email
        Client client = clientRepository.findClientByEmail(orderValidationDTO.client().email())
                .orElseGet(() -> {
                    Client c = new Client();
                    c.setFirstName(orderValidationDTO.client().firstName());
                    c.setLastName(orderValidationDTO.client().lastName());
                    c.setEmail(orderValidationDTO.client().email());
                    c.setPhone(orderValidationDTO.client().phone());

                    return clientRepository.save(c);
                });

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

    public ResponseEntity<List<Order>> getOrders () {
        List<Order> orders = orderRepository.findAll();

        return new ResponseEntity<>(orders, HttpStatus.OK);

    }

    public ResponseEntity<List<OrderWithClientDTO>> getOrdersWithClients () {
        List<OrderWithClientDTO> orders = orderRepository.getOrdersWithClient();

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    public ResponseEntity<Map<String, Object>> getOrderDetails (Long id) {

        List<Object[]> rows = orderRepository.getOrderDetails(id);

        if (rows.isEmpty()) {
            throw  new NotFoundException("this order not found");
        }

        Object[] row = rows.get(0);
        ProductDTO product = new ProductDTO((String) row[1], (String) row[2], (String) row[3]);
        List<OrderMeasurementFieldDTO> measurementFields = rows.stream()
                .map(r -> {

                    System.out.println("row 4 " + r[4]);
                    System.out.println("row 5 " + r[5]);

                    return new OrderMeasurementFieldDTO(
                            ((Number) r[4]).longValue(),
                            (String) r[5]
                    );
                })
                .distinct()
                .toList();

        product.setFields(measurementFields);

        OrderDetailsDTO orderDetails = new OrderDetailsDTO(
                ((Number) row[0]).longValue(),
                product,
                new ClothingModelDTO((String) row[6], (String) row[7]),
                new DecorationDTO((String) row[8], (String) row[9]),
                new DesignDTO((String) row[10], (String) row[11]),
                new ClientDTO(
                        (String) row[12],
                        (String) row[13],
                        (String) row[14],
                        (String) row[15],
                        (String) row[16],
                        (String) row[17],
                        (String) row[18]
                ),
                ((Number) row[19]).longValue()
        );

        List<Object[]> measurementRows = orderRepository.getMeaserementValues(orderDetails.getMeasurementSet());
        List<MeasurementValuesDTO> measurements = measurementRows.stream()
                .map(r -> {
                    return new MeasurementValuesDTO(
                            (String) r[0],
                            (Double) r[1]
                    );
                })
                .distinct()
                .toList();

        Map<String, Object> result = new HashMap<>();

        result.put("order", orderDetails);
        result.put("measures", measurements);


        return new ResponseEntity<>( result, HttpStatus.OK );
    }

    public ResponseEntity<Map<String, Object>> setMeasures ( MeasurementsValuesDTO measurementsValuesDTO ) {

        measurementsValuesDTO.measurementValues()
                .forEach(measure -> {
                    MeasurementValue measurementValue = new MeasurementValue();
                    // get measurment set form db
                    MeasurementSet measurementSet = measurementSetRepository.findById( measure.measurementSetId() )
                            .orElseThrow(() -> new NotFoundException("this measurement set not found"));

                    // get measurement field from db
                    MeasurementField measurementField = measurementFieldRepository.findById( measure.measurementFieldId() )
                            .orElseThrow(() -> new NotFoundException("this measurement field not found"));

                    // insert into measurement values
                    measurementValue.setMeasurementSet( measurementSet );
                    measurementValue.setMeasurementField( measurementField );
                    measurementValue.setValue( measure.value() );

                    // save measurement values
                    measurementValueRepository.save( measurementValue );
                });

        Map<String, Object> res = new HashMap<>();
        res.put("status", "success");
        res.put("message", "all measures are well set");

        return new ResponseEntity<>(res, HttpStatus.OK);
    }


}


























