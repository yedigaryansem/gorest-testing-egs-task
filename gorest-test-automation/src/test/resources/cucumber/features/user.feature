Feature:
  Scenario:
    Given I have a new user with name "James" and email "james-test@gmail.com" and gender "male" and status "active"
    When I send create request with that user
    Then I should get response with the same user and id
    And Delete that user
    And Ensure api returns error with message "Resource not found" when I request that user by id again
