@store
Feature: Access to Petstore orders

  Scenario: [GET] Retrieve pet inventories by status
    When I send GET request to store inventory
    Then I compare following json nodes with response body
      | sold             |
      | placed           |
      | string           |
      | alive            |
      | pending          |
      | available        |
      | Sold             |
      | NewParrotStatus  |
      | Unavailable      |
      | Not Available    |
      | pending          |
      | sold_kateryna    |
      | Available-Update |
      | Available        |
      | available 2      |
      | xyz              |
      | operating        |
    Then Status code should be 200
    And I see content type json

  Scenario: [POST] Create a pet order
    When I create a new order
      """
     {
      "id": 967786,
      "petId": 1,
      "quantity": 3,
      "shipDate": "2024-05-27T06:40:07.306Z",
      "status": "pending",
      "complete": true
     }
      """
    And I see following json nodes
      | id       |
      | petId    |
      | quantity |
      | shipDate |
      | status   |
      | complete |
    Then Status code should be 200
    And I see content type json

  Scenario: [GET] Find order by ID
    When I send GET request with orderId 967786
    And I see following json nodes
      | id       |
      | petId    |
      | quantity |
      | shipDate |
      | status   |
      | complete |
    Then Status code should be 200
    And I see content type json


  Scenario: [DELETE] Delete order by ID
    When I send DELETE request with orderId 967786
    And I see following json nodes
      | code    |
      | type    |
      | message |
    And Status code should be 200
    And I see content type json






