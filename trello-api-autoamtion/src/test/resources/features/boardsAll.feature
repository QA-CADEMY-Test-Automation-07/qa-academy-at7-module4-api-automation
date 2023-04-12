@regression @smoke @boardsAll
Feature: Boards All

  Scenario: Gets boards
    Given I have a valid key and token
    When I send a request to GET boards endpoint "members/me/boards"
    Then the response status code should be 200
    And the response should be contain "boards" field