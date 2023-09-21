package code.vanilson.startup.service;

import code.vanilson.startup.model.Customer;
import code.vanilson.startup.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {

    /**
     * Mock CustomerRepository
     */
    CustomerRepository customerRepositoryMock;
    /**
     * Current Object CustomerServiceImpl
     */
    CustomerServiceImpl currentInstance;
    /**
     * Object Customer
     */
    Customer customer;
    /**
     * List of Customers
     */
    public List<Customer> customers;

    @BeforeEach
    void setUp() {
        customer = new Customer(1L, "test", "test@test.test", "test 1");
        customerRepositoryMock = mock(CustomerRepository.class);
        currentInstance = new CustomerServiceImpl(customerRepositoryMock);
        customers = List.of(
                new Customer(1L, "test", "test@test.test", "test 1"),
                new Customer(2L, "test1", "test1@test.test", "test 2"),
                new Customer(1L, "tes2", "test2@test.test", "test 3")
        );

    }

    @Test
    @DisplayName("Find all Customers")
    void testShouldFindAllCustomers() {
        //when
        when(customerRepositoryMock.findAll()).thenReturn(customers);
        var currentActual = currentInstance.findAllCustomers();

        //Asserts
        assertEquals(customers, currentActual);
        assertFalse(currentActual.isEmpty());
        assertNotNull(currentActual);
        //verify
        verify(customerRepositoryMock, times(1)).findAll();


    }


}