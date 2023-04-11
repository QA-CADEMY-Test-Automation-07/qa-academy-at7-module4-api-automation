@regression @acceptance @boards
Feature: Boards

  Background:
    Given I have a valid key and token

  Scenario: Gets boards
    When I send a request to GET boards endpoint "members/me/boards"
    Then the response status code should be 200
    And the response should be contain "boards" field

  @createBoard @deleteBoard
  Scenario: Gets board
    When I send a request to GET a board endpoint "boards/{id}"
    Then the response status code should be 200
    And the response should have a field "name" with value "{name}"

  @deleteBoard
  Scenario: Creates board
    When I send a request to POST boards endpoint "boards" with name "example-test2023-post"
    Then the response status code should be 200
    And the response should be contain "comments" field
    And the response should have a field "name" with value "{name}"

  @createBoard @deleteBoard
  Scenario: Updates board
    When I send a request to PUT boards endpoint "boards/{id}" with name "example-test2023-put"
    Then the response status code should be 200
    And the response should be contain "prefs" field
    And the response should have a field "name" with value "{name}"

  @createBoard
  Scenario: Deletes board
    When I send a request to DELETE boards endpoint "boards/{id}"
    Then the response status code should be 200
    Then the response should be contain "{\"_value\":null}" field
