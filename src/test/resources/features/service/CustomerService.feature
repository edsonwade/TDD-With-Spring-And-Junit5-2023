Feature: Customer Service

  Scenario: Get all Customers
    Given the customer repository contains the following customers
      | id | name  | email          | address |
      | 1  | test  | test@test.test | test 1  |
      | 2  | test1 | test1@test.test| test 2  |
      | 3  | test2 | test2@test.test| test 3  |
    When I request all customers
    Then I should receive a list of customers containing
      | id | name  | email          | address |
      | 1  | test  | test@test.test | test 1  |
      | 2  | test1 | test1@test.test| test 2  |
      | 3  | test2 | test2@test.test| test 3  |

  Scenario: Get Customer by id - Success
    Given the customer repository contains a customer with id 1
    When I request the customer with id 1
    Then I should receive the customer with id 1 and details
      | id | name  | email          | address |
      | 1  | test  | test@test.test | test 1  |

  Scenario: Get Customer by id - Not Found
    Given the customer repository does not contain a customer with id 1
    When I request the customer with id 1
    Then I should receive an error message "customer with id 1 not found"

  Scenario: Create a new Customer - Success
    Given a new customer with details
      | id | name   | email           | address |
      | 123| test01 | testo1@teste.test| test 4  |
    When I save the customer
    Then the customer should be saved with details
      | id | name   | email           | address |
      | 123| test01 | testo1@teste.test| test 4  |

  Scenario: Create Customer - Not succeed
    Given a null customer
    When I try to save the null customer
    Then I should receive an error message "The 'customer' object must not be null."

  Scenario: Update Customer - Success
    Given the customer repository contains a customer with id 1
    And the customer has details
      | name | email           | address   |
      | John Doe | john@example.com | Address 1 |
    When I update the customer with id 1 to have details
      | name | email            | address        |
      | Updated Name | updated@example.com | Updated Address |
    Then the customer should be updated to have details
      | name | email            | address        |
      | Updated Name | updated@example.com | Updated Address |

  Scenario: Update Customer - Customer Not Found
    Given the customer repository does not contain a customer with id 1
    When I try to update the customer with id 1
    Then I should receive an error message "Customer with id 1 not found."

  Scenario: Update Customer - Null Input Customer
    Given the customer repository contains a customer with id 1
    When I try to update the customer with id 1 with a null customer
    Then I should receive an error message "The 'customer' object must not be null."

  Scenario: Delete Customer - Success
    Given the customer repository contains a customer with id 1
    When I delete the customer with id 1
    Then the customer with id 1 should be deleted successfully

  Scenario: Delete Customer - Not Found
    Given the customer repository does not contain a customer with id 1
    When I try to delete the customer with id 1
    Then I should receive an error message "customer with id 1 not found"
