#@ignore_customer_management
#Feature: Customer Controller
#  Scenario: Get all customers
#    Given the customer service is available
#    When I request all customers
#    Then I should receive a list of customers
#
#  Scenario: Get customer by ID
#    Given the customer service is available
#    When I request a customer with ID 1
#    Then I should receive the customer with ID 1
#
#  Scenario: Create a new customer
#    Given the customer service is available
#    When I create a new customer with name "John Doe", email "john.doe@example.com", and address "123 Main St"
#    Then the new customer should be created
#
#  Scenario: Update a customer
#    Given the customer service is available
#    When I update the customer with ID 1 to have name "Jane Doe", email "jane.doe@example.com", and address "456 Elm St"
#    Then the customer with ID 1 should be updated
#
#  Scenario: Delete a customer
#    Given the customer service is available
#    When I delete the customer with ID 1
#    Then the customer with ID 1 should be deleted
#
#  Scenario: Get customer by non-existing ID
#    Given the customer service is available
#    When I request a customer with a non-existing ID 999
#    Then I should receive a not found response
#
#  Scenario: Delete customer by non-existing ID
#    Given the customer service is available
#    When I delete a customer with a non-existing ID 999
#    Then I should receive a not found response