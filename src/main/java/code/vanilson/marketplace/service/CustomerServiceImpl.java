package code.vanilson.marketplace.service;

import code.vanilson.marketplace.dto.CustomerDto;
import code.vanilson.marketplace.exception.ObjectWithIdNotFound;
import code.vanilson.marketplace.mapper.CustomerMapper;
import code.vanilson.marketplace.model.Customer;
import code.vanilson.marketplace.repository.CustomerRepository;
import code.vanilson.marketplace.exception.IllegalRequestException;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    public static final String NOT_FOUND = " not found";
    public static final String THE_CUSTOMER_OBJECT_MUST_NOT_BE_NULL = "The 'customer' object must not be null.";
    private static final Logger logger = LogManager.getLogger(CustomerServiceImpl.class);
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Retrieves a list of all customers from the database.
     *
     * @return A list of CustomerDto objects representing all customers.
     */
    @Override
    public List<CustomerDto> findAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        logger.info(" All customers");
        return CustomerMapper.toCustomerDtoList(customers);

    }

    /**
     * Retrieves a customer by their unique identifier.
     *
     * @param id The unique identifier of the customer.
     * @return An Optional containing the CustomerDto object if found, otherwise an empty Optional.
     * @throws ObjectWithIdNotFound If a customer with the specified ID is not found.
     */

    @Override
    public Optional<CustomerDto> findCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new ObjectWithIdNotFound(String.format("customer with id %d not found", id));
        }
        logger.info("Customer with id:{}", customer.get() + "found");
        return CustomerMapper.toCustomer(customer);
    }

    /**
     * Saves a new customer to the database.
     *
     * @param customer The Customer object to be saved.
     * @return The saved Customer object.
     * @throws IllegalRequestException If the 'customer' object is null.
     */
    @Override
    @Transactional
    public Customer saveCustomer(Customer customer) {
        if (Objects.isNull(customer)) {
            logger.error("The 'customer' object must not be:{}", customer);
            throw new IllegalRequestException(THE_CUSTOMER_OBJECT_MUST_NOT_BE_NULL);
        }
        logger.info("Customer saved with success:{}", customer);
        return customerRepository.save(customer);
    }

    /**
     * Updates an existing customer in the database.
     *
     * @param id       The unique identifier of the customer.
     * @param customer The updated Customer object.
     * @return The updated Customer object.
     * @throws ObjectWithIdNotFound    If a customer with the specified ID is not found.
     * @throws IllegalRequestException If the 'customer' object is null or any of its fields are null.
     */
    @Override
    @Transactional
    public Customer updateCustomer(long id, Customer customer) {
        if (Objects.isNull(customer)) {
            logger.error(THE_CUSTOMER_OBJECT_MUST_NOT_BE_NULL);
            throw new IllegalRequestException(THE_CUSTOMER_OBJECT_MUST_NOT_BE_NULL);
        }
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isEmpty()) {
            logger.error("Customer with id {} not found.", id);
            throw new ObjectWithIdNotFound("Customer with id " + id + " not found.");
        }

        var existingCustomer = optionalCustomer.get();

        if (customer.getName() == null || customer.getEmail() == null || customer.getAddress() == null) {
            logger.error("Updating to null values for 'name', 'email', or 'address' is not allowed.");
            throw new IllegalRequestException(
                    "Updating to null values for 'name', 'email', or 'address' is not allowed.");
        }

        existingCustomer.setName(customer.getName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setAddress(customer.getAddress());

        Customer updatedCustomer = customerRepository.save(existingCustomer);
        logger.info("Customer updated with success: {}", updatedCustomer);
        return updatedCustomer;
    }

    /**
     * Deletes a customer from the database by their unique identifier.
     *
     * @param id The unique identifier of the customer.
     * @return True if the customer is successfully deleted, otherwise false.
     * @throws ObjectWithIdNotFound If a customer with the specified ID is not found.
     */
    @Override
    @Transactional
    public boolean deleteCustomer(long id) {
        var customers = customerRepository.findById(id);
        if (customers.isEmpty()) {
            throw new ObjectWithIdNotFound("customer with id " + id + NOT_FOUND);
        }
        customers.ifPresent(customerRepository::delete);
        logger.info("Delete customer with id: {}", id);
        return true;
    }

    /**
     * Deletes a customer from the database by their unique identifier.
     * This method is a duplicate of the deleteCustomer method and serves as an example of how to handle duplicate method names.
     *
     * @param id The unique identifier of the customer.
     * @return True if the customer is successfully deleted, otherwise false.
     * @throws ObjectWithIdNotFound If a customer with the specified ID is not found.
     */
    @Transactional
    public boolean deleteCustomers(long id) {
        var customerOptional = customerRepository.findById(id);

        if (customerOptional.isEmpty()) {
            throw new ObjectWithIdNotFound("Customer with id " + id + NOT_FOUND);
        }

        var customer = customerOptional.get();
        var customerDto = CustomerMapper.toCustomerDto(customer);

        customerRepository.delete(customer);

        logger.info("Delete CustomerDto with id: {}", customerDto.getCustomerId());
        return true;
    }

}
