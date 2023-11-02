package code.vanilson.startup.controller;

import code.vanilson.startup.exception.ObjectWithIdNotFound;
import code.vanilson.startup.model.Customer;
import code.vanilson.startup.model.Order;
import code.vanilson.startup.service.OrderServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderServiceImpl orderService;
    /**
     * LocalDateTime
     */
    static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.parse("2023-11-02T23:59:59.999");
    /**
     * Customer object
     */
    Customer customer;

    @BeforeEach
    void setUp() {

        MockitoAnnotations
                .openMocks(this);
        customer = new Customer(2L, "test", "test@test.test", "test 1");
    }

    @Test
    @DisplayName("Get All Orders")
    void testGetOrders() throws Exception {
        when(orderService.findAllOrders()).thenReturn(
                List.of(new Order(1L, LOCAL_DATE_TIME, customer, new HashSet<>())));

        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].orderId").value(1))
                .andExpect(jsonPath("$[0].customer.customerId").value(2))
                .andExpect(jsonPath("$[0].customer.name").value("test"))
                .andExpect(jsonPath("$[0].customer.email").value("test@test.test"))
                .andExpect(jsonPath("$[0].customer.address").value("test 1"))
                .andExpect(jsonPath("$[0].localDateTime").value(LOCAL_DATE_TIME.toString()));

        verify(orderService, times(1)).findAllOrders();
    }

    @Test
    @DisplayName("Get All Orders")
    void testGetOrdersSuccessEmptyList() throws Exception {
        when(orderService.findAllOrders()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Get Order by ID - Success")
    void testGetOrderByIdSuccess() throws Exception {
        Long orderId = 1L;
        Order order = new Order(1L, LOCAL_DATE_TIME, customer, new HashSet<>());

        when(orderService.findOrderById(orderId)).thenReturn(Optional.of(order));

        mockMvc.perform(get("/api/orders/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("orderId").value(1))
                .andExpect(jsonPath("customer.customerId").value(2))
                .andExpect(jsonPath("customer.name").value("test"))
                .andExpect(jsonPath("customer.email").value("test@test.test"))
                .andExpect(jsonPath("customer.address").value("test 1"))
                .andExpect(jsonPath("localDateTime").value(LOCAL_DATE_TIME.toString()));

        verify(orderService, times(1)).findOrderById(orderId);

    }

    @Test
    @DisplayName("Get Order by ID - Not Found")
    void testGetOrderByIdNotFound() throws Exception {
        Long orderId = 1L;
        when(orderService.findOrderById(orderId))
                .thenThrow(new ObjectWithIdNotFound(String.format("order with id %d not found", orderId)));
        mockMvc.perform(get("/api/orders/{id}", orderId))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Create a new Order")
    @Disabled("not tested yet")
    void testCreateOrder() throws Exception {
        Order order = new Order();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonContent = objectMapper.writeValueAsString(order);

        mockMvc.perform(post("/api/orders/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.orderId").exists());

    }

    @Test
    @DisplayName("Update Order")
    @Disabled("not tested yet")
    void testUpdateOrder() throws Exception {
        Long orderId = 123L;
        Order updatedOrder = new Order(orderId, new Customer(), new HashSet<>());

        when(orderService.updateOrder(orderId, updatedOrder)).thenReturn(updatedOrder);
        mockMvc.perform(put("/api/orders/update/{id}", orderId)
                        .contentType(MediaType.APPLICATION_JSON)  // Set the Content-Type header
                        .content(asJsonString(updatedOrder))) // Convert the updatedOrder to JSON
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value(orderId));
    }

    @Test
    @DisplayName("Delete Order Success")
    void testDeleteOrderSuccess() throws Exception {
        Long orderId = 1L;
        Order order = new Order(orderId, LOCAL_DATE_TIME, customer, new HashSet<>());
        when(orderService.findOrderById(orderId))
                .thenReturn(Optional.of(order));
        when(orderService.deleteOrderById(orderId)).thenReturn(true);

        mockMvc.perform(delete("/api/orders/delete/{id}", orderId))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Delete Order - Order Not Found")
    void testDeleteOrderNotFound() throws Exception {
        Long orderId = 1L;
        when(orderService.findOrderById(orderId)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/orders/delete/{id}", orderId))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Delete Order - Internal Server Error")
    void testDeleteOrderInternalServerError() throws Exception {
        Order order = new Order();
        Long orderId = 123L;
        order.setOrderId(orderId);
        when(orderService.findOrderById(orderId)).thenReturn(Optional.of(order));
        when(orderService.deleteOrderById(orderId)).thenReturn(false);

        mockMvc.perform(delete("/api/orders/delete/{id}", orderId))
                .andExpect(status().isInternalServerError()); // Expect 500 Internal Server Error
    }

    /**
     * Utility method to convert an object to JSON string
     */
    private String asJsonString(Object obj) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}



