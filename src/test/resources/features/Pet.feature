@pet
Feature: Everything about your Pets

  Scenario: [POST] Create a new pet
    Given I create a new pet
    """
{
 "id": 5,
 "category": {
   "id": 4,
   "name": "dogs"
 },
 "name": "doggie",
 "photoUrls": [
   "string"
 ],
 "tags": [
   {
     "id": 3,
     "name": "baby"
   }
 ],
 "status": "pending"
}
    """
    And I see following json nodes
      | id        |
      | category  |
      | name      |
      | photoUrls |
      | tags      |
      | status    |
    Then Status code should be 200
    And I see content type json

  Scenario: [PUT] Update an existing pet
    Given I update pet with new jsonBody
    And I see following json nodes
      | id        |
      | category  |
      | name      |
      | photoUrls |
      | tags      |
      | status    |
    Then Status code should be 200
    And I see content type json

  Scenario: [GET] Find pet by ID
    When I send GET request with ID
    And I see following json nodes
      | id        |
      | category  |
      | name      |
      | photoUrls |
      | tags      |
      | status    |
    Then Status code should be 200

  Scenario: [GET] Find pets by status
    When I send GET request with status
      | available |
      | pending   |
      | sold      |
    And I see following json keys
      | id        |
      | category  |
      | name      |
      | photoUrls |
      | tags      |
      | status    |
    Then Status code should be 200

  Scenario Outline: [POST] Update pet with formData
    Given I create a new pet with <Id>
    When I update pet <Id>,"<Name>","<Status>"
    And I see following json nodes
      | code    |
      | type    |
      | message |
    Then Status code should be 200
    And I see content type json
    Examples:
      | Id    | Name   | Status    |
      | 13579 | Mavis  | available |
      | 24680 | Sonic  | sold      |
      | 34567 | Browni | pending   |


  Scenario: [DELETE] Delete pet
    When I send DELETE request with 13579
    And I see following json nodes
      | code    |
      | type    |
      | message |
    And Status code should be 200
    And I see content type json



