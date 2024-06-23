#@ignore_customer_management
#Feature: Customer Management
#
#  Scenario: Get all customers
#    Given the following customers exist:
#      | customerId | name  | email          | address  |
#      | 1          | Alice | alice@test.com | Address1 |
#      | 2          | Bob   | bob@test.com   | Address2 |
#    When I send a GET request to "/api/customers"
#    Then the response status should be 200
#    And the response should contain the following customers:
#      | customerId | name  | email          | address  |
#      | 1          | Alice | alice@test.com | Address1 |
#      | 2          | Bob   | bob@test.com   | Address2 |
#
#  Scenario: Get customer by ID
#    Given the following customer exists:
#      | customerId | name  | email          | address  |
#      | 1          | Alice | alice@test.com | Address1 |
#    When I send a GET request to "/api/customers/1"
#    Then the response status should be 200
#    And the response should contain the customer:
#      | customerId | name  | email          | address  |
#      | 1          | Alice | alice@test.com | Address1 |
#
#  Scenario: Create a new customer
#    Given the following customer request:
#      | name  | email          | address  |
#      | Carol | carol@test.com | Address3 |
#    When I send a POST request to "/api/customers/create" with the customer request
#    Then the response status should be 201
#    And the response should contain the customer:
#      | customerId | name  | email          | address  |
#      | 3          | Carol | carol@test.com | Address3 |
#
#  Scenario: Update a customer
#    Given the following customer exists:
#      | customerId | name  | email          | address  |
#      | 1          | Alice | alice@test.com | Address1 |
#    And the following update request:
#      | name   | email           | address    |
#      | Alicia | alicia@test.com | NewAddress |
#    When I send a PUT request to "/api/customers/update/1" with the update request
#    Then the response status should be 200
#    And the response should contain the customer:
#      | customerId | name   | email           | address    |
#      | 1          | Alicia | alicia@test.com | NewAddress |
#
#  Scenario: Delete a customer
#    Given the following customer exists:
#      | customerId | name  | email          | address  |
#      | 1          | Alice | alice@test.com | Address1 |
#    When I send a DELETE request to "/api/customers/delete/1"
#    Then the response status should be 200
