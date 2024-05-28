@user
Feature: Operations about user

  Scenario: [POST] Create user object
    When I create a new user
   """
   {
      "id": 1,
      "username": "user1",
      "firstName": "Jack",
      "lastName": "Wolfskin",
      "email": "jackwolfskin@petmail.com",
      "password": "Jack123",
      "phone": "123123123",
      "userStatus": 0
    }
   """
    And I send GET request with username
    Then I retrieve newly created user data
    And Status code should be 200
    And I see content type json

  Scenario: [GET] Get user by username
    When I send request with "user1" username
    And I compare following json nodes with response body
      | id         |
      | username   |
      | firstName  |
      | lastName   |
      | email      |
      | password   |
      | phone      |
      | userStatus |
    Then Status code should be 200
    And I see content type json


  Scenario: [GET] Login user into system
    Given I login as a registered user with username "user1" and password "Jack123"
    When I get logged in user "user1" info
    Then Status code should be 200
    And I see content type json

  Scenario: [PUT] Update user
    When I update user with new jsonBody
      """
   {
     "id": 23,
     "username": "user2",
     "firstName": "Tommy",
     "lastName": "Hilfiger",
     "email": "tommyhilfiger@petmail.com",
     "password": "Tommy123",
     "phone": "123123123",
     "userStatus": 0
   }
  """
    When I send GET request with username
    Then Status code should be 200
    And I see content type json

  Scenario: [GET] Logout user
    When I logout user
    Then I see following json nodes
      | code    |
      | type    |
      | message |
    And Status code should be 200

  Scenario: [DELETE] Delete user
    When I send DELETE request with "user2"
    Then I see following json nodes
      | code    |
      | type    |
      | message |
    And Status code should be 200