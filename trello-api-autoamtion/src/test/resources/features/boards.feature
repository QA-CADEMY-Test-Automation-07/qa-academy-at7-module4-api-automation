Feature: Boards

  Scenario: Gets boards
    When I send a request to GET boards endpoint "members/me/boards"
    Then the response status code should be 200
    And the response should be contain "boards" field
    And the response should have a field "name" with value "AT01"
