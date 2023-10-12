package code.vanilson.startup.service;

import code.vanilson.startup.exception.IllegalRequestException;
import code.vanilson.startup.exception.ObjectWithIdNotFound;
import code.vanilson.startup.model.Customer;
import code.vanilson.startup.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {

    /**
     * List of Customers
     */
    public List<Customer> customers;
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

    @BeforeEach
    void setUp() {
        customer = new Customer(1L, "test", "test@test.test", "test 1");
        customerRepositoryMock = mock(CustomerRepository.class);
        currentInstance = new CustomerServiceImpl(customerRepositoryMock);
        customers = List.of(
                new Customer(1L, "test", "test@test.test", "test 1"),
                new Customer(2L, "test1", "test1@test.test", "test 2"),
                new Customer(3L, "test2", "test2@test.test", "test 3")
        );

    }

    @Test
    @DisplayName("Get all Customers")
    void testGetAllCustomers() {
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

    @Test
    @DisplayName("Get Customer by id - Success")
    void testGetCustomerByIdSuccess() {
        when(customerRepositoryMock.findById(1L)).thenReturn(Optional.of(customer));
        assertSame(currentInstance.findCustomerById(1L).get(), customer, "Customers should be the same");
        assertTrue(currentInstance.findCustomerById(1L).isPresent(), "Customer was  found");
        assertFalse(currentInstance.findCustomerById(1L).isEmpty());
        assertNotEquals(234L, currentInstance.findCustomerById(1L)
                .get()
                .getCustomerId());
        verify(customerRepositoryMock, times(4)).findById(1L);
    }

    @Test
    @DisplayName(" customer by id - Not Found")
    void testShouldThrowExceptionsWhenTheGivenIdIsNotFound() {
        when(customerRepositoryMock.findById(1L)).thenReturn(Optional.empty());
        //assert
        assertThrows(ObjectWithIdNotFound.class, () -> currentInstance.findCustomerById(1L));
        verify(customerRepositoryMock).findById(1L);
    }

    @Test
    @DisplayName("create a new Customer - Success")
    void testCreateNewCustomerSuccess() {
        Customer mockCustomer = new Customer(123L, "test01", "testo1@teste.test", "test 4");
        when(customerRepositoryMock.save(any())).thenReturn(mockCustomer);
        var actualCurrent = currentInstance.saveCustomer(mockCustomer);
        //asserts
        assertEquals(mockCustomer, actualCurrent);
        assertEquals(123L, actualCurrent.getCustomerId().intValue());
        verify(customerRepositoryMock, atLeastOnce()).save(mockCustomer);
    }

    @Test
    @DisplayName("Create Customer - Not succeed")
    void testCreateCustomerThrowExceptionWhenIsCustomerIsNull() {
        when(customerRepositoryMock.save(null)).thenThrow(IllegalRequestException.class);
        assertThrows(IllegalRequestException.class, () -> currentInstance.saveCustomer(null));
    }

    @Test
    @DisplayName("Update Customer - Success")
    void testUpdateCustomerSuccess() {
        Customer existingCustomer = new Customer(1L, "John Doe", "john@example.com", "Address 1");
        Customer updatedCustomer = new Customer(1L, "Updated Name", "updated@example.com", "Updated Address");

        when(customerRepositoryMock.findById(1L)).thenReturn(Optional.of(existingCustomer));

        when(customerRepositoryMock.save(existingCustomer)).thenReturn(updatedCustomer);

        Customer result = currentInstance.updateCustomer(1L, updatedCustomer);

        // Verify that the expected customer was returned
        assertEquals(updatedCustomer, result);
    }

    @Test
    @DisplayName("Update Customer - Customer Not Found")
    void testUpdateCustomerCustomerNotFound() {
        Customer updatedCustomer = new Customer(1L, "Updated Name", "updated@example.com", "Updated Address");

        when(customerRepositoryMock.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ObjectWithIdNotFound.class, () -> currentInstance.updateCustomer(1L, updatedCustomer));
    }

    @Test
    @DisplayName("Update Customer - Null Input Customer")
    void testUpdateCustomerNullInput() {
        assertThrows(IllegalRequestException.class, () -> currentInstance.updateCustomer(1L, null));
    }

    @Test
    @DisplayName("Update Customer - Null Values in Input")
    void testUpdateCustomerNullValuesInInput() {
        Customer updatedCustomer = new Customer(1L, null, null, null);
        when(customerRepositoryMock.findById(1L)).thenReturn(Optional.of(new Customer()));
        assertThrows(IllegalRequestException.class, () -> currentInstance.updateCustomer(1L, updatedCustomer));
    }

    @Test
    @DisplayName("Delete Customer - Success")
    void testDeleteCustomerWithSuccess() {
        when(customerRepositoryMock.findById(1L))
                .thenReturn(Optional.of(customer));
        var current = currentInstance.deleteCustomer(1);
        assertTrue(current);
        verify(customerRepositoryMock, times(1))
                .delete(customer);
    }

    @Test
    @DisplayName(" Delete Customer - Not Found")
    void testDeleteCustomerNotFound() {
        when(customerRepositoryMock.findById(1L))
                .thenReturn(Optional.empty());
        assertThrows(ObjectWithIdNotFound.class,
                () -> currentInstance.deleteCustomer(1L));
        verify(customerRepositoryMock).findById(1L);
    }

}