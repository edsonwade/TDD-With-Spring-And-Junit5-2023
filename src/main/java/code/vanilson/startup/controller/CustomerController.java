package code.vanilson.startup.controller;

import code.vanilson.startup.dto.CustomerDto;
import code.vanilson.startup.model.Customer;
import code.vanilson.startup.service.CustomerServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private static final Logger logger = LogManager.getLogger(CustomerController.class);
    private final CustomerServiceImpl customerService;

    public static final String CUSTOMER = "/customers/";

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    /**
     * Returns all customers in the database.
     *
     * @return All customers in the database.
     */
    @GetMapping
    public ResponseEntity<Iterable<CustomerDto>> getCustomers() {
        return ResponseEntity.ok().body(customerService.findAllCustomers());
    }

    /**
     * Returns the customer with the specified ID.
     *
     * @param id The ID of the customer to retrieve.
     * @return The customer with the specified ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Customer>> getCustomer(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(customerService.findCustomerById(id));
    }

    /**
     * Creates a new customer.
     *
     * @param customer The customer to create.
     * @return The created customer.
     */
    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        logger.info("Creating new customer with name: {}, email: {}, address: {}",
                customer.getName(),
                customer.getEmail(),
                customer.getAddress());

        // Create a new customer
        Customer newCustomer = customerService.saveCustomer(customer);

        try {
            // Build a created response
            return ResponseEntity
                    .created(new URI(CUSTOMER + newCustomer.getCustomerId()))
                    .body(newCustomer);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Updates the fields in the specified customer with the specified ID.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer,
                                                   @PathVariable Long id) {
        Customer customers = customerService.updateCustomer(id, customer);
        return ResponseEntity.ok().body(customers);
    }

    /**
     * Deletes the customer with the specified ID.
     *
     * @param id The ID of the customer to delete.
     * @return A ResponseEntity with one of the following status codes:
     * 200 OK if to delete was successful
     * 404 Not Found if a customer with the specified ID is not found
     * 500 Internal Service Error if an error occurs during deletion
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {

        logger.info("Deleting customer with ID {}", id);

        // Get the existing customer
        Optional<Customer> existingCustomer = customerService.findCustomerById(id);

        return existingCustomer.map(p -> {
            if (customerService.deleteCustomer(p.getCustomerId())) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }).orElse(ResponseEntity.notFound().build());
    }

}
