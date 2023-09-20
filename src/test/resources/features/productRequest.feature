Feature: Product API

  Scenario: Get products successfully
    Given the product service is available
    When a GET request is made to "/api/products"
    Then the response status code should be 200
    And the response content type should be "application/json"
    Then the response should contain the following products:
      | id | name                           | quantity | version |
      | 1  | Plate - Foam, Bread And Butter | 284      | 39      |
      | 2  | Juice - Tomato, 10 Oz          | 240      | 100     |
      | 3  | Grapefruit - Pink              | 160      | 77      |
      | 4  | Chicken - Wings, Tip Off       | 245      | 29      |
      | 5  | Fenngreek Seed                 | 368      | 70      |
      | 6  | Carrots - Mini Red Organic     | 437      | 33      |
      | 7  | Rice - Sushi                   | 431      | 63      |
      | 8  | Beef - Top Butt Aaa            | 283      | 71      |
      | 9  | Foil - 4oz Custard Cup         | 683      | 76      |
      | 10 | Brownies - Two Bite, Chocolate | 592      | 9       |

##  @ignore
##  Scenario: Get a product by ID successfully
##    Given the product service is available
##    When a GET request is made to "/api/products/1"
##    Then the response status code should be 200
##    And the response content type should be "application/json"
##    And the response should contain the following product:
##      | id | name                  | quantity | version |
##      | 1  | Plate - Foam, Bread   | 284      | 39      |
##  @ignore
##  Scenario: Get a product by non-existing ID
##    Given the product service is available
##    When a GET request is made to a non-existing product ID "/api/products/999"
##    Then the response status code should be 404
