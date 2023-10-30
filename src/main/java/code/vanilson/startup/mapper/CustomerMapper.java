package code.vanilson.startup.mapper;

import code.vanilson.startup.dto.CustomerDto;
import code.vanilson.startup.model.Customer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomerMapper {

    private CustomerMapper() {
        //default constructor
    }

    public static CustomerDto toCustomerDto(Customer customer) {
        if (customer == null) {
            return null;
        }

        return new CustomerDto(
                customer.getCustomerId(),
                customer.getName(),
                customer.getEmail(),
                customer.getAddress());
    }

    public static List<CustomerDto> toCustomerDtoList(List<Customer> customerList) {
        return customerList.stream()
                .map(CustomerMapper::toCustomerDto)
                .collect(Collectors.toList());
    }

    public static Customer toCustomer(CustomerDto customerDto) {
        if (customerDto == null) {
            return null;
        }

        Customer customer = new Customer();
        customer.setCustomerId(customerDto.getCustomerId());
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setAddress(customer.getAddress());

        return customer;
    }

    @SuppressWarnings("unused")
    public static Optional<CustomerDto> toCustomer(Optional<Customer> customerOptional) {
        return customerOptional.map(customer ->
                new CustomerDto(
                        customer.getCustomerId(),
                        customer.getName(),
                        customer.getEmail(),
                        customer.getAddress()));
    }
}


