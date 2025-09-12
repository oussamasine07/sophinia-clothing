package com.sophinia.backend.service;

import com.sophinia.backend.dto.mappingdto.*;
import com.sophinia.backend.dto.validation.AvailabilityValidationDTO;
import com.sophinia.backend.dto.validation.ClientValidationDTO;
import com.sophinia.backend.dto.validation.OrderValidationDTO;
import com.sophinia.backend.model.*;
import com.sophinia.backend.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;
    @Mock
    OrderStatusRepository orderStatusRepository;
    @Mock
    AvailabilityRepository availabilityRepository;
    @Mock
    ClientRepository clientRepository;
    @Mock
    MeasurementSetRepository measurementSetRepository;

    @InjectMocks
    OrderService orderService;
    @Mock
    ProductService productService;
    @Mock
    DecorationService decorationService;
    @Mock
    DesignService designService;
    @Mock
    FabricService fabricService;
    @Mock
    ClothingModelService clothingModelService;

    @Test
    void getOrders () {
        Order o1 = new Order();
        o1.setId(1L);
        o1.setOrderDate(LocalDate.of(2025, 9, 11));
        o1.setDeliveryDate(LocalDate.of(2025, 9, 25));

        Order o2 = new Order();
        o2.setId(1L);
        o2.setOrderDate(LocalDate.of(2025, 9, 9));
        o2.setDeliveryDate(LocalDate.of(2025, 9, 10));

        List<Order> orders = List.of(o1, o2);

        when(orderRepository.findAll()).thenReturn(orders);

        ResponseEntity<List<Order>> result = orderService.getOrders();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody()).hasSize(2);
        assertThat(result.getBody()).extracting(Order::getDeliveryDate)
                .containsExactly(
                        LocalDate.of(2025, 9, 25),
                        LocalDate.of(2025, 9, 10)
                );

    }

    @Test
    void getOrdersWithClients () {

        OrderWithClientDTO orderWithClient1 = new OrderWithClientDTO(
                1L,
                "stand by",
                "test 1",
                "test",
                "product 1"
        );

        OrderWithClientDTO orderWithClient2 = new OrderWithClientDTO(
                2L,
                "stand by",
                "test 2",
                "test",
                "product 1"
        );

        List<OrderWithClientDTO> orders = List.of( orderWithClient1, orderWithClient2 );

        when(orderRepository.getOrdersWithClient()).thenReturn( orders );

        ResponseEntity<List<OrderWithClientDTO>> result = orderService.getOrdersWithClients();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody()).hasSize(2);
        assertThat(result.getBody()).extracting(OrderWithClientDTO::firstName)
                .containsExactly("test 1", "test 2");

    }

    @Test
    void makeOrder () {

        Order order = new Order();

        order.setOrderDate(LocalDate.of(2025, 9, 11));
        order.setDeliveryDate(LocalDate.of(2025, 9, 16));

        // mock order status
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setId(1L);
        orderStatus.setStatus("stand by");
        when(orderStatusRepository.findById(1L)).thenReturn(Optional.of( orderStatus ));

        // mock product
        Product product = new Product();
        product.setId(1L);
        product.setName("product 1");
        product.setDescription("product 1 description");
        product.setImage("product-image-url");
        when( productService.getProductById(1L) ).thenReturn(ResponseEntity.ok(product));
        ResponseEntity<Product> foundProduct = productService.getProductById(1L);

        // mock decation
        Decoration decoration = new Decoration();
        decoration.setId(1L);
        decoration.setName("decoration 1");
        when(decorationService.getDecorationById(1L)).thenReturn( ResponseEntity.ok( decoration ) );
        ResponseEntity<Decoration> foundDecoration = decorationService.getDecorationById(1L);

        // mock fabric
        Fabric fabric = new Fabric();
        fabric.setId(1L);
        fabric.setName("fabric 1");
        when(fabricService.getFabricById(1L)).thenReturn( ResponseEntity.ok( fabric ));
        ResponseEntity<Fabric> foundFabric = fabricService.getFabricById(1L);

        // mock design
        Design design = new Design();
        design.setId(1L);
        design.setName("design 1");
        when(designService.getDesignById(1L)).thenReturn(ResponseEntity.ok(design));
        ResponseEntity<Design> foundDesign = designService.getDesignById( 1L );

        // mock clothing model
        ClothingModel clothingModel = new ClothingModel();
        clothingModel.setId(1L);
        clothingModel.setName( "clothing model 1" );
        when(clothingModelService.getClothingModel(1L)).thenReturn(ResponseEntity.ok(clothingModel));
        ResponseEntity<ClothingModel> foundClothingModel = clothingModelService.getClothingModel( 1L );

        // mock availability
        Availability availability = new Availability();
        availability.setStartDate(LocalDate.of(2025, 9, 11));
        availability.setEndDate(LocalDate.of(2025, 9, 15));
        when(availabilityRepository.save(any(Availability.class))).thenReturn(availability);

        ClientValidationDTO clientValidationDTO = new ClientValidationDTO(
                "client first name",
                "client last name",
                "client@mail.com",
                "0677889944",
                "this is address"
        );

        AvailabilityValidationDTO availabilityValidationDTO = new AvailabilityValidationDTO(
                LocalDate.now(),
                LocalDate.of(2025, 9, 16),
                AvailabilityType.AT_HOME
        );

        OrderValidationDTO orderValidationDTO = new OrderValidationDTO(
                1L,
                1L,
                1L,
                1L,
                1L,
                clientValidationDTO,
                availabilityValidationDTO
        );

        Client client = new Client();
        client.setFirstName( clientValidationDTO.firstName() );
        client.setLastName( clientValidationDTO.lastName() );
        client.setEmail( clientValidationDTO.email() );
        client.setPhone( clientValidationDTO.phone() );
        client.setAddress( clientValidationDTO.address() );
        when(clientRepository.findClientByEmail(client.getEmail())).thenReturn( Optional.of( client ) );

        order.setClient( client );
        order.setClothingModel( foundClothingModel.getBody() );
        order.setDecoration( foundDecoration.getBody() );
        order.setDesign( foundDesign.getBody() );
        order.setFabric( foundFabric.getBody() );
        order.setProduct( foundProduct.getBody() );

        when(orderRepository.save(any(Order.class))).thenReturn(order);


        ResponseEntity<Order> result = orderService.makeOrder( orderValidationDTO );

        MeasurementSet measurementSet = new MeasurementSet();
        measurementSet.setProduct( product );
        measurementSet.setOrder( result.getBody() );
        lenient().when(measurementSetRepository.save(any(MeasurementSet.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertThat(result.getBody()).isNotNull();
        assertEquals(LocalDate.of(2025, 9, 11), result.getBody().getOrderDate());
        assertEquals(LocalDate.of(2025, 9, 16), result.getBody().getDeliveryDate());

    }

    @Test
    void getOrderDetails () {
        Long orderId = 1L;

        List<Object[]> orderDetailsRows = List.of(
            new Object[]{
                1L,
                "ProductName", "Desc", "Img",
                10L, "Chest",
                "ClothName", "ClothDesc",
                "DecorName", "DecorDesc",
                "DesignName", "DesignDesc",
                "John", "Doe", "john@email.com", "123456", "City", "Street", "Country",
                100L
            },
            new Object[]{
                1L,
                "ProductName", "Desc", "Img",
                11L, "Waist",
                "ClothName", "ClothDesc",
                "DecorName", "DecorDesc",
                "DesignName", "DesignDesc",
                "John", "Doe", "john@email.com", "123456", "City", "Street", "Country",
                100L
            }
        );

        List<Object[]> measurementRows = List.of(
                new Object[]{"Chest", 90.0},
                new Object[]{"Waist", 75.0}
        );

        when(orderRepository.getOrderDetails(orderId)).thenReturn(orderDetailsRows);
        when(orderRepository.getMeaserementValues(100L)).thenReturn(measurementRows);

        // Act
        ResponseEntity<Map<String, Object>> response = orderService.getOrderDetails(orderId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Map<String, Object> body = response.getBody();
        assertNotNull(body);

        OrderDetailsDTO order = (OrderDetailsDTO) body.get("order");
        assertEquals(1L, order.getOrderId() );
        assertEquals("ProductName", order.getProduct().getName());
        assertEquals(2, order.getProduct().getFields().size());
        assertEquals("ClothName", order.getClothingModel().getName());
        assertEquals("DecorName", order.getDecoration().getName());
        assertEquals("DesignName", order.getDesign().getName());
        assertEquals("John", order.getClient().getFirstName());

        List<MeasurementValuesDTO> measures = (List<MeasurementValuesDTO>) body.get("measures");
        assertEquals(2, measures.size());
        assertTrue(measures.stream().anyMatch(m -> m.getMeasurementField().equals("Chest") && m.getMeasurementValue() == 90.0));
        assertTrue(measures.stream().anyMatch(m -> m.getMeasurementField().equals("Waist") && m.getMeasurementValue() == 75.0));

        verify(orderRepository).getOrderDetails(orderId);
        verify(orderRepository).getMeaserementValues(100L);
    }

}





























