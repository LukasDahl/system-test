Feature: Create account and request tokens
  Scenario: Request 5 tokens
    Given a new customer
    When the customer requests 5 tokens
    Then he has 5 tokens
    And he has not gotten an error
    And the account is deleted

  Scenario: Request 6 tokens in the right order
    Given a new customer
    When the customer requests 1 tokens
    And the customer requests 5 tokens
    Then he has 6 tokens
    And he has not gotten an error
    And the account is deleted

  Scenario: Request 6 tokens from scratch in the wrong order
    Given a new customer
    When the customer requests 5 tokens
    And the customer requests 1 tokens
    Then he has 5 tokens
    And he has gotten an error
    And the account is deleted

  Scenario: Request 6 tokens from scratch in one go
    Given a new customer
    When the customer requests 6 tokens
    Then he has 0 tokens
    And he has gotten an error
    And the account is deleted