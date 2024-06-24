Feature: Product Service

  Scenario: Get all Products
    Given the product repository contains the following products
      | id | name     | quantity | version |
      | 1  | TV       | 10       | 1       |
      | 2  | Computer | 20       | 2       |
      | 3  | Mobile   | 30       | 3       |
    When I request all Products
    Then I should receive a list of products containing
      | id | name     | quantity | version |
      | 1  | TV       | 10       | 1       |
      | 2  | Computer | 20       | 2       |
      | 3  | Mobile   | 30       | 3       |


  Scenario: Get Product by id - Success
    Given the product repository contains a product with id 1
    When I request the product with id 1
    Then I should receive the product with id 1 and details
      | id | name | quantity | version |
      | 1  | TV   | 10       | 1       |


  Scenario: Get Product by id - Not Found
    Given the product repository does not contain a product with id 1
    When I request the product with id 1
    Then I should receive an error message as "product with id 1 not found"


  Scenario: Create a new Product - Success
    Given a new product with details
      | id | name  | quantity | version |
      | 4  | Chair | 50       | 4       |
    When I save the product
    Then the product should be saved with details
      | id | name  | quantity | version |
      | 4  | Chair | 50       | 4       |


  Scenario: Create Product - Not succeed
    Given a null product
    When I try to save the null product
    Then I should receive an error message like "The 'product' object must not be null."


  Scenario: Update Product - Success
    Given the product repository contains a product with id 1
    And the product has details
      | name | quantity | version |
      | TV   | 10       | 1       |
    When I update the product with id 1 to have details
      | name    | quantity | version |
      | Monitor | 15       | 2024    |
    Then the product should be updated to have details
      | name    | quantity | version |
      | Monitor | 15       | 2024    |


  Scenario: Delete Product - Success
    Given the product repository contains a product with id 1
    When I delete the product with id 1
    Then the product with id 1 should be deleted successfully


  Scenario: Delete Product - Not Found
    Given the product repository does not contain a product with id 1
    When I try to delete the product with id 1
    Then I should receive an error message when try to delete "Product with id 1 not found"
